
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../base_state_provider.dart';
import '../example_provider.dart';


class BottomSheetPage extends ConsumerWidget {
  const BottomSheetPage({super.key});

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
            title: const Text("BottomSheetPage"),
          ),
          body: Column(
            children: [
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



  void _showLoginModal(BuildContext context) {

    final overlayContext = Navigator.of(context).overlay?.context;
    final statusBarHeight = overlayContext != null
        ? MediaQuery.of(overlayContext).padding.top
        : 0.0;

    print('Status Bar 높이: $statusBarHeight');

    showModalBottomSheet(
      context: context,
      isScrollControlled: true,
      backgroundColor: Colors.white,
      builder: (_) => FractionallySizedBox(
          heightFactor: 1.0,
          child: Column(
            children: [
              // SizedBox(height: statusBarHeight),
              const Text("showModalBottomSheet 1", style: TextStyle(fontSize: 30)),
              const Text("showModalBottomSheet 2", style: TextStyle(fontSize: 30)),

            ],
          )
      ),
      // routeSettings: const RouteSettings(name: '/web-view'),
    );
  }


}
