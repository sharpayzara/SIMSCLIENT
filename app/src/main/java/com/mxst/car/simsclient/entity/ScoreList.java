package com.mxst.car.simsclient.entity;

import java.io.Serializable;
import java.util.List;

public class ScoreList implements Serializable{
    private List<Score> jfList;

    public List<Score> getJfList() {
        return jfList;
    }

    public void setJfList(List<Score> jfList) {
        this.jfList = jfList;
    }

    public static class Score  implements Serializable{
        private int id;
        private String produceDate;
        private String jfChange;
        private String jfxw;

        public void setId(int id) {
            this.id = id;
        }

        public void setProduceDate(String produceDate) {
            this.produceDate = produceDate;
        }

        public void setJfChange(String jfChange) {
            this.jfChange = jfChange;
        }

        public void setJfxw(String jfxw) {
            this.jfxw = jfxw;
        }

        public int getId() {
            return id;
        }

        public String getProduceDate() {
            return produceDate;
        }

        public String getJfChange() {
            return jfChange;
        }

        public String getJfxw() {
            return jfxw;
        }
    }
}
