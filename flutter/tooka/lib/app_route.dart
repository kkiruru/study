

import 'package:flutter/material.dart';
import 'package:tooka/presentation/another/another_screen.dart';
import 'package:tooka/presentation/another/foo_screen.dart';
import 'package:tooka/presentation/main/main_screen.dart';
import 'package:tooka/presentation/my/my_screen.dart';
import 'package:tooka/presentation/other/bar_screen.dart';
import 'package:tooka/presentation/other/other_screen.dart';
import 'package:tooka/presentation/webview/web_view_screen.dart';



class AppRouter {
  // private constructor. 이 클래스는 인스턴스화될 필요가 없습니다.
  AppRouter._();

  // 네비게이션 헬퍼 메서드들
  static void navigateToMain(BuildContext context, {String? tabName}) {

    Navigator.of(context).pushReplacement(
      MaterialPageRoute(
        builder: (context) => MainScreen(initializeTabName: tabName),
        settings: const RouteSettings(name: '/'), 
      ),
    );
  }

  static void navigateToAnother(BuildContext context) {
    Navigator.of(context, rootNavigator: true).push(
      MaterialPageRoute(
        builder: (context) => const AnotherScreen(),
        // settings: const RouteSettings(name: 'another'),
      ),
    );
  }



  static void navigateToFoo(BuildContext context) {
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => const FooScreen(),
        fullscreenDialog: true,
      ),
    );
  }

  static void navigateToOther(BuildContext context) {
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => const OtherScreen(),
        settings: const RouteSettings(name: 'other'), 
      ),
    );
  }

  static void navigateToBar(BuildContext context) {
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => const BarScreen(),
        fullscreenDialog: true,
      ),
    );
  }

  static void navigateToMy(BuildContext context) {
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => const MyScreen(),
      ),
    );
  }

  static void navigateToWebView(BuildContext context, String url, {String? title}) {
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => WebViewScreen(url: url),
      ),
    );
  }


  // static void navigatePush(BuildContext context, Widget widget, String name) {
  //   Navigator.of(context).push(
  //     MaterialPageRoute(
  //       builder: (context) => widget.call(),
  //       settings: RouteSettings(name: name),
  //     ),
  //   );
  // }


  // static void printStack() {
  //   print('AppRouter: 현재는 GoRouter가 제거되어 스택 정보를 출력할 수 없습니다.');
  // }
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
