import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:webview_flutter/webview_flutter.dart';

// iOS 플랫폼별 기능을 사용하기 위해 필요
import 'package:webview_flutter_wkwebview/webview_flutter_wkwebview.dart';
import 'dart:io';
import 'package:flutter/cupertino.dart';

class CommonWebViewWidget extends StatefulWidget {
  final String initialUrl;
  final String? title;
  final Function(WebViewController)? onWebViewCreated; // 컨트롤러 타입 변경
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
  WebViewController? _controller; // 타입 변경
  bool _isRefreshing = false; // iOS 새로고침 UI용
  bool isLoading = false;
  bool _canGoBack = false; // WebView 뒤로가기 가능 여부 추적

  @override
  void initState() {
    super.initState();

    late final PlatformWebViewControllerCreationParams params;
    if (WebViewPlatform.instance is WebKitWebViewPlatform) {
      // webview_flutter_wkwebview 사용 시
      params = WebKitWebViewControllerCreationParams(
        allowsInlineMediaPlayback: true,
        mediaTypesRequiringUserAction: const <PlaybackMediaTypes>{},
      );
    } else {
      // 기본 또는 Android 플랫폼
      params = const PlatformWebViewControllerCreationParams();
    }

    final WebViewController controller =
        WebViewController.fromPlatformCreationParams(params);

    controller
      ..setJavaScriptMode(JavaScriptMode.unrestricted)
      ..setBackgroundColor(const Color(0x00000000)) // 배경색 설정 (예시)
      ..setNavigationDelegate(
        NavigationDelegate(
          onProgress: (int progress) {
            print('onProgress : $progress');
          },
          onPageStarted: (String url) {
            print('onPageStarted : $url');
          },
          onPageFinished: (String url) async {
            print('onPageFinished : $url');
            if (Platform.isIOS) {
              setState(() {
                _isRefreshing = false; // iOS 새로고침 인디케이터 숨김
              });
            }
            _updateCanGoBack();
            widget.onWebViewCanGoBackChanged?.call(); // 상태 변경 알림
          },
          onWebResourceError: (WebResourceError error) {
            // 웹 리소스 오류 처리
            debugPrint('''
Page resource error:
  code: ${error.errorCode}
  description: ${error.description}
  errorType: ${error.errorType}
  isForMainFrame: ${error.isForMainFrame}
          ''');
          },
          onNavigationRequest: (NavigationRequest request) {
            // 네비게이션 요청 처리 (예: 외부 링크 방지 등)
            // if (request.url.startsWith('https://www.youtube.com/')) {
            //   debugPrint('blocking navigation to ${request.url}');
            //   return NavigationDecision.prevent;
            // }

            print('onNavigationRequest : ${request.url}');

            return NavigationDecision.navigate;
          },
        ),
      )
      ..addJavaScriptChannel(
        // 필요 시 JavaScript 채널 추가
        'FlutterBridge',
        onMessageReceived: (JavaScriptMessage message) {
          print('onMessageReceived: ${message.message}');

          ScaffoldMessenger.of(
            context,
          ).showSnackBar(SnackBar(content: Text(message.message)));

          final msg = message.message;

          switch (msg) {
            case 'goBack':
              print('___ goBack');
              _handleBackPress();
              break;

            default:
              if (msg.startsWith('push:')) {
                _handlePushNavigation(msg);
              } else if (msg == 'goBack') {
                // _handleGoBack();
              }
              break;
          }
        },
      )
      ..loadRequest(Uri.parse(widget.initialUrl)); // URL 로드

    // iOS에서 스와이프 제스처 활성화
    if (controller.platform is WebKitWebViewController) {
      (controller.platform as WebKitWebViewController)
          .setAllowsBackForwardNavigationGestures(true);
    }

    _controller = controller;
    widget.onWebViewCreated?.call(controller);
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
      body: Stack(
        children: [
          WebViewWidget(controller: _controller!),
          if (isLoading) const Center(child: CircularProgressIndicator()),
          if (Platform.isIOS && _isRefreshing)
            const Positioned.fill(
              child: Center(child: CupertinoActivityIndicator(radius: 15.0)),
            ),
        ],
      ),
    );
  }
}
