import 'package:flutter/material.dart';
import 'package:kidkid/http/Http.dart';
import 'package:kidkid/models/cartoon/collection_model.dart';
import 'package:kidkid/pages/board/draw_board.dart';
import 'package:kidkid/pages/game/game.dart';
import 'package:kidkid/pages/music/music_more.dart';
import 'package:kidkid/pages/player/player.dart';
import 'package:kidkid/pages/story/story.dart';
import 'package:kidkid/providers/game_provider.dart';
import 'package:kidkid/providers/music_provider.dart';
import 'package:kidkid/util/global_colors.dart';
import 'package:kidkid/widgets/title_more.dart';
import 'package:kidkid/pages/music/widgets/music_type_item.dart';
import 'package:kidkid/pages/music/widgets/music_folder_item.dart';
import 'package:kidkid/pages/music/widgets/music_cell.dart';
import 'package:provider/provider.dart';
import 'package:kidkid/providers/draw_board_provider.dart';

class Music extends StatelessWidget {
  // 布局常量
  final navArea = 0;      // 金刚位
  final collectArea = 1;  // 歌单推荐
  final listsArea = 2;    // 内容区域

  @override
  Widget build(BuildContext context) {

    double itemWH = (MediaQuery.of(context).size.width - 20 * 2 - 20 * 2) / 3.0;

    var provider = Provider.of<MusicProvider>(context);

    return ListView.builder(
      itemCount: listsArea + provider.songList.length,
      itemBuilder: (context, index) {
        if (index == navArea) {
          return Container(
            padding: EdgeInsets.all(15.0),
            child: Column(
              children: <Widget>[
                TitleMore(title: '玩具箱'),
                Container(
                  margin: EdgeInsets.symmetric(vertical: 10.0),
                  child: Column(
                    children: <Widget>[
                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: <Widget>[
                          MusicTypeItem(
                            name: '涂鸦板', 
                            image: Image.asset('images/music/huaban.png'), 
                            onTap: (context) => MultiProvider(
                              providers: [
                                ChangeNotifierProvider<DrawBoardProvider>.value(
                                  value: DrawBoardProvider(),
                                )
                              ],
                              child:DrawBoard()
                            )
                          ),
                          MusicTypeItem(
                            name: '益智游戏', 
                            image: Image.asset('images/music/game.png'), 
                            onTap: (context) => MultiProvider(
                              providers: [
                                ChangeNotifierProvider<GameProvider>.value(
                                  value: GameProvider(),
                                )
                              ],
                              child:Game()
                            )),
                          MusicTypeItem(
                            name: '录制故事', 
                            image: Image.asset('images/music/jiazhang.png'), 
                            onTap: (context) => MultiProvider(
                              providers: [
                                ChangeNotifierProvider<GameProvider>.value(
                                  value: GameProvider(),
                                )
                              ],
                              child:Story()
                            )
                          ),
                        ],
                      )
                    ],
                  )
                ),              
              ],
            ),
          );
        } else if (index == collectArea) {
          return Column(
            children: <Widget>[
              Padding(
                padding: EdgeInsets.symmetric(horizontal: 20.0),
                child: TitleMore(title: '好听的故事合集')
              ),
              Container(
                height: itemWH,
                margin: EdgeInsets.symmetric(vertical: 10.0),
                child: ListView(
                  padding: EdgeInsets.only(left: 20.0),
                  scrollDirection: Axis.horizontal,
                  children: _getCollectionList(provider),
                ),
              ),
            ],
          );
        } else if (index == listsArea){
          return Container(
            margin: EdgeInsets.only(top: 10.0),
            padding: EdgeInsets.symmetric(horizontal: 20.0),
            child: TitleMore(title: '当前最热', pressMore: () {
              Navigator.push(context, MaterialPageRoute(
                  builder: (context) => MusicMore(provider)
              ));
            },)
          );
        } else {
          var model = provider.songList[index - listsArea];
          return GestureDetector(
            child: Padding(
              padding: EdgeInsets.symmetric(horizontal: 20.0),
              child: MusicCell(model.title, desc: model.singer, image: Image.network(model.img, fit: BoxFit.cover)),
            ),
            onTap: () {
              Navigator.push(context, MaterialPageRoute(
                  builder: (context) => Player(model)
              ));
            },
          );
        }
      },
    );
  }

  List<Widget> _getCollectionList(MusicProvider provider) {
    List<Widget> list = [];
    for (CollectionModel model in provider.collectionList) {  
      list.add(MusicFolderItem(name: model.title, image: Image.network(model.img, fit: BoxFit.fitHeight)));
    }

    return list;
  }
}