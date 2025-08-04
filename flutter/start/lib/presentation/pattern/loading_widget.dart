import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

Widget loading(bool isLoading) {
  if (!isLoading) {
    return const SizedBox.shrink();
  }

  return Stack(
    children: [
      Positioned.fill(child: Container(color: Colors.transparent)),
      const Center(child: CircularProgressIndicator()),
    ],
  );
}
