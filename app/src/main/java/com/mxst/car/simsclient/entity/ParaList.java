package com.mxst.car.simsclient.entity;

import java.io.Serializable;
import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/15.
 * version:  V1.0
 * Description:
 */
public class ParaList implements Serializable {

    private int flag;

    private ResourceDetailEntity resourceDetail;

    private List<PzbEntity> pzb;

    private List<ConfigInfoEntity> configInfo;

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setResourceDetail(ResourceDetailEntity resourceDetail) {
        this.resourceDetail = resourceDetail;
    }

    public void setPzb(List<PzbEntity> pzb) {
        this.pzb = pzb;
    }

    public void setConfigInfo(List<ConfigInfoEntity> configInfo) {
        this.configInfo = configInfo;
    }

    public int getFlag() {
        return flag;
    }

    public ResourceDetailEntity getResourceDetail() {
        return resourceDetail;
    }

    public List<PzbEntity> getPzb() {
        return pzb;
    }

    public List<ConfigInfoEntity> getConfigInfo() {
        return configInfo;
    }

    public static class ResourceDetailEntity implements Serializable {
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
        private String commit_date;
        private int colorId;
        private String outColor;

        public String getFdj() {
            return fdj;
        }

        public void setFdj(String fdj) {
            this.fdj = fdj;
        }

        public String getBsx() {
            return bsx;
        }

        public void setBsx(String bsx) {
            this.bsx = bsx;
        }

        private String inColor;
        private String inColor2;
        private int totalPrice;
        private int guidePrice;
        private int vePrice;
        private String dingjin;

        private String fdj;
        private String bsx;
        private int cjjf;
        private int gcjf;

        private List<ImgPathsEntity> imgPaths;

        public int getCjjf() {
            return cjjf;
        }

        public void setCjjf(int cjjf) {
            this.cjjf = cjjf;
        }

        public int getGcjf() {
            return gcjf;
        }

        public void setGcjf(int gcjf) {
            this.gcjf = gcjf;
        }

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

        public void setCommit_date(String commit_date) {
            this.commit_date = commit_date;
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

        public void setVePrice(int vePrice) {
            this.vePrice = vePrice;
        }

        public void setDingjin(String dingjin) {
            this.dingjin = dingjin;
        }

        public void setImgPaths(List<ImgPathsEntity> imgPaths) {
            this.imgPaths = imgPaths;
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

        public String getCommit_date() {
            return commit_date;
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

        public int getVePrice() {
            return vePrice;
        }

        public String getDingjin() {
            return dingjin;
        }

        public List<ImgPathsEntity> getImgPaths() {
            return imgPaths;
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
    }

    public static class PzbEntity {
        private String paraName;
        private String content;

        public void setParaName(String paraName) {
            this.paraName = paraName;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getParaName() {
            return paraName;
        }

        public String getContent() {
            return content;
        }
    }

    public static class ConfigInfoEntity implements Serializable {
        private String configName;

        private List<DataEntity> data;

        public void setConfigName(String configName) {
            this.configName = configName;
        }

        public void setData(List<DataEntity> data) {
            this.data = data;
        }

        public String getConfigName() {
            return configName;
        }

        public List<DataEntity> getData() {
            return data;
        }

        public static class DataEntity implements Serializable {
            private String paraName;
            private String content;

            public void setParaName(String paraName) {
                this.paraName = paraName;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getParaName() {
                return paraName;
            }

            public String getContent() {
                return content;
            }
        }
    }
}
