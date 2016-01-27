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
        private int colorId;
        private String outColorName;
        private String inColorName;
        private Object inColor2Name;
        private String price;
        private String sourceType;
        private String brand;
        private String vehicleXinghao;
        private String mj;
        private String spec;
        private String kx;
        private String vehicleType;
        private String commit_date;
        private String imgPath;

        public void setId(int id) {
            this.id = id;
        }

        public void setResourceNum(String resourceNum) {
            this.resourceNum = resourceNum;
        }

        public void setColorId(int colorId) {
            this.colorId = colorId;
        }

        public void setOutColorName(String outColorName) {
            this.outColorName = outColorName;
        }

        public void setInColorName(String inColorName) {
            this.inColorName = inColorName;
        }

        public void setInColor2Name(Object inColor2Name) {
            this.inColor2Name = inColor2Name;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setSourceType(String sourceType) {
            this.sourceType = sourceType;
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

        public void setKx(String kx) {
            this.kx = kx;
        }

        public void setVehicleType(String vehicleType) {
            this.vehicleType = vehicleType;
        }

        public void setCommit_date(String commit_date) {
            this.commit_date = commit_date;
        }

        public void setImgPath(String imgPath) {
            this.imgPath = imgPath;
        }

        public int getId() {
            return id;
        }

        public String getResourceNum() {
            return resourceNum;
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

        public Object getInColor2Name() {
            return inColor2Name;
        }

        public String getPrice() {
            return price;
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

        public String getKx() {
            return kx;
        }

        public String getVehicleType() {
            return vehicleType;
        }

        public String getCommit_date() {
            return commit_date;
        }

        public String getImgPath() {
            return imgPath;
        }
    }
}
