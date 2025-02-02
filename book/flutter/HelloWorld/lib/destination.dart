
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:hello_flutter/tab_screen.dart';

import 'alert_route.dart';
import 'dialog_demo.dart';
import 'main.dart';

class Destination {
  static const String main = "/";
  static const String alert = "/alert";
  static const String dialogDemo = "/dialog_demo";
  static const String tab = "/tab";

  static Route<dynamic> generateRoute(RouteSettings settings) {

    switch (settings.name) {
      case main:
        return MaterialPageRoute(
          builder: (context) => const MyApp(),
        );
      case alert:
        return MaterialPageRoute(
          builder: (context) => const AlertPage(),
        );
      case dialogDemo:
        return MaterialPageRoute(
          builder: (context) => const DialogDemo(),
        );

      case tab:
        return MaterialPageRoute(
          builder: (context) => MyTabs(),
        );

      default:
        throw const FormatException("Invalid Route");
    }
  }
}
