import 'package:flutter/cupertino.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_start/presentation/pattern/user_vo.dart';

import 'base_view_model.dart';
import 'global_counter_provider.dart';

// autoDispose를 사용하여 페이지 dispose 시 자동 정리
final patternViewModelProvider = ChangeNotifierProvider.autoDispose(
  (ref) => PatternViewModel(ref),
);

class PatternViewModel extends ChangeNotifier {
  final Ref _ref;
  late PatternState state;

  PatternViewModel(this._ref) {
    // 전역 counter 값으로 초기화
    _ref.listen(globalUserProvider, (previous, next) {
      // 전역 counter가 변경되면 로컬 상태도 업데이트
      if (previous != next) {
        state = state.copyWith(userVo: next);
        notifyListeners();
      }
    });
    state = PatternState(
      counter: 0,
      isLoading: false,
      userVo: _ref.read(globalUserProvider.notifier).state,
    );
  }

  void loadData() async {
    // 로딩 시작
    state = state.copyWith(isLoading: true);
    notifyListeners();

    // 3초 대기
    await Future.delayed(const Duration(seconds: 2));

    state = state.copyWith(
      counter: state.counter + 1,
      isLoading: false,
    );
    notifyListeners();
  }

  // // 전역 counter 증가 메서드
  void incrementCounter() {
    state = state.copyWith(
      counter: state.counter + 1,
    );
    notifyListeners();
  }
  //
  // 전역 counter 감소 메서드
  void decrementCounter() {
    state = state.copyWith(
      counter: state.counter - 1,
    );
    notifyListeners();
  }
}

class PatternState extends BasePatternState {
  final int counter;
  final String? url;
  final UserVo? userVo;

  PatternState({
    required this.counter,
    this.url,
    required super.isLoading,
    this.userVo,
  });

  PatternState copyWith({
    int? counter,
    String? url,
    bool? isLoading,
    UserVo? userVo,
  }) => PatternState(
    counter: counter ?? this.counter,
    url: url ?? this.url,
    isLoading: isLoading ?? this.isLoading,
    userVo: userVo ?? this.userVo,
  );
}
