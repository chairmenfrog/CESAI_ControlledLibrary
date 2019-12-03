// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'music_model.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

MusicModel _$MusicModelFromJson(Map<String, dynamic> json) {
  return MusicModel(
    json['id'] as String,
    json['img'] as String,
    json['singer'] as String,
    json['song'] as String,
    json['title'] as String,
  );
}

Map<String, dynamic> _$MusicModelToJson(MusicModel instance) =>
    <String, dynamic>{
      'id': instance.id,
      'img': instance.img,
      'singer': instance.singer,
      'title': instance.title,
      'song': instance.song,
    };
