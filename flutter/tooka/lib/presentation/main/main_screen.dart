

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:go_router/go_router.dart';
import 'package:tooka/presentation/main/first_tab_widget.dart';
import 'package:tooka/presentation/main/second_tab_widget.dart';
import 'package:tooka/presentation/main/third_tab_widget.dart';

class MainScreen extends StatefulWidget {
  const MainScreen({super.key});

  @override
  State<MainScreen> createState() => _MainScreenState();
}

class _MainScreenState extends State<MainScreen> {
  int _selectedIndex = 0;

  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return PopScope(
      canPop: false,
      onPopInvokedWithResult: (bool didPop, dynamic result) async {
        if (didPop) return;

        // back 키를 누르면 앱 종료 확인 다이얼로그 표시
        final shouldPop = await showDialog<bool>(
          context: context,
          builder: (context) => AlertDialog(
            title: const Text('앱 종료'),
            content: const Text('정말로 앱을 종료하시겠습니까?'),
            actions: [
              TextButton(
                onPressed: () => Navigator.of(context).pop(false),
                child: const Text('취소'),
              ),
              TextButton(
                onPressed: () {
                  // 디버깅을 위해 현재 라우트 스택 상태 확인
                  print('Current route stack: ${GoRouter.of(context).routerDelegate.currentConfiguration.matches}');
                  print('Can pop: ${context.canPop()}');

                  // 강제로 앱 종료
                  SystemNavigator.pop();
                },
                child: const Text('종료'),
              ),
            ],
          ),
        ) ?? false;

        if (shouldPop && context.mounted) {
          Navigator.of(context).pop();
        }
      },
      child: Scaffold(
        body: SafeArea(
          child: IndexedStack(
            index: _selectedIndex,
            children: const [
              FirstTabWidget(),
              SecondTabWidget(),
              ThirdTabWidget(),
            ],
          ),
        ),
        bottomNavigationBar: BottomNavigationBar(
          items: const <BottomNavigationBarItem>[
            BottomNavigationBarItem(
              icon: Icon(Icons.home),
              label: 'First',
            ),
            BottomNavigationBarItem(
              icon: Icon(Icons.business),
              label: 'Second',
            ),
            BottomNavigationBarItem(
              icon: Icon(Icons.school),
              label: 'Third',
            ),
          ],
          currentIndex: _selectedIndex,
          selectedItemColor: Colors.deepPurple,
          onTap: _onItemTapped,
        ),
      ),
    );
  }
}
