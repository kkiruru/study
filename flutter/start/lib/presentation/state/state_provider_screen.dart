import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'default_layout.dart';

final numberProvider = StateProvider<int>((ref) => 0);

class StateProviderScreen extends ConsumerWidget {
  const StateProviderScreen({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final provider = ref.watch(numberProvider);

    return DefaultLayout(
      title: "StateProviderScreen",
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          Text(provider.toString()),
          ElevatedButton(
            onPressed: () {
              ref.read(numberProvider.notifier).update((state) => state + 1);
            },
            child: const Text("UP"),
          ),
          ElevatedButton(
            onPressed: () {
              ref.read(numberProvider.notifier).state =
                  ref.read(numberProvider.notifier).state - 1;
            },
            child: const Text("DOWN"),
          ),
          ElevatedButton(
            onPressed: () {
              Navigator.of(
                context,
              ).push(CupertinoPageRoute(builder: (_) => _NextScreen()));
            },
            child: const Text("Next Screen"),
          ),
        ],
      ),
    );
  }
}

class _NextScreen extends ConsumerWidget {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final provider = ref.watch(numberProvider);

    return DefaultLayout(
      title: "_NextScreen",
      body: SizedBox(
        width: MediaQuery.of(context).size.width,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(provider.toString()),
            ElevatedButton(
              onPressed: () {
                ref.read(numberProvider.notifier).update((state) => state + 1);
              },
              child: const Text("UP"),
            ),
          ],
        ),
      ),
    );
  }
}
