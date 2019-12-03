// 网络请求封装

import 'package:dio/dio.dart';

typedef void HttpSuccessCallBack(result);
typedef void HttpFailureCallBack(error);

class Http {
  
  static Future<void> get(String url, {Map<String, dynamic> params, HttpSuccessCallBack success, HttpFailureCallBack failure}) async {
    try {
      Response response = await Dio().get(url, queryParameters: params);
      if (success != null) {
        success(response.toString());
      }
    } catch (exception) {
      if (failure != null) {
        failure(exception);
      }
    }
  }

  static Future<void> post(String url, [Map<String, dynamic> params]) async {
    Response response = await Dio().post(url, queryParameters: params);
    return response.data;
  }
}