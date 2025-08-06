import 'package:flutter/cupertino.dart';

abstract class BaseState {
  bool isLoading = false;
}



class BaseViewModel extends BaseState with ChangeNotifier {

  void startLoading() {
    isLoading = true;
    notifyListeners();
  }

  void stopLoading() {
    isLoading = false;
    notifyListeners();
  }
}
