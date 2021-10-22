# AppWidget Test

#### 앱 위젯 빌드 페이지 https://developer.android.com/guide/topics/appwidgets




앱 위젯을 구현하기 위해 최소한 구성

- AppWidgetProviderInfo 객체
- AppWidgetProvider 클래스 구현
- 앱 레이아웃(.xml)



### Manifest에서 앱 위젯 선언

AndroidManifest.xml 파일에서 AppWidgetProvider 클래스 선언

```xml
<application...>
	<activity...>
    	...
    </action>
    	<!-- AppWidgetProvider 클래스 선언 -->
        <receiver android:name="ExampleAppWidgetProvider" >
        <intent-filter>
            <action android:name="android.appwidget.action.APPWIDGET_UPDATE" />
        </intent-filter>
        <meta-data android:name="android.appwidget.provider"
                   android:resource="@xml/example_appwidget_info" />
    </receiver>
</application>
```



### AppWidgetProviderInfo 메타데이터 추가

앱의 기본적인 특성을 정의

```xml
<?xml version="1.0" encoding="utf-8"?>
<!--https://developer.android.com/guide/topics/appwidgets?hl=ko-->
<appwidget-provider xmlns:android="http://schemas.android.com/apk/res/android"
    android:initialLayout="@layout/my_appwidget"
    android:minWidth="40dp"
    android:minHeight="40dp"
    android:previewImage="@drawable/test_logo_783px"
    android:resizeMode="horizontal|vertical"
    android:updatePeriodMillis="86400000"
    android:widgetCategory="home_screen">
</appwidget-provider>

    <!--
    선택사항
    android:previewImage="@drawable/preview"
    android:configure="com.example.android.ExampleAppWidgetConfigure"
    android:resizeMode="horizontal|vertical"
    -->
```



### AppWidgetProvider 클래스 사용

AppWidgetProvider을 상속받은 나만의 클래스 작성

```java
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
```

