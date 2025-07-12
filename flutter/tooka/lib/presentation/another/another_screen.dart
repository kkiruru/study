import 'package:flutter/material.dart';

class AnotherScreen extends StatelessWidget {
  const AnotherScreen({super.key});

  @override
  Widget build(BuildContext context) {
    print('AnotherScreen: build called'); // 빌드 호출 확인
    return Scaffold(
      backgroundColor: Colors.red, // 눈에 띄는 배경색
      appBar: AppBar(
        title: const Text('Another Screen Title'),
        leading: IconButton( // 닫기 버튼 추가
          icon: const Icon(Icons.close),
          onPressed: () {
            if (Navigator.canPop(context)) {
              Navigator.pop(context);
            }
          },
        ),
      ),
      body: const Center(
        child: Text(
          'ANOTHER SCREEN CONTENT',
          style: TextStyle(fontSize: 24, color: Colors.white),
        ),
      ),
    );
  }
}


// //lib/presentation/another/another_screen.dart
//
// import 'package:flutter/cupertino.dart';
// import 'package:flutter/material.dart';
// // import 'package:go_router/go_router.dart';
//
// // import '../../app_route.dart';
// import '../../core/services/deep_link_service.dart';
// import '../my/my_screen.dart';
//
// class AnotherScreen extends StatelessWidget {
//   const AnotherScreen({super.key});
//
//   @override
//   Widget build(BuildContext context) {
//     print('AnotherScreen: build called');
//     print('AnotherScreen: context.mounted = ${context.mounted}');
//     print('AnotherScreen: MediaQuery.of(context).size = ${MediaQuery.of(context).size}');
//     print('AnotherScreen: Theme.of(context).brightness = ${Theme.of(context).brightness}');
//     DeepLinkService.printStack();
//
//     return Scaffold(
//       backgroundColor: Colors.green, // 임시로 초록색으로 변경해서 화면이 보이는지 확인
//       key: const ValueKey('another_screen'), // 고유한 키 추가
//       appBar: AppBar(
//         title: const Text('Another Widget'),
//         leading: IconButton(
//           icon: const Icon(Icons.close),
//           onPressed: () {
//             print('AnotherScreen: Close button pressed');
//             Navigator.of(context).pop(); // go_router 대신 Navigator 사용
//           },
//         ),
//         backgroundColor: Colors.blue,
//       ),
//       body: SafeArea(
//         top: true,
//         bottom: true,
//         child: SingleChildScrollView(
//           child: Column(
//             mainAxisAlignment: MainAxisAlignment.center,
//             children: [
//               ElevatedButton(
//                 onPressed: () {
//                   // context.push('/my-widget');
//                 },
//                 style: ElevatedButton.styleFrom(
//                   padding: const EdgeInsets.symmetric(
//                     horizontal: 30,
//                     vertical: 15,
//                   ),
//                 ),
//                 child: const Text(
//                   'push /my-widget',
//                   style: TextStyle(fontSize: 16),
//                 ),
//               ),
//               const SizedBox(height: 20),
//               ElevatedButton(
//                 onPressed: () {
//                   // context.go('/my-widget');
//                 },
//                 style: ElevatedButton.styleFrom(
//                   padding: const EdgeInsets.symmetric(
//                     horizontal: 30,
//                     vertical: 15,
//                   ),
//                 ),
//                 child: const Text(
//                   'go /my-widget',
//                   style: TextStyle(fontSize: 16),
//                 ),
//               ),
//               const SizedBox(height: 20),
//               ElevatedButton(
//                 onPressed: () {
//                   // context.go('/main');
//                 },
//                 style: ElevatedButton.styleFrom(
//                   padding: const EdgeInsets.symmetric(
//                     horizontal: 30,
//                     vertical: 15,
//                   ),
//                 ),
//                 child: const Text('go /mian', style: TextStyle(fontSize: 16)),
//               ),
//               const SizedBox(height: 20),
//               ElevatedButton(
//                 onPressed: () {
//                   // context.go('/main?tab=third');
//                 },
//                 style: ElevatedButton.styleFrom(
//                   padding: const EdgeInsets.symmetric(
//                     horizontal: 30,
//                     vertical: 15,
//                   ),
//                 ),
//                 child: const Text(
//                   'go /main?tab=third',
//                   style: TextStyle(fontSize: 16),
//                 ),
//               ),
//               const SizedBox(height: 20),
//               ElevatedButton(
//                 onPressed: () {
//                   Navigator.of(
//                     context,
//                   ).push<bool>(CupertinoPageRoute(builder: (_) => MyScreen()));
//                 },
//                 // style: ElevatedButton.styleFrom(
//                 //   padding: const EdgeInsets.symmetric(horizontal: 30, vertical: 15),
//                 // ),
//                 child: const Text(
//                   'Navigator.push MyScreen',
//                   style: TextStyle(fontSize: 16),
//                 ),
//               ),
//               const SizedBox(height: 20),
//               ElevatedButton(
//                 onPressed: () {
//                   // context.push('/main/another/foo');
//                 },
//                 style: ElevatedButton.styleFrom(
//                   padding: const EdgeInsets.symmetric(
//                     horizontal: 30,
//                     vertical: 15,
//                   ),
//                 ),
//                 child: const Text(
//                   'push /main/another/foo',
//                   style: TextStyle(fontSize: 16),
//                 ),
//               ),
//               const SizedBox(height: 20),
//               ElevatedButton(
//                 onPressed: () {
//                   // context.go('/main/another/foo');
//                 },
//                 style: ElevatedButton.styleFrom(
//                   padding: const EdgeInsets.symmetric(
//                     horizontal: 30,
//                     vertical: 15,
//                   ),
//                 ),
//                 child: const Text(
//                   'go /main/another/foo',
//                   style: TextStyle(fontSize: 16),
//                 ),
//               ),
//               const SizedBox(height: 20),
//               ElevatedButton(
//                 onPressed: () {
//                   // context.push('/main/other/bar');
//                 },
//                 style: ElevatedButton.styleFrom(
//                   padding: const EdgeInsets.symmetric(
//                     horizontal: 30,
//                     vertical: 15,
//                   ),
//                 ),
//                 child: const Text(
//                   'push /main/other/bar',
//                   style: TextStyle(fontSize: 16),
//                 ),
//               ),
//               const SizedBox(height: 20),
//               ElevatedButton(
//                 onPressed: () {
//                   // context.go('/main/other/bar');
//                 },
//                 style: ElevatedButton.styleFrom(
//                   padding: const EdgeInsets.symmetric(
//                     horizontal: 30,
//                     vertical: 15,
//                   ),
//                 ),
//                 child: const Text(
//                   'go /main/other/bar',
//                   style: TextStyle(fontSize: 16),
//                 ),
//               ),
//               const SizedBox(height: 20),
//               ElevatedButton(
//                 onPressed: () {
//                   // context.go('/main/another');
//                 },
//                 style: ElevatedButton.styleFrom(
//                   padding: const EdgeInsets.symmetric(
//                     horizontal: 30,
//                     vertical: 15,
//                   ),
//                 ),
//                 child: const Text(
//                   'go /main/another',
//                   style: TextStyle(fontSize: 16),
//                 ),
//               ),
//             ],
//           ),
//         ),
//       ),
//     );
//   }
// }
