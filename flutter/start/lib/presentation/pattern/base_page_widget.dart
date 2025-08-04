import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'base_view_model.dart';
import 'loading_widget.dart';

Widget basePage(Widget content, BasePatternState viewModel) {
  return Stack(children: [content, loading(viewModel.isLoading)]);
}
