package com.example.monumentbook.utilities.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseList<T> extends Response {
    @JsonProperty("data")
    public List<T> data;

    @JsonProperty("PAGINATION")
    public String paging;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getPaging() {
        return paging;
    }

    public void setPaging(String paging) {
        this.paging = paging;
    }
}
