import 'package:flutter/material.dart';
import 'package:tooka/presentation/another/another_screen.dart';

import '../../app_route.dart';
import '../../core/services/deep_link_service.dart';
import '../main/main_screen.dart';

class SplashScreen extends StatefulWidget {
  const SplashScreen({super.key});

  @override
  State<SplashScreen> createState() => _SplashScreenState();
}

class _SplashScreenState extends State<SplashScreen>
    with SingleTickerProviderStateMixin {
  late AnimationController _animationController;
  late Animation<double> _fadeAnimation;
  late Animation<double> _scaleAnimation;

  @override
  void initState() {
    super.initState();

    print('SplashScreen: initState() ');

    _animationController = AnimationController(
      duration: const Duration(seconds: 2),
      vsync: this,
    );

    _fadeAnimation = Tween<double>(
      begin: 0.0,
      end: 1.0,
    ).animate(CurvedAnimation(
      parent: _animationController,
      curve: Curves.easeIn,
    ));

    _scaleAnimation = Tween<double>(
      begin: 0.5,
      end: 1.0,
    ).animate(CurvedAnimation(
      parent: _animationController,
      curve: Curves.elasticOut,
    ));

    _animationController.forward();

    WidgetsBinding.instance.addPostFrameCallback((_) {
      print('SplashScreen: >>>> addPostFrameCallback');

      DeepLinkService().onAppReady();

      Future.delayed(const Duration(seconds: 3), () {
        if (mounted) {
          print('SplashScreen: >>>> Navigator.push(main)');

          // DeepLinkService().push();

          // Navigator.push(
          //   context,
          //   MaterialPageRoute(
          //     builder: (_) => const MainScreen(),
          //     settings: const RouteSettings(name: '/main'), // 라우트 이름 지정
          //   ),
          // );
          print('SplashScreen: About to navigate to MainScreen');
          final navigatorState = DeepLinkService().navigatorKey.currentState;
          print('SplashScreen: navigatorState = $navigatorState');
          
          if (navigatorState != null) {
            navigatorState.pushReplacement(
              MaterialPageRoute(
                builder: (context) => const MainScreen(),
              ),
            );
            print('SplashScreen: Navigation to MainScreen completed (pushReplacement)');
          } else {
            print('SplashScreen: navigatorState is null!');
          }
          // Navigator.of(context).push<bool>(
          //     MaterialPageRoute(
          //       builder: (_) => MainScreen(),
          //     ),
          // );
          // Navigator.
        }
      });
    });
  }

  @override
  void dispose() {
    print('SplashScreen: dispose() ');

    _animationController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    print('SplashScreen: build() >>>> ');
    DeepLinkService.printStack();

    return Scaffold(
      backgroundColor: Colors.deepPurple,
      body: Center(
        child: AnimatedBuilder(
          animation: _animationController,
          builder: (context, child) {
            return FadeTransition(
              opacity: _fadeAnimation,
              child: ScaleTransition(
                scale: _scaleAnimation,
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    // 앱 아이콘 또는 로고
                    Container(
                      width: 120,
                      height: 120,
                      decoration: BoxDecoration(
                        color: Colors.white,
                        borderRadius: BorderRadius.circular(20),
                        boxShadow: [
                          BoxShadow(
                            color: Colors.black.withValues(alpha: 0.2),
                            blurRadius: 10,
                            offset: const Offset(0, 5),
                          ),
                        ],
                      ),
                      child: const Icon(
                        Icons.flutter_dash,
                        size: 80,
                        color: Colors.deepPurple,
                      ),
                    ),
                    const SizedBox(height: 30),
                    const Text(
                      'Tooka App',
                      style: TextStyle(
                        fontSize: 32,
                        fontWeight: FontWeight.bold,
                        color: Colors.white,
                      ),
                    ),
                    const SizedBox(height: 10),
                    const Text(
                      'Welcome to Flutter',
                      style: TextStyle(
                        fontSize: 16,
                        color: Colors.white70,
                      ),
                    ),
                    const SizedBox(height: 50),
                    const CircularProgressIndicator(
                      valueColor: AlwaysStoppedAnimation<Color>(Colors.white),
                    ),
                  ],
                ),
              ),
            );
          },
        ),
      ),
    );
  }
} 