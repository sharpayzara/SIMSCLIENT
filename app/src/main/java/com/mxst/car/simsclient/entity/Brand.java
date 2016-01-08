package com.mxst.car.simsclient.entity;

import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/8.
 * version:  V1.0
 * Description:
 */
public class Brand {

    private List<Brands> brands;

    public List<Brands> getBrands() {
        return brands;
    }

    public void setBrands(List<Brands> brands) {
        this.brands = brands;
    }

    public static class Brands {
        private int id;
        private String brand;
        private String logoPath;
        private int seq;

        public void setId(int id) {
            this.id = id;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public void setLogoPath(String logoPath) {
            this.logoPath = logoPath;
        }

        public void setSeq(int seq) {
            this.seq = seq;
        }

        public int getId() {
            return id;
        }

        public String getBrand() {
            return brand;
        }

        public String getLogoPath() {
            return logoPath;
        }

        public int getSeq() {
            return seq;
        }
    }

}
