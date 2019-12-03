// 画板

import 'dart:io';
import 'dart:ui';
import 'package:path/path.dart' as path;
import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter/services.dart';
import 'package:kidkid/pages/board/widgets/darw_board_painter.dart';
import 'package:kidkid/pages/board/widgets/sticker_list.dart';
import 'package:kidkid/util/global_colors.dart';
import 'package:kidkid/widgets/loading_dialog.dart';
import 'package:path_provider/path_provider.dart';
import 'package:provider/provider.dart';
import 'package:kidkid/providers/draw_board_provider.dart';

class DrawBoard extends StatelessWidget {

  final _repaintKey = GlobalKey();

  @override
  Widget build(BuildContext context) {
    var provider = Provider.of<DrawBoardProvider>(context);

    var size = MediaQuery.of(context).size;

    return Material(
      child: Stack(
        children: <Widget>[
          Positioned(
            top: 50.0,
            width: size.width,
            child: Container(
              padding: EdgeInsets.symmetric(horizontal: 10.0),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: <Widget>[
                  GestureDetector(
                    child: Icon(Icons.arrow_back_ios, color: GlobalColors.red),
                    onTap: () => Navigator.pop(context),
                  ),

                  Row(
                    children: <Widget>[
                      // 保存图片按钮
                      GestureDetector(
                        child: Icon(Icons.save, color: GlobalColors.red),
                        onTap: () async {
                          showDialog(
                            context: context,
                            barrierDismissible: false,
                            builder: (context) {
                              return LoadingDialog(text: '保存中');
                            }
                          );

                          RenderRepaintBoundary boundary = _repaintKey.currentContext.findRenderObject();
                          var image = await boundary.toImage();
                          ByteData byteData = await image.toByteData(format: ImageByteFormat.png);
                          var pngBytes = byteData.buffer.asUint8List();
                          
                          Directory appDocDir = await getApplicationDocumentsDirectory();
                          String appDocPath = appDocDir.path;

                          final unixTime = DateTime.now();
                          final imageFile = File(path.join(appDocPath, '$unixTime.png'));
                          await imageFile.writeAsBytes(pngBytes); 
                        },
                      ),
                    ],
                  )
                ],
              ),
            )
          ),
          Container(
            child: Column(
              children: <Widget>[
                Expanded(
                  child: RepaintBoundary(
                    key: _repaintKey,
                    child: Stack(
                      children: <Widget>[
                        CustomPaint(painter: DrawBoardPainter(provider.points)),
                        GestureDetector(
                          onPanUpdate: (details) {
                            RenderBox referenceBox = context.findRenderObject();
                            Offset localPosition = referenceBox.globalToLocal(details.globalPosition);
                            provider.addPoint(localPosition);
                            provider.addSticker(null);
                            provider.notify();
                          },
                          onPanEnd: (details) {
                            provider.addPoint(null);
                            provider.changePainerColor(Colors.red);
                          },
                        ),
                        Stack(
                          children: _getStickerList(context),
                        )
                      ],
                    ),
                  ),
                ),
                Container(
                  height: 150,
                  decoration: BoxDecoration(
                    color: GlobalColors.white,
                    border: Border(top: BorderSide(width: 0.5, color: Colors.grey), bottom: BorderSide(width: 0.5, color: Colors.grey))
                  ),
                  child: StickerList(),
                )
              ],
            ),
          )
        ],
      )
    );
  }

  List<Widget> _getStickerList(BuildContext context) {
    var provider = Provider.of<DrawBoardProvider>(context);

    List<Widget> lists = [];
    for (int i = 0; i < provider.stickerLists.length; i++) {
      var viewModel = provider.stickerLists[i];
      if (viewModel == null) continue; 
      lists.add(
        Positioned(
          left: viewModel.x,
          top: viewModel.y,
          child: GestureDetector(
            onPanUpdate: (details) {
              provider.updateSticker(i, x: details.delta.dx, y: details.delta.dy);
            },
            onLongPress: (){
              provider.deleteSticker(i);
            },
            child:Container(
              width: viewModel.width,
              height: viewModel.height,
              child: Image.asset(viewModel.imagePath),
            )
          ),
        )
      );
    }

    return lists;
  }
}