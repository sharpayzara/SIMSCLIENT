package com.mxst.car.simsclient.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/1/4.
 */
public class HomeInfoEntity {

    private List<HotBrandEntity> hotBrand;

    private List<ZxsEntity> zxs;

    public void setHotBrand(List<HotBrandEntity> hotBrand) {
        this.hotBrand = hotBrand;
    }

    public void setZxs(List<ZxsEntity> zxs) {
        this.zxs = zxs;
    }

    public List<HotBrandEntity> getHotBrand() {
        return hotBrand;
    }

    public List<ZxsEntity> getZxs() {
        return zxs;
    }

    public static class HotBrandEntity {
        private String brand;
        private String img;

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getBrand() {
            return brand;
        }

        public String getImg() {
            return img;
        }
    }

    public static class ZxsEntity {
        private String title;
        private String subtitle;
        private String img;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public void setImg(String img) {
            this.img = img;
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
    }
}
