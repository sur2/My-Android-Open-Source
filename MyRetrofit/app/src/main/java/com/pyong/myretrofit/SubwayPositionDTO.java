package com.pyong.myretrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubwayPositionDTO {

    @SerializedName("subwayId")
    @Expose
    private String subwayId;
    @Expose
    private String subwayNm;
    @Expose
    private String statnId;
    @Expose
    private String statnNm;
    @Expose
    private String trainNo;
    @Expose
    private String lastRecptnDt;
    @Expose
    private String recptnDt;
    @Expose
    private String updnLine;
    @Expose
    private String statnTid;
    @Expose
    private String statnTnm;
    @Expose
    private String trainSttus;
    @Expose
    private String directAt;
    @Expose
    private String lstcarAt;

    public String getSubwayId() {
        return subwayId;
    }

    public void setSubwayId(String subwayId) {
        this.subwayId = subwayId;
    }

    public String getSubwayNm() {
        return subwayNm;
    }

    public void setSubwayNm(String subwayNm) {
        this.subwayNm = subwayNm;
    }

    public String getStatnId() {
        return statnId;
    }

    public void setStatnId(String statnId) {
        this.statnId = statnId;
    }

    public String getStatnNm() {
        return statnNm;
    }

    public void setStatnNm(String statnNm) {
        this.statnNm = statnNm;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getLastRecptnDt() {
        return lastRecptnDt;
    }

    public void setLastRecptnDt(String lastRecptnDt) {
        this.lastRecptnDt = lastRecptnDt;
    }

    public String getRecptnDt() {
        return recptnDt;
    }

    public void setRecptnDt(String recptnDt) {
        this.recptnDt = recptnDt;
    }

    public String getUpdnLine() {
        return updnLine;
    }

    public void setUpdnLine(String updnLine) {
        this.updnLine = updnLine;
    }

    public String getStatnTid() {
        return statnTid;
    }

    public void setStatnTid(String statnTid) {
        this.statnTid = statnTid;
    }

    public String getStatnTnm() {
        return statnTnm;
    }

    public void setStatnTnm(String statnTnm) {
        this.statnTnm = statnTnm;
    }

    public String getTrainSttus() {
        return trainSttus;
    }

    public void setTrainSttus(String trainSttus) {
        this.trainSttus = trainSttus;
    }

    public String getDirectAt() {
        return directAt;
    }

    public void setDirectAt(String directAt) {
        this.directAt = directAt;
    }

    public String getLstcarAt() {
        return lstcarAt;
    }

    public void setLstcarAt(String lstcarAt) {
        this.lstcarAt = lstcarAt;
    }

    @Override
    public String toString() {
        return "SubwayPositionDTO{" +
                "subwayId='" + subwayId + '\'' +
                ", subwayNm='" + subwayNm + '\'' +
                ", statnId='" + statnId + '\'' +
                ", statnNm='" + statnNm + '\'' +
                ", trainNo='" + trainNo + '\'' +
                ", lastRecptnDt='" + lastRecptnDt + '\'' +
                ", recptnDt='" + recptnDt + '\'' +
                ", updnLine='" + updnLine + '\'' +
                ", statnTid='" + statnTid + '\'' +
                ", statnTnm='" + statnTnm + '\'' +
                ", trainSttus='" + trainSttus + '\'' +
                ", directAt='" + directAt + '\'' +
                ", lstcarAt='" + lstcarAt + '\'' +
                '}';
    }
}
