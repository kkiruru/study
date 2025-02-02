import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class MyTabs extends StatefulWidget {
  @override
  MyTabsState createState() => MyTabsState();
}

class MyTabsState extends State<MyTabs> with SingleTickerProviderStateMixin {

  late TabController controller;

  @override
  void initState() {
    super.initState();
    controller = TabController(vsync: this, length: 3);
  }

  @override
  void dispose() {
    controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
            title: Text("Pages"), backgroundColor: Colors.deepOrange,
            bottom: TabBar(
                controller: controller,
                tabs: <Tab>[
                  Tab(icon: TabIcon(foo: 39)),
                  Tab(icon: TabIcon(foo: 12)),
                  Tab(icon: TabIcon(foo: 5)),
                ]
            )
        ),
        bottomNavigationBar: Material(
            color: Colors.deepOrange,
            child: TabBar(
                controller: controller,
                tabs: const <Tab>[
                  Tab(icon: Icon(Icons.arrow_back)),
                  Tab(icon: Icon(Icons.arrow_downward)),
                  Tab(icon: Icon(Icons.arrow_back))
                ]
            )
        ),
        body: TabBarView(
            controller: controller,
            children: <Widget>[
              First(),
              Second(),
              Third()
            ]
        )
    );
  }
}

class First extends StatelessWidget {
  @override
  Widget build(BuildContext context){
    return Container(
        child: Center(
            child: Icon(Icons.accessibility_new, size: 150.0, color: Colors.brown)
        )
    );
  }
}

class Second extends StatelessWidget {

  @override
  Widget build(BuildContext context){
    return Container(
        child: Center(
            child: Icon(Icons.favorite, size: 150.0, color: Colors.red)
        )
    );
  }
}

class Third extends StatelessWidget {

  @override
  Widget build(BuildContext context){
    return Container(
        child: Center(
            child: Icon(Icons.local_pizza, size: 150.0, color: Colors.teal)
        )
    );
  }
}

class TabIcon extends StatelessWidget {
  final int foo;
  const TabIcon({
    super.key,
    required this.foo,
  });
  @override
  Widget build(BuildContext context){
    return SizedBox(
        child: Column(
            children: [
              Text(
                "${this.foo}"
              ),
              Text(
                  "총운송"
              )
            ],
        )
    );
  }
}