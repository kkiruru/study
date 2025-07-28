import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'custom_app_bar.dart';

class CommonScreen extends ConsumerWidget {
  const CommonScreen({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return Scaffold(
      primary: true,
      backgroundColor: Colors.white,
      // appBar: CustomAppBar(),
      appBar: AppBar(
          backgroundColor: Colors.blue.withAlpha(50),
          primary: false,
          title: const Text("CommonScreen")
      ),
      extendBodyBehindAppBar: true,
      body: Column(
        children: [
          const Text(
              "Scaffold Widget 1",
              style: TextStyle(fontSize: 30)
          ),
          const Text(
              "Scaffold Widget 2",
              style: TextStyle(fontSize: 30)
          ),
          const Text(
              "Scaffold Widget 3",
              style: TextStyle(fontSize: 30)
          ),
          const Text(
              "Scaffold Widget 4",
              style: TextStyle(fontSize: 30)
          ),
        ],
      )

    );
  }
}

