package com.example.checkservice;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.concurrent.atomic.AtomicBoolean;

public class MessengerClient {

    Context mContext;

    Messenger mServiceMessenger;
    Messenger mMessenger;
    ServiceConnection mSvcCon;

    Handler incomingHandler;

    Thread mBindThread;

    AtomicBoolean flag;

    int[] bound;

    public MessengerClient(Context context) {
        this.mContext = context;
        this.bound = new int[1];
        this.flag = flag = new AtomicBoolean(false);

        this.mSvcCon = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.i("Client", "onServiceConnected: " + name.getClassName());
                Log.i("Client", Thread.currentThread().getName());
                mServiceMessenger = new Messenger(service);
                flag.set(true);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i("Client", "onServiceDisconnected: " + name.getClassName());
                mServiceMessenger = null;
                flag.set(false);
                if (mSvcCon != null)
                    unbindService();
            }
        };

        incomingHandler = new Handler(Looper.getMainLooper()) {

            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    Log.i("Client", "Receive msg: 1");
                    try {
                        Bundle bundle = msg.getData();
                        bundle.setClassLoader(UserItem.class.getClassLoader());
                        Log.i("Client", "Receive String: " + bundle.getString("testString"));
                        UserItem item = bundle.getParcelable("test");
                        Log.i("Client", "Receive Item: " + item.name);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.i("Client", "Exception: " + e.getMessage());
                    }
                }
            }
        };

        this.mMessenger = new Messenger(incomingHandler);
    }

    public void callMethod() {
        if (!flag.get() || mServiceMessenger == null) {
            new Handler(Looper.getMainLooper()).post(() -> {
                callMethod();
            });
            return;
        }
        Message message = Message.obtain();
        message.what = 1;
        message.replyTo = this.mMessenger;
        try {
            mServiceMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void bindService() {
        Intent intent = new Intent("action.TEST-SERVICE");
        intent.setPackage(mContext.getPackageName());
        Log.i("Client", Thread.currentThread().getName() + ": before bind.");
        mContext.bindService(intent, mSvcCon, Service.BIND_AUTO_CREATE);
        Log.i("Client", Thread.currentThread().getName() + ": after bind.");
    }

    public void unbindService() {
        mContext.unbindService(mSvcCon);
    }
}
