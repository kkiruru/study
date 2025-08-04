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
    final state3 = ref.watch(gStateFuture2Provider);
    final state4 = ref.watch(gStateMultiplyProvider(number1: 10, number2: 20));
    final state5 = ref.watch(gStateNotifierProvider);

    print('CodeGenerationScreen build');

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
            state3.when(
              data: (data) {
                return Text('State3: ${data.toString()}');
              },
              error: (error, stack) => Text('State3: ${error.toString()}'),
              loading: () => const CircularProgressIndicator(),
            ),
            Text('State4: $state4'),
            Text('State5: $state5'),
            Consumer(
              builder: (context, ref, child) {
                print('Consumer builder build');
                final state5 = ref.watch(gStateNotifierProvider);

                return child ?? Text('State5: $state5');
              },
              child: const Text("Consumer child"),
            ),
            Row(
              children: [
                ElevatedButton(
                  onPressed: () {
                    ref.read(gStateNotifierProvider.notifier).increment();
                  },
                  child: const Text('Increment'),
                ),
                ElevatedButton(
                  onPressed: () {
                    ref.read(gStateNotifierProvider.notifier).decrement();
                  },
                  child: const Text('Decrement'),
                ),
              ],
            ),
            ElevatedButton(
              onPressed: () {
                ref.invalidate(gStateNotifierProvider);
              },
              child: const Text('Invalidate'),
            ),
          ],
        )
    );
  }
}

class _StateFiveWidget extends ConsumerWidget {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final state5 = ref.watch(gStateNotifierProvider);

    return Text('State5: $state5');
  }

}