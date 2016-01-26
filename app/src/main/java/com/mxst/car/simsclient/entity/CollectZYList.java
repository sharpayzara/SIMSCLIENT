package com.mxst.car.simsclient.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/1/7.
 */
public class CollectZYList implements Serializable{
    public  List<ZYEntity> resources;

    public List<ZYEntity> getResources() {
        return resources;
    }

    public void setResources(List<ZYEntity> resources) {
        this.resources = resources;
    }

    public static class ZYEntity{
        private int id;
        private String resourceNum;
        private String sourceType;
        private String brand;
        private String vehicleXinghao;
        private String mj;
        private String colorId;
        private String spec;
        private String vehicleType;
        private String commit_date;
        private String zj;
        private String kx;
        private String imgPath;

        private List<ColorsEntity> colors;

        public String getImgPath() {
            return imgPath;
        }

        public void setImgPath(String imgPath) {
            this.imgPath = imgPath;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getKx() {
            return kx;
        }

        public void setKx(String kx) {
            this.kx = kx;
        }

        public void setResourceNum(String resourceNum) {
            this.resourceNum = resourceNum;
        }

        public void setSourceType(String sourceType) {
            this.sourceType = sourceType;
        }

        public String getColorId() {
            return colorId;
        }

        public void setColorId(String colorId) {
            this.colorId = colorId;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public void setVehicleXinghao(String vehicleXinghao) {
            this.vehicleXinghao = vehicleXinghao;
        }

        public void setMj(String mj) {
            this.mj = mj;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        public void setVehicleType(String vehicleType) {
            this.vehicleType = vehicleType;
        }

        public void setCommit_date(String commit_date) {
            this.commit_date = commit_date;
        }

        public void setZj(String zj) {
            this.zj = zj;
        }

        public void setColors(List<ColorsEntity> colors) {
            this.colors = colors;
        }

        public int getId() {
            return id;
        }

        public String getResourceNum() {
            return resourceNum;
        }

        public String getSourceType() {
            return sourceType;
        }

        public String getBrand() {
            return brand;
        }

        public String getVehicleXinghao() {
            return vehicleXinghao;
        }

        public String getMj() {
            return mj;
        }

        public String getSpec() {
            return spec;
        }

        public String getVehicleType() {
            return vehicleType;
        }

        public String getCommit_date() {
            return commit_date;
        }

        public String getZj() {
            return zj;
        }

        public List<ColorsEntity> getColors() {
            return colors;
        }

        public static class ColorsEntity {
            private int colorId;
            private String outColorName;
            private String inColorName;
            private String inColor2Name;

            public void setColorId(int colorId) {
                this.colorId = colorId;
            }

            public void setOutColorName(String outColorName) {
                this.outColorName = outColorName;
            }

            public void setInColorName(String inColorName) {
                this.inColorName = inColorName;
            }

            public void setInColor2Name(String inColor2Name) {
                this.inColor2Name = inColor2Name;
            }

            public int getColorId() {
                return colorId;
            }

            public String getOutColorName() {
                return outColorName;
            }

            public String getInColorName() {
                return inColorName;
            }

            public String getInColor2Name() {
                return inColor2Name;
            }
        }
    }
}
