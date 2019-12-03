// 合集model

import 'package:json_annotation/json_annotation.dart';
part 'collection_model.g.dart';

@JsonSerializable()
class CollectionModel extends Object {
  final String id;
  final String title;
  final String img;

  CollectionModel(this.id, this.title, this.img);

  factory CollectionModel.fromJson(Map<String, dynamic> json) => _$CollectionModelFromJson(json);
  Map<String, dynamic> toJson() => _$CollectionModelToJson(this);
}