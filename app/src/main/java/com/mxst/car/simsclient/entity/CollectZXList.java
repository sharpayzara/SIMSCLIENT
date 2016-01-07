package com.mxst.car.simsclient.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/1/6.
 */
public class CollectZXList implements Serializable{
    public List<ZXEntity> newsList;

    public List<ZXEntity> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<ZXEntity> newsList) {
        this.newsList = newsList;
    }

    public static class ZXEntity{
        private String id;
        private String title;
        private String img;
        private String release_time;
        private String dianjishu;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getRelease_time() {
            return release_time;
        }

        public void setRelease_time(String release_time) {
            this.release_time = release_time;
        }

        public String getDianjishu() {
            return dianjishu;
        }

        public void setDianjishu(String dianjishu) {
            this.dianjishu = dianjishu;
        }
    }
}
