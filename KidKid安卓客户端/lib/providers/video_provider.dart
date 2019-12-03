import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:kidkid/http/API.dart';
import 'package:kidkid/http/Http.dart';
import 'package:kidkid/models/cartoon/cartoon_model.dart';

class VideoProvider with ChangeNotifier {
  List<CartoonModel> videoList = [];
  List<String> adList = [
    "http://zjimg.5054399.com/allimg/191106/15_191106173649_1.jpg",
    "http://zjimg.5054399.com/allimg/190904/15_190904150636_1.jpg",
    "http://zjimg.5054399.com/allimg/191108/15_191108134936_1.jpg"
  ];

  VideoProvider() {
    getData();
  }

  void getData() {
    Http.get(
      API_CARTOON_LIST,
      success: (result) {
        var jsonData = json.decode(result)["data"]["lists"];
        for (var videoJson in jsonData) {
          this.videoList.add(CartoonModel.fromJson(videoJson));
        }
        
        notifyListeners();
      },
      failure: (error) {
        print(error);
      }
    );
  }
}