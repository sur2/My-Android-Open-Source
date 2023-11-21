package com.pyong.myretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button getBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.print_text_view);
        getBtn = findViewById(R.id.get_btn);
        getBtn.setOnClickListener(v -> {
            getTrainPosition("4f5a42695470796f313130784644464d",
                    "json",
                    "realtimePosition",
                    0,
                    5,
                    "수인분당선");
        });

    }

    private void getTrainPosition(String key, String type, String service, int startIndex, int endIndex, String subwayNm) {
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        RetrofitAPI retrofitAPI = retrofitClient.getRetrofitAPI(); // RetrofitClient.getRetrofitAPI();

        Call<ResponseTrainPosition> resTrainPosition = retrofitAPI
                .getTrainPositionList(key, type, service, startIndex, endIndex, subwayNm);
        resTrainPosition.enqueue(new Callback<ResponseTrainPosition>() {
            @Override
            public void onResponse(Call<ResponseTrainPosition> call, Response<ResponseTrainPosition> response) {
                if (response.isSuccessful()) {
                    ResponseTrainPosition responseTrainPosition = response.body();
                    ErrorMessageDTO errorMessage = responseTrainPosition != null ? responseTrainPosition.getErrorMessage() : null;
                    if (errorMessage != null) {
                        textView.setText("onResponse " + errorMessage.toString());
                    }
                    int i = 0;
                    StringBuilder sb = new StringBuilder();
                    SubwayPositionDTO[] subwayPositionList = responseTrainPosition.getRealtimePositionList();
                    for (SubwayPositionDTO subwayPosition : subwayPositionList) {
                        sb.append("[").append(i++).append("]\n");
                        sb.append(subwayPosition.toString()).append("\n");
                    }
                    textView.setText(sb.toString());
                }

            }

            @Override
            public void onFailure(Call<ResponseTrainPosition> call, Throwable t) {
                textView.setText("onFailure " + t.getMessage());
            }
        });

    }
}