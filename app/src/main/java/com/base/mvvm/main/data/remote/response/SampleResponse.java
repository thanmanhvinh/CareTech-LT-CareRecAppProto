package com.base.mvvm.main.data.remote.response;

import com.base.mvvm.common.data.remote.response.BaseResponse;
import com.base.mvvm.main.data.remote.model.Test;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SampleResponse extends BaseResponse {
    @SerializedName("data")
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {
        @SerializedName("content")
        private List<Test> eventByUsers;

        public List<Test> getEventByUsers() {
            return eventByUsers;
        }

        public void setEventByUsers(List<Test> eventByUsers) {
            this.eventByUsers = eventByUsers;
        }
    }

}

