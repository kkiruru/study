import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_start/presentation/pattern/pattern_view_model.dart';

import 'detail_pattern_page.dart';

class PatternPage extends ConsumerWidget {
  const PatternPage({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    var viewMode = ref.watch(patternViewModelProvider);
    var state = ref.read(patternViewModelProvider);
    return Stack(
      children: [
        Scaffold(
          appBar: AppBar(title: Text("Pattern")),
          body: Padding(
            padding: EdgeInsets.symmetric(horizontal: 16, vertical: 16),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.stretch,
              children: [
                Text('state.counter: ${viewMode.state.counter}', style: TextStyle(fontSize: 16)),
                Text('state.name: ${viewMode.state.name}', style: TextStyle(fontSize: 16)),
                ElevatedButton(
                  onPressed: () {
                    state.loadData();
                  },
                  style: ElevatedButton.styleFrom(
                    padding: const EdgeInsets.symmetric(
                      horizontal: 30,
                      vertical: 15,
                    ),
                  ),
                  child: const Text(
                    'Load Data',
                    style: TextStyle(fontSize: 16),
                  ),
                ),
                ElevatedButton(
                  onPressed: () {
                    Navigator.of(context).push<bool>(
                      CupertinoPageRoute(
                        builder: (_) => const DetailPatternPage(),
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
                    'DetailPatternPage',
                    style: TextStyle(fontSize: 16),
                  ),
                ),
              ],
            ),
          ),
        ),
        loading(viewMode.state.isLoading)
      ],
    );
  }

  Widget loading(bool isLoading) {
    if (!isLoading) {
      return const SizedBox.shrink();
    }

    return Center(child: CircularProgressIndicator());
  }
}
