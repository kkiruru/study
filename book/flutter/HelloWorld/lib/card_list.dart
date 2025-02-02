import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'const/LColors.dart';

class CardListDemo extends StatefulWidget {
  const CardListDemo({super.key});

  @override
  CardListDemoState createState() => CardListDemoState();
}

class CardListDemoState extends State<CardListDemo> {
  final GlobalKey<ScaffoldState> _scaffoldKey = GlobalKey<ScaffoldState>();

  @override
  Widget build(BuildContext context) {
    final ThemeData theme = Theme.of(context);

    return Scaffold(
      key: _scaffoldKey,
      appBar: AppBar(
        title: const Text('Dialogs'),
      ),
      body: Column(
        children: [
          _transportStateListWidget(),
          Expanded(
            child: _transportListWidget(),
          ),
        ],
      ),
    );
  }

  Widget _transportStateListWidget() {
    Widget transportStateBox(Color color, String title) {
      return SizedBox(
        child: Container(
          height: 23,
          width: 82,
          alignment: Alignment.center,
          decoration: BoxDecoration(
            color: color,
            borderRadius: BorderRadius.circular(10),
            border: Border.all(
              color: LColors.gray400,
              width: 1,
            ),
          ),
          child: Text(
            title,
            style: const TextStyle(
                color: LColors.black,
                fontSize: 12,
                fontWeight: FontWeight.w400),
          ),
        ),
      );
    }

    return Column(children: [
      Padding(
        padding: const EdgeInsets.fromLTRB(20, 16, 20, 16),
        child: SingleChildScrollView(
          scrollDirection: Axis.horizontal,
          child: Row(
            children: [
              transportStateBox(const Color(0xFFFAE0D4), '상차가능'),
              const Padding(padding: EdgeInsets.only(left: 10)),
              transportStateBox(const Color(0xFFD9E5FF), '배송가능'),
              const Padding(padding: EdgeInsets.only(left: 10)),
              transportStateBox(const Color(0xFFFAF4C0), '수거/회수'),
              const Padding(padding: EdgeInsets.only(left: 10)),
              transportStateBox(const Color(0xFFCEFBC9), '포장수거'),
            ],
          ),
        ),
      )
    ]);
  }

