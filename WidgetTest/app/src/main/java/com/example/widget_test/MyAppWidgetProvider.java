package com.example.widget_test;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

public class MyAppWidgetProvider extends AppWidgetProvider {

    /**
     * AppWidgetProviderInfo(.xml) updatePeriodMillis 속성에 의해 정의된 간격으로 앱 위젯을 업데이트하기 위해 호출
     * 사용자가 앱 위젯을 추가할 때도 호출
     * 단, 앱 구성 활동을 선언한 경우 구성 활동(객체)에서 첫 번째 업데이트를 실행해야함
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        final int N = appWidgetIds.length;
        Log.d("AppWidgetProvider", "appWidgetIds: " + N);

        for (int i = 0; i < N; i++) {
            Log.d("AppWidgetProvider", "onUpdate: " + appWidgetIds[i]);
            int appWidgetId = appWidgetIds[i];
            // 위젯에서 메인앱으로 이동하는 인텐트
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_appwidget);
//            views.setOnClickPendingIntent();

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }

    }

    /**
     * 위젯이 처음으로 배치될 때와 위젯의 크기가 조절될 때 호출
     */
    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
        Log.d("AppWidgetProvider", "onAppWidgetOptionsChanged: " + appWidgetId);
        appWidgetManager.getAppWidgetOptions(appWidgetId);
    }

    /**
     * 앱 위젯이 앱 위젯 호스트에서 삭제될 때마다 호출
     */
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            Log.d("AppWidgetProvider", "onDelete: " + appWidgetIds[i]);
        }
    }

    /**
     * 앱 위젯의 인스턴스가 처음으로 생성될 때 호출(앱 위젯을 두 개이상 추가해도 처음에만 호출)
     * 앱 위젯 인스턴스에서 한 번만 발생해야 할 작업을 여기서 실행
     */
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.d("AppWidgetProvider", "onEnabled");
    }

    /**
     * 앱 위젯의 마지막 인스턴스가 햅 위젯 호스트에서 삭제될 때 호출
     * onEnabled에서 실행된 모든 작업을 반드시 정리
     */
    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d("AppWidgetProvider", "onDisabled");
    }

    /**
     * 모든 브로드캐스트에서 AppWidgetProvider 콜백 메서드 이전에 호출
     * 브로드캐스트 리시버를 선언했기 때문에 일반적으로 이 메서드를 작성할 필요는 없음
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.d("AppWidgetProvider", "onReceive");
    }
}
