package com.mxst.car.simsclient.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Joy on 2016/1/9 0009.
 */
public class Stores {

    private List<StoresEntity> stores;

    public void setStores(List<StoresEntity> stores) {
        this.stores = stores;
    }

    public List<StoresEntity> getStores() {
        return stores;
    }

    public static class StoresEntity implements Serializable{
        private int id;
        private String storeImg;
        private String name;
        private String num;
        private String address;
        private String phone;

        public void setId(int id) {
            this.id = id;
        }

        public void setStoreImg(String storeImg) {
            this.storeImg = storeImg;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getId() {
            return id;
        }

        public String getStoreImg() {
            return storeImg;
        }

        public String getName() {
            return name;
        }

        public String getNum() {
            return num;
        }

        public String getAddress() {
            return address;
        }

        public String getPhone() {
            return phone;
        }
    }
}
