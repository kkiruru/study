import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'base_state_provider.dart';
import 'bottomsheet/BottomSheetPage.dart';
import 'example_provider.dart';

class CommonScreen extends ConsumerWidget {
  const CommonScreen({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {

    final loadingState = ref.watch(gLoadingStateNotifierProvider);
    final userState = ref.watch(userProvider);

    return Stack(
      fit: StackFit.expand,
      children: [
        Scaffold(
          backgroundColor: Colors.white,
          appBar: AppBar(
            backgroundColor: Colors.blue.withAlpha(50),
            title: const Text("CommonScreen"),
          ),
          body: Column(
            children: [
              const Text("Scaffold Widget 1", style: TextStyle(fontSize: 30)),
              const Text("Scaffold Widget 2", style: TextStyle(fontSize: 30)),
              const Text("Scaffold Widget 3", style: TextStyle(fontSize: 30)),
              Text("${userState.value}"),
              ElevatedButton(
                  onPressed: () {
                    ref.read(userProvider.notifier).bar();
                  },
                  child: Text('Loading')
              ),
              const SizedBox(height: 16),
              ElevatedButton(
                onPressed: () {
                  Navigator.of(context).push<bool>(
                    CupertinoPageRoute(builder: (_) => const BottomSheetPage()),
                  );
                },
                style: ElevatedButton.styleFrom(
                  padding: const EdgeInsets.symmetric(
                    horizontal: 30,
                    vertical: 15,
                  ),
                ),
                child: const Text(
                  'CommonScreen',
                  style: TextStyle(fontSize: 16),
                ),
              ),
            ],
          ),
        ),
        _loading(context, loadingState),
      ],
    );
  }

  Widget _loading(BuildContext context, BaseLoading loading) {
    if (loading == BaseLoading.normal) {
      return const SizedBox.shrink();
    } else {
      return const Center(
          child: CircularProgressIndicator()
      );
    }
  }
}
