import 'package:flutter_riverpod/flutter_riverpod.dart';

// 전역 counter 상태 관리
final globalCounterProvider = StateProvider<int>((ref) => 0);