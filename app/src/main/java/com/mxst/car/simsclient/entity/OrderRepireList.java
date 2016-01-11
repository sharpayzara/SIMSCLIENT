package com.mxst.car.simsclient.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/1/11.
 */
public class OrderRepireList {
    private List<OrderRepire> yyList;

    public List<OrderRepire> getYyList() {
        return yyList;
    }

    public void setYyList(List<OrderRepire> yyList) {
        this.yyList = yyList;
    }

    public class OrderRepire{

        private String wxlxName;
        private String handmanName;
        private String fixName;
        private String yyDate;
        private int rowNumber;
        private String pp;
        private String license;
        private String lx;

        public void setWxlxName(String wxlxName) {
            this.wxlxName = wxlxName;
        }

        public void setHandmanName(String handmanName) {
            this.handmanName = handmanName;
        }

        public void setFixName(String fixName) {
            this.fixName = fixName;
        }

        public void setYyDate(String yyDate) {
            this.yyDate = yyDate;
        }

        public void setRowNumber(int rowNumber) {
            this.rowNumber = rowNumber;
        }

        public void setPp(String pp) {
            this.pp = pp;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public void setLx(String lx) {
            this.lx = lx;
        }

        public String getWxlxName() {
            return wxlxName;
        }

        public String getHandmanName() {
            return handmanName;
        }

        public String getFixName() {
            return fixName;
        }

        public String getYyDate() {
            return yyDate;
        }

        public int getRowNumber() {
            return rowNumber;
        }

        public String getPp() {
            return pp;
        }

        public String getLicense() {
            return license;
        }

        public String getLx() {
            return lx;
        }
    }
}
