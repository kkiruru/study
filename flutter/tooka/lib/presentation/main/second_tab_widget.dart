import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import '../../app_route.dart';
import '../../core/services/deep_link_service.dart';
import '../another/another_screen.dart';

class SecondTabWidget extends StatelessWidget {
  const SecondTabWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          const Text(
            'Second Tab',
            style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
          ),
          const SizedBox(height: 20),
          const Text(
            'This is the second tab content',
            style: TextStyle(fontSize: 16),
          ),
          const SizedBox(height: 30),
          SingleChildScrollView(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                ElevatedButton(
                  // lib/presentation/main/second_tab_widget.dart
                  onPressed: () {
                    print('SecondTabWidget: Navigate to Another button pressed');
                    final navigatorState = DeepLinkService().navigatorKey.currentState;
                    print('SecondTabWidget: navigatorState = $navigatorState');
                    print('SecondTabWidget: navigatorState.mounted = ${navigatorState?.mounted}');

                    if (navigatorState != null && navigatorState.mounted) {
                      print('SecondTabWidget: Attempting to push AnotherScreen...');
                      navigatorState.push(
                        MaterialPageRoute(
                          builder: (context) => const AnotherScreen(), // 단순화된 AnotherScreen 사용
                        ),
                      ).then((_) {
                        print('SecondTabWidget: Navigation to AnotherScreen push completed (then)');
                      }).catchError((error) {
                        print('SecondTabWidget: Navigation to AnotherScreen FAILED with error: $error');
                      });
                      print('SecondTabWidget: Navigation to AnotherScreen push initiated');
                    } else {
                      print('SecondTabWidget: navigatorState is null or not mounted!');
                    }
                  },
                    // ...

                  // onPressed: () {
                  //   print('SecondTabWidget: Navigate to Another button pressed');
                  //   final navigatorState = DeepLinkService().navigatorKey.currentState;
                  //   print('SecondTabWidget: navigatorState = $navigatorState');
                  //   if (navigatorState != null) {
                  //     navigatorState.push(
                  //       MaterialPageRoute(
                  //         builder: (context) => const AnotherScreen(),
                  //       ),
                  //     );
                  //     print('SecondTabWidget: Navigation to AnotherScreen completed (pushReplacement)');
                  //   } else {
                  //     print('SecondTabWidget: navigatorState is null!');
                  //   }
                  //
                  //
                  // },
                  style: ElevatedButton.styleFrom(
                    padding: const EdgeInsets.symmetric(
                      horizontal: 30,
                      vertical: 15,
                    ),
                  ),
                  child: const Text(
                    'Navigate to Another',
                    style: TextStyle(fontSize: 16),
                  ),
                ),
                const SizedBox(height: 30),
                ElevatedButton(
                  onPressed: () {
                    AppRouter.navigateToAnother(context);
                  },
                  style: ElevatedButton.styleFrom(
                    padding: const EdgeInsets.symmetric(
                      horizontal: 30,
                      vertical: 15,
                    ),
                  ),
                  child: const Text(
                    'Navigate to Another (Replace)',
                    style: TextStyle(fontSize: 16),
                  ),
                ),
                const SizedBox(height: 30),
                ElevatedButton(
                  onPressed: () {
                    Navigator.of(context).push<bool>(
                      CupertinoPageRoute(builder: (_) => const AnotherScreen()),
                    );
                  },
                  style: ElevatedButton.styleFrom(
                    padding: const EdgeInsets.symmetric(
                      horizontal: 30,
                      vertical: 15,
                    ),
                  ),
                  child: const Text(
                    'Navigator.push AnotherScreen',
                    style: TextStyle(fontSize: 16),
                  ),
                ),
                const SizedBox(height: 20),
                ElevatedButton(
                  onPressed: () {
                    // 탭 변경은 부모 위젯에서 처리해야 함
                    // 여기서는 단순히 메시지만 출력
                    print('Navigate to third tab');
                  },
                  style: ElevatedButton.styleFrom(
                    padding: const EdgeInsets.symmetric(
                      horizontal: 30,
                      vertical: 15,
                    ),
                  ),
                  child: const Text(
                    'Navigate to Third Tab',
                    style: TextStyle(fontSize: 16),
                  ),
                ),
                const SizedBox(height: 30),
                ElevatedButton(
                  onPressed: () {
                    AppRouter.navigateToFoo(context);
                  },
                  style: ElevatedButton.styleFrom(
                    padding: const EdgeInsets.symmetric(
                      horizontal: 30,
                      vertical: 15,
                    ),
                  ),
                  child: const Text(
                    'Navigate to Foo',
                    style: TextStyle(fontSize: 16),
                  ),
                ),
                ElevatedButton(
                  onPressed: () {
                    AppRouter.navigateToFoo(context);
                  },
                  style: ElevatedButton.styleFrom(
                    padding: const EdgeInsets.symmetric(
                      horizontal: 30,
                      vertical: 15,
                    ),
                  ),
                  child: const Text(
                    'Push to Foo',
                    style: TextStyle(fontSize: 16),
                  ),
                ),
                const SizedBox(height: 30),
                ElevatedButton(
                  onPressed: () {
                    AppRouter.navigateToAnother(context);
                  },
                  style: ElevatedButton.styleFrom(
                    padding: const EdgeInsets.symmetric(
                      horizontal: 30,
                      vertical: 15,
                    ),
                  ),
                  child: const Text(
                    'Navigate to Another',
                    style: TextStyle(fontSize: 16),
                  ),
                ),
                ElevatedButton(
                  onPressed: () {
                    AppRouter.navigateToAnother(context);
                  },
                  style: ElevatedButton.styleFrom(
                    padding: const EdgeInsets.symmetric(
                      horizontal: 30,
                      vertical: 15,
                    ),
                  ),
                  child: const Text(
                    'Push to Another',
                    style: TextStyle(fontSize: 16),
                  ),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
