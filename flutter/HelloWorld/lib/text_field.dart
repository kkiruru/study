import 'package:flutter/material.dart';
import 'package:hello_flutter/widget/alert.dart';

import 'const/LColors.dart';

class TextFieldPage extends StatelessWidget {
  const TextFieldPage({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Text Field Test',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const TextFieldScreen(title: 'Text Field Test'),
    );
  }
}

class TextFieldScreen extends StatelessWidget {
  const TextFieldScreen({super.key, required this.title});

  final String title;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: SingleChildScrollView(
            padding: EdgeInsets.symmetric(vertical: 24, horizontal: 20),
            child: Container(
                color: LColors.blue,
                child: Column(children: [
                  Padding(
                    padding: EdgeInsets.symmetric(vertical: 12, horizontal: 5),
                    child: TextFormField(
                      autofocus: false,
                      obscureText: false,
                      decoration: InputDecoration(
                        border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(8),
                        ),
                        filled: true,
                        fillColor: LColors.white,
                        enabledBorder: OutlineInputBorder(
                          borderRadius: BorderRadius.all(Radius.circular(8.0)),
                          borderSide:
                              BorderSide(width: 1, color: LColors.gray400),
                        ),
                        focusedBorder: OutlineInputBorder(
                          borderRadius: BorderRadius.all(Radius.circular(8.0)),
                          borderSide:
                              BorderSide(width: 1, color: LColors.green),
                        ),
                      ),
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.symmetric(vertical: 12, horizontal: 5),
                    child: TextFormField(
                      autofocus: false,
                      obscureText: false,
                      decoration: InputDecoration(
                          border: OutlineInputBorder(
                            borderRadius: BorderRadius.circular(8),
                          ),
                          filled: true,
                          fillColor: LColors.white,
                          enabledBorder: OutlineInputBorder(
                            borderRadius:
                                BorderRadius.all(Radius.circular(8.0)),
                            borderSide:
                                BorderSide(width: 1, color: LColors.gray400),
                          ),
                          focusedBorder: OutlineInputBorder(
                            borderRadius:
                                BorderRadius.all(Radius.circular(8.0)),
                            borderSide:
                                BorderSide(width: 1, color: LColors.green),
                          ),
                          constraints: const BoxConstraints.expand(
                              height: 300, width: 150)),
                          maxLines: 50,

                    ),
                  ),
                  Padding(padding: EdgeInsets.only(top: 24)),
                ]))));
  }
}
