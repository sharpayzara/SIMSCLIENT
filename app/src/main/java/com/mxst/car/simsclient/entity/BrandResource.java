package com.mxst.car.simsclient.entity;

/**
 * author   Joy
 * Date:  2016/1/6.
 * version:  V1.0
 * Description:
 */
public class BrandResource {

    /**
     * resourceNum : SRS1511003
     * brand : 宝马
     * xinghao : 120i
     * carType : 轿车
     * location : 中国
     * sourceType : 现货
     * guiGe : 中规
     * nianKuan : 2015款
     * kuanXing : 领先型
     * colorId : 8
     * outColor : 黑色
     * inColor : 黑色
     * inColor2 : 黑色
     * totalPrice : 222320
     * guidePrice : null
     */

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
}
