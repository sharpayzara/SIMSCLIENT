package com.mxst.car.simsclient.entity;

import java.io.Serializable;
import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/14.
 * version:  V1.0
 * Description:
 */
public class CarList implements Serializable{

    private List<CarsEntity> cars;

    public void setCars(List<CarsEntity> cars) {
        this.cars = cars;
    }

    public List<CarsEntity> getCars() {
        return cars;
    }

    public static class CarsEntity implements Serializable {
        private String cx;
        private String license;
        private String pp;

        public void setCx(String cx) {
            this.cx = cx;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public void setPp(String pp) {
            this.pp = pp;
        }

        public String getCx() {
            return cx;
        }

        public String getLicense() {
            return license;
        }

        public String getPp() {
            return pp;
        }
    }
}
