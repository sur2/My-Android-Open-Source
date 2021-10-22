# NumberPickerTest
시간, 날짜 등 숫자를 선택하는 View



### 사용자 정의 Class

class

```java
package com.example.numberpickertest;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.NumberPicker;

public class MyNumberPicker extends NumberPicker {
    // 생성자의 매개변수에 Context, AttributeSet 필수
    public MyNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
```

xml

```xml
<com.example.numberpickertest.MyNumberPicker
            android:id="@+id/np_hour"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />
```

