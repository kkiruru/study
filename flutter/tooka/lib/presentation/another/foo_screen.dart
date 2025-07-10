import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:tooka/presentation/another/baz_screen.dart';

import '../../app_route.dart';

class FooScreen extends StatelessWidget {
  const FooScreen({super.key});

  @override
  Widget build(BuildContext context) {

    print('FooScreen: build');
    AppRouter.printStack();

    return Scaffold(
      appBar: AppBar(
        title: const Text('Foo'),
        leading: IconButton(
          icon: const Icon(Icons.close),
          onPressed: () {
            context.pop();
          },
        ),
        backgroundColor: Colors.orange,
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
              onPressed: () {
                showModalBottomSheet(
                  context: context,
                  isScrollControlled: true,
                  backgroundColor: Colors.red,
                  builder: (_) => BazScreen(),
                );
              },
              style: ElevatedButton.styleFrom(
                padding: const EdgeInsets.symmetric(horizontal: 30, vertical: 15),
              ),
              child: const Text(
                'showModalBottomSheet',
                style: TextStyle(fontSize: 16),
              ),
            ),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: () {
                context.go('/main?tab=first');
              },
              style: ElevatedButton.styleFrom(
                padding: const EdgeInsets.symmetric(
                  horizontal: 30,
                  vertical: 15,
                ),
              ),
              child: const Text(
                'go /main?tab=my',
                style: TextStyle(fontSize: 16),
              ),
            ),

          ],
        ),
      ),
    );
  }
} 