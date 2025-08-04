part 'base_state.g.dart';

import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:riverpod_annotation/riverpod_annotation.dart';

enum BaseLoading {
  normal, loading
}

@riverpod
Future<BaseLoading> gLoadingStateFuture(Ref ref) async {
  await Future.delayed(const Duration(seconds: 3));
  return BaseLoading.normal;
}

@riverpod
class GStateNotifier extends _$GStateNotifier {
  @override
  BaseLoading build() {
    return BaseLoading.normal;
  }

  loading(bool isLoading) {
    state = isLoading ? BaseLoading.loading : BaseLoading.normal;
  }
} 