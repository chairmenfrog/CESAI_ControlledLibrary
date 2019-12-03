// 视频播放器
import 'package:chewie/chewie.dart';
import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';
import 'package:kidkid/util/global_colors.dart';
import 'package:video_player/video_player.dart';

class VideoPlayer extends StatefulWidget {
    final String url;
    
    VideoPlayer(this.url);

    @override
  State<StatefulWidget> createState() {
    return _VideoPlayerState(url);
  }
}

class _VideoPlayerState extends State<VideoPlayer> {

  final url;
  VideoPlayerController videoPlayerController;
  ChewieController chewieController;

  _VideoPlayerState(this.url);

  void initState() {
    super.initState();

    videoPlayerController = VideoPlayerController.network(url);
    chewieController = ChewieController(
      videoPlayerController: videoPlayerController,
      // aspectRatio: 16 / 9,
      autoPlay: true,
      looping: true,
      showControls: true,
      // 占位图
      // placeholder: new Container(
      //   child: Row(
      //     mainAxisAlignment: MainAxisAlignment.center,
      //     crossAxisAlignment: CrossAxisAlignment.center,
      //     children: <Widget>[
      //       Text('加载中')
      //     ],
      //   ),
      // ),

      // 是否在 UI 构建的时候就加载视频
      // autoInitialize: !true,

      // 拖动条样式颜色
      materialProgressColors: ChewieProgressColors(
        playedColor: Colors.red,
        handleColor: Colors.blue,
        backgroundColor: Colors.grey,
        bufferedColor: Colors.lightGreen,
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Material(
      child: CupertinoPageScaffold(
        backgroundColor: GlobalColors.bgColor,
          navigationBar: CupertinoNavigationBar(
            border: null,
            backgroundColor: GlobalColors.white,
            actionsForegroundColor: GlobalColors.red,
            middle: Text('视频播放')
          ),
          child: Container(
            child: Center(
              child: Chewie(
                controller: chewieController
            )
          )
        )
      )
    );
  }

  @override
  void dispose() {
    videoPlayerController.dispose();
    chewieController.dispose();
    super.dispose();
  }
}