// 网页浏览器

import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';
import 'package:kidkid/util/global_colors.dart';
import 'package:webview_flutter/webview_flutter.dart';

class KKWebView extends StatelessWidget {
  
  final String title;
  final String url;

  KKWebView({Key key, this.title, this.url});

  @override
  Widget build(BuildContext context) {
    return CupertinoPageScaffold(
      backgroundColor: GlobalColors.bgColor,
      navigationBar: CupertinoNavigationBar(
        border: null,
        backgroundColor: GlobalColors.white,
        actionsForegroundColor: GlobalColors.red,
        middle: Text(title)
      ),
      child: WebView(
        initialUrl: url,
        javascriptMode: JavascriptMode.unrestricted,
      )
    );
  }
}