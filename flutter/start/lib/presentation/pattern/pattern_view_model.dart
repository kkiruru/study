import 'package:flutter/cupertino.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_start/presentation/pattern/user_vo.dart';

import '../../di/di.dart';
import 'base_view_model.dart';
import 'global_counter_provider.dart';

// autoDispose를 사용하여 페이지 dispose 시 자동 정리
final patternViewModelProvider = ChangeNotifierProvider.autoDispose(
  (ref) => PatternViewModel(ref),
);

class PatternViewModel with ChangeNotifier {
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

    state = state.copyWith(counter: state.counter + 1, isLoading: false);
    notifyListeners();
  }

  // // 전역 counter 증가 메서드
  void incrementCounter() {
    state = state.copyWith(counter: state.counter + 1);
    notifyListeners();
  }

  //
  // 전역 counter 감소 메서드
  void decrementCounter() {
    state = state.copyWith(counter: state.counter - 1);
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


final shareProvider = Provider.autoDispose((ref) {
  final shareRepository = Share();
  return shareRepository;
});

class Share {
  // 공유하는 데이터
}

final viewModelProvider1 = ChangeNotifierProvider.autoDispose<ViewModel1>((ref) {
  var share = ref.watch(shareProvider);
  return ViewModel1(share: share);
});

final viewModelProvider2 = ChangeNotifierProvider.autoDispose<ViewModel2>((ref) {
  var share = ref.watch(shareProvider);
  return ViewModel2(share: share);
});

class ViewModel1 with ChangeNotifier {
  final Share share;

  ViewModel1({required this.share});
}

class ViewModel2 with ChangeNotifier {
  final Share share;

  ViewModel2({required this.share});
}


class User{
  final String id;
  final String name;
  final String email;
  User({
    required this.id,
    required this.name,
    required this.email,
  });
}


abstract class UserRepository {
  Future<User> getUser(String id, String password);
}

// user_repository_impl.dart (구현체)
class UserRepositoryImpl implements UserRepository {
  @override
  Future<User> getUser(String id, String password) async {
    // 실제로는 여기서 http 통신이나 DB 조회를 수행합니다.
    // 예시를 위해 더미 데이터를 반환합니다.
    await Future.delayed(Duration(seconds: 1));
    return User(id: id, name: '홍길동', email: 'gildong@example.com');
  }
}

class GetUserUseCase {
  final UserRepository _repository;

  // 생성자를 통해 외부에서 Repository 구현체를 주입받습니다.
  GetUserUseCase(this._repository);

  // 'call' 메소드를 통해 UseCase를 함수처럼 호출할 수 있게 합니다.
  // 이 안에서 비즈니스 로직을 처리합니다.
  Future<User> call(String id, String password) async {
    if (id.isEmpty) {
      throw Exception('사용자 ID가 비어있습니다.');
    }
    // Repository를 통해 데이터를 가져옵니다.
    // 필요하다면 여기서 데이터를 가공하거나 다른 UseCase와 조합할 수 있습니다.
    return await _repository.getUser(id, password);
  }
}


final userViewModelProvider = ChangeNotifierProvider.autoDispose((ref) {
  return getIt<UserViewModel>();
});


class UserViewModel with ChangeNotifier {
  final GetUserUseCase _getUserUseCase;

  UserViewModel(this._getUserUseCase);

  User? _user;
  User? get user => _user;

  bool _isLoading = false;
  bool get isLoading => _isLoading;

  String? _errorMessage;
  String? get errorMessage => _errorMessage;

  // View로부터 사용자 정보를 가져오라는 요청을 받습니다.
  Future<void> fetchUser(String id, String password) async {
    _isLoading = true;
    _errorMessage = null;
    notifyListeners(); // UI에 로딩 상태 변경을 알림

    try {
      // ViewModel은 직접 Repository를 호출하지 않고,
      // UseCase를 통해 비즈니스 로직을 실행합니다.
      _user = await _getUserUseCase.call(id, password);
    } catch (e) {
      _errorMessage = e.toString();
    } finally {
      _isLoading = false;
      notifyListeners(); // UI에 작업 완료 및 상태 변경을 알림
    }
  }
}



class BaseViewModel with ChangeNotifier {
  final bool isLoading;

  BaseViewModel({required this.isLoading});

  BaseViewModel copyWith({
    bool? isLoading,
  }) => BaseViewModel(
    isLoading: isLoading ?? this.isLoading,
  );

  void startLoading() {
    copyWith(
      isLoading: true
    );
    notifyListeners();
  }

  void stopLoading() {
    copyWith(
        isLoading: true
    );
    notifyListeners();
  }

}


class BazViewModel extends BaseViewModel {
  BazViewModel({required super.isLoading});

  void loadData() async {
    startLoading();

    await Future.delayed(const Duration(seconds: 2));
    stopLoading();
  }
}






