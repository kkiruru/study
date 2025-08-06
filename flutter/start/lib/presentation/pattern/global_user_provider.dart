import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'user_model.dart';

// 전역 User 상태 관리 Provider
final globalUserProvider = StateNotifierProvider<GlobalUserNotifier, User?>((
  ref,
) {
  return GlobalUserNotifier();
});

class GlobalUserNotifier extends StateNotifier<User?> {
  GlobalUserNotifier() : super(null);

  void setUser(User user) {
    state = user;
  }

  void clearUser() {
    state = null;
  }
}
