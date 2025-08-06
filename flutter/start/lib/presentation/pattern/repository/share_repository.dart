import '../share_model.dart';

abstract class ShareRepository {
  Future<Share> getLaundryState();
}

class ShareRepositoryImpl implements ShareRepository {
  @override
  Future<Share> getLaundryState() async {
    // 실제로는 여기서 http 통신이나 DB 조회를 수행합니다.
    // 예시를 위해 더미 데이터를 반환합니다.
    await Future.delayed(Duration(seconds: 2));
    return Share(
      title: '세탁기 상태',
      content: '세탁이 완료되었습니다.',
      url: 'https://example.com/laundry/status',
      createdAt: DateTime.now(),
    );
  }
}
