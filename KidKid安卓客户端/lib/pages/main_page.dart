// Tabbar选项卡使用的结构

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:kidkid/util/global_colors.dart';
import 'package:kidkid/widgets/title_line.dart';

class MainPage extends StatelessWidget {

  final String title;
  final Widget body;
  final List<ChangeNotifier> providers;

  MainPage({this.title, this.body, this.providers});

  Widget build(BuildContext context) {

    return Material(
      child: CupertinoPageScaffold(
        backgroundColor: GlobalColors.bgColor,
        navigationBar: CupertinoNavigationBar(
          heroTag: this.title,
          transitionBetweenRoutes: false,
          border: null,
          backgroundColor: GlobalColors.bgColor,
          leading: TitleLine(title: '${title ?? ""}'),
          automaticallyImplyMiddle: false,
          trailing: Icon(Icons.settings, color: Colors.grey, size: 24.0),
        ),
        child: body
      ),
    );
  }
}