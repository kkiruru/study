import 'package:flutter/material.dart';

import 'home_view_model.dart';

class MvvmPage extends StatelessWidget {
  const MvvmPage({super.key, required this.viewModel});

  final HomeViewModel viewModel;

  @override
  Widget build(BuildContext context) {
    print('[MvvmPage] build');
    return Scaffold(
      backgroundColor: Color.fromARGB(255, 155, 155, 255),
      body: SafeArea(
        top: true,
        bottom: true,
        child: ListenableBuilder(
          listenable: viewModel,
          builder: (context, _) {
            return Stack(
              fit: StackFit.expand,
              children: [
                Column(
                  children: [
                    Text('mvvm', style: TextStyle(fontSize: 20)),
                    Text(viewModel.user?.name ?? '로그인 필요', style: TextStyle(fontSize: 20)),
                    FilledButton(
                      style: FilledButton.styleFrom(
                        fixedSize: Size(double.infinity, 20), // 너비는 최대, 높이는 20
                      ),
                      onPressed: viewModel.fetchUser.execute,
                      child: Text("loading"),
                    ),
                  ],
                ),
                _loading(viewModel.loading),
              ],
            );
          },
        ),
      ),
    );
  }

  Widget _loading(bool isLoading) {
    print('_loading ${isLoading}');

    if (!isLoading) {
      return const SizedBox.shrink();
    }

    return Stack(
      children: [
        Positioned.fill(child: Container(color: Colors.transparent)),
        const Center(child: CircularProgressIndicator()),
      ],
    );
  }
}
