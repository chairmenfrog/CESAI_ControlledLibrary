// 动画片model

import 'package:json_annotation/json_annotation.dart';
part 'cartoon_model.g.dart';

@JsonSerializable()
class CartoonModel extends Object {
  final String id;
  final String title;
  final String img;
  final String cartoon;

  CartoonModel(this.id, this.title, this.img, this.cartoon);

  factory CartoonModel.fromJson(Map<String, dynamic> json) => _$CartoonModelFromJson(json);
  Map<String, dynamic> toJson() => _$CartoonModelToJson(this);
}