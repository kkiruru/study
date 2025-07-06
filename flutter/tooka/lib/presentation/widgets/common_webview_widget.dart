import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:webview_flutter/webview_flutter.dart';

import 'dart:io';
import 'package:flutter/cupertino.dart';
import 'package:webview_flutter_wkwebview/webview_flutter_wkwebview.dart';
import 'dart:async';

class CommonWebViewWidget extends StatefulWidget {
  final String initialUrl;
  final String? title;
  final Color backgroundColor = Colors.white;
  final Function(WebViewController)? onWebViewCreated;
  final Function()? onWebViewCanGoBackChanged;
  final bool enablePopGesture;
  final VoidCallback? onPop;

  const CommonWebViewWidget({
    super.key,
    required this.initialUrl,
    this.title,
    this.onWebViewCreated,
    this.onWebViewCanGoBackChanged,
    this.enablePopGesture = false,
    this.onPop,
  });

  @override
  State<CommonWebViewWidget> createState() => _CommonWebViewWidgetState();
}

class _CommonWebViewWidgetState extends State<CommonWebViewWidget> {
  WebViewController? _controller;
  bool _isRefreshing = false;
  bool _isLoading = true; // 로딩 상태 관리
  bool _hasError = false; // 에러 상태 관리
  bool _canGoBack = false;
  String? _errorMessage; // 에러 메시지
  Timer? _timeoutTimer; // 타임아웃 타이머

  @override
  void initState() {
    super.initState();
    _initializeWebView();
  }

  @override
  void dispose() {
    _timeoutTimer?.cancel();
    super.dispose();
  }

  void _initializeWebView() {
    late final PlatformWebViewControllerCreationParams params;
    if (WebViewPlatform.instance is WebKitWebViewPlatform) {
      params = WebKitWebViewControllerCreationParams(
        allowsInlineMediaPlayback: true,
        mediaTypesRequiringUserAction: const <PlaybackMediaTypes>{},
      );
    } else {
      params = const PlatformWebViewControllerCreationParams();
    }

    final WebViewController controller =
        WebViewController.fromPlatformCreationParams(params);

    controller
      ..setJavaScriptMode(JavaScriptMode.unrestricted)
      ..setBackgroundColor(const Color(0x00000000))
      ..setNavigationDelegate(
        NavigationDelegate(
          onProgress: (int progress) {
            print('onProgress : $progress');
            // 진행률이 100%에 가까워지면 로딩 완료로 간주
            if (progress >= 90) {
              _setLoadingState(false);
            }
          },
          onPageStarted: (String url) {
            print('onPageStarted : $url');
            _setLoadingState(true);
            _setErrorState(false);
            _startTimeoutTimer();
          },
          onPageFinished: (String url) async {
            print('onPageFinished : $url');
            _timeoutTimer?.cancel();
            
            // JavaScript 실행 완료 확인
            await _checkJavaScriptReady();
            
            if (Platform.isIOS) {
              setState(() {
                _isRefreshing = false;
              });
            }
            _updateCanGoBack();
            widget.onWebViewCanGoBackChanged?.call();
          },
          onWebResourceError: (WebResourceError error) {
            print('WebResourceError: ${error.description}');
            _handleWebError(error);
          },
          onNavigationRequest: (NavigationRequest request) {
            print('onNavigationRequest : ${request.url}');
            return NavigationDecision.navigate;
          },
        ),
      )
      ..addJavaScriptChannel(
        'FlutterBridge',
        onMessageReceived: (JavaScriptMessage message) {
          print('onMessageReceived: ${message.message}');
          _handleJavaScriptMessage(message.message);
        },
      )
      ..loadRequest(Uri.parse(widget.initialUrl));

    if (controller.platform is WebKitWebViewController) {
      (controller.platform as WebKitWebViewController)
          .setAllowsBackForwardNavigationGestures(true);
    }

    _controller = controller;
    widget.onWebViewCreated?.call(controller);
  }

  // 로딩 상태 설정
  void _setLoadingState(bool loading) {
    if (mounted) {
      setState(() {
        _isLoading = loading;
      });
    }
  }

  // 에러 상태 설정
  void _setErrorState(bool hasError, {String? message}) {
    if (mounted) {
      setState(() {
        _hasError = hasError;
        _errorMessage = message;
        _isLoading = false;
      });
    }
  }

  // 타임아웃 타이머 시작
  void _startTimeoutTimer() {
    _timeoutTimer?.cancel();
    _timeoutTimer = Timer(const Duration(seconds: 30), () {
      if (_isLoading) {
        _setErrorState(true, message: '페이지 로딩 시간이 초과되었습니다.');
      }
    });
  }

  // JavaScript 실행 완료 확인
  Future<void> _checkJavaScriptReady() async {
    try {
      // DOM이 완전히 로드되었는지 확인
      final result = await _controller?.runJavaScriptReturningResult('''
        (function() {
          return {
            readyState: document.readyState,
            bodyContent: document.body ? document.body.innerHTML.length : 0,
            title: document.title
          };
        })();
      ''');
      
      print('JavaScript ready check: $result');
      
      // 추가로 1초 대기하여 JavaScript 실행 완료 보장
      await Future.delayed(const Duration(milliseconds: 1000));
      
      _setLoadingState(false);
    } catch (e) {
      print('JavaScript ready check error: $e');
      _setLoadingState(false);
    }
  }

