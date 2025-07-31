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


class Parameter {
  final int number1;
  final int number2;

  Parameter({
    required this.number1,
    required this.number2,
  });
}

final _testFamilyProvider = Provider.family<int, Parameter>(
    (ref, Parameter) => Parameter.number1 * Parameter.number2);


@riverpod
int gStateMultiply(Ref ref, {
    required int number1,
    required int number2,
}) {
  return number1 * number2;
}

@riverpod
class GStateNotifier extends _$GStateNotifier {

  @override
  int build() {
    return 0;
  }
  increment() {
    state++;
  }

  decrement() {
    state--;
  }

}