package com.mxst.car.simsclient.entity;

import java.util.List;

/**
 * Created by Joy on 2016/1/14 0014.
 */
public class JiGong {

    private List<JigongsEntity> jigongs;

    public void setJigongs(List<JigongsEntity> jigongs) {
        this.jigongs = jigongs;
    }

    public List<JigongsEntity> getJigongs() {
        return jigongs;
    }

    public static class JigongsEntity {
        private int id;
        private String headPortrait;
        private String name;
        private String num;
        private String phone;
        private float star_level;
  
        public void setId(int id) {
            this.id = id;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setStar_level(float star_level) {
            this.star_level = star_level;
        }

        public int getId() {
            return id;
        }

        public String getHeadPortrait() {
            return headPortrait;
        }

        public String getName() {
            return name;
        }

        public String getNum() {
            return num;
        }

        public String getPhone() {
            return phone;
        }

        public float getStar_level() {
            return star_level;
        }
    }
}
