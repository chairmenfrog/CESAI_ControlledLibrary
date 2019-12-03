// 首页歌曲状态管理

import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:kidkid/http/API.dart';
import 'package:kidkid/http/Http.dart';
import 'package:kidkid/models/cartoon/collection_model.dart';
import 'package:kidkid/models/music/music_model.dart';

class MusicProvider with ChangeNotifier {

  List<MusicModel> songList = [];
  List<CollectionModel> collectionList = [];

  MusicProvider() {
    getData();
  }

  void getData() {
    Http.get(
      API_SONG_LIST,
      success: (result) {
        var jsonData = json.decode(result)["data"]["lists"];
        for (var musicJson in jsonData) {
          this.songList.add(MusicModel.fromJson(musicJson));
        }
        
        notifyListeners();
      },
      failure: (error) {
        print(error);
      }
    );

    Http.get(
      API_COLLECTION_LIST,
      success: (result) {
        var jsonData = json.decode(result)["data"]["lists"];
        for (var collectionJson in jsonData) {
          this.collectionList.add(CollectionModel.fromJson(collectionJson));
        }
        
        notifyListeners();
      },
      failure: (error) {
        print(error);
      }
    );
  }
}