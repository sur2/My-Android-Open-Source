package com.pyong.myretrofit;

import com.google.gson.annotations.Expose;

public class ResponseTrainPosition {

    @Expose
    private ErrorMessageDTO errorMessage;

    @Expose
    private SubwayPositionDTO[] realtimePositionList;

    public ErrorMessageDTO getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessageDTO errorMessage) {
        this.errorMessage = errorMessage;
    }

    public SubwayPositionDTO[] getRealtimePositionList() {
        return realtimePositionList;
    }

    public void setRealtimePositionList(SubwayPositionDTO[] realtimePositionList) {
        this.realtimePositionList = realtimePositionList;
    }
}
