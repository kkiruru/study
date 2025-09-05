import 'package:flutter/cupertino.dart';

import '../../utils/command.dart';
import '../../utils/result.dart';
import '../pattern/repository/user_repository.dart';
import '../pattern/user_model.dart';

class HomeViewModel extends BaseViewModel {
  HomeViewModel({
    required UserRepository userRepository,
  }) : _userRepository = userRepository {
    fetchUser = Command0(_fetchUser);
    _load();
  }

  final UserRepository _userRepository;
  User? _user;

  late Command0 fetchUser;
  User? get user => _user;

  Future<Result> _load() async {
    print('[HomeViewModel] _load');
    _startLoading();
    try {
      final userResult = await _userRepository.getUser2();
      switch (userResult) {
        case Ok<User>():
          _user = userResult.value;
        case Error<User>():

      }
      return userResult;
    } finally {
      _stopLoading();
      notifyListeners();
    }
  }

  Future<Result> _fetchUser() async {
    print('[HomeViewModel] fetch');

    _loading(true);
    try {
      final userResult = await _userRepository.fetchUser();
      switch (userResult) {
        case Ok<User>():
          _user = userResult.value;
        case Error<User>():

      }
      return userResult;
    } finally {
      _loading(false);
      notifyListeners();
    }
  }
}


abstract class BaseViewModel extends ChangeNotifier {
  bool loading = false;

  void _startLoading() {
    print('[HomeViewModel] _startLoading');
    _loading(true);
  }

  void _stopLoading() {
    print('[HomeViewModel] _stopLoading');
    _loading(false);
  }

  void _loading(bool loading) {
    print('[HomeViewModel] _loading : ${loading}');
    this.loading = loading;
    notifyListeners();
  }
}