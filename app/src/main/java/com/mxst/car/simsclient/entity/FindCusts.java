package com.mxst.car.simsclient.entity;

import java.io.Serializable;
import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/25.
 * version:  V1.0
 * Description:
 */
public class FindCusts implements Serializable {

    private List<CustsEntity> custs;

    public void setCusts(List<CustsEntity> custs) {
        this.custs = custs;
    }

    public List<CustsEntity> getCusts() {
        return custs;
    }

    public static class CustsEntity implements Serializable {
        private int id;
        private String name;
        private String phone;
        private String effective;
        private String tjDate;
        private String tjbrand;
        private String store;
        private String tjchexing;

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setEffective(String effective) {
            this.effective = effective;
        }

        public void setTjDate(String tjDate) {
            this.tjDate = tjDate;
        }

        public void setTjbrand(String tjbrand) {
            this.tjbrand = tjbrand;
        }

        public void setStore(String store) {
            this.store = store;
        }

        public void setTjchexing(String tjchexing) {
            this.tjchexing = tjchexing;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }

        public String getEffective() {
            return effective;
        }

        public String getTjDate() {
            return tjDate;
        }

        public String getTjbrand() {
            return tjbrand;
        }

        public String getStore() {
            return store;
        }

        public String getTjchexing() {
            return tjchexing;
        }
    }
}
