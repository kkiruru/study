import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import '../const/LColors.dart';

Widget commonButtonWidget({
  required String title,
  required VoidCallback? onPressed,
  final double? width,
  final double? height,
  final TextStyle? textStyle,
  final bool? enable,
  final Color? normalColor,
  final Color? pressedColor,
  final Color? disabledColor,
  final MaterialStateProperty<OutlinedBorder?>? shape,
  final MaterialStateProperty<BorderSide?>? side,
}) {
  return _BaseButtonWidget(
      title: title,
      onPressed: onPressed,
      width: width ?? double.infinity,
      height: height ?? double.infinity,
      textStyle: textStyle ??
          const TextStyle(
              color: LColors.white, fontSize: 20, fontWeight: FontWeight.w700),
      enable: enable ?? true,
      normalColor: normalColor ?? LColors.green,
      pressedColor: pressedColor ?? LColors.greenPressed,
      disabledColor: disabledColor ?? LColors.gray500,
      shape: shape ??
          MaterialStateProperty.all(RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(5),
          )),
      side: side);
}

Widget grayButtonWidget({
  required String title,
  required VoidCallback? onPressed,
  final EdgeInsetsGeometry? padding,
}) {
  return commonButtonWidget(
    title: title,
    onPressed: onPressed,
    normalColor: LColors.gray200,
    pressedColor: LColors.gray300,
    textStyle: const TextStyle(
        color: LColors.black, fontSize: 15, fontWeight: FontWeight.w700),
  );
}

Widget outlinedButtonWidget({
  required String title,
  required VoidCallback? onPressed,
  final EdgeInsetsGeometry? padding,
}) {
  return commonButtonWidget(
      title: title,
      onPressed: onPressed,
      normalColor: LColors.white,
      pressedColor: LColors.gray100,
      disabledColor: LColors.gray500,
      textStyle: const TextStyle(
          color: LColors.gray800, fontSize: 15, fontWeight: FontWeight.w700),
      shape: MaterialStateProperty.all(RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(5),
      )),
      side: MaterialStateProperty.all(
          const BorderSide(width: 1, color: LColors.gray800)));
}

class _BaseButtonWidget extends StatelessWidget {
  final String title;
  final VoidCallback? onPressed;
  final double width;
  final double height;
  final TextStyle textStyle;
  final bool enable;

  final Color normalColor;
  final Color pressedColor;
  final Color disabledColor;
  final MaterialStateProperty<OutlinedBorder?>? shape;
  final MaterialStateProperty<BorderSide?>? side;

  const _BaseButtonWidget({
    Key? key,
    required this.title,
    required this.onPressed,
    required this.width,
    required this.height,
    required this.textStyle,
    required this.enable,
    required this.normalColor,
    required this.pressedColor,
    required this.disabledColor,
    required this.shape,
    required this.side,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    MaterialStateProperty<Color?>? backgroundColor =
        MaterialStateProperty.resolveWith(
      (Set<MaterialState> states) {
        if (states.contains(MaterialState.pressed)) {
          return pressedColor;
        }
        return normalColor;
      },
    );

    VoidCallback? onPressedCallback;

    if (enable == true) {
      onPressedCallback = onPressed;
    } else {
      backgroundColor = MaterialStateProperty.all(disabledColor);
    }

    return Padding(
        padding: const EdgeInsets.all(0),
        child: Container(
            color: LColors.orange,
            child: Column(
              children: [
                Row(
                  children: [
                    SizedBox.expand(
                      child:
                        TextButton(
                            onPressed: onPressedCallback,
                            style: ButtonStyle(
                              backgroundColor: backgroundColor,
                              // textStyle: MaterialStateProperty.all(textStyle),
                              shape: shape,
                              side: side,
                              minimumSize: MaterialStateProperty.all(Size.zero),
                              padding: MaterialStateProperty.all(EdgeInsets.zero),
                              splashFactory: NoSplash.splashFactory,
                            ),
                            child: Text(title, style: textStyle)),
                    )
                  ],
                )
              ],
            )));
  }
}
