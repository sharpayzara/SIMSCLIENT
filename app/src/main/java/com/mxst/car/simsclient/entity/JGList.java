package com.mxst.car.simsclient.entity;

import java.io.Serializable;
import java.util.List;

public class JGList implements Serializable {
    private List<JG> jigongs;

    public List<JG> getJigongs() {
        return jigongs;
    }

    public void setJigongs(List<JG> jigongs) {
        this.jigongs = jigongs;
    }

    public class JG {
        private String id;
        private String phone;
        private String num;
        private String headPortrait;
        private String star_level;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getHeadPortrait() {
            return headPortrait;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
        }

        public String getStar_level() {
            return star_level;
        }

        public void setStar_level(String star_level) {
            this.star_level = star_level;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
