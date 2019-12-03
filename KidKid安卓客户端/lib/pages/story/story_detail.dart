// 故事详情页面

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:kidkid/util/global_colors.dart';

class StoryDetail extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Material(
      child: CupertinoPageScaffold(
      backgroundColor: GlobalColors.bgColor,
        navigationBar: CupertinoNavigationBar(
          border: null,
          backgroundColor: GlobalColors.white,
          actionsForegroundColor: GlobalColors.red,
          middle: Text('故事详情'),
          trailing: Icon(Icons.cloud_upload),
        ),
        child: Container(
          color: GlobalColors.white,
          child: Column(
            children: <Widget>[
              Expanded(
                child: ListView(
                  padding: EdgeInsets.all(10.0),
                  children: <Widget>[
                    Text("CGAffineTransform形变是通过仿射变换矩来控制的,其中平移是矩阵相加,旋转与缩放则是矩阵相乘,为了合并矩阵运算中的加法和乘法,引入了齐次坐标的概念,它提供了用矩阵运算把二维、三维甚至高维空间中的一个点集从一个坐标系变换到另一个坐标系的有效方法.CGAffineTransform形变就是把二维形变使用一个三维矩阵来表示,其中第三列总是(0,0,1),形变通过前两列来控制,系统提供了CGAffineTransformMake结构体来控制形变作者：蚊香酱",
                      style: TextStyle(fontWeight: FontWeight.w300, fontSize: 20.0),
                    ),
                    Container(
                      margin: EdgeInsets.symmetric(vertical: 20.0),
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceAround,
                        children: <Widget>[
                          Container(
                            width: 100.0,
                            height: 1.0,
                            color: Colors.grey,
                          ),
                          Text("成品展示"),
                          Container(
                            width: 100.0,
                            height: 1.0,
                            color: Colors.grey,
                          )
                        ],
                      ),
                    ),
                    Container(
                      padding: EdgeInsets.only(bottom: 10.0),
                      decoration: BoxDecoration(
                        border: Border(bottom: BorderSide(width: 0.2, color: Colors.grey))
                      ),
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: <Widget>[
                          Text("录音标题"),
                          Icon(Icons.play_circle_filled)
                        ],
                      )
                    )
                  ],
                )
              ),
              Container(
                height: 60.0,
                color: Colors.red,
              )
            ],
          ),
        )
      )
    );
  }
}