import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'base_state_provider.dart';

final fooProvider = FutureProvider<int>((ref) async {

  final loadingBar = ref.watch(gLoadingStateNotifierProvider.notifier);
  loadingBar.loading(true);
  await Future.delayed(const Duration(seconds: 2));

  loadingBar.loading(false);
  return 1;
});




class LoginNotifier extends AutoDisposeAsyncNotifier<int> {
  @override
  Future<int> build() async {
    return await fetchUser();
  }

  Future<int> fetchUser() async {
    await Future.delayed(const Duration(seconds: 1)); // Simulate network delay
    return 1;
  }
}



final userProvider = AsyncNotifierProvider<UserNotifier, String>(() => UserNotifier());

class UserNotifier extends AsyncNotifier<String> {
  @override
  Future<String> build() async {
    // 초기 데이터 로드
    return await '초기 데이터 로드';
  }

  Future<void> updateProfile(String name) async {
    final loadingBar = ref.watch(gLoadingStateNotifierProvider.notifier);
    loadingBar.loading(true);

    state = const AsyncValue.loading();
    state = await AsyncValue.guard(() async {
      //final updatedUser = await api.updateUser(name);
      await Future.delayed(const Duration(seconds: 2));
      loadingBar.loading(false);
      return 'updatedUser';
    });
  }

  Future<void> bar() async {
    final loadingBar = ref.watch(gLoadingStateNotifierProvider.notifier);
    loadingBar.loading(true);

    state = const AsyncValue.loading();
    state = await AsyncValue.guard(() async {
      //final updatedUser = await api.updateUser(name);
      await Future.delayed(const Duration(seconds: 2));
      loadingBar.loading(false);
      return 'updatedUser';
    });
  }
}