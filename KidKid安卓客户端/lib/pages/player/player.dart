// 音乐播放器界面

import 'dart:ui';

import 'package:audioplayers/audioplayers.dart';
import 'package:flutter/material.dart';
import 'package:kidkid/models/music/music_model.dart';
import 'package:kidkid/util/global_colors.dart';

class Player extends StatefulWidget {
  final MusicModel music;
  int index = 0;

  Player(this.music, {this.index});

  _PlayerState createState() => new _PlayerState(music, index);
}

class _PlayerState extends State<Player> {

  AudioPlayer _audioPlayer = AudioPlayer();

  MusicModel _music;
  var _index;
  Duration _duration;
  Duration _currentTime;
  AudioPlayerState _playerState;

  _PlayerState(this._music, this._index) {
    _duration = Duration();
    _currentTime = Duration();
    _playerState = AudioPlayerState.PLAYING;

    _initListeners();
    play();
  }

  @override
  Widget build(BuildContext context) {

    Size size = MediaQuery.of(context).size;

    return Scaffold(
      body: Container(
        color: Color(0xFF232436),
        padding: EdgeInsets.symmetric(horizontal: 20, vertical: 40),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            GestureDetector(
              child: Icon(Icons.close, color: GlobalColors.white),
              onTap: () {
                Navigator.pop(context);
              },
            ),
            Container(
              width: size.width * 0.5,
              margin: EdgeInsets.symmetric(vertical: 10),
              child: Text(
                _music.title, 
                style: TextStyle(
                  fontSize: 20, 
                  color: Colors.white,
                  fontWeight: FontWeight.w500
                )
              ),
            ),
            Text(
              _music.singer,
              style: TextStyle(
                color: Colors.grey
              ),
            ),
            Expanded(
              child: Row(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: <Widget>[
                  Stack(
                    children: <Widget>[
                      Container(
                        width: size.width * 0.7,
                        height: size.width * 0.7,
                        decoration: BoxDecoration(
                          color: Colors.grey,
                          borderRadius: BorderRadius.circular(10.0),
                          image: DecorationImage(
                            image: NetworkImage(_music.img),
                            fit: BoxFit.cover
                          )
                        ),
                      ),
                      ClipRRect(
                        borderRadius: BorderRadius.circular(10.0),
                        child: BackdropFilter(
                          filter: ImageFilter.blur(sigmaX: 5.0, sigmaY: 5.0),
                          child: Container(
                            width: size.width * 0.7,
                            height: size.width * 0.7,
                            decoration: new BoxDecoration(
                              color: Colors.grey.shade200.withOpacity(0.4)
                            ),
                          ),
                        )
                      ),
                      Positioned(
                        top: (size.width * 0.7 - 50.0) * 0.5,
                        left: (size.width * 0.7 - 50.0) * 0.5,
                        child: GestureDetector(
                          child: Image.asset('images/player/${_playerState == AudioPlayerState.PLAYING ? "playing.png" : "pause.png"}', width: 50, height: 50),
                          onTap: () {
                            // var state = _playerState;
                            if (_playerState == AudioPlayerState.PLAYING) {
                              _audioPlayer.pause();
                            } else {
                              _audioPlayer.resume();
                            }
                            
                            // setState(() {
                            //   _playerState = state;
                            // });
                            
                          },
                        ),
                      )
                    ],
                  )
                ],
              ),
            ),
            Row(
              children: <Widget>[
                Text(
                  '${_currentTime.inMinutes.toString()}:${_currentTime.inSeconds - _currentTime.inMinutes * 60}',
                  style: TextStyle(
                    color: Colors.grey,
                    fontSize: 12
                  ),
                ),
                Expanded(
                  child: Slider(
                    value: _currentTime.inSeconds * 1.0,
                    max: _duration.inSeconds * 1.0,
                    min: 0.0,
                    activeColor: Color(0xFFFC1F49),
                    onChanged: (val) => {

                    },
                  ),
                ),
                Text(
                  '${_duration.inMinutes.toString()}:${_duration.inSeconds - _duration.inMinutes * 60}',
                  style: TextStyle(
                    color: Colors.grey,
                    fontSize: 12
                  ),
                )
              ],
            ),
            SizedBox(
              height: 10,
            ),
          ],
        ),
      ),
    );
  }

  void _initListeners() {
    _audioPlayer.onDurationChanged.listen((Duration d) {
      setState(() {
        _duration = d;
      });
    });

    _audioPlayer.onAudioPositionChanged.listen((Duration p) {
      setState(() {
        _currentTime = p;
      });
    });

    _audioPlayer.onPlayerStateChanged.listen((AudioPlayerState s) {
      setState(() {
        _playerState = s;
      });
    });
  }

  void play() {
    _audioPlayer.play(_music.song);
  }

  void pause() {
    _audioPlayer.pause();
  }

  dispose() {
    _audioPlayer.stop();
    _audioPlayer.release();
    super.dispose();
  }
}