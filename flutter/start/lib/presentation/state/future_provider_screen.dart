import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_start/presentation/state/default_layout.dart';

import 'future_provider.dart';

class FutureProviderScreen extends ConsumerWidget {
  const FutureProviderScreen({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final state = ref.watch(multiplesFutureProvider);

    return DefaultLayout(
      title: 'FutureProviderScreen',
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          state.when(
              data: (foo) {
                return Text(
                  foo.toString(),
                  textAlign: TextAlign.center,
                );
              },
              error: (err, stack) => Text(
                err.toString(),
              ),
              loading: () => const Center(
                child: CircularProgressIndicator(),
              )
          )
        ]
      ),
    );
  }
}
