import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_state_bloc/blocs/counter/bloc/counter_bloc.dart';
import 'package:flutter_state_bloc/screens/counter_screen.dart';
import 'package:flutter_state_bloc/screens/home_screen.dart';


void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (context) => CounterBloc(),
      child: MaterialApp(
        title: 'Flutter State BloC',
        routes: {
          '/': (context) => HomeScreen(),
          '/counter': (context) => CounterScreen(),
        },
        initialRoute: '/',
      )
    );
  }
}