  // 웹 에러 처리
  void _handleWebError(WebResourceError error) {
    _timeoutTimer?.cancel();
    
    String errorMessage = '페이지를 불러올 수 없습니다.';
    
    // 에러 코드 기반으로 메시지 결정
    switch (error.errorCode) {
      case 400:
        errorMessage = '잘못된 요청입니다.';
        break;
      case 401:
        errorMessage = '인증이 필요합니다.';
        break;
      case 403:
        errorMessage = '접근이 거부되었습니다.';
        break;
      case 404:
        errorMessage = '페이지를 찾을 수 없습니다.';
        break;
      case 408:
        errorMessage = '요청 시간이 초과되었습니다.';
        break;
      case 500:
        errorMessage = '서버 내부 오류가 발생했습니다.';
        break;
      case 502:
        errorMessage = '서버가 일시적으로 사용할 수 없습니다.';
        break;
      case 503:
        errorMessage = '서비스가 일시적으로 사용할 수 없습니다.';
        break;
      case 504:
        errorMessage = '게이트웨이 시간 초과가 발생했습니다.';
        break;
      default:
        // WebResourceErrorType으로 기본 처리
        switch (error.errorType) {
          case WebResourceErrorType.unknown:
            errorMessage = '알 수 없는 오류가 발생했습니다.';
            break;
          case WebResourceErrorType.badUrl:
            errorMessage = '잘못된 URL입니다.';
            break;
          case WebResourceErrorType.timeout:
            errorMessage = '페이지 로딩 시간이 초과되었습니다.';
            break;
          case WebResourceErrorType.unsupportedScheme:
            errorMessage = '지원하지 않는 프로토콜입니다.';
            break;
          case WebResourceErrorType.authentication:
            errorMessage = '인증 오류가 발생했습니다.';
            break;
          default:
            errorMessage = '페이지를 불러올 수 없습니다. (오류 코드: ${error.errorCode})';
            break;
        }
        break;
    }
    
    _setErrorState(true, message: errorMessage);
  }

  // JavaScript 메시지 처리
  void _handleJavaScriptMessage(String message) {
    switch (message) {
      case 'goBack':
        print('___ goBack');
        _handleBackPress();
        break;
      default:
        if (message.startsWith('push:')) {
          _handlePushNavigation(message);
        }
        break;
    }
  }

  void _handlePushNavigation(String msg) {
    final uri = Uri.parse(msg.substring('push:'.length));
    final String encodedUrl = Uri.encodeComponent(uri.toString());
    context.push('/web-view/$encodedUrl');
  }

  // WebView 뒤로가기 가능 여부 업데이트
  Future<void> _updateCanGoBack() async {
    if (_controller != null) {
      final canGoBack = await _controller!.canGoBack();
      if (mounted) {
        setState(() {
          _canGoBack = canGoBack;
        });
      }
    }
  }

  // 뒤로가기 처리
  Future<bool> _handleBackPress() async {
    if (_controller != null) {
      final canGoBack = await _controller!.canGoBack();

      print('_handleBackPress ___ canGoBack ${canGoBack}');

      if (canGoBack) {
        // WebView 히스토리가 있으면 뒤로가기
        await _controller!.goBack();
        _updateCanGoBack(); // 상태 업데이트
        return false; // PopScope가 처리하지 않도록
      } else {
        // WebView 히스토리가 없으면 위젯 팝
        if (widget.onPop != null) {
          widget.onPop!();
        }
        return true; // PopScope가 처리하도록
      }
    }
    return true;
  }

  Future<void> _handleRefresh() async {
    if (Platform.isAndroid) {
      await _controller?.reload();
    } else if (Platform.isIOS) {
      // iOS에서는 JavaScript를 통해 새로고침을 트리거하거나,
      // 혹은 단순히 UI 상태만 변경하여 사용자에게 피드백을 줄 수 있습니다.
      // webview_flutter는 PullToRefreshController와 같은 직접적인 새로고침 UI를 제공하지 않습니다.
      setState(() {
        _isRefreshing = true;
      });
      // 짧은 지연 후 새로고침 완료 (실제로는 reload가 완료되면 onPageFinished가 호출됨)
      // await Future.delayed(Duration(seconds: 1));
      await _controller?.reload();
      // onPageFinished에서 _isRefreshing = false; 처리
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: widget.title != null ? AppBar(title: Text(widget.title!)) : null,
      backgroundColor: widget.backgroundColor,
      body: Stack(
        children: [
          // WebView
          if (_controller != null)
            WebViewWidget(controller: _controller!),
          
          // 로딩 인디케이터
          if (_isLoading)
            const Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  CircularProgressIndicator(),
                  SizedBox(height: 16),
                  Text('페이지를 불러오는 중...'),
                ],
              ),
            ),
          
          // 에러 화면
          if (_hasError)
            Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  const Icon(Icons.error_outline, size: 64, color: Colors.red),
                  const SizedBox(height: 16),
                  Text(
                    _errorMessage ?? '오류가 발생했습니다.',
                    textAlign: TextAlign.center,
                    style: const TextStyle(fontSize: 16),
                  ),
                  const SizedBox(height: 16),
                  ElevatedButton(
                    onPressed: () {
                      _setErrorState(false);
                      _controller?.reload();
                    },
                    child: const Text('다시 시도'),
                  ),
                ],
              ),
            ),
          
          // iOS 새로고침 인디케이터
          if (Platform.isIOS && _isRefreshing)
            const Positioned.fill(
              child: Center(child: CupertinoActivityIndicator(radius: 15.0)),
            ),
        ],
      ),
    );
  }
}
