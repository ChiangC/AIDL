package com.chiang.chnplayer.music.aidl;

import com.chiang.chnplayer.music.domain.SongInfo;

interface IMusicService {
	void updateMusicList(in List<SongInfo> musicFileList);
	   
	   void getPlayList(out List<SongInfo> musicFileList);
	   
	   boolean rePlay();
	   
	   boolean play(int position);
	   
	   boolean pause();
	   
	   boolean stop();
	   
	   boolean playNext();
	   
	   boolean playPre();
	   
	   boolean seekTo(long rate);
	   
	   long getCurPosition();
	   
	   long getDuration();
	   
	   int getPlayState();
	   
	   int getPlayMode();
	   
	   void setPlayMode(int mode);
	   
	   void sendPlayStateBroadcast();
	   
	   void exit();
	   
}
