import '../repository/user_repository.dart';
import '../user_model.dart';

class FetchUserUseCase {
  final UserRepository _repository;

  FetchUserUseCase(this._repository);

  Future<User> call(String id, String password) async {
    if (id.isEmpty) {
      throw Exception('사용자 ID가 비어있습니다.');
    }
    if (password.isEmpty) {
      throw Exception('비밀번호가 비어있습니다.');
    }

    // Repository를 통해 데이터를 가져옵니다.
    return await _repository.getUser(id, password);
  }
}
