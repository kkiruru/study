

import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:tooka/presentation/another/another_screen.dart';
import 'package:tooka/presentation/another/foo_screen.dart';
import 'package:tooka/presentation/error/error_screen.dart';
import 'package:tooka/presentation/main/main_screen.dart';
import 'package:tooka/presentation/my/my_screen.dart';
import 'package:tooka/presentation/other/bar_screen.dart';
import 'package:tooka/presentation/other/other_screen.dart';
import 'package:tooka/presentation/splash/splash_screen.dart';
import 'package:tooka/presentation/webview/web_view_screen.dart';

import 'core/services/deep_link_service.dart';



class AppRouter {
  AppRouter._();

  static String initialLocation = '/splash';

  // GoRouter 설정을 static final 변수로 정의합니다.
  static final GoRouter router = GoRouter(
    initialLocation: initialLocation,
    routes: [
      GoRoute(
        path: '/splash',
        builder: (context, state) => const SplashScreen(),
      ),
      GoRoute(
        path: '/',
        builder: (context, state) {
          final String? tabName = state.uri.queryParameters['tab'];
          print('>>>>>> GoRoute path: / tabName: $tabName');
          return MainScreen(initializeTabName: tabName);
        },
        routes: [
          GoRoute(
              path: '/another',
              pageBuilder: (context, state) {
                return CustomTransitionPage(
                    key: state.pageKey,
                    child: const AnotherScreen(),
                    transitionsBuilder: (context, animation, secondaryAnimation, child) {
                      const begin = Offset(0.0, 1.0);
                      const end = Offset.zero;
                      const curve = Curves.easeInOut;

                      final tween = Tween(begin: begin, end: end).chain(CurveTween(curve: curve));
                      final offsetAnimation = animation.drive(tween);

                      return SlideTransition(
                        position: offsetAnimation,
                        child: child,
                      );
                    });
              },
              routes: [
                GoRoute(
                  path: '/foo',
                  pageBuilder: (context, state) => MaterialPage(
                    fullscreenDialog: true,
                    child: FooScreen(),
                  ),
                ),
              ]
          ),
          GoRoute(
              path: '/other',
              pageBuilder: (context, state) {
                return CustomTransitionPage(
                    key: state.pageKey,
                    child: const OtherScreen(),
                    transitionsBuilder: (context, animation, secondaryAnimation, child) {
                      const begin = Offset(0.0, 1.0);
                      const end = Offset.zero;
                      const curve = Curves.easeInOut;

                      final tween = Tween(begin: begin, end: end).chain(CurveTween(curve: curve));
                      final offsetAnimation = animation.drive(tween);

                      return SlideTransition(
                        position: offsetAnimation,
                        child: child,
                      );
                    });
              },
              routes: [
                GoRoute(
                  path: '/bar',
                  pageBuilder: (context, state) => MaterialPage(
                    fullscreenDialog: true,
                    child: BarScreen(),
                  ),
                ),
              ]
          ),
        ],
      ),
      GoRoute(
        path: '/my-widget',
        builder: (context, state) => const MyScreen(),
      ),
      // GoRoute(
      //   path: '/another',
      //   builder: (context, state) => const AnotherScreen(),
      // ),
      GoRoute(
        path: '/web-view/:url',
        builder: (context, state) {
          // 경로 파라미터 'url' 값을 가져옵니다.
          final url = state.pathParameters['url'];

          final String? title = state.uri.queryParameters['title'];

          // url 값이 null이 아니고 String 타입인지 확인하는 것이 좋습니다.
          // 그리고 WebViewScreen 생성 시 const를 사용할 수 없습니다.
          // 왜냐하면 state.pathParameters['url']은 컴파일 타임 상수가 아니기 때문입니다.
          if (url != null) {
            return WebViewScreen(url: url);
          } else {
            return const Scaffold(
              body: Center(
                child: Text('URL 파라미터가 없습니다.'),
              ),
            );
          }
        },
      ),
      GoRoute(
        path: '/nothing',
        builder: (context, state) => const SplashScreen(),
      ),
    ],
    redirect: (context, state) {
      print('>>> redirect: ${state.matchedLocation}, isInitialized:${DeepLinkService().isInitialized}');

      print('>>> ______ fullPath ${state.fullPath}');
      print('>>> ______ uri ${state.uri.toString()}');

      switch (state.uri.host) {
        case 'guide':
          return '/?tab=guide';
      }

    },
    errorBuilder: (context, state) => ErrorScreen(error: state.error!),
  );


  static void printStack() {
    final RouteMatchList matchList = router.routerDelegate.currentConfiguration;

    print('====== printStack  =====================');
    for (var match in matchList.matches) {
      print('   : ${match.matchedLocation}');
    };
    print('----------------------------------------');

  }
}


class NavigationData extends ChangeNotifier {
  Map<String, dynamic>? _dataFromC;

  Map<String, dynamic>? get dataFromC => _dataFromC;

  void setDataFromC(Map<String, dynamic> data) {
    _dataFromC = data;
    notifyListeners();
  }

  void clearData() {
    _dataFromC = null;
    notifyListeners();
  }
}
