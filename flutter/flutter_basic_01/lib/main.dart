import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp(
    title: 'Hello workd',
  ));
}

class MyApp extends StatelessWidget {
  final String title;
  const MyApp({super.key, required this.title});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or simply save your changes to "hot reload" in a Flutter IDE).
        // Notice that the counter didn't reset back to zero; the application
        // is not restarted.
        primarySwatch: Colors.blueGrey,
      ),
      home: MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatelessWidget {
  final String title;
  List<Widget> myChildren = [];

  MyHomePage({required this.title});

  @override
  Widget build(BuildContext context) {
    for (var i = 0; i < 50; i++) {
      myChildren.add(Text('Hello, World ${i}', style: TextStyle(fontSize: 25)));
    }
    return Scaffold(
      appBar: AppBar(
        title: Text(
          title,
        ),
      ),
      // body: const Center(
      //   child: Text('Hello, Flutter!!'),
      // ),
      // body: Container(
      //   child: const Text("Hello, Flutter!"),
      //   padding: EdgeInsets.symmetric(vertical: 130, horizontal: 100),
      //   color: Colors.blue,
      //   height: 300,
      //   width: 300,
      // body: Center(
      //   // child: Image.asset('images/barcode.png'),
      //   child: Image.network('https://www.laundrygo.com/wp-content/uploads/2022/11/%EA%B9%80%EA%B8%B0%EC%86%94%EA%B9%80%EC%84%B1%EA%B7%9C%EC%9D%B4%EC%9A%B0%EC%84%AD_%EB%A9%94%EC%9D%B8.png',
      //   width: 400,
      //   height: 300,),
      // ),
      // body: Center(
      //   child: Text(
      //       'Hello, 허현!',
      //     style: TextStyle(
      //       fontSize: 25,
      //       color: Colors.purple,
      //       fontFamily: 'NotoSansKR',
      //       fontWeight: FontWeight.w700,
      //     )
      //   ),
      // )
      // body: Center(
      //   child: Column(
      //     mainAxisAlignment: MainAxisAlignment.center,
      //     // crossAxisAlignment: CrossAxisAlignment.start,
      //     children: [
      //       Text('1. First String', style: TextStyle(fontSize: 25)),
      //       Text('2. Second String', style: TextStyle(fontSize: 20)),
      //       Text('3. Third String', style: TextStyle(fontSize: 15)),
      //     ],
      //   )
      // )
      // body: Center(
      //   child: Row(
      //     // mainAxisAlignment: MainAxisAlignment.center,
      //     // mainAxisAlignment: MainAxisAlignment.spaceAround,
      //     // mainAxisAlignment: MainAxisAlignment.spaceBetween,
      //     mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      //     children: [
      //       Text('1. First', style: TextStyle(fontSize: 25)),
      //       Text('2. Second', style: TextStyle(fontSize: 20)),
      //       Text('3. Third', style: TextStyle(fontSize: 15)),
      //     ],
      //   ),
      // ),
      // body: Center(
      //   child: ListView(
      //     children: myChildren,
      //   ),
      // ),
      // body: Center(
      //   child: ListView(
      //     scrollDirection: Axis.horizontal,
      //     children: myChildren,
      //   ),
      // ),
      // body: Center(
      //   child: ListView.builder(
      //     itemCount: 50,
      //       itemBuilder: (BuildContext context, int index){
      //         return Text('$index' + ' Text', style: TextStyle(fontSize: 25));
      //       },
      //   )
      // ),
      // body: Center(
      //   child: Stack(
      //     children: [
      //       Image.asset('images/barcode.png'),
      //       Positioned(
      //         left: 0,
      //         bottom: 0,
      //         child: Image.network('https://www.laundrygo.com/wp-content/uploads/2022/11/%EA%B9%80%EA%B8%B0%EC%86%94%EA%B9%80%EC%84%B1%EA%B7%9C%EC%9D%B4%EC%9A%B0%EC%84%AD_%EB%A9%94%EC%9D%B8.png',
      //          height: 300,
      //           width: 300,
      //         ),
      //       ),
      //     ],
      //   )
      // ),
      // body: Center(
      //   child: Column(
      //     mainAxisAlignment: MainAxisAlignment.center,
      //     children: [
      //       TextButton(onPressed: () {}, child: Text('Text Button'),),
      //       Padding(padding: EdgeInsets.all(20)),
      //       ElevatedButton(onPressed: () {}, child: Text('Elevated Button')),
      //       Padding(padding: EdgeInsets.all(20)),
      //       OutlinedButton(onPressed: () {}, child: Text('Outlined Button')),
      //       Padding(padding: EdgeInsets.all(20)),
      //       IconButton(onPressed: () {}, icon: Icon(Icons.star)),
      //     ],
      //   ),
      // ),
    );
  }
}
