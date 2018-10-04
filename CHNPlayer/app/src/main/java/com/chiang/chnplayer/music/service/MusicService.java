package com.chiang.chnplayer.music.service;

import java.util.List;

import com.chiang.chnplayer.CHNPlayerApplication;
import com.chiang.chnplayer.music.aidl.IMusicService;
import com.chiang.chnplayer.music.common.MusicConstants;
import com.chiang.chnplayer.music.domain.SongInfo;
import com.chiang.chnplayer.music.player.MusicPlayer;
import com.chiang.chnplayer.utils.LogUtil;
import com.chiang.chnplayer.utils.PreferenceUtils;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.RemoteException;
import android.telephony.TelephonyManager;

public class MusicService extends Service {
	
private static final String TAG = "MusicService";
	
	private MusicPlayer mMusicPlayer;	
	private SDcardStateReceiver mSDcardStateReceiver;
	private PhoneStateReceiver mPhoneStateReceiver;
	private MyReceiver myReceiver;
	
	@Override
	public void onCreate() {
		super.onCreate();
		Context context=getApplicationContext();
//		mMusicPlayer=new MusicPlayer(CHNPlayerApplication.mContext);
		mMusicPlayer=new MusicPlayer(context);
		mSDcardStateReceiver=new SDcardStateReceiver();
		mPhoneStateReceiver=new PhoneStateReceiver();
		myReceiver=new MyReceiver();
		
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(Intent.ACTION_MEDIA_MOUNTED);
		intentFilter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);    
		intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED); 
		intentFilter.addAction(Intent.ACTION_MEDIA_EJECT);    
		intentFilter.addDataScheme("file"); 
		
		IntentFilter phoneStateIntentFilter=new IntentFilter();
		phoneStateIntentFilter.addAction("android.intent.action.PHONE_STATE");
		
		IntentFilter myIntentFilter=new IntentFilter();
		myIntentFilter.addAction(MusicConstants.ACTION_MUSIC_SERVICE_CMD);
		
		registerReceiver(mSDcardStateReceiver, intentFilter);
		registerReceiver(mPhoneStateReceiver, phoneStateIntentFilter);
		registerReceiver(myReceiver, myIntentFilter);
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		LogUtil.i(TAG, "-------MusicService:onBind()");
		return mBinder;
	}

	@Override
	public void onDestroy() {
		LogUtil.i(TAG, "-------MusicService:onDestroy()");
		unregisterReceiver(mSDcardStateReceiver);
		unregisterReceiver(mPhoneStateReceiver);
		unregisterReceiver(myReceiver);
		super.onDestroy();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		LogUtil.i(TAG, "-------MusicService:onStartCommand()");
		return super.onStartCommand(intent, flags, startId);
		
	}
	
	private IMusicService.Stub mBinder=new IMusicService.Stub() {
		
		@Override
		public boolean stop() throws RemoteException {
			
			return mMusicPlayer.stop();
		}
		
		@Override
		public void setPlayMode(int mode) throws RemoteException {
			mMusicPlayer.setPlayMode(mode);
		}
		
		@Override
		public void sendPlayStateBroadcast() throws RemoteException {
			mMusicPlayer.sendPlayStateBroadcast();
		}
		
		@Override
		public boolean seekTo(long rate) throws RemoteException {
			return mMusicPlayer.seekTo(rate);
		}
		
		@Override
		public void updateMusicList(List<SongInfo> musicFileList)
				throws RemoteException {
			mMusicPlayer.updateMusicList(musicFileList);
		}
		
		@Override
		public boolean rePlay() throws RemoteException {
			
			return mMusicPlayer.rePlay();
		}
		
		@Override
		public boolean playPre() throws RemoteException {
			
			return mMusicPlayer.playPre();
		}
		
		@Override
		public boolean playNext() throws RemoteException {
			
			return mMusicPlayer.playNext();
		}
		
		@Override
		public boolean play(int position) throws RemoteException {
			
			return mMusicPlayer.play(position);
		}
		
		@Override
		public boolean pause() throws RemoteException {
			
			return mMusicPlayer.pause();
		}
		
		@Override
		public int getPlayState() throws RemoteException {
			return mMusicPlayer.getPlayState();
		}
		
		@Override
		public int getPlayMode() throws RemoteException {
			return mMusicPlayer.getPlayMode();
		}
		
		@Override
		public void getPlayList(List<SongInfo> musicFileList)
				throws RemoteException {
			List<SongInfo> tmp = mMusicPlayer.getFileList();
			int count = tmp.size();
			for(int i = 0; i < count; i++)
			{
				musicFileList.add(tmp.get(i));
			}
		}
		
		@Override
		public long getDuration() throws RemoteException {
			
			return mMusicPlayer.getDuration();
		}
		
		@Override
		public long getCurPosition() throws RemoteException {
			
			return mMusicPlayer.getCurrentPosition();
		}
		
		@Override
		public void exit() throws RemoteException {
			mMusicPlayer.exit();
		}
	};
	
	
	@Override
	public boolean onUnbind(Intent intent) {
		LogUtil.i(TAG, "-------MusicService:onUnbind()");
		return super.onUnbind(intent);
	}
	
	class PhoneStateReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			int currentIndex=mMusicPlayer.getCurrentPlayIndex();
			TelephonyManager mTelManager = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			boolean isringpause = false;
			switch (mTelManager.getCallState()) {
			case TelephonyManager.CALL_STATE_RINGING:// 响铃
				if (mMusicPlayer != null && mMusicPlayer.isPlaying()) {
					PreferenceUtils.putInt("lastPlayedIndex", mMusicPlayer.getCurrentPlayIndex());
					PreferenceUtils.putString("lastPlayedMusicPath", mMusicPlayer.getFileList().get(mMusicPlayer.getCurrentPlayIndex()).getDataPath());
					mMusicPlayer.pause();
					isringpause = true;
				}
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:// 通话
				if (mMusicPlayer != null && mMusicPlayer.isPlaying()) {
					mMusicPlayer.pause();
					isringpause = true;
				}
				break;
			case TelephonyManager.CALL_STATE_IDLE:// 通话结束
				if (mMusicPlayer != null && isringpause == true) {
					mMusicPlayer.play(currentIndex);
					LogUtil.i(TAG, "-------currentIndex:"+currentIndex);
					isringpause = false;
				}
				break;
			}
		}
	}
	
	class SDcardStateReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			if (action.equals(Intent.ACTION_MEDIA_MOUNTED)) {

			} else if (action.equals(Intent.ACTION_MEDIA_UNMOUNTED)) {

			} else if (Intent.ACTION_MEDIA_SCANNER_FINISHED.equals(action)) {

			} else if (Intent.ACTION_MEDIA_EJECT.equals(action)) {
				mMusicPlayer.exit();
			}

		}
	}

	class MyReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			String action=intent.getAction();
			if(action.equals(MusicConstants.ACTION_MUSIC_SERVICE_CMD)){
				String cmd=intent.getStringExtra("command");
				if(cmd.equals("pause")){
					LogUtil.i(TAG, "-------received command to pause music play.");
					mMusicPlayer.pause();
				}
			}
		}
		
	}
}
