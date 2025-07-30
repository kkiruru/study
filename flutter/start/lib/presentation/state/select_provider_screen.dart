import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_start/presentation/state/select_provider.dart';

import 'default_layout.dart';

class SelectProviderScreen extends ConsumerWidget {
  const SelectProviderScreen({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {

    print('SelectProviderScreen: build');

    final state = ref.watch(selectProvider.select((value) => value.isSpicy));

    ref.listen(selectProvider.select((value) => value.hasBought), (previous, next) {
      print('hasBought: $next');
    });

    return DefaultLayout(
      title: "SelectProviderScreen",
      body: SizedBox(
        width: double.infinity,
        child: Column(
          children: [
            Text(state.toString()),
            // Text(state.name),
            // Text(state.isSpicy.toString()),
            // Text(state.hasBought.toString()),
            ElevatedButton(
              onPressed: () {
                ref.read(selectProvider.notifier).toggleIsSpicy();
              },
              child: Text("toggleIsSpicy"),
            ),
            ElevatedButton(
              onPressed: () {
                ref.read(selectProvider.notifier).toggleHasBought();
              },
              child: Text("toggleHasBought"),
            ),
          ]
        )
      )
    );
  }
}