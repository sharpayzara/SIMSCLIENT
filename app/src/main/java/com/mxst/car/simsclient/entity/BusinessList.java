package com.mxst.car.simsclient.entity;

import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/5.
 * version:  V1.0
 * Description:
 */
public class BusinessList {
    NtPaidList count;

    public NtPaidList getCount() {
        return count;
    }

    public void setCount(NtPaidList count) {
        this.count = count;
    }

   public class NtPaidList{
        List<BusinessEntity> notPaidList;

        public List<BusinessEntity> getNotPaidList() {
            return notPaidList;
        }

        public void setNotPaidList(List<BusinessEntity> notPaidList) {
            this.notPaidList = notPaidList;
        }
    }
    public class BusinessEntity{
        private int yjcurr;
        private String handmanName;
        private String fixName;
        private String wxlx;
        private String noteDate;
        private String loginNo;
        private String license;

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

        public void setLoginNo(String loginNo) {
            this.loginNo = loginNo;
        }

        public void setLicense(String license) {
            this.license = license;
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

        public String getLoginNo() {
            return loginNo;
        }

        public String getLicense() {
            return license;
        }
    }
}
