// 游戏列表

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:kidkid/models/game/game_model.dart';
import 'package:kidkid/util/global_colors.dart';
import 'package:kidkid/pages/game/widgets/game_cell.dart';
import 'package:kidkid/widgets/kk_web_view.dart';
import 'package:provider/provider.dart';
import 'package:kidkid/providers/game_provider.dart';

class Game extends StatelessWidget {

  @override
  Widget build(BuildContext context) {

    return Material(
      child: CupertinoPageScaffold(
      backgroundColor: GlobalColors.bgColor,
        navigationBar: CupertinoNavigationBar(
          border: null,
          backgroundColor: GlobalColors.white,
          actionsForegroundColor: GlobalColors.red,
          middle: Text('益智游戏')
        ),
        child: _getContentWidget(context)
      )
    );
  }

  Widget _getContentWidget(BuildContext context) {
    var provider = Provider.of<GameProvider>(context);
    var height = MediaQuery.of(context).size.height;
    if (provider.gameList.length == 0) {
      return Container(
        height: height,
        child: Row(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            Text("暂无数据", style: TextStyle(fontSize: 12.0, color: Colors.grey))
          ],
        ),
      );
    } else {
      return ListView.builder(
        itemCount: provider.gameList.length,
        itemBuilder: (context, index) {
          GameModel model = provider.gameList[index];
          return GestureDetector(
            child: GameCell(title: model.title, desc: model.category, content: model.html5introduce, image: Image.network(model.pic)),
            onTap: () {
              Navigator.push(
                context, 
                MaterialPageRoute(
                  builder: (context) => KKWebView(title: model.title, url: "http://h.4399.com/" + model.playlink)
                )
              );
            },
          );
        },
      );
    }
  }
}