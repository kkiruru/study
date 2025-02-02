import 'package:flutter/material.dart';
import 'package:flutter_chapter3/list_screen.dart';
import 'package:flutter_chapter3/detail_screen.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Book List APP',
      home: ListScreen(),
    );
  }
}
