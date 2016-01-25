package com.mxst.car.simsclient.entity;

import java.io.Serializable;
import java.util.List;

public class TradeList implements Serializable {
    List<Trade> custs;

    public List<Trade> getCusts() {
        return custs;
    }

    public void setCusts(List<Trade> custs) {
        this.custs = custs;
    }

    public class Trade implements Serializable {
        private String id;
        private String name;
        private String phone;
        private String effective;
        private String tjDate;
        private String brand;
        private String store;
        private String xinghao;
        private String buyDate;
        private String niankuan;
        private String kuanxing;
        private String chengjiaojf;

        public void setId(String id) {
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

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public void setStore(String store) {
            this.store = store;
        }

        public void setXinghao(String xinghao) {
            this.xinghao = xinghao;
        }

        public void setBuyDate(String buyDate) {
            this.buyDate = buyDate;
        }

        public void setNiankuan(String niankuan) {
            this.niankuan = niankuan;
        }

        public void setKuanxing(String kuanxing) {
            this.kuanxing = kuanxing;
        }

        public void setChengjiaojf(String chengjiaojf) {
            this.chengjiaojf = chengjiaojf;
        }

        public String getId() {
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

        public String getBrand() {
            return brand;
        }

        public String getStore() {
            return store;
        }

        public String getXinghao() {
            return xinghao;
        }

        public String getBuyDate() {
            return buyDate;
        }

        public String getNiankuan() {
            return niankuan;
        }

        public String getKuanxing() {
            return kuanxing;
        }

        public String getChengjiaojf() {
            return chengjiaojf;
        }
    }
}
