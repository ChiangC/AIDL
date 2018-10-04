package com.chiang.chnplayer.music.common;

import android.R.integer;

public class MusicPlayState {

	public static final int MPS_NO_FILE = -1;		// 无音乐文件
	
	public static final int MPS_INVALID = 0;		// 当前音乐文件无效	
	
	public static final int MPS_PREPARED = 1;		// 准备就绪
	
	public static final int MPS_PLAYING = 2;		// 播放中
	
	public static final int MPS_PAUSE = 3;			// 暂停
	
	public static final int MPS_END=4;              //结束
	
	
	public static final String PLAY_STATE_NAME = "PLAY_STATE_NAME";
	public static final String PLAY_MUSIC_INDEX = "PLAY_MUSIC_INDEX";
	public static final String MUSIC_INVALID = "MUSIC_INVALID";
	public static final String MUSIC_PREPARED = "MUSIC_PREPARED";
	public static final String MUSIC_PLAY = "MUSIC_PLAY";
	public static final String MUSIC_PAUSE = "MUSIC_PAUSE";
	public static final String MUSIC_STOP = "MUSIC_STOP";
	
}