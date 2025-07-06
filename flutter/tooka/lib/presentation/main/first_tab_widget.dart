import 'package:flutter/material.dart';
import '../widgets/common_webview_widget.dart';

class FirstTabWidget extends StatelessWidget {
  const FirstTabWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return const CommonWebViewWidget(
      initialUrl: 'https://v2webapp.laundry24.kr/home',
      title: '네이버',
    );
  }
} 