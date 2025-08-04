import 'dart:io';

import 'package:dio/dio.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_start/presentation/state/default_layout.dart';

class LoginScreen extends StatelessWidget {
  const LoginScreen({super.key});

  final emulatorIp = '10.0.2.2:(port)';
  final simulatorIp = '127.0.0.1:(port)';

  @override
  Widget build(BuildContext context) {
    final dio = Dio();
    final ip = Platform.isIOS ? simulatorIp : emulatorIp;

    return DefaultLayout(
      title: "LoginScreen",
      body: Column(children: []),
    );
  }
}
