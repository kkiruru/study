
import 'package:flutter/material.dart';
import 'package:hello_flutter/widget/alert.dart';

class AlertPage extends StatelessWidget {
  const AlertPage({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const AlertScreen(title: 'Alert Test'),
    );
  }
}

class AlertScreen extends StatelessWidget {
  const AlertScreen({super.key, required this.title});
  final String title;

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
        child: Column(
          children: [
            topToastAlert(
              context: context,
              content: "안전운전 하세요!",
            ),
          ]
        )
    );
  }
}