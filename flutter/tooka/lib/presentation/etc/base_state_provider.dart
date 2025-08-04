import 'package:flutter_riverpod/flutter_riverpod.dart';

enum BaseLoading {
  normal, loading
}

class GLoadingStateNotifier extends StateNotifier<BaseLoading> {
  GLoadingStateNotifier() : super(BaseLoading.normal);

  loading(bool isLoading) {
    state = isLoading ? BaseLoading.loading : BaseLoading.normal;
  }
}

final gLoadingStateNotifierProvider = StateNotifierProvider<GLoadingStateNotifier, BaseLoading>((ref) {
  return GLoadingStateNotifier();
});
