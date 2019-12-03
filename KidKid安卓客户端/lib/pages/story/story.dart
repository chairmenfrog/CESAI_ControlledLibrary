// 故事列表页面

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:kidkid/pages/story/story_detail.dart';
import 'package:kidkid/util/global_colors.dart';

class Story extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Material(
      child: CupertinoPageScaffold(
      backgroundColor: GlobalColors.bgColor,
        navigationBar: CupertinoNavigationBar(
          border: null,
          backgroundColor: GlobalColors.white,
          actionsForegroundColor: GlobalColors.red,
          middle: Text('故事列表')
        ),
        child: ListView.builder(
          itemCount: 10,
          itemBuilder: (context, index) {
            return GestureDetector(
              onTap: () {
                Navigator.push(context, MaterialPageRoute(
                  builder: (context) => StoryDetail()
                ));
              },
              child: Container(
                height: 150.0,
                margin: EdgeInsets.symmetric(vertical: 10.0, horizontal: 20.0),
                padding: EdgeInsets.all(10.0),
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(10.0),
                  color: Colors.white,
                ),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: <Widget>[
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: <Widget>[
                        Row(
                          children: <Widget>[
                            Icon(Icons.title),
                            Text("我是标题啊啊啊啊啊", style: TextStyle(fontWeight: FontWeight.bold, fontSize: 18.0))
                          ],
                        ),
                        Text("作者")
                      ],
                    ),
                    SizedBox(height: 10.0),
                    Text("1、上报问题的提单规则：bug标题名称为【数据上报】+内容； 2、bug修复说明打分：0分：填写内容没分析价值，例如“done、已解决”；60分：只简单描述问题原因，不能对根本原因分析起到帮助，例如server端问题、代码逻辑错误；80分：有产生原因和修复方法说明，其他角色能看懂，并对分析和测试回归提供明确方向；100分：对bug产生原因和解决方法有详细说明，对避免出现同样问题有借鉴作用。 建议类问题，说明可较简单，测试人员缺省给80分。",
                      style: TextStyle(
                        fontWeight: FontWeight.w300
                      ),
                      overflow: TextOverflow.ellipsis,
                      softWrap: true,
                      maxLines: 4,
                    )
                  ],
                ),
              )
            );
          },
        )
      )
    );
  }
}