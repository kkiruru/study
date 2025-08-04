import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class DetailPatternPage extends StatelessWidget {
  const DetailPatternPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Pattern")),
      body: Padding(
        padding: EdgeInsets.symmetric(horizontal: 16, vertical: 16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            ElevatedButton(
              onPressed: () {
                Navigator.of(context).pop();
              },
              style: ElevatedButton.styleFrom(
                padding: const EdgeInsets.symmetric(
                  horizontal: 30,
                  vertical: 15,
                ),
              ),
              child: const Text('back', style: TextStyle(fontSize: 16)),
            ),
          ],
        ),
      ),
    );
  }
}
