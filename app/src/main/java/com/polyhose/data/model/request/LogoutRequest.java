package com.polyhose.data.model.request;

public class LogoutRequest {

    private String UserId;

    private String ApiKey;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getApiKey() {
        return ApiKey;
    }

    public void setApiKey(String apiKey) {
        ApiKey = apiKey;
    }
}
