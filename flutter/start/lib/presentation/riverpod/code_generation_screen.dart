import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_start/presentation/riverpod/code_generation_provider.dart';
import 'package:flutter_start/presentation/state/default_layout.dart';

class CodeGenerationScreen extends ConsumerWidget {
  const CodeGenerationScreen({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final state0 = ref.watch(testProvider);
    final state1 = ref.watch(gStateProvider);
    final state2 = ref.watch(gStateFutureProvider);


    return DefaultLayout(
        title: "CodeGenerationScreen",
        body: Column(
          children: [
            Text('State0: $state0'),
            Text('State1: $state1'),
            Text('State2: $state2'),
            state2.when(
                data: (data) {
                  return Text('State2: ${data.toString()}');
                },
                error: (error, stack) => Text('State2: ${error.toString()}'),
                loading: () => const CircularProgressIndicator(),
            ),
          ],
        )
    );
  }

}