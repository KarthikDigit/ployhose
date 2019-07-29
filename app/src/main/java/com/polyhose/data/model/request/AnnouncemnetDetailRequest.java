package com.polyhose.data.model.request;

public class AnnouncemnetDetailRequest {

    private String AnnouncementID;

    public AnnouncemnetDetailRequest(String announcementID) {
        AnnouncementID = announcementID;
    }

    public String getAnnouncementID() {
        return AnnouncementID;
    }

    public void setAnnouncementID(String announcementID) {
        AnnouncementID = announcementID;
    }
}
