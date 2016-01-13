package com.mxst.car.simsclient.entity;

import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/13.
 * version:  V1.0
 * Description:
 */
public class RepairDetail {
    private String wxlx;
    private String gls;

    private FyEntity fy;
    private String cx;
    private String loginNo;
    private String license;
    private String pp;
    private String notedate;

    private List<XmEntity> xm;

    private List<ClEntity> cl;

    public void setWxlx(String wxlx) {
        this.wxlx = wxlx;
    }

    public void setGls(String gls) {
        this.gls = gls;
    }

    public void setFy(FyEntity fy) {
        this.fy = fy;
    }

    public void setCx(String cx) {
        this.cx = cx;
    }

    public void setLoginNo(String loginNo) {
        this.loginNo = loginNo;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public void setPp(String pp) {
        this.pp = pp;
    }

    public void setNotedate(String notedate) {
        this.notedate = notedate;
    }

    public void setXm(List<XmEntity> xm) {
        this.xm = xm;
    }

    public void setCl(List<ClEntity> cl) {
        this.cl = cl;
    }

    public String getWxlx() {
        return wxlx;
    }

    public String getGls() {
        return gls;
    }

    public FyEntity getFy() {
        return fy;
    }

    public String getCx() {
        return cx;
    }

    public String getLoginNo() {
        return loginNo;
    }

    public String getLicense() {
        return license;
    }

    public String getPp() {
        return pp;
    }

    public String getNotedate() {
        return notedate;
    }

    public List<XmEntity> getXm() {
        return xm;
    }

    public List<ClEntity> getCl() {
        return cl;
    }

    public static class FyEntity {
        private String yjcurr;
        private String jfcurr;
        private String sjcurr;

        public void setYjcurr(String yjcurr) {
            this.yjcurr = yjcurr;
        }

        public void setJfcurr(String jfcurr) {
            this.jfcurr = jfcurr;
        }

        public void setSjcurr(String sjcurr) {
            this.sjcurr = sjcurr;
        }

        public String getYjcurr() {
            return yjcurr;
        }

        public String getJfcurr() {
            return jfcurr;
        }

        public String getSjcurr() {
            return sjcurr;
        }
    }

    public static class XmEntity {
        private String lx;

        public void setLx(String lx) {
            this.lx = lx;
        }

        public String getLx() {
            return lx;
        }
    }

    public static class ClEntity {
        private String amount;
        private String lx;

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public void setLx(String lx) {
            this.lx = lx;
        }

        public String getAmount() {
            return amount;
        }

        public String getLx() {
            return lx;
        }
    }
}
