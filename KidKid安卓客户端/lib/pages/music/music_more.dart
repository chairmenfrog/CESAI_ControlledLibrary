// 更多音乐页面

import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';
import 'package:kidkid/pages/player/player.dart';
import 'package:kidkid/providers/music_provider.dart';
import 'package:kidkid/util/global_colors.dart';
import 'package:kidkid/pages/music/widgets/music_cell.dart';

class MusicMore extends StatelessWidget {

  MusicProvider provider;

  MusicMore(this.provider);

  @override
  Widget build(BuildContext context) {
    return Material(
      child: CupertinoPageScaffold(
      backgroundColor: GlobalColors.bgColor,
        navigationBar: CupertinoNavigationBar(
          border: null,
          backgroundColor: GlobalColors.white,
          actionsForegroundColor: GlobalColors.red,
          middle: Text('更多')
        ),
        child: ListView.builder(
          itemCount: provider.songList.length,
          itemBuilder: (context, index) {
            var model = provider.songList[index];
            return GestureDetector(
              child: Padding(
                padding: EdgeInsets.symmetric(horizontal: 20.0),
                child:  MusicCell(model.title, desc: model.singer, image: Image.network(model.img, fit: BoxFit.cover)),
              ),
              onTap: () {
                Navigator.push(context, MaterialPageRoute(
                    builder: (context) => Player(model)
                ));
              },
            );
          },
        )
      )
    );
  }
}