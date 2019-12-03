// 音乐模型
import 'package:json_annotation/json_annotation.dart';
part 'music_model.g.dart';

@JsonSerializable()
class MusicModel extends Object {
  final String id;
  final String img;
  final String singer;
  final String title;
  final String song;

  MusicModel(this.id, this.img, this.singer, this.song, this.title);

  factory MusicModel.fromJson(Map<String, dynamic> json) => _$MusicModelFromJson(json);
  Map<String, dynamic> toJson() => _$MusicModelToJson(this); 
}