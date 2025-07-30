import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_start/presentation/state/future_provider_screen.dart';
import 'package:flutter_start/presentation/state/state_notifier_provider_screen.dart';
import 'package:flutter_start/presentation/state/state_provider_screen.dart';
import 'package:flutter_start/presentation/state/stream_provider_screen.dart';

class HomePage extends StatelessWidget {
  const HomePage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        // backgroundColor: Colors.blue,
        title: Text("Home"),
      ),
      body: Padding(
        padding: EdgeInsets.symmetric(horizontal: 16, vertical: 16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            ElevatedButton(
              onPressed: () {
                Navigator.of(context).push<bool>(
                  CupertinoPageRoute(
                    builder: (_) => const StateProviderScreen(),
                  ),
                );
              },
              style: ElevatedButton.styleFrom(
                padding: const EdgeInsets.symmetric(
                  horizontal: 30,
                  vertical: 15,
                ),
              ),
              child: const Text(
                'StateProviderScreen',
                style: TextStyle(fontSize: 16),
              ),
            ),
            const SizedBox(height: 12),
            ElevatedButton(
              onPressed: () {
                Navigator.of(context).push<bool>(
                  CupertinoPageRoute(
                    builder: (_) => const StateNotifierProviderScreen(),
                  ),
                );
              },
              style: ElevatedButton.styleFrom(
                padding: const EdgeInsets.symmetric(
                  horizontal: 30,
                  vertical: 15,
                ),
              ),
              child: const Text(
                'StateNotifierProviderScreen',
                style: TextStyle(fontSize: 16),
              ),
            ),
            const SizedBox(height: 12),
            ElevatedButton(
              onPressed: () {
                Navigator.of(context).push<bool>(
                  CupertinoPageRoute(
                    builder: (_) => const FutureProviderScreen(),
                  ),
                );
              },
              style: ElevatedButton.styleFrom(
                padding: const EdgeInsets.symmetric(
                  horizontal: 30,
                  vertical: 15,
                ),
              ),
              child: const Text(
                'FutureProviderScreen',
                style: TextStyle(fontSize: 16),
              ),
            ),
            const SizedBox(height: 12),
            ElevatedButton(
              onPressed: () {
                Navigator.of(context).push<bool>(
                  CupertinoPageRoute(
                    builder: (_) => const StreamProviderScreen(),
                  ),
                );
              },
              style: ElevatedButton.styleFrom(
                padding: const EdgeInsets.symmetric(
                  horizontal: 30,
                  vertical: 15,
                ),
              ),
              child: const Text(
                'StreamProviderScreen',
                style: TextStyle(fontSize: 16),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
