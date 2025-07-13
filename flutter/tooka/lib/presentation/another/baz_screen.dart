import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import '../../app_route.dart';
import '../../core/services/deep_link_service.dart';

class BazScreen extends StatelessWidget {
  const BazScreen({super.key});

  @override
  Widget build(BuildContext context) {

    print('BazScreen: build');
    AppRouter.printStack();

    return Scaffold(
      appBar: AppBar(
        toolbarHeight: 156.0,
        leading: IconButton(
            onPressed: () {
              // context.pop();
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
