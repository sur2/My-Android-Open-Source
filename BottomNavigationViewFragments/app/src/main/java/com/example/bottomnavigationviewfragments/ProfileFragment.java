package com.example.bottomnavigationviewfragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    public static final String TAG = "로그";
    public static final String FragmentName = "ProfileFragment";
    private static ProfileFragment instance = null;

    public static ProfileFragment newInstance() {
        if (instance == null) {
            instance = new ProfileFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, FragmentName + " - onCreate() call");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, FragmentName + " - onAttach() call");
    }

    /**
     * 프레그먼트와 레이아웃을 연결
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, FragmentName + " - onCreateView() call");
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }
}
