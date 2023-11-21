package com.example.checkservice;

import android.os.Parcel;
import android.os.Parcelable;

public class UserItem implements Parcelable {
    int no;
    String name;
    boolean yn;
    String[] arr;

    public UserItem(int no, String name, boolean yn, String[] arr) {
        this.no = no;
        this.name = name;
        this.yn = yn;
        this.arr = arr;
    }

    protected UserItem(Parcel in) {
        no = in.readInt();
        name = in.readString();
        yn = in.readByte() != 0;
        arr = in.createStringArray();
    }

    public static final Creator<UserItem> CREATOR = new Creator<UserItem>() {
        @Override
        public UserItem createFromParcel(Parcel in) {
            return new UserItem(in);
        }

        @Override
        public UserItem[] newArray(int size) {
            return new UserItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(no);
        dest.writeString(name);
        dest.writeByte((byte) (yn ? 1 : 0));
        dest.writeStringArray(arr);
    }
}
