// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'cartoon_model.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

CartoonModel _$CartoonModelFromJson(Map<String, dynamic> json) {
  return CartoonModel(
    json['id'] as String,
    json['title'] as String,
    json['img'] as String,
    json['cartoon'] as String,
  );
}

Map<String, dynamic> _$CartoonModelToJson(CartoonModel instance) =>
    <String, dynamic>{
      'id': instance.id,
      'title': instance.title,
      'img': instance.img,
      'cartoon': instance.cartoon,
    };
