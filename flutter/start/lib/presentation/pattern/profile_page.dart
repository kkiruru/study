import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_start/presentation/pattern/pattern_view_model.dart';

import 'base_page_widget.dart';

class ProfilePage extends ConsumerWidget {
  const ProfilePage({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {

    final userViewModel = ref.watch(userViewModelProvider);

    return basePage(
      Scaffold(
        appBar: AppBar(title: const Text("Profile")),
        body: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 16),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              Text(
                userViewModel.user?.name ?? '사용자 정보 없음',
                style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
              ),
              const SizedBox(height: 16),
              ElevatedButton(
                onPressed: () => userViewModel.fetchUser('01087199467', '1234'),
                style: ElevatedButton.styleFrom(
                  padding: const EdgeInsets.symmetric(
                    horizontal: 30,
                    vertical: 15,
                  ),
                ),
                child: const Text(
                  'Load Data', 
                  style: TextStyle(fontSize: 16)
                ),
              ),
            ],
          ),
        ),
      ),
        viewModel: userViewModel
      // BasePatternState(isLoading: userViewModel.isLoading),
    );
  }
}
