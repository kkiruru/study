import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:go_router/go_router.dart';
import 'package:tooka/presentation/web_view_screen.dart';
import 'presentation/main/main_screen.dart';
import 'presentation/splash/splash_screen.dart';
import 'presentation/main/first_tab_widget.dart';
import 'presentation/main/second_tab_widget.dart';
import 'presentation/main/third_tab_widget.dart';
import 'presentation/another_screen.dart';
import 'presentation/my_screen.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp.router(
      title: 'Tooka App',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      routerConfig: _router,
    );
  }
}


final GoRouter _router = GoRouter(
  initialLocation: '/',
  routes: [
    GoRoute(
      path: '/',
      builder: (context, state) => const SplashScreen(),
    ),
    GoRoute(
      path: '/main',
      builder: (context, state) => const MainScreen(),
    ),
    GoRoute(
      path: '/another',
      builder: (context, state) => const AnotherScreen(),
    ),
    GoRoute(
      path: '/my-widget',
      builder: (context, state) => const MyScreen(),
    ),
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
  ],
);