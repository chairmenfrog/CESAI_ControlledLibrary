import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:kidkid/pages/ai/ai.dart';
import 'package:kidkid/pages/video/video.dart';
import 'package:kidkid/providers/music_provider.dart';
import 'package:kidkid/providers/video_provider.dart';
import 'package:kidkid/util/global_colors.dart';
import 'package:kidkid/pages/main_page.dart';
import 'package:kidkid/pages/music/music.dart';
import 'package:provider/provider.dart';

class App extends StatelessWidget {
  Widget build(BuildContext context) {
    return MaterialApp(
      home: CupertinoTabScaffold(
        tabBar: CupertinoTabBar(
          border: null,
          backgroundColor: GlobalColors.white,
          items: [
            _getBottomNavigationBarItem(
              icon: Image.asset('images/i1.png'),
              activeIcon: Image.asset('images/i1_s.png')
            ),
            _getBottomNavigationBarItem(
              icon: Image.asset('images/voice.png'),
              activeIcon: Image.asset('images/ai.png')
            ),
            _getBottomNavigationBarItem(
              icon: Image.asset('images/i2.png'),
              activeIcon: Image.asset('images/i2_s.png')
            )
          ],
          iconSize: 25.0,
          activeColor: Color(0xFFFC1F50),
        ),
        tabBuilder: (context, index) {
          // return Container(
          //   color: GlobalColors.bgColor,
          // );
          Widget page;
          switch (index) {
            case 0: 
              page = MainPage(
                      title: '广场', 
                      body: MultiProvider(
                        providers: [
                          ChangeNotifierProvider(
                            builder: (context) => MusicProvider(),
                          )
                        ],
                        child: Music(),
                      )
                    ); 
              break;
            case 1: page = AI(); break;
            case 2: page = MainPage(
                      title: '视频', 
                      body: MultiProvider(
                        providers: [
                          ChangeNotifierProvider(
                            builder: (context) => VideoProvider(),
                          )
                        ],
                        child: Video(),
                      )
                    ); 
              break;
          }

          return Container(
            color: GlobalColors.bgColor,
            child: Column(
              children: <Widget>[
                Expanded(
                  child: page,
                ),
                // MiniPlayer()
                index == 1 ? Container(
                  height: 50.0,
                  color: Colors.green,
                ): Container()
              ],
            ),
          );
        },
      )
    );
  }

  BottomNavigationBarItem _getBottomNavigationBarItem({Widget icon, Widget activeIcon}) {
    return BottomNavigationBarItem(
      icon: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          Container(
            width: 35.0,
            height: 35.0,
            child: icon
          ),
          ClipOval(
            child: Container(
              width: 5.0,
              height: 5.0,
              color: GlobalColors.white,
            )
          )
        ],
      ),
      activeIcon: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          Container(
            width: 35.0,
            height: 35.0,
            child: activeIcon
          ),
          ClipOval(
            child: Container(
              width: 5.0,
              height: 5.0,
              color: GlobalColors.red,
            )
          )
        ],
      ),
      // title: Text(name)
    );
  }

  // AIBot 动画效果
  // Widget aibotTransition() {
  //   return 
  // }
}
