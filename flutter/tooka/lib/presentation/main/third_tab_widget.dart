import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:tooka/presentation/main/sliver_app_bar_screen.dart';


class ThirdTabWidget extends StatelessWidget {
  const ThirdTabWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Third Tab')),
      body: SingleChildScrollView(
        child: Padding(
          padding: EdgeInsets.symmetric(vertical: 15, horizontal: 10),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              ElevatedButton(
                onPressed: () {
                  Navigator.of(context).push<bool>(
                    CupertinoPageRoute(builder: (_) => const SliverAppBarScreen()),
                  );
                },
                style: ElevatedButton.styleFrom(
                  padding: const EdgeInsets.symmetric(
                    horizontal: 30,
                    vertical: 15,
                  ),
                ),
                child: const Text(
                  'SliverAppBarScreen',
                  style: TextStyle(fontSize: 16),
                ),
              ),
              const SizedBox(height: 30),
            ],
          ),
        ),
      ),
    );
  }
}
