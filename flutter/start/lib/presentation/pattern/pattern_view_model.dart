import 'package:flutter/cupertino.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'base_view_model.dart';

final patternViewModelProvider = ChangeNotifierProvider(
  (ref) => PatternViewModel(),
);

class PatternViewModel extends ChangeNotifier {
  PatternState state = PatternState(
    counter: 0,
    name: 'kkiruru',
    isLoading: false,
  );

  void loadData() async {
    // 로딩 시작
    state = state.copyWith(isLoading: true);
    notifyListeners();

    // 3초 대기
    await Future.delayed(const Duration(seconds: 2));

    // 데이터 업데이트
    state = state.copyWith(
      isLoading: false,
      counter: state.counter + 1,
      name: 'kkiruru ${state.counter + 1}', // counter + 1을 사용해야 함
    );
    notifyListeners();
  }
}

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

  PatternState copyWith({
    String? name,
    int? counter,
    String? url,
    bool? isLoading,
  }) => PatternState(
    name: name ?? this.name,
    counter: counter ?? this.counter,
    url: url ?? this.url,
    isLoading: isLoading ?? this.isLoading,
  );
}
