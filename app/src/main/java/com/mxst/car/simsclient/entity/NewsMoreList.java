package com.mxst.car.simsclient.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/1/6.
 */
public class NewsMoreList implements Serializable{
    private List<NewsMore> newsList;

    public List<NewsMore> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<NewsMore> newsList) {
        this.newsList = newsList;
    }

    public static  class NewsMore implements Serializable{
        private int id;
        private String category;
        private String title;
        private String subtitle;
        private String img;
        private String releaseTime;
        private int dianjishu;
        private String interval_str;

        public void setId(int id) {
            this.id = id;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setReleaseTime(String releaseTime) {
            this.releaseTime = releaseTime;
        }

        public void setDianjishu(int dianjishu) {
            this.dianjishu = dianjishu;
        }

        public void setInterval_str(String interval_str) {
            this.interval_str = interval_str;
        }

        public int getId() {
            return id;
        }

        public String getCategory() {
            return category;
        }

        public String getTitle() {
            return title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public String getImg() {
            return img;
        }

        public String getReleaseTime() {
            return releaseTime;
        }

        public int getDianjishu() {
            return dianjishu;
        }

        public String getInterval_str() {
            return interval_str;
        }
    }
}
