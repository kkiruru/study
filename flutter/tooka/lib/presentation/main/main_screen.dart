import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:go_router/go_router.dart';
import 'package:tooka/presentation/main/first_tab_widget.dart';
import 'package:tooka/presentation/main/second_tab_widget.dart';
import 'package:tooka/presentation/main/third_tab_widget.dart';

import '../../app_route.dart';

class MainScreen extends StatefulWidget {

  final String? initializeTabName;
  const MainScreen({super.key, this.initializeTabName});


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
  void initState() {
    super.initState();
    print('MainScreen initState ${widget.initializeTabName} : ${_selectedIndex}');
    _updateTabFromName(widget.initializeTabName);
  }


  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    // 라우트 파라미터가 변경되었는지 확인
    final currentTabName = GoRouterState.of(context).uri.queryParameters['tab'];
    print('MainScreen didChangeDependencies ${currentTabName}');

    _updateTabFromName(currentTabName);
  }


  void _updateTabFromName(String? tabName) {
    print('MainScreen _updateTabFromName: $tabName');
    
    switch (tabName) {
      case 'first':
        _selectedIndex = 0;
        break;
      case 'second':
        _selectedIndex = 1;
        break;
      case 'third':
        _selectedIndex = 2;
        break;
      default:
        _selectedIndex = 0;
        break;
    }

    print('MainScreen _updateTabFromName:  [${_selectedIndex}] $tabName');
    if (mounted) {
      setState(() {});
    }
  }


  @override
  Widget build(BuildContext context) {
    print('MainScreen: build() >>>> ');
    AppRouter.printStack();

    return AnnotatedRegion<SystemUiOverlayStyle>(
      value: const SystemUiOverlayStyle(
        // 투명한 status bar 설정
        statusBarColor: Colors.transparent, // Status bar 배경을 투명하게
        statusBarIconBrightness: Brightness.dark, // Android: 다크 아이콘
        statusBarBrightness: Brightness.light, // iOS: 라이트 배경 (다크 아이콘)
        systemNavigationBarColor: Colors.transparent, // 네비게이션 바 투명
        systemNavigationBarIconBrightness: Brightness.dark, // 네비게이션 아이콘 다크
      ),
      child: PopScope(
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
            top: false, // Status bar 영역을 SafeArea에서 제외 (투명하므로)
            bottom: true,
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
      ),
    );
  }

  @override
  void dispose() {
    print('MainScreen: dispose()');
    super.dispose();
  }
}
