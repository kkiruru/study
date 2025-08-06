import '../repository/share_repository.dart';
import '../share_model.dart';

class LoadLaundryStateUseCase {
  final ShareRepository _repository;

  LoadLaundryStateUseCase(this._repository);

  Future<Share> call() async {
    // Repository를 통해 세탁기 상태 데이터를 가져옵니다.
    return await _repository.getLaundryState();
  }
}
