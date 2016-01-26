package com.mxst.car.simsclient.entity;

public class TJKHInfo {
    public TJKHEntity tjkh;

    public TJKHEntity getTjkh() {
        return tjkh;
    }

    public void setTjkh(TJKHEntity tjkh) {
        this.tjkh = tjkh;
    }

    public class TJKHEntity{
        private int id;
        private String produceDate;
        private String jfChange;
        private String name;
        private String store;
        private String jfxw;
        private String brand;
        private String vehicleXinghao;
        private String vehicleType;
        private String mj;
        private String spec;
        private String kx;

        public void setId(int id) {
            this.id = id;
        }

        public void setProduceDate(String produceDate) {
            this.produceDate = produceDate;
        }

        public void setJfChange(String jfChange) {
            this.jfChange = jfChange;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setStore(String store) {
            this.store = store;
        }

        public void setJfxw(String jfxw) {
            this.jfxw = jfxw;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public void setVehicleXinghao(String vehicleXinghao) {
            this.vehicleXinghao = vehicleXinghao;
        }

        public void setVehicleType(String vehicleType) {
            this.vehicleType = vehicleType;
        }

        public void setMj(String mj) {
            this.mj = mj;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        public void setKx(String kx) {
            this.kx = kx;
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

        public String getName() {
            return name;
        }

        public String getStore() {
            return store;
        }

        public String getJfxw() {
            return jfxw;
        }

        public String getBrand() {
            return brand;
        }

        public String getVehicleXinghao() {
            return vehicleXinghao;
        }

        public String getVehicleType() {
            return vehicleType;
        }

        public String getMj() {
            return mj;
        }

        public String getSpec() {
            return spec;
        }

        public String getKx() {
            return kx;
        }
    }
}
