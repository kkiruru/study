import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class DefaultLayout extends StatelessWidget{

  final String title;
  final Widget body;
  final List<Widget>? actions;

  const DefaultLayout({
    super.key,
    required this.title,
    required this.body,
    this.actions
  });

  @override
  Widget build(BuildContext context) {

    return Scaffold(
      appBar: AppBar(
        title: Text(title),
          actions: actions,
      ),
      body: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16),
        child: body,
      )
    );
  }
}


class StateProviderScreen extends StatelessWidget {
  const StateProviderScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return DefaultLayout(
        title: "StateProviderScreen",
        body: ListView(
          children: const [],
        )
    );
  }
}