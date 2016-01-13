package com.mxst.car.simsclient.entity;

import java.io.Serializable;
import java.util.List;

public class InsureDetailList implements Serializable{
    private List<InsureDetail> bxList;

    public List<InsureDetail> getBxList() {
        return bxList;
    }

    public void setBxList(List<InsureDetail> bxList) {
        this.bxList = bxList;
    }

    public class InsureDetail{
        private String noteNo;
        private String sxeDate;
        private String sxbDate;
        private String name;
        private String currHjsj;

        public void setNoteNo(String noteNo) {
            this.noteNo = noteNo;
        }

        public void setSxeDate(String sxeDate) {
            this.sxeDate = sxeDate;
        }

        public void setSxbDate(String sxbDate) {
            this.sxbDate = sxbDate;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCurrHjsj(String currHjsj) {
            this.currHjsj = currHjsj;
        }

        public String getNoteNo() {
            return noteNo;
        }

        public String getSxeDate() {
            return sxeDate;
        }

        public String getSxbDate() {
            return sxbDate;
        }

        public String getName() {
            return name;
        }

        public String getCurrHjsj() {
            return currHjsj;
        }
    }
}
