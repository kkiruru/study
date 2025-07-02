import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';

class SecondTabWidget extends StatelessWidget {
  const SecondTabWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          const Text(
            'Second Tab',
            style: TextStyle(
              fontSize: 24,
              fontWeight: FontWeight.bold,
            ),
          ),
          const SizedBox(height: 20),
          const Text(
            'This is the second tab content',
            style: TextStyle(fontSize: 16),
          ),
          const SizedBox(height: 30),
          ElevatedButton(
            onPressed: () {
              context.push('/another');
            },
            style: ElevatedButton.styleFrom(
              padding: const EdgeInsets.symmetric(horizontal: 30, vertical: 15),
            ),
            child: const Text(
              'Go to Another Screen',
              style: TextStyle(fontSize: 16),
            ),
          ),
        ],
      ),
    );
  }
} 