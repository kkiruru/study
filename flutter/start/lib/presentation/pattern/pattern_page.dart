import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_start/presentation/pattern/pattern_view_model.dart';
import 'package:flutter_start/presentation/pattern/profile_page.dart';
import 'package:flutter_start/presentation/pattern/global_user_provider.dart';

import 'base_page_widget.dart';

class PatternPage extends ConsumerWidget {
  const PatternPage({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    var viewModel = ref.watch(patternViewModelProvider);
    var user = ref.watch(globalUserProvider); // 전역 User 상태 관찰

    // 이벤트 리스닝
    ref.listen<PatternEvent?>(patternEventProvider, (previous, next) {
      if (next != null) {
        switch (next) {
          case UserFetchSuccess(user: final user):
            ScaffoldMessenger.of(context).showSnackBar(
              SnackBar(
                content: Text('사용자 정보 가져오기 성공: ${user.name}'),
                backgroundColor: Colors.green,
              ),
            );
          case UserFetchError(error: final error):
            ScaffoldMessenger.of(context).showSnackBar(
              SnackBar(
                content: Text('사용자 정보 가져오기 실패: $error'),
                backgroundColor: Colors.red,
              ),
            );
          case LoadDataSuccess(share: final share):
            ScaffoldMessenger.of(context).showSnackBar(
              SnackBar(
                content: Text('세탁기 상태 가져오기 성공: ${share.title}'),
                backgroundColor: Colors.green,
              ),
            );
          case LoadDataError(error: final error):
            ScaffoldMessenger.of(context).showSnackBar(
              SnackBar(
                content: Text('세탁기 상태 가져오기 실패: $error'),
                backgroundColor: Colors.red,
              ),
            );
        }
      }
    });

    return basePage(
      Scaffold(
        appBar: AppBar(title: Text("MVVM Pattern")),
        body: Padding(
          padding: EdgeInsets.symmetric(horizontal: 16, vertical: 16),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              // User 정보 표시 (전역 상태)
              Card(
                child: Padding(
                  padding: EdgeInsets.all(16),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        'User 정보 (전역 상태)',
                        style: TextStyle(
                          fontSize: 18,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                      SizedBox(height: 8),
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
              SizedBox(height: 16),

              // Share 정보 표시 (로컬 상태 - 페이지 dispose 시 사라짐)
              Card(
                child: Padding(
                  padding: EdgeInsets.all(16),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        'Share 정보 (로컬 상태)',
                        style: TextStyle(
                          fontSize: 18,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                      SizedBox(height: 8),
                      if (viewModel.state.share != null) ...[
                        Text('제목: ${viewModel.state.share!.title}'),
                        Text('내용: ${viewModel.state.share!.content}'),
                        Text('URL: ${viewModel.state.share!.url}'),
                        Text(
                          '생성일: ${viewModel.state.share!.createdAt.toString()}',
                        ),
                      ] else ...[
                        Text(
                          '공유 정보가 없습니다.',
                          style: TextStyle(color: Colors.grey),
                        ),
                      ],
                    ],
                  ),
                ),
              ),
              SizedBox(height: 16),

              // 에러 메시지 표시
              if (viewModel.state.errorMessage != null) ...[
                Container(
                  padding: EdgeInsets.all(12),
                  decoration: BoxDecoration(
                    color: Colors.red.shade100,
                    borderRadius: BorderRadius.circular(8),
                  ),
                  child: Text(
                    '에러: ${viewModel.state.errorMessage}',
                    style: TextStyle(color: Colors.red.shade800),
                  ),
                ),
                SizedBox(height: 16),
              ],

              // 버튼들
              ElevatedButton(
                onPressed: viewModel.isLoading
                    ? null
                    : () {
                        viewModel.fetchUser('user123', 'password123');
                      },
                style: ElevatedButton.styleFrom(
                  padding: const EdgeInsets.symmetric(
                    horizontal: 30,
                    vertical: 15,
                  ),
                ),
                child: Text(
                  viewModel.isLoading ? '로딩 중...' : '사용자 정보 가져오기',
                  style: TextStyle(fontSize: 16),
                ),
              ),
              SizedBox(height: 8),
              ElevatedButton(
                onPressed: viewModel.isLoading
                    ? null
                    : () {
                        viewModel.loadData();
                      },
                style: ElevatedButton.styleFrom(
                  padding: const EdgeInsets.symmetric(
                    horizontal: 30,
                    vertical: 15,
                  ),
                ),
                child: Text(
                  viewModel.isLoading ? '로딩 중...' : '세탁기 상태 가져오기',
                  style: TextStyle(fontSize: 16),
                ),
              ),
              const SizedBox(height: 8),
              ElevatedButton(
                onPressed: () {
                  Navigator.of(context).push<bool>(
                    CupertinoPageRoute(builder: (_) => const ProfilePage()),
                  );
                },
                style: ElevatedButton.styleFrom(
                  padding: const EdgeInsets.symmetric(
                    horizontal: 30,
                    vertical: 15,
                  ),
                ),
                child: const Text(
                  'ProfilePage',
                  style: TextStyle(fontSize: 16),
                ),
              ),
            ],
          ),
        ),
      ),
      viewModel: viewModel,
    );
  }
}
