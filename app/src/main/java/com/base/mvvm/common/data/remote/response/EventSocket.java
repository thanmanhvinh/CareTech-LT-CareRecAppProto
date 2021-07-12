package com.base.mvvm.common.data.remote.response;

import com.google.gson.annotations.SerializedName;

public class EventSocket {
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("startDate")
    private String startDate ;
    @SerializedName("endDate")
    private String endDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
