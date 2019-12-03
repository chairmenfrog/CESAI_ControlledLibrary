// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'game_model.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

GameModel _$GameModelFromJson(Map<String, dynamic> json) {
  return GameModel(
    json['title'] as String,
    json['pic'] as String,
    json['category'] as String,
    json['wapclicks'] as String,
    json['playlink'] as String,
    json['html5introduce'] as String,
    json['zzylink'] as String,
  );
}

Map<String, dynamic> _$GameModelToJson(GameModel instance) => <String, dynamic>{
      'title': instance.title,
      'pic': instance.pic,
      'category': instance.category,
      'wapclicks': instance.wapclicks,
      'playlink': instance.playlink,
      'html5introduce': instance.html5introduce,
      'zzylink': instance.zzylink,
    };
