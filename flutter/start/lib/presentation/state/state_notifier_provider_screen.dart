import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_start/presentation/state/default_layout.dart';
import 'package:flutter_start/presentation/state/state_notifier_provider.dart';

class StateNotifierProviderScreen extends ConsumerWidget {
  const StateNotifierProviderScreen({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final state = ref.watch(shoppingListProvider);

    return DefaultLayout(
      title: "StateNotifierProvider",
      body: ListView (
          children: state.map(
            (e) => CheckboxListTile(
                title: Text(e.name),
                value: e.hasBought,
                onChanged: (value) {
                  ref.read(shoppingListProvider.notifier).toggleHasBought(name: e.name);
                }
              )
          ).toList()
      ),
    );
  }
}
