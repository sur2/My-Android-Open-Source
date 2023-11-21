package com.example.checkservice;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MessengerService extends Service {

    Messenger mToClient;
    Messenger mMessenger;
    Handler incomingHandler;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("Service", "onBind");
        mMessenger = new Messenger(incomingHandler);
        return mMessenger.getBinder();
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Service", "onCreate");
        incomingHandler = new Handler(getMainLooper()) {

            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    UserItem item = new UserItem(1, "test01", true, new String[]{"0", "1"});
                    Log.i("Service", "Service msg: 1");
                    Bundle bundle = new Bundle();
                    bundle.putString("testString", "To Service");
                    bundle.putParcelable("test", item);

                    Message message = Message.obtain();
                    message.what = 1;
                    message.setData(bundle);
                    mToClient = msg.replyTo;
                    try {
                        mToClient.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }

        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
