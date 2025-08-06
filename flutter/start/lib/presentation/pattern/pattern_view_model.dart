import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'base_view_model.dart';
import 'user_model.dart';
import 'share_model.dart';
import 'usecase/fetch_user_usecase.dart';
import 'usecase/load_laundry_state_usecase.dart';
import 'repository/user_repository.dart';
import 'repository/share_repository.dart';

// autoDispose를 사용하여 페이지 dispose 시 자동 정리
final patternViewModelProvider = ChangeNotifierProvider.autoDispose(
  (ref) => PatternViewModel(
    ref.read(fetchUserUseCaseProvider),
    ref.read(loadLaundryStateUseCaseProvider),
  ),
);

// UseCase Provider들
final fetchUserUseCaseProvider = Provider<FetchUserUseCase>((ref) {
  return FetchUserUseCase(ref.read(userRepositoryProvider));
});

final loadLaundryStateUseCaseProvider = Provider<LoadLaundryStateUseCase>((
  ref,
) {
  return LoadLaundryStateUseCase(ref.read(shareRepositoryProvider));
});

// Repository Provider들
final userRepositoryProvider = Provider<UserRepository>((ref) {
  return UserRepositoryImpl();
});

final shareRepositoryProvider = Provider<ShareRepository>((ref) {
  return ShareRepositoryImpl();
});

class PatternViewModel extends BaseViewModel {
  final FetchUserUseCase _fetchUserUseCase;
  final LoadLaundryStateUseCase _loadLaundryStateUseCase;

  PatternViewModel(this._fetchUserUseCase, this._loadLaundryStateUseCase) {
    // 초기 상태 설정
    _state = PatternState(
      user: null,
      share: null,
      isLoading: false,
      errorMessage: null,
    );
  }

  late PatternState _state;
  PatternState get state => _state;

  // User 상태 업데이트
  Future<void> fetchUser(String id, String password) async {
    _state = _state.copyWith(isLoading: true, errorMessage: null);
    notifyListeners();

    try {
      final user = await _fetchUserUseCase.call(id, password);
      _state = _state.copyWith(user: user, isLoading: false);
      notifyListeners();
    } catch (e) {
      _state = _state.copyWith(errorMessage: e.toString(), isLoading: false);
      notifyListeners();
    }
  }

  // Share 상태 업데이트
  Future<void> loadData() async {
    _state = _state.copyWith(isLoading: true, errorMessage: null);
    notifyListeners();

    try {
      final share = await _loadLaundryStateUseCase.call();
      _state = _state.copyWith(share: share, isLoading: false);
      notifyListeners();
    } catch (e) {
      _state = _state.copyWith(errorMessage: e.toString(), isLoading: false);
      notifyListeners();
    }
  }
}

class PatternState extends BaseState {
  final User? user;
  final Share? share;
  final String? errorMessage;

  PatternState({
    this.user,
    this.share,
    required bool isLoading,
    this.errorMessage,
  });

  PatternState copyWith({
    User? user,
    Share? share,
    bool? isLoading,
    String? errorMessage,
  }) {
    return PatternState(
      user: user ?? this.user,
      share: share ?? this.share,
      isLoading: isLoading ?? this.isLoading,
      errorMessage: errorMessage ?? this.errorMessage,
    );
  }
}
