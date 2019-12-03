// 歌单推荐Item

import 'package:flutter/material.dart';

class MusicFolderItem extends StatelessWidget {
  
  final String name;
  final Image image;

  MusicFolderItem({this.name, this.image});

  @override
  Widget build(BuildContext context) {
    
    double itemWH = (MediaQuery.of(context).size.width - 20 * 2 - 20 * 2) / 3.0;

    return Container(
      margin: EdgeInsets.only(right: 10.0),
      width: itemWH,
      height: itemWH * 0.1,
      // color: Colors.red,
      child: Stack(
        children: <Widget>[
          ClipRRect(
            borderRadius: BorderRadius.circular(10.0),
            child: image,
          ),
          // Padding(
          //   padding: EdgeInsets.all(10.0),
          //   child: Column(
          //     mainAxisAlignment: MainAxisAlignment.spaceBetween,
          //     crossAxisAlignment: CrossAxisAlignment.start,
          //     children: <Widget>[
          //       Text("", style: TextStyle(fontWeight: FontWeight.w600)),
          //       Row(
          //         mainAxisAlignment: MainAxisAlignment.end,
          //         children: <Widget>[
          //           Icon(Icons.play_circle_filled, color: Colors.grey)
          //         ],
          //       )
          //     ], 
          //   ),
          // )
        ],
      ),
    );
  }
}