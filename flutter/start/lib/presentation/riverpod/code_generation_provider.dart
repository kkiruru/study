import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:riverpod_annotation/riverpod_annotation.dart';

part 'code_generation_provider.g.dart';

final testProvider = Provider<String>((ref) => 'Hello Code Generation1');

@riverpod
String gState(Ref ref) {
  return 'Hello Code Generation2';
}

@riverpod
Future<int> gStateFuture(Ref ref) async {
  await Future.delayed(const Duration(seconds: 3));
  return 10;
}

@Riverpod(
    keepAlive: true
)
Future<int> gStateFuture2(Ref ref) async {
  await Future.delayed(const Duration(seconds: 3));
  return 10;
}
