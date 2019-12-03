// AI助手状态

import 'package:flutter/material.dart';
import 'package:kidkid/http/API.dart';
import 'package:kidkid/http/Http.dart';
import 'dart:convert';
import 'package:convert/convert.dart';
import 'package:crypto/crypto.dart';

class AIProvider with ChangeNotifier {
  List<Object> chatList = [];

  final APP_SECRET = "57db87590bee4c3dabfd4604aaa28535";

  AIProvider() {
  }

  void chat(String str) {
    var params = {
      "appkey": "038313f698144a5599d18f99e0649144",
      "api": "nli",
      "timestamp": DateTime.now().millisecondsSinceEpoch,
      "cusid": "kidkid",
      "rq": json.encode({
        "data_type": "stt",
        "data": {
          "input_type": 1,
          "text": str,
        }
      })
    };

    params["sign"] = generateMd5(APP_SECRET + "api=nli" + "appkey=${params['appkey']}" + "timestamp=${params['timestamp']}" + APP_SECRET);
    Http.get(API_AI_BOT, 
      params: params, 
      success: (res) {
        print("xxx");
        print(params);
        print(res);
      }
    );
  }

  String generateMd5(String data) {
    var content = new Utf8Encoder().convert(data);
    var digest = md5.convert(content);
    // 这里其实就是 digest.toString()
    return hex.encode(digest.bytes);
  }
}