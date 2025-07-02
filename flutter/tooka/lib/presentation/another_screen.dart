import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';

class AnotherScreen extends StatelessWidget {
  const AnotherScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Another Widget'),
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            const Text(
              'Another Widget',
              style: TextStyle(
                fontSize: 24,
                fontWeight: FontWeight.bold,
              ),
            ),
            const SizedBox(height: 20),
            const Text(
              'This is the another widget page',
              style: TextStyle(fontSize: 16),
            ),
            const SizedBox(height: 30),
            ElevatedButton(
              onPressed: () {
                context.push('/my-widget');
              },
              style: ElevatedButton.styleFrom(
                padding: const EdgeInsets.symmetric(horizontal: 30, vertical: 15),
              ),
              child: const Text(
                'Go to My Widget',
                style: TextStyle(fontSize: 16),
              ),
            ),
            const SizedBox(height: 20),
            TextButton(
              onPressed: () {
                context.go('/main');
              },
              child: const Text('Back to Main'),
            ),
          ],
        ),
      ),
    );
  }
} 