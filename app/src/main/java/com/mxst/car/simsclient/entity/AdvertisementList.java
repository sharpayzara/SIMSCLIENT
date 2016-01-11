package com.mxst.car.simsclient.entity;

import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/8.
 * version:  V1.0
 * Description:
 */
public class AdvertisementList {
    List<Advertisement> index;

    public List<Advertisement> getIndex() {
        return index;
    }

    public void setIndex(List<Advertisement> index) {
        this.index = index;
    }

   public class Advertisement{
        Integer id;
        String adImg;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getAdImg() {
            return adImg;
        }

        public void setAdImg(String adImg) {
            this.adImg = adImg;
        }
    }
}
