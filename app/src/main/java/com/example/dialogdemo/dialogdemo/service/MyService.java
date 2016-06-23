package com.example.dialogdemo.dialogdemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.FileDescriptor;

/**
 * Created by Administrator on 2016/6/20 0020.
 */
public class MyService extends Service {

    public MyBinder mBinder = new MyBinder();
    public MyService() {
        super();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("lulu","服务创建了");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("lulu","服务开启了"+super.onStartCommand(intent, flags, startId));
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("lulu","服务销毁了");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("lulu","服务解绑了"+super.onUnbind(intent));
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d("lulu","服务重新绑定了");
    }
    public class MyBinder extends Binder {
        public void startDownload(){
            Log.d("TAG","startDownload 执行了");
        }
    }
}
