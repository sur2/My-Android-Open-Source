# AppWidget Test

### Manifest에서 앱 위젯 선언

```xml
<application...>
	<activity...>
    	...
    </action>
        <receiver android:name="ExampleAppWidgetProvider" >
        <intent-filter>
            <action android:name="android.appwidget.action.APPWIDGET_UPDATE" />
        </intent-filter>
        <meta-data android:name="android.appwidget.provider"
                   android:resource="@xml/example_appwidget_info" />
    </receiver>
</application>
```