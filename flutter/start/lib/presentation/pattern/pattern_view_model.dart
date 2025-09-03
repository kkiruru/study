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
    _state = PatternState(share: null, errorMessage: null);
  }

  late PatternState _state;
  PatternState get state => _state;

  // 전역 User 상태 가져오기
  User? get user => _ref.read(globalUserProvider);

  // User 상태 업데이트 (전역 상태로 관리)
  Future<void> fetchUser(String id, String password) async {
    _state = _state.copyWith(errorMessage: null);
    notifyListeners();

    try {
      startLoading();
      print('____ startLoading()');
      final user = await _fetchUserUseCase.call(id, password);
      _ref.read(globalUserProvider.notifier).setUser(user);
      _ref.read(patternEventProvider.notifier).emitEvent(
        UserFetchSuccess(user),
      );
    } catch (e) {
      _state = _state.copyWith(errorMessage: e.toString());
      // notifyListeners();
    } finally {
      stopLoading();
      print('____ stopLoading()');
    }
  }

  // Share 상태 업데이트 (로컬 상태로 관리 - 페이지 dispose 시 사라짐)
  Future<void> loadData() async {
    _state = _state.copyWith(errorMessage: null);
    // notifyListeners();
    try {
      startLoading();
      print('____ startLoading()');
      final share = await _loadLaundryStateUseCase.call();
      _state = _state.copyWith(share: share);
      // notifyListeners();
    } catch (e) {
      _state = _state.copyWith(errorMessage: e.toString());
      // notifyListeners();
    } finally {
      stopLoading();
      print('____ stopLoading()');
    }
  }
}

class PatternState  {
  final Share? share;
  final String? errorMessage;

  PatternState({this.share, this.errorMessage});

  PatternState copyWith({Share? share, String? errorMessage}) {
    return PatternState(
      share: share ?? this.share,
      errorMessage: errorMessage ?? this.errorMessage,
    );
  }
}


sealed class PatternEvent {
  const PatternEvent();
}

class UserFetchSuccess extends PatternEvent {
  final User user;
  const UserFetchSuccess(this.user);
}

class UserFetchError extends PatternEvent {
  final String error;
  const UserFetchError(this.error);
}

class LoadDataSuccess extends PatternEvent {
  final Share share;
  const LoadDataSuccess(this.share);
}

class LoadDataError extends PatternEvent {
  final String error;
  const LoadDataError(this.error);
}

// 이벤트를 관리하는 StateNotifier
class PatternEventNotifier extends StateNotifier<PatternEvent?> {
  PatternEventNotifier() : super(null);

  void emitEvent(PatternEvent event) {
    state = event;
    // 이벤트를 즉시 소비하도록 null로 리셋
    Future.microtask(() => state = null);
  }
}

final patternEventProvider = StateNotifierProvider<PatternEventNotifier, PatternEvent?>((ref) {
  return PatternEventNotifier();
});