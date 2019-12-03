// 带下划线的标题

import 'package:flutter/material.dart';
import 'package:kidkid/util/global_colors.dart';

class TitleLine extends StatelessWidget {

  final String title;

  TitleLine({ this.title });

  @override
  Widget build(BuildContext context) {
    return Stack(
      children: <Widget>[
        Positioned(
          bottom: 10,
          child: ClipRRect(
            borderRadius: BorderRadius.circular(5.0),
            child: Container(
              width: this.title.length * 25.0,
              height: 10,
              color: GlobalColors.red,
            ),
          )
        ),
        Positioned(
          width: this.title.length * 25.0,
          bottom: 10,
          child: Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Text(
                this.title ?? '', 
                style: TextStyle(color: GlobalColors.black, letterSpacing: 5.0, fontSize: 20.0, fontWeight: FontWeight.bold)
              ),
            ],
          ),
        )
      ],
    );
  }
}