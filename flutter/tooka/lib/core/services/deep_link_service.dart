import 'dart:async';

import 'package:app_links/app_links.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import '../../app_route.dart';
import '../../presentation/main/main_screen.dart';

class DeepLinkService {
  static final DeepLinkService _instance = DeepLinkService._internal();
  factory DeepLinkService() => _instance;
  DeepLinkService._internal();

  StreamSubscription? _subscription;
  final GlobalKey<NavigatorState> navigatorKey = GlobalKey<NavigatorState>();
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
          _handleDeepLink(uri.toString());
        }
      }, onError: (err) {
        print('DeepLink 에러: $err');
      });
    } catch (e) {
      print('DeepLink 초기화 에러: $e');
    }
  }

  void onAppReady() {
    print('DeepLinkService: onAppReady() called');
    print('DeepLinkService: _pendingDeepLink = $_pendingDeepLink');
    
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
    print('DeepLinkService: onAppReady() completed, _isInitialized = $_isInitialized');
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
      case 'main':
        _navigateToHome();
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
        // Navigator를 사용하여 네비게이션
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
      AppRouter.navigateToMain(context);
      Future.delayed(const Duration(milliseconds: 500), () {
        final newContext = _getCurrentContext();
        if (newContext != null) {
          AppRouter.navigateToWebView(newContext, url, title: title);
        }
      });
    }
  }

  /// WebView 푸시
  void _pushWebView(String url, String title) {
    final context = _getCurrentContext();
    if (context != null) {
      AppRouter.navigateToWebView(context, url, title: title);
    }
  }

  /// 현재 context 가져오기
  BuildContext? _getCurrentContext() {
    try {
      return navigatorKey.currentContext;
    } catch (e) {
      print('Context 가져오기 실패: $e');
      return null;
    }
  }

  /// 홈 탭으로 이동
  void _navigateToHome() {
    final context = _getCurrentContext();

    if (context != null) {
      final navigator = ModalRoute.of(context);
      navigator?.settings.name;

      print('settings.name ${navigator?.settings.name}');

      AppRouter.navigateToMain(context, tabName: 'first');

      Future.delayed(const Duration(milliseconds: 300), () {
        _isProcessingDeepLink = false;
      });
    }
  }

  /// 마이 탭으로 이동
  void _navigateToMy() {
    final context = _getCurrentContext();
    if (context != null) {
      AppRouter.navigateToMain(context, tabName: 'third');

      Future.delayed(const Duration(milliseconds: 300), () {
        _isProcessingDeepLink = false;
      });
    }
  }

  /// 가이드 탭으로 이동
  void _navigateToGuide() {
    final context = _getCurrentContext();
    if (context != null) {
      AppRouter.navigateToMain(context, tabName: 'second');

      Future.delayed(const Duration(milliseconds: 300), () {
        _isProcessingDeepLink = false;
      });
    }
  }

  /// 서비스 정리
  void dispose() {
    _subscription?.cancel();
  }

  void push() {
    final context = _getCurrentContext();
    if (context != null) {
      // AppRouter를 사용하여 일관된 네비게이션 방식 적용
      AppRouter.navigateToMain(context);
    } else {
      print('DeepLink push() - context를 가져올 수 없음');
    }
  }



  void another() {
    final context = _getCurrentContext();
    if (context != null) {
      // AppRouter를 사용하여 일관된 네비게이션 방식 적용
      AppRouter.navigateToAnother(context);
    } else {
      print('DeepLink push() - context를 가져올 수 없음');
    }
  }

  static void printStack() {
    print('Route settings  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>');
    DeepLinkService().navigatorKey.currentState?.popUntil((route) {
      print('  name: ${route.settings.name}');
      return route.isFirst;
    });
    print('Route settings  _____________________________');
  }

  /// Deep Link 테스트용 메서드
  static void testDeepLink(String link) {
    DeepLinkService()._handleDeepLink(link);
  }
}