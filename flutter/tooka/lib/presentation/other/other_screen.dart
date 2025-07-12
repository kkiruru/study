import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import '../../app_route.dart';
import '../../core/services/deep_link_service.dart';
import '../my/my_screen.dart';

class OtherScreen extends StatelessWidget {
  const OtherScreen({super.key});

  @override
  Widget build(BuildContext context) {

    print('OtherScreen: build');
    DeepLinkService.printStack();

    return Scaffold(
      appBar: AppBar(
        title: const Text('Other Screen'),
        leading: IconButton(
          icon: const Icon(Icons.close),
          onPressed: () {
            // context.pop();
          },
        ),
        backgroundColor: Colors.greenAccent,
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
              onPressed: () {
                // context.push('/my-widget');
              },
              style: ElevatedButton.styleFrom(
                padding: const EdgeInsets.symmetric(horizontal: 30, vertical: 15),
              ),
              child: const Text(
                'push /my-widget',
                style: TextStyle(fontSize: 16),
              ),
            ),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: () {
                // context.go('/my-widget');
              },
              style: ElevatedButton.styleFrom(
                padding: const EdgeInsets.symmetric(horizontal: 30, vertical: 15),
              ),
              child: const Text(
                'go /my-widget',
                style: TextStyle(fontSize: 16),
              ),
            ),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: () {
                Navigator.of(context).push<bool>(
                  CupertinoPageRoute(
                    builder: (_) => MyScreen(),
                  ),
                );
              },
              // style: ElevatedButton.styleFrom(
              //   padding: const EdgeInsets.symmetric(horizontal: 30, vertical: 15),
              // ),
              child: const Text(
                'Navigator.push MyScreen',
                style: TextStyle(fontSize: 16),
              ),
            ),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: () {
                // context.push('/main/another/foo');
              },
              style: ElevatedButton.styleFrom(
                padding: const EdgeInsets.symmetric(horizontal: 30, vertical: 15),
              ),
              child: const Text(
                'push /main/another/foo',
                style: TextStyle(fontSize: 16),
              ),
            ),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: () {
                // context.go('/main/another/foo');
              },
              style: ElevatedButton.styleFrom(
                padding: const EdgeInsets.symmetric(horizontal: 30, vertical: 15),
              ),
              child: const Text(
                'go /main/another/foo',
                style: TextStyle(fontSize: 16),
              ),
            ),
          ],
        ),
      ),
    );
  }
}