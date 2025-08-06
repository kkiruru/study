import 'package:get_it/get_it.dart';

import '../presentation/pattern/repository/user_repository.dart';
import '../presentation/pattern/repository/share_repository.dart';
import '../presentation/pattern/usecase/fetch_user_usecase.dart';
import '../presentation/pattern/usecase/load_laundry_state_usecase.dart';

final getIt = GetIt.instance;

void setupDependencies() {
  // --- Repository 등록 ---
  // UserRepository라는 타입으로 요청이 오면, 항상 동일한 UserRepositoryImpl 인스턴스를 반환 (싱글톤)
  getIt.registerSingleton<UserRepository>(UserRepositoryImpl());

  // ShareRepository 등록
  getIt.registerSingleton<ShareRepository>(ShareRepositoryImpl());

  // --- UseCase 등록 ---
  // FetchUserUseCase는 생성될 때마다 UserRepository를 필요로 하므로,
  // getIt()을 통해 등록된 UserRepository 인스턴스를 자동으로 주입받아 새로운 인스턴스를 생성
  getIt.registerFactory<FetchUserUseCase>(
    () => FetchUserUseCase(getIt<UserRepository>()),
  );

  // LoadLaundryStateUseCase 등록
  getIt.registerFactory<LoadLaundryStateUseCase>(
    () => LoadLaundryStateUseCase(getIt<ShareRepository>()),
  );
}
