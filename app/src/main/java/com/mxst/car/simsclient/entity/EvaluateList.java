package com.mxst.car.simsclient.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/1/11.
 */
public class EvaluateList {
    private List<Evaluate> evaluateList;

    public List<Evaluate> getEvaluateList() {
        return evaluateList;
    }

    public void setEvaluateList(List<Evaluate> evaluateList) {
        this.evaluateList = evaluateList;
    }

    public class Evaluate{
        private String wxlx;
        private String noteDate;
        private String gls;
        private ArtisanEntity artisan;
        private String handman;
        private String cx;
        private String loginNo;
        private String license;
        private String pp;

        public void setWxlx(String wxlx) {
            this.wxlx = wxlx;
        }

        public void setNoteDate(String noteDate) {
            this.noteDate = noteDate;
        }

        public void setGls(String gls) {
            this.gls = gls;
        }

        public void setArtisan(ArtisanEntity artisan) {
            this.artisan = artisan;
        }

        public void setHandman(String handman) {
            this.handman = handman;
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

        public String getWxlx() {
            return wxlx;
        }

        public String getNoteDate() {
            return noteDate;
        }

        public String getGls() {
            return gls;
        }

        public ArtisanEntity getArtisan() {
            return artisan;
        }

        public String getHandman() {
            return handman;
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

        public class ArtisanEntity {
            private String id;
            private String headPortrait;
            private String name;
            private String phone;

            public void setId(String id) {
                this.id = id;
            }

            public void setHeadPortrait(String headPortrait) {
                this.headPortrait = headPortrait;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getId() {
                return id;
            }

            public String getHeadPortrait() {
                return headPortrait;
            }

            public String getName() {
                return name;
            }

            public String getPhone() {
                return phone;
            }
        }

    }
}
