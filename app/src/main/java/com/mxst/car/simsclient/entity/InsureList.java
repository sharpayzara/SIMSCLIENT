package com.mxst.car.simsclient.entity;

import java.io.Serializable;
import java.util.List;

public class InsureList implements Serializable{
    private List<Insure> cars;

    public List<Insure> getCars() {
        return cars;
    }

    public void setCars(List<Insure> cars) {
        this.cars = cars;
    }

    public class Insure{
        private String pp;
        private String brandNo;
        private String license;
        private String vinNo;

        public void setPp(String pp) {
            this.pp = pp;
        }

        public void setBrandNo(String brandNo) {
            this.brandNo = brandNo;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public void setVinNo(String vinNo) {
            this.vinNo = vinNo;
        }

        public String getPp() {
            return pp;
        }

        public String getBrandNo() {
            return brandNo;
        }

        public String getLicense() {
            return license;
        }

        public String getVinNo() {
            return vinNo;
        }
    }
}
