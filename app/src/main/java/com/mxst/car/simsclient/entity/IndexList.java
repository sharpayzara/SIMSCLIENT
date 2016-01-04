package com.mxst.car.simsclient.entity;

import java.io.Serializable;

/**
 * author   Joy
 * Date:  2015/12/14.
 * version:  V1.0
 * Description:
 */
public class IndexList implements Serializable {

    private int id;
    private String title;
    private String subtitle;
    private int dianjishu;
    private String img;
    private long release_time;
    private String releaseTime;
    private String cname;
    private String userName;
    private String interval_str;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setDianjishu(int dianjishu) {
        this.dianjishu = dianjishu;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setRelease_time(long release_time) {
        this.release_time = release_time;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setInterval_str(String interval_str) {
        this.interval_str = interval_str;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public int getDianjishu() {
        return dianjishu;
    }

    public String getImg() {
        return img;
    }

    public long getRelease_time() {
        return release_time;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public String getCname() {
        return cname;
    }

    public String getUserName() {
        return userName;
    }

    public String getInterval_str() {
        return interval_str;
    }


}
