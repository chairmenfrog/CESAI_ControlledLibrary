// 歌曲Cell

import 'package:flutter/material.dart';
import 'package:kidkid/util/global_colors.dart';

class MusicCell extends StatelessWidget {
  
  final String name;
  final String desc;
  final Image image;

  MusicCell(this.name, {this.desc, this.image});

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.symmetric(vertical: 10.0),
      // padding: EdgeInsets.symmetric(horizontal: 20.0),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: <Widget>[
          Row(
            children: <Widget>[
              ClipOval(
                child: Stack(
                  children: <Widget>[
                    Container(
                      width: 50.0,
                      height: 50.0,
                      color: GlobalColors.white,
                      child: image,
                    ),
                  ],
                ),
              ),
              SizedBox(width: 15.0),
              Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Text('$name', style: TextStyle(color: GlobalColors.black, fontSize: 14.0, fontWeight: FontWeight.bold)),
                  Text('$desc', style: TextStyle(color: Colors.grey, fontSize: 12.0))
                ],
              ),
            ],
          ),
          Icon(Icons.play_circle_filled, color: GlobalColors.black)
        ],
      )
    );
  }
}