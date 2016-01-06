package com.mxst.car.simsclient.entity;

import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/6.
 * version:  V1.0
 * Description:
 */
public class Filtrate {
    
    private String mj;

    private List<MjkxsEntity> mjkxs;

    public void setMj(String mj) {
        this.mj = mj;
    }

    public void setMjkxs(List<MjkxsEntity> mjkxs) {
        this.mjkxs = mjkxs;
    }

    public String getMj() {
        return mj;
    }

    public List<MjkxsEntity> getMjkxs() {
        return mjkxs;
    }

    public static class MjkxsEntity {
        private String mjkx;

        public void setMjkx(String mjkx) {
            this.mjkx = mjkx;
        }

        public String getMjkx() {
            return mjkx;
        }
    }
}
