// 画板底部贴图

import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:kidkid/models/draw_board/sticker_view_model.dart';
import 'package:kidkid/util/global_colors.dart';
import 'package:provider/provider.dart';
import 'package:kidkid/providers/draw_board_provider.dart';

typedef void StickerListDidSelectItemCallBack(StickerViewModel viewModel);

class StickerList extends StatelessWidget {

  final stickerCount = 36;
  final stickerCrossAxisCount = 8;

  final stickerItemWH = 30.0;

  // StickerList({this.stickerListDidSelectItemCallBack});

  @override
  Widget build(BuildContext context) {
    
    var screenWidth = MediaQuery.of(context).size.width;
    var crossSpacing = ((screenWidth - stickerCrossAxisCount * stickerItemWH) - 10 * 2) / (stickerCrossAxisCount - 1);

    return Container(
      color: GlobalColors.white,
      child: GridView.count(
        crossAxisSpacing: crossSpacing,
        mainAxisSpacing: 20.0,
        crossAxisCount: stickerCrossAxisCount,
        padding: EdgeInsets.symmetric(horizontal: 10.0, vertical: 20.0),
        children: _getItemList(context),
      )
    );
  }

  // 数据操作
  List<Widget> _getItemList(BuildContext context) {

    var screenSize = MediaQuery.of(context).size;
    var itemWH = 50.0;

    List<Widget> items = [];
    for (int i = 0;i < stickerCount;i++) {
      items.add(GestureDetector(
        child: Container(
          width: stickerItemWH,
          height: stickerItemWH,
          child: Image.asset('images/sticker/$i.png'),
        ),
        onTap: () {
          var stickerViewModel = StickerViewModel(imagePath: 'images/sticker/$i.png', x: (screenSize.width - itemWH) * 0.5, y: (screenSize.height - itemWH) * 0.5, width: itemWH, height: itemWH);
          Provider.of<DrawBoardProvider>(context).addSticker(stickerViewModel);
        },
      ));
    }
    
    return items;
  }
}