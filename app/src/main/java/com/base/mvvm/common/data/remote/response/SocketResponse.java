package com.base.mvvm.common.data.remote.response;

import com.google.gson.annotations.SerializedName;

public class SocketResponse extends BaseResponse {

    @SerializedName("event")
    private EventSocket event;
    @SerializedName("userId")
    private int userId;
    @SerializedName("action")
    private String action;
    @SerializedName("result")
    private boolean result;

    public EventSocket getEvent() {
        return event;
    }

    public void setEvent(EventSocket event) {
        this.event = event;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
