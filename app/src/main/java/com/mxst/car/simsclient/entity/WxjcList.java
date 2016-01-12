package com.mxst.car.simsclient.entity;

import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/12.
 * version:  V1.0
 * Description:
 */
public class WxjcList {

    /**
     * id : 13
     * fixName : 东风日产门店
     * overDate : 2016-01-31 09:54
     * noteDate : 2016-01-09 09:54
     * wxlx : 一般
     * license : 渝A88881
     */

    private List<WxjcListEntity> wxjcList;

    public void setWxjcList(List<WxjcListEntity> wxjcList) {
        this.wxjcList = wxjcList;
    }

    public List<WxjcListEntity> getWxjcList() {
        return wxjcList;
    }

    public static class WxjcListEntity {
        private int id;
        private String fixName;
        private String overDate;
        private String noteDate;
        private String wxlx;
        private String license;

        public void setId(int id) {
            this.id = id;
        }

        public void setFixName(String fixName) {
            this.fixName = fixName;
        }

        public void setOverDate(String overDate) {
            this.overDate = overDate;
        }

        public void setNoteDate(String noteDate) {
            this.noteDate = noteDate;
        }

        public void setWxlx(String wxlx) {
            this.wxlx = wxlx;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public int getId() {
            return id;
        }

        public String getFixName() {
            return fixName;
        }

        public String getOverDate() {
            return overDate;
        }

        public String getNoteDate() {
            return noteDate;
        }

        public String getWxlx() {
            return wxlx;
        }

        public String getLicense() {
            return license;
        }
    }
}
