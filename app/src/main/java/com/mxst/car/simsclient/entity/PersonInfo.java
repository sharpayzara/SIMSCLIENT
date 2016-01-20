package com.mxst.car.simsclient.entity;

/**
 * author   Joy
 * Date:  2016/1/20.
 * version:  V1.0
 * Description:
 */
public class PersonInfo {

    private VipInfoEntity vipInfo;

    public void setVipInfo(VipInfoEntity vipInfo) {
        this.vipInfo = vipInfo;
    }

    public VipInfoEntity getVipInfo() {
        return vipInfo;
    }

    public static class VipInfoEntity {
        private int id;
        private String vipNum;
        private String name;
        private String nickName;
        private String gender;
        private String phone;
        private String email;
        private String birthday;
        private String age;
        private String address;
        private String registDate;
        private int jifen;
        private int continueQd;
        private String headImg;

        public void setId(int id) {
            this.id = id;
        }

        public void setVipNum(String vipNum) {
            this.vipNum = vipNum;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setRegistDate(String registDate) {
            this.registDate = registDate;
        }

        public void setJifen(int jifen) {
            this.jifen = jifen;
        }

        public void setContinueQd(int continueQd) {
            this.continueQd = continueQd;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public int getId() {
            return id;
        }

        public String getVipNum() {
            return vipNum;
        }

        public String getName() {
            return name;
        }

        public String getNickName() {
            return nickName;
        }

        public String getGender() {
            return gender;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }

        public String getBirthday() {
            return birthday;
        }

        public String getAge() {
            return age;
        }

        public String getAddress() {
            return address;
        }

        public String getRegistDate() {
            return registDate;
        }

        public int getJifen() {
            return jifen;
        }

        public int getContinueQd() {
            return continueQd;
        }

        public String getHeadImg() {
            return headImg;
        }
    }
}
