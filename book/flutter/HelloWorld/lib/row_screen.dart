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
            mainAxisSize: MainAxisSize.max,

            // mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text(
                "운송기사조회",
                style: TextStyle(fontSize: 18.0),
              ),
              // Expanded(
              //   // child: Row(
              //   //   children: [
              //   //     Expanded(
              //   //       child: Container(
              //   //         color: Colors.yellow,
              //   //         child: button2(),
              //   //     )),
              //   //   ],
              //   // ),
              //     child: button2(),
              // ),
              button2(),
              Text(
                "운송기사조회",
                style: TextStyle(fontSize: 18.0),
              ),
              Row(children: [
                Text(
                  "운송기사조회",
                  style: TextStyle(fontSize: 18.0),
                ),
                Expanded(child: button2()),
              ]),
              Row(children: [
                Expanded(child: button2()),
                Expanded(child: button2()),
              ]),
              Row(
                  mainAxisSize: MainAxisSize.max,
                  children: [
                    Expanded(
                      child:
                     button2(),
                    )
                  ]
              )
            ],
          )),
    );
  }

  Widget button2() {
    return Column(
      mainAxisSize: MainAxisSize.min,
      children: [
        Row(
          mainAxisSize: MainAxisSize.max,
          children: [
            Expanded(
                child: FilledButton(
              onPressed: () => {},
              style: ButtonStyle(
                tapTargetSize: MaterialTapTargetSize.shrinkWrap,
                padding: MaterialStateProperty.all(const EdgeInsets.all(0)),
                foregroundColor: MaterialStateProperty.all(LColors.white),
                backgroundColor: MaterialStateProperty.resolveWith(
                    (Set<MaterialState> states) {
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
            ))
          ],
        )
      ],
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
