package com.mxst.car.simsclient.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/1/4.
 */
public class HomeInfoEntity {
    private int cjNum;
    private int recNum;
    private int qdFlg;
    private VipInfo vipInfo;

    private List<HotBrandEntity> hotBrand;

    private List<ZxsEntity> zxs;

    public int getCjNum() {
        return cjNum;
    }

    public void setCjNum(int cjNum) {
        this.cjNum = cjNum;
    }

    public int getRecNum() {
        return recNum;
    }

    public void setRecNum(int recNum) {
        this.recNum = recNum;
    }

    public int getQdFlg() {
        return qdFlg;
    }

    public void setQdFlg(int qdFlg) {
        this.qdFlg = qdFlg;
    }

    public VipInfo getVipInfo() {
        return vipInfo;
    }

    public void setVipInfo(VipInfo vipInfo) {
        this.vipInfo = vipInfo;
    }

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
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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
    public static class VipInfo{
        private String nickName;
        private String phone;
        private String jifen;
        private String continueQd;
        private String headImg;
        private String vipType;

        public String getVipType() {
            return vipType;
        }

        public void setVipType(String vipType) {
            this.vipType = vipType;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getJifen() {
            return jifen;
        }

        public void setJifen(String jifen) {
            this.jifen = jifen;
        }

        public String getContinueQd() {
            return continueQd;
        }

        public void setContinueQd(String continueQd) {
            this.continueQd = continueQd;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }
    }
}
