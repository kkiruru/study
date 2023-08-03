
import 'package:flutter/material.dart';

import 'const/LColors.dart';

class RowScreen extends StatelessWidget {
  const RowScreen({super.key});

  // This widget is the root of your application.

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        color: LColors.blue,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Row(
              mainAxisSize: MainAxisSize.max,
              children: [
                button2(),
              ],
            ),
          ],
        )
      ),
    );
  }

  Widget button2() {
    return FilledButton(
      onPressed: () => {},
      style: ButtonStyle(
        tapTargetSize: MaterialTapTargetSize.shrinkWrap,
        padding: MaterialStateProperty.all(const EdgeInsets.all(0)),
        foregroundColor: MaterialStateProperty.all(LColors.white),
        backgroundColor:
            MaterialStateProperty.resolveWith((Set<MaterialState> states) {
          if (states.contains(MaterialState.pressed)) {
            return LColors.greenPressed;
          }
          return LColors.green;
        }),
        splashFactory: NoSplash.splashFactory,
        shape: MaterialStateProperty.all(
          const RoundedRectangleBorder(
            borderRadius: BorderRadius.all(
              Radius.circular(12),
            ),
          ),
        ),
      ),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Container(
              margin: const EdgeInsets.all(0.0),
              child: const Text(
                "운송기사조회",
                style: TextStyle(fontSize: 18.0),
              ))
        ],
      ),
    );
  }


  Widget button() {
    MaterialStateProperty<Color?>? backgroundColor =
    MaterialStateProperty.resolveWith(
          (Set<MaterialState> states) {
        if (states.contains(MaterialState.pressed)) {
          return LColors.greenPressed;
        }
        return LColors.green;
      },
    );

    return TextButton(
      style: TextButton.styleFrom(
        foregroundColor: LColors.green,
        backgroundColor: LColors.greenPressed,
        side: const BorderSide(color: LColors.green, width: 5),
        padding: const EdgeInsets.all(0),
        textStyle: const TextStyle(
            color: LColors.white, fontSize: 124, fontWeight: FontWeight.w700),
      ),
      onPressed: () {
        print('Pressed');
      },
      child: Text('운송기사 조회'),
    );
  }
}
