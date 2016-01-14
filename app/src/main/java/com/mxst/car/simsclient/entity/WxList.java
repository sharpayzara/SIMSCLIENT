package com.mxst.car.simsclient.entity;

import java.io.Serializable;
import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/14.
 * version:  V1.0
 * Description:
 */
public class WxList {


    private List<WxlsListEntity> wxlsList;

    public void setWxlsList(List<WxlsListEntity> wxlsList) {
        this.wxlsList = wxlsList;
    }

    public List<WxlsListEntity> getWxlsList() {
        return wxlsList;
    }

    public static class WxlsListEntity implements Serializable {
        private String id;
        private String loginNo;
        private String noteDate;
        private String wxlx;
        private int sjcurr;
        private String gls;

        public void setId(String id) {
            this.id = id;
        }

        public void setLoginNo(String loginNo) {
            this.loginNo = loginNo;
        }

        public void setNoteDate(String noteDate) {
            this.noteDate = noteDate;
        }

        public void setWxlx(String wxlx) {
            this.wxlx = wxlx;
        }

        public void setSjcurr(int sjcurr) {
            this.sjcurr = sjcurr;
        }

        public void setGls(String gls) {
            this.gls = gls;
        }

        public String getId() {
            return id;
        }

        public String getLoginNo() {
            return loginNo;
        }

        public String getNoteDate() {
            return noteDate;
        }

        public String getWxlx() {
            return wxlx;
        }

        public int getSjcurr() {
            return sjcurr;
        }

        public String getGls() {
            return gls;
        }
    }
}
