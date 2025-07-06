import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:go_router/go_router.dart';

import 'widgets/common_webview_widget.dart';

class WebViewScreen extends StatelessWidget {
  final String url;
  final String? title;
  final Color? backgroundColor;
  const WebViewScreen({super.key, required this.url, this.title, this.backgroundColor = Colors.white});

  @override
  Widget build(BuildContext context) {
    return AnnotatedRegion<SystemUiOverlayStyle>(
      value: SystemUiOverlayStyle(
        // Status bar 스타일 설정
        statusBarColor: backgroundColor, // 투명 배경
        statusBarIconBrightness: Brightness.dark, // 다크 아이콘 (iOS)
        statusBarBrightness: Brightness.light, // 라이트 배경 (Android)
        // Navigation bar 스타일 설정
        systemNavigationBarColor: backgroundColor, // 네비게이션 바 투명
        systemNavigationBarIconBrightness: Brightness.dark, // 네비게이션 아이콘 다크
        systemNavigationBarDividerColor: Colors.transparent, // 네비게이션 바 구분선 투명
      ),
      child: Scaffold(
        backgroundColor: backgroundColor,
        // SafeArea로 감싸서 status bar와 navigation bar 영역 모두 보호
        body: SafeArea(
          // bottom: false로 설정하면 하단 네비게이션 바 영역은 SafeArea에서 제외
          // 대신 전체 SafeArea를 사용하여 모든 안전하지 않은 영역을 피함
          child: CommonWebViewWidget(
            initialUrl: url,
            onPop: () {
              context.pop();
            },
          ),
        ),
      ),
    );
  }
}
