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
      body: Column(children: [
        Text(provider.toString()),
      ]),
    );
  }
}
