import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'const/LColors.dart';

class FullScreen extends StatelessWidget {
  const FullScreen({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return Container(
        color: LColors.white,
        child: Column(
          children: [
            SizedBox(
                child: Container(
                  padding: EdgeInsets.fromLTRB(20, 0, 20, 24),
                  width: double.maxFinite,
                  color: LColors.green,
                    child: SafeArea(
                      child: Text(
                          "어쩌구 저쩌구"
                      ),
                    )
                )
            ),
            Expanded(
              child: SingleChildScrollView(
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Container(
                      height: 150,
                      color: Colors.amberAccent,
                    ),
                    Container(
                      height: 150,
                      color: Colors.blueAccent,
                    ),
                    Container(
                      color: Colors.redAccent,
                    ),
                    Container(
                      height: 150,
                      color: Colors.greenAccent,
                    ),
                    Container(
                      height: 150,
                      color: Colors.cyanAccent,
                    ),
                    Container(
                      height: 150,
                      color: Colors.amberAccent,
                    ),
                    Container(
                      height: 150,
                      color: Colors.blueAccent,
                    ),
                    Container(
                      height: 150,
                      color: Colors.redAccent,
                    ),
                    Container(
                      height: 150,
                      color: Colors.greenAccent,
                    ),
                    Container(
                      height: 150,
                      color: Colors.cyanAccent,
                    ),
                    Container(
                      height: 150,
                      color: Colors.amberAccent,
                    ),
                    Container(
                      height: 150,
                      color: Colors.blueAccent,
                    ),
                  ],
                ),
              )
            ),
            SizedBox(
              child: Container(
                  padding: EdgeInsets.fromLTRB(20, 0, 20, 24),
                  width: double.maxFinite,
                  color: LColors.white,
                  child: Text(
                      "로그아웃"
                  )
              )
            ),
          ],
        ),
    );
  }
}