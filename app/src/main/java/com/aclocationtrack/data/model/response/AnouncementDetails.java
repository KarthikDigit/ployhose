package com.aclocationtrack.data.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AnouncementDetails implements Parcelable
{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<AnouncementDetails> CREATOR = new Creator<AnouncementDetails>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AnouncementDetails createFromParcel(Parcel in) {
            return new AnouncementDetails(in);
        }

        public AnouncementDetails[] newArray(int size) {
            return (new AnouncementDetails[size]);
        }

    }
            ;

    protected AnouncementDetails(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public AnouncementDetails() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(success);
        dest.writeValue(data);
        dest.writeValue(message);
    }

    public int describeContents() {
        return 0;
    }



    public static class Data implements Parcelable
    {

        @SerializedName("announcements")
        @Expose
        private Announcements announcements;
        @SerializedName("announcement_images")
        @Expose
        private List<AnnouncementImage> announcementImages = null;
        public final static Parcelable.Creator<Data> CREATOR = new Creator<Data>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Data createFromParcel(Parcel in) {
                return new Data(in);
            }

            public Data[] newArray(int size) {
                return (new Data[size]);
            }

        }
                ;

        protected Data(Parcel in) {
            this.announcements = ((Announcements) in.readValue((Announcements.class.getClassLoader())));
            in.readList(this.announcementImages, (AnnouncementImage.class.getClassLoader()));
        }

        public Data() {
        }

        public Announcements getAnnouncements() {
            return announcements;
        }

        public void setAnnouncements(Announcements announcements) {
            this.announcements = announcements;
        }

        public List<AnnouncementImage> getAnnouncementImages() {
            return announcementImages;
        }

        public void setAnnouncementImages(List<AnnouncementImage> announcementImages) {
            this.announcementImages = announcementImages;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(announcements);
            dest.writeList(announcementImages);
        }

        public int describeContents() {
            return 0;
        }


        public static class Announcements implements Parcelable
        {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("title")
            @Expose
            private String title;
            @SerializedName("description")
            @Expose
            private String description;
            public final static Parcelable.Creator<Announcements> CREATOR = new Creator<Announcements>() {


                @SuppressWarnings({
                        "unchecked"
                })
                public Announcements createFromParcel(Parcel in) {
                    return new Announcements(in);
                }

                public Announcements[] newArray(int size) {
                    return (new Announcements[size]);
                }

            }
                    ;

            protected Announcements(Parcel in) {
                this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
                this.title = ((String) in.readValue((String.class.getClassLoader())));
                this.description = ((String) in.readValue((String.class.getClassLoader())));
            }

            public Announcements() {
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public void writeToParcel(Parcel dest, int flags) {
                dest.writeValue(id);
                dest.writeValue(title);
                dest.writeValue(description);
            }

            public int describeContents() {
                return 0;
            }

        }


        public static class AnnouncementImage implements Parcelable
        {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("image")
            @Expose
            private String image;
            @SerializedName("img_ext")
            @Expose
            private String imgExt;
            @SerializedName("img_url")
            @Expose
            private String imgUrl;
            public final static Parcelable.Creator<AnnouncementImage> CREATOR = new Creator<AnnouncementImage>() {


                @SuppressWarnings({
                        "unchecked"
                })
                public AnnouncementImage createFromParcel(Parcel in) {
                    return new AnnouncementImage(in);
                }

                public AnnouncementImage[] newArray(int size) {
                    return (new AnnouncementImage[size]);
                }

            }
                    ;

            protected AnnouncementImage(Parcel in) {
                this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
                this.image = ((String) in.readValue((String.class.getClassLoader())));
                this.imgExt = ((String) in.readValue((String.class.getClassLoader())));
                this.imgUrl = ((String) in.readValue((String.class.getClassLoader())));
            }

            public AnnouncementImage() {
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getImgExt() {
                return imgExt;
            }

            public void setImgExt(String imgExt) {
                this.imgExt = imgExt;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public void writeToParcel(Parcel dest, int flags) {
                dest.writeValue(id);
                dest.writeValue(image);
                dest.writeValue(imgExt);
                dest.writeValue(imgUrl);
            }

            public int describeContents() {
                return 0;
            }

        }

    }

}