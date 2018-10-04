package com.chiang.chnplayer.music.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import io.vov.vitamio.activity.InitActivity;

import com.chiang.chnplayer.music.aidl.IMusicService;
import com.chiang.chnplayer.music.common.MusicPlayMode;
import com.chiang.chnplayer.music.common.MusicPlayState;
import com.chiang.chnplayer.music.domain.SongInfo;
import com.chiang.chnplayer.music.interfaces.IOnServiceConnectComplete;
import com.chiang.chnplayer.utils.LogUtil;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;

public class ServiceManager {

	private final static String TAG = "ServiceManager";
	private final static String SERVICE_ACTION_NAME = "com.chiang.chnplayer.music.service.musicservice";
	
	private static Context mContext;
	private IMusicService mIMusicService;
	private boolean isServiceConnected=false;
	private static MServiceConnetion mServiceConnetion;
	private IOnServiceConnectComplete mIOnServiceConnectComplete;

	public ServiceManager(Context context){
		initManager(context);
	}
	
	public void initManager(Context context) {
		mContext=context;
		mServiceConnetion=new MServiceConnetion();
	}

	private class MServiceConnetion implements ServiceConnection{
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service){ 
//			LogUtil.i(TAG, "-------Connected to MusicService");
			mIMusicService=IMusicService.Stub.asInterface(service);
			if(mIMusicService!=null){
				LogUtil.i(TAG, "-------Connected to MusicService");
				isServiceConnected=true;
				if(mIOnServiceConnectComplete!=null){
					mIOnServiceConnectComplete.OnServiceConnectComplete();
				}
			}else{
				LogUtil.i(TAG, "-------mIMusicService is null");
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			LogUtil.i(TAG, "-------ServiceDisconnected");
			
		}
		
	}
	
	public boolean connectService()
	{
		if (isServiceConnected == true){
			return true;
		}
		//warnning:Implicit intents with startService are not safe
//		Intent intent = new Intent(SERVICE_ACTION_NAME);
		Intent intent = new Intent(mContext, MusicService.class);
		if (mContext != null){
			LogUtil.i(TAG, "-------begin to connectService	");
			mContext.bindService(intent, mServiceConnetion,  Context.BIND_AUTO_CREATE);
			mContext.startService(intent);
			isServiceConnected = true;
			return  true;
		}
		return false;
	}
	
	public boolean disconnectService() {
		if (isServiceConnected == false) {
			return true;
		}
		Intent intent = new Intent(SERVICE_ACTION_NAME);
		if (mContext != null) {
			LogUtil.i(TAG, "-------begin to disconnectService");
			mContext.unbindService(mServiceConnetion);
//			mContext.stopService(intent);
			mIMusicService = null;
			isServiceConnected = false;
			LogUtil.i(TAG, "-------musicservice disconnected.");
			return true;
		}
		return false;
	}
	
	/**
	 * release resources,unbind service and stop service
	 */
	public void exit(){
		if(mIMusicService!=null){
			try {
				mIMusicService.exit();
			} catch (RemoteException e) {
				LogUtil.e(TAG, "-------exit() error:"+e.toString());
				e.printStackTrace();
			}
			//unbindService
			Intent intent =new Intent(SERVICE_ACTION_NAME);
			mContext.unbindService(mServiceConnetion);
			mContext.stopService(intent);
			mIMusicService=null;
			isServiceConnected=false;
		}
	}
	
	/**
	 * 
	 * @return return -1,if isn't connected to MusicService
	 */
	public long getCurrentPosition(){
		if (mIMusicService!=null) {
			try {
				return mIMusicService.getCurPosition();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}
	
	public long getDuration(){
		if (mIMusicService!=null) {
			try {
				return mIMusicService.getDuration();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}
	
	public List<SongInfo> getPlayList(){
		List<SongInfo> playList = new ArrayList<SongInfo>();
		if (mIMusicService!=null) {
			try {
				mIMusicService.getPlayList(playList);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return playList;
	}
	
	public int getPlayMode(){
		if (mIMusicService!=null) {
			try {
				return mIMusicService.getPlayMode();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return MusicPlayMode.MPM_ORDER_PLAY;
	}
	
	public int getPlayState(){
		if (mIMusicService!=null) {
			try {
				return mIMusicService.getPlayState();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return MusicPlayState.MPS_NO_FILE;
	}
	
	public boolean pause(){
		if (mIMusicService!=null) {
			try {
				return mIMusicService.pause();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean play(int position){
		if (mIMusicService!=null) {
			try {
				return mIMusicService.play(position);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean playNext(){
		if (mIMusicService!=null) {
			try {
				mIMusicService.playNext();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean playPrevious(){
		if (mIMusicService!=null) {
			try {
				mIMusicService.playPre();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean playPre(){
		if (mIMusicService!=null) {
			try {
				mIMusicService.playPre();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public void updatePlayList(List<SongInfo> playList){
		if (mIMusicService!=null) {
			try {
				mIMusicService.updateMusicList(playList);;
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean replay(){
		if (mIMusicService!=null) {
			try {
				return mIMusicService.rePlay();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public void reset(){
		if (mIMusicService!=null) {
			try {
				mIMusicService.exit();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean seekTo(long position){
		if (mIMusicService!=null) {
			try {
				return mIMusicService.seekTo(position);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public void setOnServiceConnectComplete(IOnServiceConnectComplete iConnectComplete){
		mIOnServiceConnectComplete = iConnectComplete;
	}
	
	public void setPlayMode(int mode){
		if (mIMusicService!=null) {
			try {
				mIMusicService.setPlayMode(mode);;
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean stop(){
		if (mIMusicService!=null) {
			try {
				return mIMusicService.stop();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return false;
	}


	void ai(){
		Message message = Message.obtain();
		message.replyTo;
		CopyOnWriteArrayList
	}

}
