import 'package:flutter/material.dart';
import 'package:kidkid/models/cartoon/cartoon_model.dart';
import 'package:kidkid/pages/video/video_player.dart';
import 'package:kidkid/util/global_colors.dart';
import 'package:kidkid/widgets/kk_web_view.dart';

class VideoCell extends StatelessWidget {

  final CartoonModel model;

  VideoCell(this.model);

  @override
  Widget build(BuildContext context) {
    // return GestureDetector(
    //   onTap: () {
    //     Navigator.push(context, MaterialPageRoute(
    //       builder: (context) => VideoPlayer(model.cartoon)
    //     ));
    //   },
    //   child: 
    // );
    return Container(
      margin: EdgeInsets.symmetric(vertical: 5.0),
      height: 120.0,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          ClipRRect(
            borderRadius: BorderRadius.circular(10.0),
            child: Container(
              color: Colors.grey,
              height: 100.0,
              child: Image.network(model.img, fit: BoxFit.cover,),
            )
          ),
          Text(model.title, style: TextStyle(fontWeight: FontWeight.bold))
        ],
      ),
    );
  }
}