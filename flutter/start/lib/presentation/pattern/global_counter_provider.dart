import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_start/presentation/pattern/user_vo.dart';

// 전역 counter 상태 관리
// final globalCounterProvider = StateProvider<int>((ref) => 0);



final globalUserProvider = StateProvider<UserVo>((ref) => UserVo(name: '__'));



