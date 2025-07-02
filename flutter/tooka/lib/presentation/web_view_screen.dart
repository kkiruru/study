import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import '../widgets/common_webview_widget.dart';

class WebViewScreen extends StatelessWidget {
  final String url;

  const WebViewScreen({super.key, required this.url});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(child: CommonWebViewWidget(initialUrl: url)),
    );
  }
}
