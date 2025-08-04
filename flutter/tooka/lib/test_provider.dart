part 'test_provider.g.dart';

import 'package:riverpod_annotation/riverpod_annotation.dart';

@riverpod
String hello(HelloRef ref) {
  return 'Hello World';
} 