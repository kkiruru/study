import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter_start/presentation/pattern/user_vo.dart';

import 'global_counter_provider.dart';

class DetailPatternPage extends ConsumerWidget {
  const DetailPatternPage({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    var detailPatternState = ref.watch(globalUserProvider);
    return Scaffold(
      appBar: AppBar(title: Text("Detail Pattern")),
      body: Padding(
        padding: EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            Text(
              'globalUserProvider ${detailPatternState.name}',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            SizedBox(height: 16),
            ElevatedButton(
              onPressed: () {
                ref.read(detailPatternViewModelProvider).loadData();
              },
              child: Text('load User Data'),
            ),
            SizedBox(height: 8),
            ElevatedButton(
              onPressed: () {
                Navigator.of(context).pop();
              },
              child: Text('Go Back'),
            ),
          ],
        ),
      ),
    );
  }
}


final detailPatternViewModelProvider = ChangeNotifierProvider.autoDispose(
      (ref) => DetailPatternViewModel(ref),
);

class DetailPatternViewModel extends ChangeNotifier {
  final Ref _ref;

  DetailPatternViewModel(this._ref) {

  }

  void loadData() async {
    await Future.delayed(const Duration(seconds: 2));
    _ref.read(globalUserProvider.notifier)
        .update((state) => UserVo(name: 'hyun'));
  }

}