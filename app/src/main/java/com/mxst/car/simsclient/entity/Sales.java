package com.mxst.car.simsclient.entity;

import java.util.List;

/**
 * Created by Joy on 2016/1/9 0009.
 */
public class Sales {

    private List<SalesEntity> sales;

    public void setSales(List<SalesEntity> sales) {
        this.sales = sales;
    }

    public List<SalesEntity> getSales() {
        return sales;
    }

    public static class SalesEntity {
        private int id;
        private String name;
        private String phone;
        private float star_level;
        private String img;

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setStar_level(float star_level) {
            this.star_level = star_level;
        }

        public void setImg(String img) {
            this.img = img;
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

        public float getStar_level() {
            return star_level;
        }

        public String getImg() {
            return img;
        }
    }
}
