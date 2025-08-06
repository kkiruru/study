import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_start/presentation/pattern/pattern_view_model.dart';
import 'package:flutter_start/presentation/pattern/global_user_provider.dart';

import 'base_page_widget.dart';

class ProfilePage extends ConsumerWidget {
  const ProfilePage({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final userViewModel = ref.watch(patternViewModelProvider);
    final user = ref.watch(globalUserProvider); // 전역 User 상태 관찰

    return basePage(
      Scaffold(
        appBar: AppBar(title: const Text("Profile")),
        body: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 16),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              Card(
                child: Padding(
                  padding: EdgeInsets.all(16),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        'Profile Page - 전역 User 상태',
                        style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                      ),
                      SizedBox(height: 16),
                      if (user != null) ...[
                        Text('ID: ${user.id}'),
                        Text('이름: ${user.name}'),
                        Text('이메일: ${user.email}'),
                        Text('전화번호: ${user.phone}'),
                      ] else ...[
                        Text(
                          '사용자 정보가 없습니다.',
                          style: TextStyle(color: Colors.grey),
                        ),
                      ],
                    ],
                  ),
                ),
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
      viewModel: userViewModel.state,
    );
  }
}
