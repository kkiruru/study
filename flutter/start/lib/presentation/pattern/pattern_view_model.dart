import 'package:flutter/cupertino.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';




class PatternState extends BasePatternState {
  final int counter;
  final String name;
  final String? url;

  PatternState({
    required this.counter,
    required this.name,
    this.url,
    required super.isLoading,
  });

  PatternState copyWith({String? name, int? counter, String? url, bool? isLoading}) => PatternState(
    name: name ?? this.name,
    counter: counter ?? this.counter,
    url: url ?? this.url,
    isLoading: isLoading ?? this.isLoading,
  );

}


abstract class BasePatternState {
  final bool isLoading;
  BasePatternState({
    required this.isLoading,
  });
}


final patternViewModelProvider = ChangeNotifierProvider((ref) => PatternViewModel());


class PatternViewModel extends ChangeNotifier {
  PatternState state = PatternState(
      counter: 0,
      name: 'kkiruru',
      isLoading: false,
      );


  void loadData() async {
    state.copyWith(
        isLoading: true
    );
    await Future.delayed(const Duration(seconds: 3));
    state.copyWith(
      isLoading: false,
      counter: state.counter + 1,
      name: 'kkiruru $state.counter',
    );

  }
}