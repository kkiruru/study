import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';

import '../../app_route.dart';

class BarScreen extends StatelessWidget {
  const BarScreen({super.key});

  @override
  Widget build(BuildContext context) {

    print('BarScreen: build');
    AppRouter.printStack();

    return Scaffold(
      appBar: AppBar(
        title: const Text('Boo'),
        leading: IconButton(
          icon: const Icon(Icons.close),
          onPressed: () {
            context.pop();
          },
        ),
        backgroundColor: Colors.grey,
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
        ),
      ),
    );
  }
} 