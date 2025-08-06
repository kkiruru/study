import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_start/presentation/pattern/pattern_view_model.dart';
import 'package:flutter_start/presentation/pattern/profile_page.dart';

import 'base_page_widget.dart';
import 'detail_pattern_page.dart';

class PatternPage extends ConsumerWidget {
  const PatternPage({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    var viewModel = ref.watch(patternViewModelProvider);

    return basePage(
      Scaffold(
        appBar: AppBar(title: Text("Pattern")),
        body: Padding(
          padding: EdgeInsets.symmetric(horizontal: 16, vertical: 16),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              Text(
                'Local counter: ${viewModel.state.counter}',
                style: TextStyle(fontSize: 16),
              ),
              Text(
                'state.name: ${viewModel.state.userVo?.name}',
                style: TextStyle(fontSize: 16),
              ),
              SizedBox(height: 16),
              ElevatedButton(
                onPressed: () {
                  viewModel.loadData();
                },
                style: ElevatedButton.styleFrom(
                  padding: const EdgeInsets.symmetric(
                    horizontal: 30,
                    vertical: 15,
                  ),
                ),
                child: const Text('Load Data', style: TextStyle(fontSize: 16)),
              ),
              SizedBox(height: 8),
              ElevatedButton(
                onPressed: () {
                  viewModel.incrementCounter();
                },
                style: ElevatedButton.styleFrom(
                  padding: const EdgeInsets.symmetric(
                    horizontal: 30,
                    vertical: 15,
                  ),
                ),
                child: const Text('Increment Global Counter', style: TextStyle(fontSize: 16)),
              ),
              SizedBox(height: 8),
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
              SizedBox(height: 8),
              ElevatedButton(
                onPressed: () {
                  Navigator.of(context).push<bool>(
                    CupertinoPageRoute(
                      builder: (_) => const ProfilePage(),
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
                  'ProfilePage',
                  style: TextStyle(fontSize: 16),
                ),
              ),
            ],
          ),
        ),
      ),
      viewModel: viewModel.state,
    );
  }
}
