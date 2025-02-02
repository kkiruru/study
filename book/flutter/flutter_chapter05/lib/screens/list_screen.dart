import 'dart:async';

import 'package:flutter/material.dart';
import 'package:todo_list/providers/todo_default.dart';
import 'package:todo_list/providers/todo_sqlite.dart';

import '../models/todo.dart';

class ListScreen extends StatefulWidget {
  const ListScreen({super.key});

  @override
  _ListScreenState createState() => _ListScreenState();
}

class _ListScreenState extends State<ListScreen> {
  List<Todo> todos = [];
  bool isLoading = true;
  TodoDefault todoDefault = TodoDefault();
  TodoSqlite todoSqlite = TodoSqlite();

  Future initDb() async{
    await todoSqlite.initDb().then((value) async{
      todos = await todoSqlite.getTodos();
    });
  }

  @override
  void initState() {
    super.initState();
    Timer(const Duration(seconds: 2), () {
      // todos = todoDefault.getTodos();
      // setState(() {
      //   isLoading = false;
      // });
      initDb().then((_){
        setState(() {
          isLoading = false;
        });
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('할 일 목록 앱'),
        actions: [
          InkWell(
            onTap: () {},
            child: Container(
              padding: EdgeInsets.all(5),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Icon(Icons.book),
                  Text('뉴스'),
                ],
              ),
            ),
          )
        ],
      ),
      floatingActionButton: FloatingActionButton(
        child: Text('+', style: TextStyle(fontSize: 25)),
        onPressed: () {
          showDialog(
              context: context,
              builder: (BuildContext context) {
                String title = '';
                String description = '';
                return AlertDialog(
                  title: Text('할 일 추가하기'),
                  content: Container(
                    height: 200,
                      child: Column(
                        children: [
                          TextField(
                            onChanged: (value) {
                              title = value;
                            },
                            decoration: InputDecoration(labelText: '제목'),
                          ),
                          TextField(
                            onChanged: (value) {
                              description = value;
                            },
                            decoration: const InputDecoration(labelText: '설명'),
                          ),
                        ],
                      ),
                  ),
                  actions: [
                    TextButton(
                      child: const Text('추가'),
                      onPressed: () {
                        setState(() {
                          print("[UI] ADD");
                          todoDefault.addTodo(
                            Todo(title: title, description: description),
                          );
                        });
                        Navigator.of(context).pop();
                      }),
                    TextButton(
                      child: const Text('취소'),
                      onPressed: () {
                        Navigator.of(context).pop();
                      }),
                  ],
                );
              })
          ;},
      ),
      body: isLoading
          ? const Center(child: CircularProgressIndicator())
          : ListView.separated(
            itemCount: todos.length,
            itemBuilder: (context, index) {
              return ListTile(
                title: Text(todos[index].title),
                onTap: () {
                  showDialog(
                      context: context,
                      builder: (BuildContext context) {
                        return SimpleDialog(
                          title: const Text('할 일'),
                          children: [
                            Container(
                              padding: const EdgeInsets.all(10),
                              child: Text('제목 : ' + todos[index].title),
                            ),
                            Container(
                              padding: EdgeInsets.all(10),
                              child: Text('설명 : ' + todos[index].description),
                            )
                          ],
                        );
                    }
                  );
                },
                trailing: Container(
                  width: 80,
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.end,
                    children: [
                      Container(
                        padding: const EdgeInsets.all(5),
                        child: InkWell(
                          child: const Icon(Icons.edit),
                          onTap: () {
                            showDialog(
                                context: context,
                                builder: (BuildContext context) {
                                  String title = todos[index].title;
                                  String description = todos[index].description;
                                  return AlertDialog(
                                    title: const Text('할 일 수정하기'),
                                    content: Container(
                                      height: 200,
                                        child: Column(
                                          children: [
                                            TextField(
                                              onChanged: (value) {
                                                title = value;
                                              },
                                              decoration: InputDecoration(
                                                hintText: todos[index].title,
                                              ),
                                            ),
                                            TextField(
                                              onChanged: (value) {
                                                description = value;
                                              },
                                              decoration: InputDecoration(
                                                hintText: todos[index].description,
                                              ),
                                            ),
                                          ],
                                        ),
                                    ),
                                    actions: [
                                      TextButton(
                                        child: const Text('수정'),
                                        onPressed: () async {
                                          Todo newTodo = Todo(
                                            id: todos[index].id,
                                            title : title,
                                            description : description,
                                          );
                                          setState(() {
                                            todoDefault.updateTodo(newTodo);
                                          });
                                          Navigator.of(context).pop();
                                        }
                                      ),
                                      TextButton(
                                        child: const Text('취소'),
                                        onPressed: () {
                                          Navigator.of(context).pop();
                                        }
                                      ),
                                  ],
                                );
                              }
                            );
                          },
                        ),
                      ),
                      Container(
                        padding: EdgeInsets.all(5),
                        child: InkWell(
                          child: Icon(Icons.delete),
                          onTap: () {
                            showDialog(
                                context: context,
                                builder: (BuildContext context){
                                  return AlertDialog(
                                    title: Container(
                                      child: Text('삭제하시겠습니까?'),
                                    ),
                                    actions: [
                                      TextButton(
                                        child: Text('삭제'),
                                        onPressed: () async {
                                            setState(() {
                                              todoDefault.deleteTodo(
                                                todos[index].id ?? 0
                                              );
                                            });
                                            Navigator.of(context).pop();
                                        },
                                      ),
                                      TextButton(
                                        child: Text('취소'),
                                        onPressed: () {
                                          Navigator.of(context).pop();
                                        },
                                      )
                                    ],
                                  );
                                }
                            );
                          },
                        ),
                      ),
                    ],
                  ),
                ),
              );
            },
            separatorBuilder: (context, index) {
              return Divider();
            }
      ),
    );
  }
}