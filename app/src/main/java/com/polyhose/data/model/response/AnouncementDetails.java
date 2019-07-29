package com.polyhose.data.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AnouncementDetails implements Serializable
{

    @SerializedName("announcementID")
    @Expose
    private Integer announcementID;
    @SerializedName("announcementHeading")
    @Expose
    private String announcementHeading;
    @SerializedName("shortAnnouncement")
    @Expose
    private String shortAnnouncement;
    @SerializedName("announcement")
    @Expose
    private String announcement;
    @SerializedName("publishDate")
    @Expose
    private String publishDate;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    private final static long serialVersionUID = 6162576627932946763L;

    public Integer getAnnouncementID() {
        return announcementID;
    }

    public void setAnnouncementID(Integer announcementID) {
        this.announcementID = announcementID;
    }

    public String getAnnouncementHeading() {
        return announcementHeading;
    }

    public void setAnnouncementHeading(String announcementHeading) {
        this.announcementHeading = announcementHeading;
    }

    public String getShortAnnouncement() {
        return shortAnnouncement;
    }

    public void setShortAnnouncement(String shortAnnouncement) {
        this.shortAnnouncement = shortAnnouncement;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

}