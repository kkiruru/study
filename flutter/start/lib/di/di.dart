import 'package:get_it/get_it.dart';

import '../presentation/pattern/pattern_view_model.dart';

final getIt = GetIt.instance;

void setupDependencies() {
  // --- Repository 등록 ---
  // UserRepository라는 타입으로 요청이 오면, 항상 동일한 UserRepositoryImpl 인스턴스를 반환 (싱글톤)
  getIt.registerSingleton<UserRepository>(UserRepositoryImpl());

  // --- UseCase 등록 ---
  // GetUserUseCase는 생성될 때마다 UserRepository를 필요로 하므로,
  // getIt()을 통해 등록된 UserRepository 인스턴스를 자동으로 주입받아 새로운 인스턴스를 생성
  getIt.registerFactory<GetUserUseCase>(
        () => GetUserUseCase(getIt<UserRepository>()),
  );

  // --- ViewModel 등록 ---
  // UserViewModel도 생성될 때마다 GetUserUseCase를 필요로 함
  // getIt<GetUserUseCase>()를 통해 UseCase 인스턴스를 주입받아 생성
  getIt.registerFactory<UserViewModel>(
        () => UserViewModel(getIt<GetUserUseCase>()),
  );
}