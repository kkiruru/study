import 'dart:async';

import 'package:app_links/app_links.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/services.dart';
import 'package:go_router/go_router.dart';

import '../../app_route.dart';

class DeepLinkService {
  static final DeepLinkService _instance = DeepLinkService._internal();
  factory DeepLinkService() => _instance;
  DeepLinkService._internal();

  StreamSubscription? _subscription;
  final AppLinks _appLinks = AppLinks();

  String? _pendingDeepLink;
  bool _isInitialized = false;
  bool _isProcessingDeepLink = false;

  /// Deep Link 처리 중인지 확인
  bool get isProcessingDeepLink => _isProcessingDeepLink;
  bool get isInitialized => _isInitialized;

  /// Deep Link 초기화 및 리스너 설정
  Future<void> initialize() async {

    print('DeepLink initialize() >>> ');

    try {
      final initialLink = await _appLinks.getInitialAppLink();

      if (initialLink != null) {
        _pendingDeepLink = initialLink.toString();
        print('DeepLink DeepLinkService initialize:: ${_pendingDeepLink}');
      }
      _subscription = _appLinks.uriLinkStream.listen((Uri? uri) {
        if (uri != null) {
          print('DeepLink 실행 중에 딥링크 수신 : ${uri.toString()}');
          _handleDeepLink(uri. toString());
        }
      }, onError: (err) {
        print('DeepLink 에러: $err');
      });

    } catch (e) {
      print('DeepLink 초기화 에러: $e');
    }
  }

  void onAppReady() {
    if (_pendingDeepLink != null) {
      print('DeepLink onAppReady 된 이후에 _pendingDeepLink 처리');

      final link = _pendingDeepLink!;
      _pendingDeepLink = null;

      _isProcessingDeepLink = true;

      SystemChrome.setEnabledSystemUIMode(SystemUiMode.edgeToEdge);

      Future.delayed(const Duration(milliseconds: 500), () {
        _handleDeepLink(link, isInitial: true);
      });
    } else {
      print('Deep Link onAppReady - pendingDeepLink가 없음');
    }

    _isInitialized = true;
  }

  /// Deep Link 처리 로직
  void _handleDeepLink(String link, {bool isInitial = false}) {
    print('Deep Link 수신: $link');

    try {
      final uri = Uri.parse(link);

      if (uri.scheme == 'tooka') {
        _handleLaundry24Scheme(uri, isInitial);
      }
    } catch (e) {
      print('Deep Link 파싱 에러: $e');
      _isProcessingDeepLink = false;
    }
  }

  void _handleLaundry24Scheme(Uri uri, bool isInitial) {
    final path = uri.host;
    final queryParams = uri.queryParameters;

    print('스키마 처리 - path: $path, params: $queryParams');

    switch (path) {
      case 'webview':
      case 'openweb':
        _openWebView(queryParams, isInitial);
        break;
      case 'home':
        _navigateToHome();
        break;
      case 'guide':
        _navigateToGuide();
        break;
      case 'my':
        _navigateToMy();
        break;
      default:
        print('알 수 없는 Deep Link 경로: $path');
        _navigateToHome();
    }
  }

  /// WebView 열기
  void _openWebView(Map<String, String> params, bool isInitial) {
    final url = params['url'];
    final title = params['title'] ?? '';

    if (url != null && url.isNotEmpty) {
      if (isInitial) {
        // GoRouter를 사용하여 네비게이션
        _navigateToMainWithWebView(url, title);
      } else {
        _pushWebView(url, title);
      }
      _isProcessingDeepLink = false;
    } else {
      _isProcessingDeepLink = false;
    }
  }

  /// 메인 화면으로 이동하고 WebView 열기
  void _navigateToMainWithWebView(String url, String title) {
    final context = _getCurrentContext();
    if (context != null) {
      context.go('/main');
      Future.delayed(const Duration(milliseconds: 500), () {
        final newContext = _getCurrentContext();
        if (newContext != null) {
          newContext.push('/main/web-view/${Uri.encodeComponent(url)}?title=${Uri.encodeComponent(title)}');
        }
      });
    }
  }

  /// WebView 푸시
  void _pushWebView(String url, String title) {
    final context = _getCurrentContext();
    if (context != null) {
      context.push('/main/web-view/${Uri.encodeComponent(url)}?title=${Uri.encodeComponent(title)}');
    }
  }

  /// 현재 context 가져오기
  BuildContext? _getCurrentContext() {
    try {
      return AppRouter.router.routerDelegate.navigatorKey.currentContext;
    } catch (e) {
      print('Context 가져오기 실패: $e');
      return null;
    }
  }

  /// 홈 탭으로 이동
  void _navigateToHome() {
    final context = _getCurrentContext();
    if (context != null) {
      context.go('/main?tab=home');

      Future.delayed(const Duration(milliseconds: 300), () {
        _isProcessingDeepLink = false;
      });
    }
  }

  /// 마이 탭으로 이동
  void _navigateToMy() {
    final context = _getCurrentContext();
    if (context != null) {
      context.go('/main?tab=my');

      Future.delayed(const Duration(milliseconds: 300), () {
        _isProcessingDeepLink = false;
      });
    }
  }

  /// 가이드 탭으로 이동
  void _navigateToGuide() {
    final context = _getCurrentContext();
    if (context != null) {
      context.go('/main?tab=guide');

      Future.delayed(const Duration(milliseconds: 300), () {
        _isProcessingDeepLink = false;
      });
    }
  }

  /// 서비스 정리
  void dispose() {
    _subscription?.cancel();
  }

  /// Deep Link 테스트용 메서드
  static void testDeepLink(String link) {
    DeepLinkService()._handleDeepLink(link);
  }
}