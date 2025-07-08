// my_screen.dart
import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import '../widgets/common_webview_widget.dart'; // 경로 확인
import 'package:webview_flutter/webview_flutter.dart';
import 'dart:io';

class MyScreen extends StatefulWidget {
  const MyScreen({super.key});

  @override
  State<MyScreen> createState() => _MyScreenState();
}

class _MyScreenState extends State<MyScreen> {
  WebViewController? _webViewController;
  bool _canGoBack = false;
  bool _hasError = false;
  String? _errorMessage = null;

  @override
  Widget build(BuildContext context) {
    Widget content = PopScope(
      canPop: true,
      onPopInvokedWithResult: (bool didPop, dynamic result) async {
        print('MyWidget onPopInvokedWithResult ${didPop}');
        if (didPop) return;

        // WebView 뒤로가기 가능 여부 확인
        if (_webViewController != null) {
          final canGoBack = await _webViewController!.canGoBack();
          print('MyWidget: WebView canGoBack = $canGoBack');
          if (canGoBack) {
            // WebView 히스토리가 있으면 뒤로가기
            print('MyWidget: Executing WebView goBack()');
            await _webViewController!.goBack();
            _updateCanGoBack();
          } else {
            // WebView 히스토리가 없으면 위젯 팝
            print('MyWidget: No WebView history, popping widget');
            context.pop();
          }
        } else {
          print('MyWidget: No WebViewController, popping widget');
          context.pop();
        }
      },
      child: Scaffold(
        appBar: AppBar(
          title: const Text('My Widget'),
          actions: [
            // 테스트용 버튼 추가
            IconButton(
              icon: const Icon(Icons.navigate_next),
              onPressed: () async {
                if (_webViewController != null) {
                  await _webViewController!.loadRequest(Uri.parse('https://www.google.com'));
                  print('MyWidget: Loaded Google for testing');
                }
              },
            ),
          ],
        ),
        body:  Stack(
          children: [
            CommonWebViewWidget(
              initialUrl: 'https://v2webapp.laundry24.kr/home',
              enablePopGesture: true, // iOS 스와이프 제스처 활성화
              onPop: () {
                // 커스텀 뒤로가기 동작
                print('MyWidget onPop callback');
                context.pop();
              },
              onWebViewCreated: (controller) {
                print('MyWidget: WebView Created with webview_flutter');
                _webViewController = controller;
                _updateCanGoBack();
              },
              onWebViewCanGoBackChanged: (canGoBack) {
                print('MyWidget: WebView canGoBack status changed (webview_flutter).');
                _updateCanGoBack();
              },
              onError: (message) {
                setState(() {
                  if (message.isNotEmpty) {
                    _hasError = true;
                    _errorMessage = message;
                  } else {
                    _hasError = false;
                    _errorMessage = null;
                  }
                });
              },
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
                        setState(() {
                            _hasError = false;
                            _errorMessage = null;
                          }
                        );
                        _webViewController?.reload();
                      },
                      child: const Text('다시 시도'),
                    ),
                  ],
                ),
              ),
          ]
        ),
      )
    );

    // iOS에서 스와이프 제스처를 더 확실하게 감지하기 위해 GestureDetector 추가
    if (Platform.isIOS) {
      return GestureDetector(
        onHorizontalDragEnd: (details) {
          // 오른쪽에서 왼쪽으로 스와이프 (뒤로가기)
          if (details.primaryVelocity! > 0) {
            print('MyWidget: iOS swipe gesture detected');
            _handleSwipeBack();
          }
        },
        child: content,
      );
    }

    return content;
  }

  Future<void> _handleSwipeBack() async {
    if (_webViewController != null) {
      final canGoBack = await _webViewController!.canGoBack();
      print('MyWidget: Swipe back - WebView canGoBack = $canGoBack');
      if (canGoBack) {
        await _webViewController!.goBack();
        _updateCanGoBack();
      } else {
        context.pop();
      }
    } else {
      context.pop();
    }
  }

  Future<void> _updateCanGoBack() async {
    if (_webViewController != null) {
      final canGoBack = await _webViewController!.canGoBack();
      print('MyWidget: _updateCanGoBack = $canGoBack');
      if (mounted) {
        setState(() {
          _canGoBack = canGoBack;
        });
      }
    }
  }
}