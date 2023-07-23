import 'package:flutter/material.dart';

enum DialogDemoAction {
  cancel,
  discard,
  disagree,
  agree,
}


/*
  https://gist.github.com/Andrious/6fe652be64cf42b9be8b69d42c0fa2a4#file-dialog_demo-dart-L85
 */

const String _alertWithTitleText =
    'Let Google help apps determine location. This means sending anonymous location '
    'data to Google, even when no apps are running.';

class DialogDemo extends StatefulWidget {

  const DialogDemo({super.key});


  @override
  DialogDemoState createState() => DialogDemoState();
}

class DialogDemoState extends State<DialogDemo> {
  final GlobalKey<ScaffoldState> _scaffoldKey = GlobalKey<ScaffoldState>();

  void showDemoDialog<T>({required BuildContext context, required Widget child}) {
    showDialog<T>(
      context: context,
      builder: (BuildContext context) => child,
    ).then<void>((T? value) {
      // The value passed to Navigator.pop() or null.

      if( value != null && value is DialogDemoAction ) {
        ScaffoldMessenger.of(context)
            .showSnackBar(SnackBar(content: Text(value.toString())));
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    final ThemeData theme = Theme.of(context);
    // final TextStyle dialogTextStyle =
    // theme.textTheme.subhead.copyWith(color: theme.textTheme.caption.color);

    return Scaffold(
      key: _scaffoldKey,
      appBar: AppBar(
        title: const Text('Dialogs'),
      ),
      body: ListView(
        padding: const EdgeInsets.symmetric(vertical: 24.0, horizontal: 72.0),
        children: <Widget>[
          ElevatedButton(
              child: const Text('SAMPLE'),
              onPressed: () {
                showDialog<void>(
                  context: context,
                  barrierDismissible: false, // user must tap button!
                  builder: (BuildContext context) {
                    return AlertDialog(
                      title: Text('Rewind and remember'),
                      content: SingleChildScrollView(
                        child: ListBody(
                          children: <Widget>[
                            Text('You will never be satisfied.'),
                            Text('You\’re like me. I’m never satisfied.'),
                          ],
                        ),
                      ),
                      actions: <Widget>[
                        FilledButton(
                          child: Text('Regret'),
                          onPressed: () {
                            Navigator.of(context).pop();
                          },
                        ),
                      ],
                    );
                  },
                );
              }),
          ElevatedButton(
            child: const Text('ALERT'),
            onPressed: () {
              showDemoDialog<DialogDemoAction>(
                context: context,
                child: AlertDialog(
                  content: Text(
                    "Discard draft?",
                    // style: dialogTextStyle,
                  ),
                  actions: <Widget>[
                    FilledButton(
                      child: const Text('CANCEL'),
                      onPressed: () {
                        Navigator.pop(context, DialogDemoAction.cancel);
                      },
                    ),
                    FilledButton(
                      child: const Text('DISCARD'),
                      onPressed: () {
                        Navigator.pop(context, DialogDemoAction.discard);
                      },
                    ),
                  ],
                ),
              );
            },
          ),
          ElevatedButton(
            child: const Text('ALERT WITH TITLE'),
            onPressed: () {
              showDemoDialog<DialogDemoAction>(
                context: context,
                child: AlertDialog(
                  title: const Text('Use Google\'s location service?'),
                  content: Text(
                    _alertWithTitleText,
                    // style: dialogTextStyle,
                  ),
                  actions: <Widget>[
                    FilledButton(
                      child: const Text('DISAGREE'),
                      onPressed: () {
                        Navigator.pop(context, DialogDemoAction.disagree);
                      },
                    ),
                    FilledButton(
                      child: const Text('AGREE'),
                      onPressed: () {
                        Navigator.pop(context, DialogDemoAction.agree);
                      },
                    ),
                  ],
                ),
              );
            },
          ),
          ElevatedButton(
            child: const Text('SIMPLE'),
            onPressed: () {
              showDemoDialog<String>(
                context: context,
                child: SimpleDialog(
                  title: const Text('Set backup account'),
                  children: <Widget>[
                    DialogDemoItem(
                      icon: Icons.account_circle,
                      color: theme.primaryColor,
                      text: 'username@gmail.com',
                      onPressed: () {
                        Navigator.pop(context, 'username@gmail.com');
                      }, key: _scaffoldKey,
                    ),
                    DialogDemoItem(
                      icon: Icons.account_circle,
                      color: theme.primaryColor,
                      text: 'user02@gmail.com',
                      onPressed: () {
                        Navigator.pop(context, 'user02@gmail.com');
                      }, key: _scaffoldKey,
                    ),
                    DialogDemoItem(
                      icon: Icons.add_circle,
                      text: 'add account',
                      color: theme.disabledColor, key: _scaffoldKey, onPressed: () {  },
                    ),
                  ],
                ),
              );
            },
          ),
        ]
        // Add a little space between the buttons
            .map<Widget>((Widget button) {
          return Container(
            padding: const EdgeInsets.symmetric(vertical: 8.0),
            child: button,
          );
        }).toList(),
      ),
    );
  }
}

class DialogDemoItem extends StatelessWidget {
  const DialogDemoItem(
      {required Key key, required this.icon, required this.color, required this.text, required this.onPressed})
      : super(key: key);

  final IconData icon;
  final Color color;
  final String text;
  final VoidCallback onPressed;

  @override
  Widget build(BuildContext context) {
    return SimpleDialogOption(
      onPressed: onPressed,
      child: Row(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: <Widget>[
          Icon(icon, size: 36.0, color: color),
          Padding(
            padding: const EdgeInsets.only(left: 16.0),
            child: Text(text),
          ),
        ],
      ),
    );
  }
}
