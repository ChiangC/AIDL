package com.chiang.chnplayer.music.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class SongInfo implements Parcelable{

	//MediaStore.Video.Media.TITLE 音乐名 
	//MediaStore.Audio.Media.DURATION 音乐的总时间            
	//MediaStore.Audio.Media.ARTIST 艺术家          
	//MediaStore.Audio.Media._ID id号            
	//MediaStore.Audio.Media.DISPLAY_NAME 音乐文件名
	//MediaStore.Audio.Media.DATA 音乐文件的路径
	
	private int singId;
	private String title;
	private String artist;
	private String displayName;
	private String dataPath;
	private long duration;
	private String albumName;
	private String albumPath;
	private String albumkey;
	
	public SongInfo(){
		
	}
	
	public SongInfo(int singId, String title, String artist,
			String displayName, String dataPath, long duration,
			String albumName, String albumPath, String albumkey) {
		this.singId = singId;
		this.title = title;
		this.artist = artist;
		this.displayName = displayName;
		this.dataPath = dataPath;
		this.duration = duration;
		this.albumName = albumName;
		this.albumPath = albumPath;
		this.albumkey = albumkey;
	}

	public SongInfo(Parcel source){
		this.singId = source.readInt();
		this.title = source.readString();
		this.artist = source.readString();
		this.displayName = source.readString();
		this.dataPath = source.readString();
		this.duration = source.readLong();
		this.albumkey=source.readString();
		this.albumName=source.readString();
		this.albumPath=source.readString();
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(singId);
		dest.writeString(title);
		dest.writeString(artist);
		dest.writeString(displayName);
		dest.writeString(dataPath);
		dest.writeLong(duration);
		dest.writeString(albumkey);
		dest.writeString(albumName);
		dest.writeString(albumPath);
	}

	 public static final Creator<SongInfo> CREATOR = new Creator<SongInfo>(){

		@Override
		public SongInfo createFromParcel(Parcel source) {
			return new SongInfo(source);
		}

		@Override
		public SongInfo[] newArray(int size) {
			return new SongInfo[size];
		}
		 
	 };

	public int getSingId() {
		return singId;
	}


	public void setSingId(int singId) {
		this.singId = singId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getArtist() {
		return artist;
	}


	public void setArtist(String artist) {
		this.artist = artist;
	}


	public String getDisplayName() {
		return displayName;
	}


	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}


	public String getDataPath() {
		return dataPath;
	}


	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}


	public long getDuration() {
		return duration;
	}

	
	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getAlbumPath() {
		return albumPath;
	}

	public void setAlbumPath(String albumPath) {
		this.albumPath = albumPath;
	}

	public String getAlbumkey() {
		return albumkey;
	}

	public void setAlbumkey(String albumkey) {
		this.albumkey = albumkey;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((albumName == null) ? 0 : albumName.hashCode());
		result = prime * result
				+ ((albumPath == null) ? 0 : albumPath.hashCode());
		result = prime * result
				+ ((albumkey == null) ? 0 : albumkey.hashCode());
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result
				+ ((dataPath == null) ? 0 : dataPath.hashCode());
		result = prime * result
				+ ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result + (int) (duration ^ (duration >>> 32));
		result = prime * result + singId;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SongInfo other = (SongInfo) obj;
		if (albumName == null) {
			if (other.albumName != null)
				return false;
		} else if (!albumName.equals(other.albumName))
			return false;
		if (albumPath == null) {
			if (other.albumPath != null)
				return false;
		} else if (!albumPath.equals(other.albumPath))
			return false;
		if (albumkey == null) {
			if (other.albumkey != null)
				return false;
		} else if (!albumkey.equals(other.albumkey))
			return false;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		if (dataPath == null) {
			if (other.dataPath != null)
				return false;
		} else if (!dataPath.equals(other.dataPath))
			return false;
		if (displayName == null) {
			if (other.displayName != null)
				return false;
		} else if (!displayName.equals(other.displayName))
			return false;
		if (duration != other.duration)
			return false;
		if (singId != other.singId)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SongInfo [singId=" + singId + ", title=" + title + ", artist="
				+ artist + ", displayName=" + displayName + ", dataPath="
				+ dataPath + ", duration=" + duration  + ", albumName=" + albumName + ", albumPath="
				+ albumPath + ", albumkey=" + albumkey + "]";
	}

	
}
