// IMusicService.aidl
package com.chiang.chnplayer.aidl;

// Declare any non-default types here with import statements
import com.chiang.chnplayer.aidl.SongInfo;

interface IMusicService {

    void updateMusicList(in List<SongInfo> musicFileList);

    void getPlayList(out List<SongInfo> musicFileList);

    boolean play(int position);

    boolean pause();

    boolean stop();

}
