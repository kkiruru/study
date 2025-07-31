import 'package:flutter_riverpod/flutter_riverpod.dart';

class Logger extends ProviderObserver {
  @override
  void didUpdateProvider(ProviderBase provider, Object? previousValue, Object? newValue, ProviderContainer container) {
    print('[Provider updated] provider: $provider / pv: $previousValue /nv: $newValue');
  }


  @override
  void didAddProvider(ProviderBase<Object?> provider, Object? value, ProviderContainer container) {
    print('[Provider added] provider: $provider / value: $value');
    super.didAddProvider(provider, value, container);
  }

  @override void didDisposeProvider(ProviderBase<Object?> provider, ProviderContainer container) {
    print('[Provider disposed] provider: $provider');
    super.didDisposeProvider(provider, container);
  }
}