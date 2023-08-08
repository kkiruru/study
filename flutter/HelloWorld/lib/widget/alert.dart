import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';

import '../const/LColors.dart';


topToastAlert({
  required BuildContext context,
  String content = "",
  Function()? onDismiss
}) {
  _topToastAlert(
      context: context,
      content: content,
      onDismiss: onDismiss,
      color: LColors.green,
      icon: "assets/images/ic_check_notification.svg",
  );
}

topWarningToastAlert({
  required BuildContext context,
  String content = "",
  Function()? onDismiss
}) {
  _topToastAlert(
    context: context,
    content: content,
    onDismiss: onDismiss,
    color: LColors.orange,
    icon: "assets/images/ic_check_alert.svg",
  );
}


_topToastAlert({
  required BuildContext context,
  String content = "",
  Color color = LColors.green,
  String icon = "assets/images/ic_check_notification.svg",
  Function()? onDismiss
}) async {

  // Future.delayed(const Duration(milliseconds: 1500), () {
  //   Navigator.pop(context);
  //   if (onDismiss != null) {
  //     return onDismiss();
  //   }
  // });

  showGeneralDialog(
    context: context,
    barrierDismissible: false,
    pageBuilder: (_, __, ___) {
      return Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Container(
              margin: const EdgeInsets.symmetric(horizontal: 42, vertical: 62),
              padding: const EdgeInsets.all(16),
              decoration: BoxDecoration(
                color: LColors.white,
                border: Border.all(
                  color: color,
                  width: 2,
                ),
                borderRadius: BorderRadius.circular(10),
                  boxShadow: [
                    BoxShadow(
                      color: Colors.black.withOpacity(0.1),
                      spreadRadius: 0,
                      blurRadius: 5.0,
                      offset: const Offset(0, 10), // changes position of shadow
                    ),
                  ],
              ),
              child:
                  Row(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      SvgPicture.asset(
                        icon,
                        width: 19,
                        height: 19,
                      ),
                      const Padding(padding: EdgeInsets.only(left: 10)),
                      DefaultTextStyle(
                          style: const TextStyle(
                              color: LColors.gray800,
                              fontSize: 15,
                              fontWeight: FontWeight.w700),
                          child: Text(content)
                      )
                    ],
                  )
          ),
        ],
      );
    },
  );
}
