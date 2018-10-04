package com.chiang.chnplayer;

/**
 * ==================================================================
 * Copyright (C) 2018 FMTech All Rights Reserved.
 *
 * @author Drew.Chiang
 * @email chiangchuna@gmail.com
 * @create_date 2018/10/4 11:54
 * <p>
 * ==================================================================
 */


public interface IMusicService extends android.os.IInterface {
    /**
     * Local-side IPC implementation stub class.
     */
    public static abstract class Stub extends android.os.Binder implements com.chiang.chnplayer.aidl.IMusicService {
        private static final java.lang.String DESCRIPTOR = "com.chiang.chnplayer.aidl.IMusicService";

        /**
         * Construct the stub at attach it to the interface.
         */
        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        /**
         * Cast an IBinder object into an com.chiang.chnplayer.aidl.IMusicService interface,
         * generating a proxy if needed.
         */
        public static com.chiang.chnplayer.aidl.IMusicService asInterface(android.os.IBinder obj) {
            if ((obj == null)) {
                return null;
            }
            android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin != null) && (iin instanceof com.chiang.chnplayer.aidl.IMusicService))) {
                return ((com.chiang.chnplayer.aidl.IMusicService) iin);
            }
            return new com.chiang.chnplayer.aidl.IMusicService.Stub.Proxy(obj);
        }

        @Override
        public android.os.IBinder asBinder() {
            return this;
        }

        @Override
        public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
            switch (code) {
                case INTERFACE_TRANSACTION: {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                case TRANSACTION_updateMusicList: {
                    data.enforceInterface(DESCRIPTOR);
                    java.util.List<com.chiang.chnplayer.aidl.SongInfo> _arg0;
                    _arg0 = data.createTypedArrayList(com.chiang.chnplayer.aidl.SongInfo.CREATOR);
                    this.updateMusicList(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_getPlayList: {
                    data.enforceInterface(DESCRIPTOR);
                    java.util.List<com.chiang.chnplayer.aidl.SongInfo> _arg0;
                    _arg0 = new java.util.ArrayList<com.chiang.chnplayer.aidl.SongInfo>();
                    this.getPlayList(_arg0);
                    reply.writeNoException();
                    reply.writeTypedList(_arg0);
                    return true;
                }
                case TRANSACTION_play: {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    boolean _result = this.play(_arg0);
                    reply.writeNoException();
                    reply.writeInt(((_result) ? (1) : (0)));
                    return true;
                }
                case TRANSACTION_pause: {
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result = this.pause();
                    reply.writeNoException();
                    reply.writeInt(((_result) ? (1) : (0)));
                    return true;
                }
                case TRANSACTION_stop: {
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result = this.stop();
                    reply.writeNoException();
                    reply.writeInt(((_result) ? (1) : (0)));
                    return true;
                }
            }
            return super.onTransact(code, data, reply, flags);
        }

        private static class Proxy implements com.chiang.chnplayer.aidl.IMusicService {
            private android.os.IBinder mRemote;

            Proxy(android.os.IBinder remote) {
                mRemote = remote;
            }

            @Override
            public android.os.IBinder asBinder() {
                return mRemote;
            }

            public java.lang.String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            @Override
            public void updateMusicList(java.util.List<com.chiang.chnplayer.aidl.SongInfo> musicFileList) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeTypedList(musicFileList);
                    mRemote.transact(Stub.TRANSACTION_updateMusicList, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override
            public void getPlayList(java.util.List<com.chiang.chnplayer.aidl.SongInfo> musicFileList) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(Stub.TRANSACTION_getPlayList, _data, _reply, 0);
                    _reply.readException();
                    _reply.readTypedList(musicFileList, com.chiang.chnplayer.aidl.SongInfo.CREATOR);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override
            public boolean play(int position) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                boolean _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(position);
                    mRemote.transact(Stub.TRANSACTION_play, _data, _reply, 0);
                    _reply.readException();
                    _result = (0 != _reply.readInt());
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }

            @Override
            public boolean pause() throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                boolean _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(Stub.TRANSACTION_pause, _data, _reply, 0);
                    _reply.readException();
                    _result = (0 != _reply.readInt());
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }

            @Override
            public boolean stop() throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                boolean _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(Stub.TRANSACTION_stop, _data, _reply, 0);
                    _reply.readException();
                    _result = (0 != _reply.readInt());
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }
        }

        static final int TRANSACTION_updateMusicList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
        static final int TRANSACTION_getPlayList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
        static final int TRANSACTION_play = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
        static final int TRANSACTION_pause = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
        static final int TRANSACTION_stop = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
    }

    public void updateMusicList(java.util.List<com.chiang.chnplayer.aidl.SongInfo> musicFileList) throws android.os.RemoteException;

    public void getPlayList(java.util.List<com.chiang.chnplayer.aidl.SongInfo> musicFileList) throws android.os.RemoteException;

    public boolean play(int position) throws android.os.RemoteException;

    public boolean pause() throws android.os.RemoteException;

    public boolean stop() throws android.os.RemoteException;
}

