package com.mxst.car.simsclient.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/1/16.
 */
public class InsureItemDetail {

    private String jghj;
    private String qd;
    private List<String> xm2List;
    private List<XmListEntity> xmList;
    private List<String> hdList;

    public void setJghj(String jghj) {
        this.jghj = jghj;
    }

    public void setQd(String qd) {
        this.qd = qd;
    }

    public void setXm2List(List<String> xm2List) {
        this.xm2List = xm2List;
    }

    public void setXmList(List<XmListEntity> xmList) {
        this.xmList = xmList;
    }

    public void setHdList(List<String> hdList) {
        this.hdList = hdList;
    }

    public String getJghj() {
        return jghj;
    }

    public String getQd() {
        return qd;
    }

    public List<String> getXm2List() {
        return xm2List;
    }

    public List<XmListEntity> getXmList() {
        return xmList;
    }

    public List<String> getHdList() {
        return hdList;
    }

    public static class XmListEntity {
        private String val;
        private String xmm;

        public void setVal(String val) {
            this.val = val;
        }

        public void setXmm(String xmm) {
            this.xmm = xmm;
        }

        public String getVal() {
            return val;
        }

        public String getXmm() {
            return xmm;
        }
    }
}
