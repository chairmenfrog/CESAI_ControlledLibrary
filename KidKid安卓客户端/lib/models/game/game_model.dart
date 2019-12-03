// 游戏模型

import 'package:json_annotation/json_annotation.dart';
part 'game_model.g.dart';

@JsonSerializable()
class GameModel extends Object {
  final String title;
  final String pic;
  final String category;
  final String wapclicks;
  final String playlink;
  final String html5introduce;
  final String zzylink;

  GameModel(this.title, this.pic, this.category, this.wapclicks, this.playlink, this.html5introduce, this.zzylink);

  factory GameModel.fromJson(Map<String, dynamic> json) => _$GameModelFromJson(json);
  Map<String, dynamic> toJson() => _$GameModelToJson(this);
}