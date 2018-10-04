package com.chiang.chnplayer.music.domain;//package com.chiang.chnplayer.music.domain;
//
//import java.util.ArrayList;
//
//import android.content.ContentResolver;
//import android.content.ContentUris;
//import android.content.Context;
//import android.database.Cursor;
//import android.provider.MediaStore;
//import com.chiang.chnplayer.music.domain.SongInfo;
//import com.chiang.chnplayer.music.domain.SongInfoItem;
//
//public class SongInfoData {
//
//	/**
//	 * 获取 所以音乐的音乐名称
//	 * 
//	 * @param SongInfos
//	 * @return
//	 */
//	public static String[] GetAll(ArrayList<SongInfo> SongInfos) {
//		String[] names = new String[SongInfos.size()];
//		for (int i = 0; i < names.length; i++) {
//			names[i] = SongInfos.get(i).getSongInfoName();
//		}
//		return names;
//	}
//
//	public static ArrayList<SongInfo> getMultiDatas(Context context) {
//		ArrayList<SongInfo> SongInfos = new ArrayList<SongInfo>();
//		// 循环找出所有的歌曲和信息
//		ContentResolver resolver = context.getContentResolver();
//		Cursor SongInfoCursor = resolver.query(
//				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
//				null);
//		int SongInfoColumnIndex;
//		SongInfo SongInfo;
//		// 遍历游标内容
//		if (null != SongInfoCursor && SongInfoCursor.getCount() > 0) {
//			for (SongInfoCursor.moveToFirst(); !SongInfoCursor.isAfterLast(); SongInfoCursor
//					.moveToNext()) {
//				SongInfo = new SongInfo();
//				// 取得音乐的名字
//				SongInfo.setId(SongInfoCursor.getInt(SongInfoCursor
//						.getColumnIndex("_id")));
//				SongInfoColumnIndex = SongInfoCursor
//						.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE);
//				SongInfo.setSongInfoName(SongInfoCursor.getString(SongInfoColumnIndex));
//				SongInfoColumnIndex = SongInfoCursor
//						.getColumnIndex(MediaStore.Audio.AudioColumns.DATA);
//				SongInfo.setSavePath(SongInfoCursor.getString(SongInfoColumnIndex));
//				SongInfoColumnIndex = SongInfoCursor
//						.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM);
//				SongInfo.setAlbumName(SongInfoCursor.getString(SongInfoColumnIndex));
//				SongInfoColumnIndex = SongInfoCursor
//						.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST);
//				SongInfo.setSinger(SongInfoCursor.getString(SongInfoColumnIndex));
//				SongInfoColumnIndex = SongInfoCursor
//						.getColumnIndex(MediaStore.Audio.AudioColumns.DURATION);
//				SongInfo.setTime(SongInfoCursor.getString(SongInfoColumnIndex));
//				SongInfoColumnIndex = SongInfoCursor
//						.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM_KEY);
//				SongInfo.setAlbumkey(SongInfoCursor.getString(SongInfoColumnIndex));
//				SongInfos.add(SongInfo);
//			}
//			SongInfoCursor.close();
//		}
//		return SongInfos;
//	}
//
//	public static String gealbumby(Context context, String albumkey) {
//		// 取得歌曲对应的专辑Key 这里由于专辑图片太占内存 就不在播放列表上显示了
//		String[] argArr = { albumkey };
//		Cursor albumCursor = context.getContentResolver().query(
//				MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, null,
//				MediaStore.Audio.AudioColumns.ALBUM_KEY + " = ?", argArr, null);
//		if (null != albumCursor && albumCursor.getCount() > 0) {
//			albumCursor.moveToFirst();
//			int albumArtIndex = albumCursor
//					.getColumnIndex(MediaStore.Audio.AlbumColumns.ALBUM_ART);
//			String SongInfoAlbumArtPath = albumCursor.getString(albumArtIndex);
//			if (null != SongInfoAlbumArtPath) {
//				return SongInfoAlbumArtPath;
//			} else {
//				return null;
//			}
//		} else {
//			return null;
//		}
//	}
//
//	public static ArrayList<SongInfo> getMultiDataBycurrsor(Context context,
//			Cursor SongInfoCursor) {
//		ArrayList<SongInfo> SongInfos = new ArrayList<SongInfo>();
//		// 循环找出所有的歌曲和信息
//		int SongInfoColumnIndex;
//		// 遍历游标内容
//		if (null != SongInfoCursor && SongInfoCursor.getCount() > 0) {
//			for (SongInfoCursor.moveToFirst(); !SongInfoCursor.isAfterLast(); SongInfoCursor
//					.moveToNext()) {
//				SongInfo SongInfo = new SongInfo();
//				// 取得音乐的名字
//				SongInfo.setId(SongInfoCursor.getInt(SongInfoCursor
//						.getColumnIndex("_id")));
//				SongInfoColumnIndex = SongInfoCursor
//						.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE);
//				SongInfo.setSongInfoName(SongInfoCursor.getString(SongInfoColumnIndex));
//				SongInfoColumnIndex = SongInfoCursor
//						.getColumnIndex(MediaStore.Audio.AudioColumns.DATA);
//				SongInfo.setSavePath(SongInfoCursor.getString(SongInfoColumnIndex));
//				SongInfoColumnIndex = SongInfoCursor
//						.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM);
//				SongInfo.setAlbumName(SongInfoCursor.getString(SongInfoColumnIndex));
//				SongInfoColumnIndex = SongInfoCursor
//						.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST);
//				SongInfo.setSinger(SongInfoCursor.getString(SongInfoColumnIndex));
//				SongInfoColumnIndex = SongInfoCursor
//						.getColumnIndex(MediaStore.Audio.AudioColumns.DURATION);
//				SongInfo.setTime(SongInfoCursor.getString(SongInfoColumnIndex));
//				// 取得歌曲对应的专辑Key 这里由于专辑图片太占内存 就不在播放列表上显示了
//				SongInfoColumnIndex = SongInfoCursor
//						.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM_KEY);
//				String[] argArr = { SongInfoCursor.getString(SongInfoColumnIndex) };
//				ContentResolver albumResolver = context.getContentResolver();
//				Cursor albumCursor = albumResolver.query(
//						MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, null,
//						MediaStore.Audio.AudioColumns.ALBUM_KEY + " = ?",
//						argArr, null);
//				if (null != albumCursor && albumCursor.getCount() > 0) {
//					albumCursor.moveToFirst();
//					int albumArtIndex = albumCursor
//							.getColumnIndex(MediaStore.Audio.AlbumColumns.ALBUM_ART);
//					if (null != albumCursor.getString(albumArtIndex)) {
//						SongInfo.setAlbumPath(albumCursor.getString(albumArtIndex));
//					} else {
//						SongInfo.setAlbumPath(null);
//					}
//				} else {
//					SongInfo.setAlbumPath(null);
//				}
//				albumCursor.close();
//				SongInfos.add(SongInfo);
//			}
//			SongInfoCursor.close();
//		}
//		return SongInfos;
//	}
//
//	/**
//	 * 获取指定id的音乐信息
//	 * 
//	 * @param context
//	 * @param id
//	 *            音乐id
//	 * @return SongInfo
//	 */
//	public static SongInfo getSongInfobyid(Context context, String id) {
//		// 循环找出所有的歌曲和信息
//		Cursor SongInfoCursor = context.getContentResolver().query(
//				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, "_id=?",
//				new String[] { id }, null);
//		int SongInfoColumnIndex;
//		SongInfo SongInfo = new SongInfo();
//		// 遍历游标内容
//		if (null != SongInfoCursor && SongInfoCursor.getCount() > 0) {
//			SongInfoCursor.moveToNext();
//			// 取得音乐的名字
//			SongInfoColumnIndex = SongInfoCursor
//					.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE);
//			SongInfo.setSongInfoName(SongInfoCursor.getString(SongInfoColumnIndex));
//			SongInfoColumnIndex = SongInfoCursor
//					.getColumnIndex(MediaStore.Audio.AudioColumns.DATA);
//			SongInfo.setSavePath(SongInfoCursor.getString(SongInfoColumnIndex));
//			SongInfoColumnIndex = SongInfoCursor
//					.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM);
//			SongInfo.setAlbumName(SongInfoCursor.getString(SongInfoColumnIndex));
//			SongInfoColumnIndex = SongInfoCursor
//					.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST);
//			SongInfo.setSinger(SongInfoCursor.getString(SongInfoColumnIndex));
//			SongInfoColumnIndex = SongInfoCursor
//					.getColumnIndex(MediaStore.Audio.AudioColumns.DURATION);
//			SongInfo.setTime(SongInfoCursor.getString(SongInfoColumnIndex));
//			// 取得歌曲对应的专辑Key 这里由于专辑图片太占内存 就不在播放列表上显示了
//			SongInfoColumnIndex = SongInfoCursor
//					.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM_KEY);
//			String albumkey = SongInfoCursor.getString(SongInfoColumnIndex);
//			SongInfo.setAlbumPath(gealbumby(context, albumkey));
//		}
//		SongInfoCursor.close();
//		return SongInfo;
//	}
//
//	public static ArrayList<SongInfo> getSongInfosByitem(ArrayList<SongInfoItem> items,
//			Context context) {
//		ArrayList<SongInfo> SongInfos = new ArrayList<SongInfo>();
//		if (items != null && items.size() > 0) {
//			int SongInfoColumnIndex;
//			for (SongInfoItem it : items) {
//				Cursor SongInfoCursor = context.getContentResolver().query(
//						ContentUris.withAppendedId(
//								MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
//								it.getSongInfoId()), null, null, null, null);
//				if (SongInfoCursor.moveToNext()) {
//					SongInfo SongInfo = new SongInfo();
//					SongInfo.setId(SongInfoCursor.getInt(SongInfoCursor
//							.getColumnIndex("_id")));
//					SongInfoColumnIndex = SongInfoCursor
//							.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE);
//					SongInfo.setSongInfoName(SongInfoCursor.getString(SongInfoColumnIndex));
//					SongInfoColumnIndex = SongInfoCursor
//							.getColumnIndex(MediaStore.Audio.AudioColumns.DATA);
//					SongInfo.setSavePath(SongInfoCursor.getString(SongInfoColumnIndex));
//					SongInfoColumnIndex = SongInfoCursor
//							.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM);
//					SongInfo.setAlbumName(SongInfoCursor.getString(SongInfoColumnIndex));
//					SongInfoColumnIndex = SongInfoCursor
//							.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST);
//					SongInfo.setSinger(SongInfoCursor.getString(SongInfoColumnIndex));
//					SongInfoColumnIndex = SongInfoCursor
//							.getColumnIndex(MediaStore.Audio.AudioColumns.DURATION);
//					SongInfo.setTime(SongInfoCursor.getString(SongInfoColumnIndex));
//					SongInfoColumnIndex = SongInfoCursor
//							.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM_KEY);
//					SongInfo.setAlbumkey(SongInfoCursor.getString(SongInfoColumnIndex));
//					// 将SongInfo信息添加到集合
//					SongInfos.add(SongInfo);
//				}
//			}
//		}
//
//		return SongInfos;
//	}
//
//}
