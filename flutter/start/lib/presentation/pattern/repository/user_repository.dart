import '../user_model.dart';

abstract class UserRepository {
  Future<User> getUser(String id, String password);
}

class UserRepositoryImpl implements UserRepository {
  @override
  Future<User> getUser(String id, String password) async {
    // 실제로는 여기서 http 통신이나 DB 조회를 수행합니다.
    // 예시를 위해 더미 데이터를 반환합니다.
    await Future.delayed(Duration(seconds: 1));
    return User(
      id: id,
      name: '홍길동',
      email: 'gildong@example.com',
      phone: '010-1234-5678',
    );
  }
}
