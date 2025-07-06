import 'package:flutter/material.dart';
import '../widgets/common_webview_widget.dart';

class ThirdTabWidget extends StatelessWidget {
  const ThirdTabWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return const CommonWebViewWidget(
      initialUrl: 'https://v2webapp.laundry24.kr/my-page',
    );
  }
} 