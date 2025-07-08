import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:go_router/go_router.dart';

class BazScreen extends StatelessWidget {
  const BazScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        toolbarHeight: 156.0,
        // // title: SafeArea(
        // //     top : true,
        // //     bottom: false,
        // //     child: const Text('Baz1'),
        // // ),
        // title: Padding(
        //   padding: const EdgeInsets.only(top: 56.0),
        //   child: const Text('Baz2'),
        // ),
        leading: IconButton(
            onPressed: () {
              context.pop();
            },
            icon: Icon(Icons.wallet),
        ),
        backgroundColor: Colors.amber,
        systemOverlayStyle: SystemUiOverlayStyle(
          statusBarColor: Color(0xFF4DD0E1),
          statusBarIconBrightness: Brightness.dark,
          statusBarBrightness: Brightness.light,
        ),
      ),
      backgroundColor: Color(0xFF4DD0E1),
      body: Center(
        child: Column(mainAxisAlignment: MainAxisAlignment.center),
      ),
    );
  }
}
