import 'package:flutter/material.dart';
import 'package:kidkid/util/global_colors.dart';
import 'package:kidkid/widgets/kk_web_view.dart';

class GameCell extends StatelessWidget {

  final String title;
  final String desc;
  final String content;
  final Image image;

  GameCell({this.title, this.desc, this.content, this.image});

  @override
  Widget build(BuildContext context) {
    var view = Container(
      height: 80.0,
      margin: EdgeInsets.only(top: 10.0),
      padding: EdgeInsets.symmetric(horizontal: 10.0),
      color: GlobalColors.white,
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: <Widget>[
          Container(
            width: 60.0,
            height: 60.0,
            child: ClipRRect(
              borderRadius: BorderRadius.circular(5.0),
              child: image
            )
          ),
          SizedBox(
            width: 5.0,
          ),
          Expanded(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                Text(title),
                Text(desc, style: TextStyle(fontSize: 12.0, color: Colors.grey)),
                Text(content, style: TextStyle(fontSize: 12.0, color: Colors.grey))
              ],
            ),
          ),
          Container(
            width: 60.0,
            height: 30.0,
            decoration: BoxDecoration(
              border: Border.all(color: Colors.green, width: 1),
              borderRadius: BorderRadius.circular(5.0)
            ),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: <Widget>[
                Text('开始玩', style: TextStyle(fontSize: 12.0, color: Colors.green))
              ],
            ),
          )
        ],
      )
    );

    return view;
  }
}