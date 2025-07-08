import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:go_router/go_router.dart';
import 'package:tooka/presentation/another/foo_screen.dart';
import 'package:tooka/presentation/webview/web_view_screen.dart';
import 'package:tooka/app_route.dart';
import 'presentation/main/main_screen.dart';
import 'presentation/splash/splash_screen.dart';
import 'presentation/main/first_tab_widget.dart';
import 'presentation/main/second_tab_widget.dart';
import 'presentation/main/third_tab_widget.dart';
import 'presentation/another/another_screen.dart';
import 'presentation/my/my_screen.dart';

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
      routerConfig: AppRouter.router,
    );
  }
}
