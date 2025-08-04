import 'package:flutter/cupertino.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'base_view_model.dart';
import 'global_counter_provider.dart';

// autoDispose를 사용하여 페이지 dispose 시 자동 정리
final patternViewModelProvider = ChangeNotifierProvider.autoDispose(
  (ref) => PatternViewModel(ref),
);

class PatternViewModel extends ChangeNotifier {
  final Ref _ref;

  PatternViewModel(this._ref) {
    // 전역 counter 값으로 초기화
    _ref.listen(globalCounterProvider, (previous, next) {
      // 전역 counter가 변경되면 로컬 상태도 업데이트
      if (previous != next) {
        state = state.copyWith(counter: next);
        notifyListeners();
      }
    });
  }

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

    // 전역 counter 증가
    _ref.read(globalCounterProvider.notifier).state++;

    // 로컬 상태 업데이트
    state = state.copyWith(
      isLoading: false,
      name: 'kkiruru ${_ref.read(globalCounterProvider)}',
    );
    notifyListeners();
  }

  // 전역 counter 증가 메서드
  void incrementGlobalCounter() {
    _ref.read(globalCounterProvider.notifier).state++;
  }

  // 전역 counter 감소 메서드
  void decrementGlobalCounter() {
    _ref.read(globalCounterProvider.notifier).state--;
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
