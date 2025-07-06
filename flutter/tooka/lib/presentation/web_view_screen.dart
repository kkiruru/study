import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';

import 'widgets/common_webview_widget.dart';

class WebViewScreen extends StatelessWidget {
  final String url;
  final String? title;

  const WebViewScreen({super.key, required this.url, this.title});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: CommonWebViewWidget(
          initialUrl: url,
          onPop: () {
            context.pop();
          },
        ),
      ),
    );
  }
}
