import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'base_view_model.dart';
import 'user_model.dart';
import 'share_model.dart';
import 'usecase/fetch_user_usecase.dart';
import 'usecase/load_laundry_state_usecase.dart';
import 'repository/user_repository.dart';
import 'repository/share_repository.dart';
import 'global_user_provider.dart';

// autoDispose를 사용하여 페이지 dispose 시 자동 정리 (Share 데이터만)
final patternViewModelProvider = ChangeNotifierProvider.autoDispose(
  (ref) => PatternViewModel(
    ref.read(fetchUserUseCaseProvider),
    ref.read(loadLaundryStateUseCaseProvider),
    ref,
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
  final Ref _ref;

  PatternViewModel(
    this._fetchUserUseCase,
    this._loadLaundryStateUseCase,
    this._ref,
  ) {
    // 초기 상태 설정 (Share만 로컬 상태로 관리)
    _state = PatternState(share: null, isLoading: false, errorMessage: null);
  }

  late PatternState _state;
  PatternState get state => _state;

  // 전역 User 상태 가져오기
  User? get user => _ref.read(globalUserProvider);

  // User 상태 업데이트 (전역 상태로 관리)
  Future<void> fetchUser(String id, String password) async {
    _state = _state.copyWith(isLoading: true, errorMessage: null);
    notifyListeners();

    try {
      final user = await _fetchUserUseCase.call(id, password);
      // 전역 User 상태 업데이트
      _ref.read(globalUserProvider.notifier).setUser(user);
      _state = _state.copyWith(isLoading: false);
      notifyListeners();
    } catch (e) {
      _state = _state.copyWith(errorMessage: e.toString(), isLoading: false);
      notifyListeners();
    }
  }

  // Share 상태 업데이트 (로컬 상태로 관리 - 페이지 dispose 시 사라짐)
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
  final Share? share;
  final String? errorMessage;

  PatternState({this.share, required bool isLoading, this.errorMessage});

  PatternState copyWith({Share? share, bool? isLoading, String? errorMessage}) {
    return PatternState(
      share: share ?? this.share,
      isLoading: isLoading ?? this.isLoading,
      errorMessage: errorMessage ?? this.errorMessage,
    );
  }
}
