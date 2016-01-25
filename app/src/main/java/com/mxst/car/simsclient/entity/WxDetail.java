package com.mxst.car.simsclient.entity;

/**
 * author   Joy
 * Date:  2016/1/25.
 * version:  V1.0
 * Description:
 */
public class WxDetail {

    private DetailEntity detail;

    public void setDetail(DetailEntity detail) {
        this.detail = detail;
    }

    public DetailEntity getDetail() {
        return detail;
    }

    public static class DetailEntity {
        private int yjcurr;
        private String handmanName;
        private String fixName;
        private String wxlx;
        private String noteDate;
        private double jfPrice;
        private int kyjf;
        private String loginNo;
        private String license;
        private int curJf;

        public void setYjcurr(int yjcurr) {
            this.yjcurr = yjcurr;
        }

        public void setHandmanName(String handmanName) {
            this.handmanName = handmanName;
        }

        public void setFixName(String fixName) {
            this.fixName = fixName;
        }

        public void setWxlx(String wxlx) {
            this.wxlx = wxlx;
        }

        public void setNoteDate(String noteDate) {
            this.noteDate = noteDate;
        }

        public void setJfPrice(double jfPrice) {
            this.jfPrice = jfPrice;
        }

        public void setKyjf(int kyjf) {
            this.kyjf = kyjf;
        }

        public void setLoginNo(String loginNo) {
            this.loginNo = loginNo;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public void setCurJf(int curJf) {
            this.curJf = curJf;
        }

        public int getYjcurr() {
            return yjcurr;
        }

        public String getHandmanName() {
            return handmanName;
        }

        public String getFixName() {
            return fixName;
        }

        public String getWxlx() {
            return wxlx;
        }

        public String getNoteDate() {
            return noteDate;
        }

        public double getJfPrice() {
            return jfPrice;
        }

        public int getKyjf() {
            return kyjf;
        }

        public String getLoginNo() {
            return loginNo;
        }

        public String getLicense() {
            return license;
        }

        public int getCurJf() {
            return curJf;
        }
    }
}