  Widget _transportListWidget() {
    final Color backgroundColor = const Color(0xFFD9E5FF);

    Widget transportItem(int no, String address) {

    return Card(
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(10),
        ),
        elevation: 4.0,
        child:
        IntrinsicHeight(
        child: Row(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            // ConstrainedBox(
            //   constraints: BoxConstraints.expand(),
            //   child: Container(
            //     width: 32,
            //     alignment: Alignment.center,
            //     decoration: BoxDecoration(
            //       color: backgroundColor,
            //       borderRadius: BorderRadius.only(
            //         topLeft: Radius.circular(10),
            //         bottomLeft: Radius.circular(10),
            //       ),
            //     ),
            //     child:
            //     Text(
            //       "${no + 1}",
            //       style: const TextStyle(
            //           color: LColors.black,
            //           fontSize: 15,
            //           fontWeight: FontWeight.w700),
            //     ),
            //   ),
            // ),
            Center(
              child: Container(
                width: 32,
                alignment: Alignment.center,
                decoration: BoxDecoration(
                  color: backgroundColor,
                  borderRadius: BorderRadius.only(
                    topLeft: Radius.circular(10),
                    bottomLeft: Radius.circular(10),
                  ),
                ),
                child:
                Text(
                  "${no + 1}",
                  style: const TextStyle(
                      color: LColors.black,
                      fontSize: 15,
                      fontWeight: FontWeight.w700),
                ),
              ),
            ),
            Expanded(
              child: Padding(
                padding:
                const EdgeInsets.symmetric(vertical: 16, horizontal: 12),
                child: Column(
                    mainAxisAlignment: MainAxisAlignment.spaceAround,
                    children: [
                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: [
                          Text(
                            "2023-07-26",
                            style: const TextStyle(
                                color: Color(0xFF004EA7),
                                fontSize: 14,
                                fontWeight: FontWeight.w700),
                          ),
                          Expanded(
                            child: Row(
                              mainAxisAlignment: MainAxisAlignment.end,
                              children: [
                                const Text(
                                  "수거",
                                  style: TextStyle(
                                      color: LColors.black,
                                      fontSize: 14,
                                      fontWeight: FontWeight.w700),
                                ),
                                Container(
                                  margin: const EdgeInsets.only(left: 8),
                                  alignment: Alignment.center,
                                  decoration: BoxDecoration(
                                    color: LColors.green,
                                    borderRadius: BorderRadius.circular(5),
                                  ),
                                  padding: const EdgeInsets.symmetric(
                                      vertical: 2, horizontal: 6),
                                  child: Text(
                                    "ZFKBF23",
                                    style: const TextStyle(
                                        color: LColors.white,
                                        fontSize: 11,
                                        fontWeight: FontWeight.w700),
                                  ),
                                ),
                              ],
                            ),
                          ),
                        ],
                      ),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: [
                          Container(
                            height: 24,
                            padding: const EdgeInsets.symmetric(horizontal: 8),
                            alignment: Alignment.center,
                            decoration: BoxDecoration(
                              color: LColors.gray200,
                              borderRadius: BorderRadius.circular(15),
                            ),
                            child: Text(
                              "런드렛",
                              style: const TextStyle(
                                  color: LColors.black,
                                  fontSize: 13,
                                  fontWeight: FontWeight.w500),
                            ),
                          ),
                          const Expanded(
                            child: Row(
                              mainAxisAlignment: MainAxisAlignment.end,
                              children: [
                                Image(
                                  image:
                                  AssetImage('assets/images/ic_map.png'),
                                  height: 29,
                                  width: 29,
                                ),
                                SizedBox(width: 10),
                                Image(
                                  image: AssetImage(
                                      'assets/images/ic_picture.png'),
                                  height: 29,
                                  width: 29,
                                ),
                              ],
                            ),
                          ),
                        ],
                      ),
                      Container(
                        alignment: Alignment.centerLeft,
                        child: Text(address,
                            style: const TextStyle(
                                color: LColors.black,
                                fontSize: 13,
                                fontWeight: FontWeight.w700),
                            textAlign: TextAlign.left),
                      ),
                    ]),
                // ),
              ),
            ),
          ],
        ),
        )
    );
      //
      //
      //
      // return Row(
      //   children: [
      //     Container(
      //       margin: const EdgeInsets.symmetric(vertical: 4, horizontal: 20),
      //       decoration: BoxDecoration(
      //         color: LColors.white,
      //         borderRadius: BorderRadius.circular(10),
      //         boxShadow: [
      //           BoxShadow(
      //             color: Colors.black.withOpacity(0.25),
      //             spreadRadius: 0,
      //             blurRadius: 4,
      //             offset: const Offset(0, 4), // changes position of shadow
      //           ),
      //         ],
      //       ),
      //       child: Column(
      //         children: [
      //           Container(
      //             width: 32,
      //             alignment: Alignment.center,
      //             decoration: BoxDecoration(
      //               color: backgroundColor,
      //               borderRadius: BorderRadius.only(
      //                 topLeft: Radius.circular(10),
      //                 bottomLeft: Radius.circular(10),
      //               ),
      //             ),
      //           child: Text(
      //             "${no + 1}",
      //             style: const TextStyle(
      //               color: LColors.black,
      //               fontSize: 15,
      //               fontWeight: FontWeight.w700),
      //             ),
      //           )
      //         ],
      //       ),
      //     ),
      //     Expanded(
      //       child: Padding(
      //         padding:
      //             const EdgeInsets.symmetric(vertical: 16, horizontal: 12),
      //         child: Column(
      //             mainAxisAlignment: MainAxisAlignment.spaceAround,
      //             children: [
      //               Row(
      //                 mainAxisAlignment: MainAxisAlignment.spaceBetween,
      //                 children: [
      //                   Text(
      //                     "2023-07-26",
      //                     style: const TextStyle(
      //                         color: Color(0xFF004EA7),
      //                         fontSize: 14,
      //                         fontWeight: FontWeight.w700),
      //                   ),
      //                   Expanded(
      //                     child: Row(
      //                       mainAxisAlignment: MainAxisAlignment.end,
      //                       children: [
      //                         const Text(
      //                           "수거",
      //                           style: TextStyle(
      //                               color: LColors.black,
      //                               fontSize: 14,
      //                               fontWeight: FontWeight.w700),
      //                         ),
      //                         Container(
      //                           margin: const EdgeInsets.only(left: 8),
      //                           alignment: Alignment.center,
      //                           decoration: BoxDecoration(
      //                             color: LColors.green,
      //                             borderRadius: BorderRadius.circular(5),
      //                           ),
      //                           padding: const EdgeInsets.symmetric(
      //                               vertical: 2, horizontal: 6),
      //                           child: Text(
      //                             "ZFKBF23",
      //                             style: const TextStyle(
      //                                 color: LColors.white,
      //                                 fontSize: 11,
      //                                 fontWeight: FontWeight.w700),
      //                           ),
      //                         ),
      //                       ],
      //                     ),
      //                   ),
      //                 ],
      //               ),
      //               Row(
      //                 mainAxisAlignment: MainAxisAlignment.spaceBetween,
      //                 children: [
      //                   Container(
      //                     height: 24,
      //                     padding: const EdgeInsets.symmetric(horizontal: 8),
      //                     alignment: Alignment.center,
      //                     decoration: BoxDecoration(
      //                       color: LColors.gray200,
      //                       borderRadius: BorderRadius.circular(15),
      //                     ),
      //                     child: Text(
      //                       "런드렛",
      //                       style: const TextStyle(
      //                           color: LColors.black,
      //                           fontSize: 13,
      //                           fontWeight: FontWeight.w500),
      //                     ),
      //                   ),
      //                   const Expanded(
      //                     child: Row(
      //                       mainAxisAlignment: MainAxisAlignment.end,
      //                       children: [
      //                         Image(
      //                           image:
      //                               AssetImage('assets/images/ic_map.png'),
      //                           height: 29,
      //                           width: 29,
      //                         ),
      //                         SizedBox(width: 10),
      //                         Image(
      //                           image: AssetImage(
      //                               'assets/images/ic_picture.png'),
      //                           height: 29,
      //                           width: 29,
      //                         ),
      //                       ],
      //                     ),
      //                   ),
      //                 ],
      //               ),
      //               Container(
      //                 alignment: Alignment.centerLeft,
      //                 child: Text(address,
      //                     style: const TextStyle(
      //                         color: LColors.black,
      //                         fontSize: 13,
      //                         fontWeight: FontWeight.w700),
      //                     textAlign: TextAlign.left),
      //               ),
      //             ]),
      //           // ),
      //         ),
      //       ),
      //   ],
      // );
    }

    List<String> addresses = [
      "경기도 성남시 분당구 동판교로225 봇들마을 3단지",
      "경기도 성남시 분당구 동판교로225 봇들마을 3단지 303동 1001호",
      "전라남도 나주시 우정로 101 광주전남공동혁신도시 광주전남공동혁신도시 빛가람 대방엘리움 로얄카운티 2차 2020동 1010호",
    ];

    return ListView.separated(
      // shrinkWrap: true,
      padding: EdgeInsets.symmetric(vertical: 16, horizontal: 12),
      itemBuilder: (BuildContext context, int index) {
        return transportItem(index, addresses[index]);
      },
      separatorBuilder: (BuildContext context, int index) {
        return const Divider(
          color: Colors.transparent,
          thickness: 0,
          height: 8,
        );
        // return const Padding(padding: EdgeInsets.symmetric(vertical: 4));
      },
      itemCount: 3,
    );
  }
}
