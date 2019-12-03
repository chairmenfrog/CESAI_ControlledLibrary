// 今日推荐顶部分类

import 'package:flutter/material.dart';
import 'package:kidkid/util/global_colors.dart';

class MusicTypeItem extends StatelessWidget {

  final String name;
  final Image image;
  // final BuildContext context;
  final Function onTap;

  MusicTypeItem({this.name, this.image, this.onTap});
  
  @override
  Widget build(BuildContext context) {
    double itemWH = (MediaQuery.of(context).size.width - 20 * 2 - 20 * 2) / 3.0;

    var view = Container(
      width: itemWH,
      height: itemWH,
      decoration: BoxDecoration(
        color: GlobalColors.white,
        borderRadius: BorderRadius.circular(10.0)
      ),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          Container(
            width: 35.0,
            height: 35.0,
            child: image,
          ),
          SizedBox(height: 5.0),
          Text(name, style: TextStyle(color: GlobalColors.black, fontSize: 12.0))
        ],
      ),
    );

    return GestureDetector(
      onTap: () {
        Navigator.push(context, MaterialPageRoute(
          builder: (context) => onTap(context)
        ));
      },
      child: view
    );
  }
}