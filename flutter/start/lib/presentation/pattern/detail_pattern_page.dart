import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'global_counter_provider.dart';

class DetailPatternPage extends ConsumerWidget {
  const DetailPatternPage({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    var globalCounter = ref.watch(globalCounterProvider);

    return Scaffold(
      appBar: AppBar(title: Text("Detail Pattern")),
      body: Padding(
        padding: EdgeInsets.all(16),
        child: Column(
          children: [
            Text(
              'Global Counter from Detail Page: $globalCounter',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            SizedBox(height: 16),
            ElevatedButton(
              onPressed: () {
                ref.read(globalCounterProvider.notifier).state++;
              },
              child: Text('Increment Global Counter'),
            ),
            SizedBox(height: 8),
            ElevatedButton(
              onPressed: () {
                Navigator.of(context).pop();
              },
              child: Text('Go Back'),
            ),
          ],
        ),
      ),
    );
  }
}
