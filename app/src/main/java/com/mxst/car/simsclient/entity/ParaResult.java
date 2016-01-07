package com.mxst.car.simsclient.entity;

import java.io.Serializable;
import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/7.
 * version:  V1.0
 * Description:
 */
public class ParaResult implements Serializable {
    private ResourceDetail resourceDetail;

    public ResourceDetail getResourceDetail() {
        return resourceDetail;
    }

    public void setResourceDetail(ResourceDetail resourceDetail) {
        this.resourceDetail = resourceDetail;
    }

    public static class ResourceDetail implements Serializable {

        private int id;
        private String resourceNum;
        private String brand;
        private String xinghao;
        private String carType;
        private String location;
        private String sourceType;
        private String guiGe;
        private String nianKuan;
        private String kuanXing;
        private int colorId;
        private String outColor;
        private String inColor;
        private String inColor2;
        private int totalPrice;
        private int guidePrice;

        private List<ImgPathsEntity> imgPaths;

        private List<ParaListEntity> paraList;

        public void setId(int id) {
            this.id = id;
        }

        public void setResourceNum(String resourceNum) {
            this.resourceNum = resourceNum;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public void setXinghao(String xinghao) {
            this.xinghao = xinghao;
        }

        public void setCarType(String carType) {
            this.carType = carType;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public void setSourceType(String sourceType) {
            this.sourceType = sourceType;
        }

        public void setGuiGe(String guiGe) {
            this.guiGe = guiGe;
        }

        public void setNianKuan(String nianKuan) {
            this.nianKuan = nianKuan;
        }

        public void setKuanXing(String kuanXing) {
            this.kuanXing = kuanXing;
        }

        public void setColorId(int colorId) {
            this.colorId = colorId;
        }

        public void setOutColor(String outColor) {
            this.outColor = outColor;
        }

        public void setInColor(String inColor) {
            this.inColor = inColor;
        }

        public void setInColor2(String inColor2) {
            this.inColor2 = inColor2;
        }

        public void setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
        }

        public void setGuidePrice(int guidePrice) {
            this.guidePrice = guidePrice;
        }

        public void setImgPaths(List<ImgPathsEntity> imgPaths) {
            this.imgPaths = imgPaths;
        }

        public void setParaList(List<ParaListEntity> paraList) {
            this.paraList = paraList;
        }

        public int getId() {
            return id;
        }

        public String getResourceNum() {
            return resourceNum;
        }

        public String getBrand() {
            return brand;
        }

        public String getXinghao() {
            return xinghao;
        }

        public String getCarType() {
            return carType;
        }

        public String getLocation() {
            return location;
        }

        public String getSourceType() {
            return sourceType;
        }

        public String getGuiGe() {
            return guiGe;
        }

        public String getNianKuan() {
            return nianKuan;
        }

        public String getKuanXing() {
            return kuanXing;
        }

        public int getColorId() {
            return colorId;
        }

        public String getOutColor() {
            return outColor;
        }

        public String getInColor() {
            return inColor;
        }

        public String getInColor2() {
            return inColor2;
        }

        public int getTotalPrice() {
            return totalPrice;
        }

        public int getGuidePrice() {
            return guidePrice;
        }

        public List<ImgPathsEntity> getImgPaths() {
            return imgPaths;
        }

        public List<ParaListEntity> getParaList() {
            return paraList;
        }

        public static class ImgPathsEntity {
            private String imgPath;

            public void setImgPath(String imgPath) {
                this.imgPath = imgPath;
            }

            public String getImgPath() {
                return imgPath;
            }
        }

        public static class ParaListEntity {
            private String paraName;
            private String paraVal;

            public void setParaName(String paraName) {
                this.paraName = paraName;
            }

            public void setParaVal(String paraVal) {
                this.paraVal = paraVal;
            }

            public String getParaName() {
                return paraName;
            }

            public String getParaVal() {
                return paraVal;
            }
        }
    }
}
