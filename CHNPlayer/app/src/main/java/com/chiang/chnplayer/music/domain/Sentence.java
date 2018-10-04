package com.chiang.chnplayer.music.domain;

import java.io.Serializable;

import com.chiang.chnplayer.utils.LogUtil;

import android.graphics.Color;

public class Sentence implements Serializable{

	private static final long DISAPPEAR_TIME = 1000L;
	private static final long serialVersionUID = 20071125L;
	private String content;
	private long fromTime;
	private long toTime;

	public Sentence(String sentenceStr) {
		this(sentenceStr, 0L, 0L);
	}

	public Sentence(String sentenceStr, long fromTime) {
		this(sentenceStr, fromTime, 0L);
	}

	public Sentence(String sentenceStr, long fromTime, long toTime) {
		this.content = sentenceStr;
		this.fromTime = fromTime;
		Object localObject;
		this.toTime = toTime;
	}

	public Color getBestInColor(Color paramColor1, Color paramColor2,
			long paramLong) {
		if (1.0F * (float) (paramLong - this.fromTime)
				/ (float) getDuring() > 0.1F)
			return paramColor1;
		long l = getDuring();
		float f = 1.0F * (float) (paramLong - this.fromTime)
				/ (0.1F * (float) l);
		if ((f > 1.0F) || (f < 0.0F))
			return paramColor1;
		return null;
	}

	public Color getBestOutColor(Color paramColor1, Color paramColor2,
			long paramLong) {
		if (isInTime(paramLong))
			return paramColor1;
		float f = 1.0F * (float) (paramLong - this.toTime) / 1000.0F;
		if ((f > 1.0F) || (f <= 0.0F))
			return paramColor2;
		return null;
	}

	public String getContent() {
		return this.content;
	}

//	public int getContentHeight(Paint paramPaint) {
//		Paint.FontMetrics localFontMetrics = paramPaint.getFontMetrics();
//		return (int) (2.0D + Math.ceil(localFontMetrics.descent
//				- localFontMetrics.top));
//	}
//
//	public int getContentWidth(Paint paramPaint) {
//		return (int) paramPaint.measureText(this.content);
//	}

	public long getDuring() {
		return this.toTime - this.fromTime;
	}

	public long getFromTime() {
		return this.fromTime;
	}

//	public int getHIncrease(Paint paramPaint, long paramLong) {
//		return (int) ((0 + getContentWidth(paramPaint)) * (1.0D * (paramLong - this.fromTime) / (this.toTime - this.fromTime)));
//	}
//
//	public long getTimeH(int paramInt, Paint paramPaint) {
//		return getDuring() * paramInt / getContentWidth(paramPaint);
//	}
//
//	public long getTimeV(int paramInt, Paint paramPaint) {
//		return getDuring() * paramInt / getContentHeight(paramPaint);
//	}
//
//	public long getToTime() {
//		return this.toTime;
//	}
//
//	public int getVIncrease(Paint paramPaint, long paramLong) {
//		return (int) ((0 + getContentHeight(paramPaint)) * (1.0D * (paramLong - this.fromTime) / (this.toTime - this.fromTime)));
//	}

	public boolean isInTime(long currentPosition) {
//		LogUtil.i("Sentence", "-------currentPosition:"+currentPosition+",fromTime:"+fromTime+", toTime:"+toTime);
		return (currentPosition >= this.fromTime) && (currentPosition <= this.toTime);
	}

	public void setFromTime(long time) {
		this.fromTime = time;
	}

	public void setToTime(long time) {
		this.toTime = time;
	}

	public String toString() {
		return "{" + this.fromTime + "(" + this.content + ")" + this.toTime
				+ "}";
	}

}
