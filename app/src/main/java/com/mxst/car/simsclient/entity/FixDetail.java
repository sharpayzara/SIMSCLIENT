package com.mxst.car.simsclient.entity;

/**
 * author   Joy
 * Date:  2016/1/12.
 * version:  V1.0
 * Description:
 */
public class FixDetail {

    private DetailEntity detail;

    public void setDetail(DetailEntity detail) {
        this.detail = detail;
    }

    public DetailEntity getDetail() {
        return detail;
    }

    public static class DetailEntity {
        private String fixName;
        private String gz;
        private String tag;
        private String skdate;
        private int id;
        private String handmanName;
        private String jgDate;
        private String overDate;
        private String wxlx;
        private String noteDate;
        private String wxMobile;
        private String jsDate;
        private String loginNo;
        private String kgDate;
        private String license;

        public void setFixName(String fixName) {
            this.fixName = fixName;
        }

        public void setGz(String gz) {
            this.gz = gz;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public void setSkdate(String skdate) {
            this.skdate = skdate;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setHandmanName(String handmanName) {
            this.handmanName = handmanName;
        }

        public void setJgDate(String jgDate) {
            this.jgDate = jgDate;
        }

        public void setOverDate(String overDate) {
            this.overDate = overDate;
        }

        public void setWxlx(String wxlx) {
            this.wxlx = wxlx;
        }

        public void setNoteDate(String noteDate) {
            this.noteDate = noteDate;
        }

        public void setWxMobile(String wxMobile) {
            this.wxMobile = wxMobile;
        }

        public void setJsDate(String jsDate) {
            this.jsDate = jsDate;
        }

        public void setLoginNo(String loginNo) {
            this.loginNo = loginNo;
        }

        public void setKgDate(String kgDate) {
            this.kgDate = kgDate;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getFixName() {
            return fixName;
        }

        public String getGz() {
            return gz;
        }

        public String getTag() {
            return tag;
        }

        public String getSkdate() {
            return skdate;
        }

        public int getId() {
            return id;
        }

        public String getHandmanName() {
            return handmanName;
        }

        public String getJgDate() {
            return jgDate;
        }

        public String getOverDate() {
            return overDate;
        }

        public String getWxlx() {
            return wxlx;
        }

        public String getNoteDate() {
            return noteDate;
        }

        public String getWxMobile() {
            return wxMobile;
        }

        public String getJsDate() {
            return jsDate;
        }

        public String getLoginNo() {
            return loginNo;
        }

        public String getKgDate() {
            return kgDate;
        }

        public String getLicense() {
            return license;
        }
    }
}
