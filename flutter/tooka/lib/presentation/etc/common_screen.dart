import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'base_state_provider.dart';
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
              )
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
