// 语音助手功能
import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';
import 'package:kidkid/providers/ai_provider.dart';
import 'package:kidkid/util/global_colors.dart';
import 'package:kidkid/widgets/title_line.dart';

class AI extends StatelessWidget {

  AI() {
    AIProvider().chat("童话故事");
  }

  @override
  Widget build(BuildContext context) {
    return Material(
      child: CupertinoPageScaffold(
        backgroundColor: GlobalColors.bgColor,
        navigationBar: CupertinoNavigationBar(
          border: null,
          backgroundColor: GlobalColors.bgColor,
          middle: TitleLine(title: '小K'),
          // leading: TitleLine(title: '${title ?? ""}'),
        ),
        child: Container(
          // child: ListView.builder(
          //   itemCount: 10,
          //   itemBuilder: (context, index) {
          //     if (index == 0) {
          //       return TitleLine(title: '你好，我是小K');
          //     } else {
          //       return TitleLine(title: '你好，我是小K');
          //     }
          //   },
          // ),
        )
      ),
    );
  }
}