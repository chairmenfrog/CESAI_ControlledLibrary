// 游戏状态管理

import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:kidkid/http/Http.dart';
import 'package:kidkid/models/game/game_model.dart';

class GameProvider with ChangeNotifier {

  List<GameModel> gameList = [];
  
  int _page = 0;
  final int _maxPage = 10;

  GameProvider() {
    getData();
  }

  void getData() {
    var url = "http://h.4399.com/data/iphone_c13_$_page.js";
    Http.get(
      url, 
      success: (result) {
        print(result);
        var jsonData = json.decode(result)["data"];
        for (var gameJson in jsonData) {
          this.gameList.add(GameModel.fromJson(gameJson));
        }

        notifyListeners();
      }
    );
  }
}