package com.mxst.car.simsclient.entity;

import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/15.
 * version:  V1.0
 * Description:
 */
public class JGDetail {

    private ArtisanDetailEntity artisanDetail;

    private List<CommentsEntity> comments;

    private List<ZhengShuEntity> zhengShu;

    public void setArtisanDetail(ArtisanDetailEntity artisanDetail) {
        this.artisanDetail = artisanDetail;
    }

    public void setComments(List<CommentsEntity> comments) {
        this.comments = comments;
    }

    public void setZhengShu(List<ZhengShuEntity> zhengShu) {
        this.zhengShu = zhengShu;
    }

    public ArtisanDetailEntity getArtisanDetail() {
        return artisanDetail;
    }

    public List<CommentsEntity> getComments() {
        return comments;
    }

    public List<ZhengShuEntity> getZhengShu() {
        return zhengShu;
    }

    public static class ArtisanDetailEntity {
        private int id;
        private String headPortrait;
        private String name;
        private String phone;

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        private String intro;
        private float star_level;
           
        public void setId(int id) {
            this.id = id;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
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

        public int getId() {
            return id;
        }

        public String getHeadPortrait() {
            return headPortrait;
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
    }

    public static class CommentsEntity {
        private String content;
        private float star;
        private long comment_date;
        private String name;

        public void setContent(String content) {
            this.content = content;
        }

        public void setStar(float star) {
            this.star = star;
        }

        public void setComment_date(long comment_date) {
            this.comment_date = comment_date;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public float getStar() {
            return star;
        }

        public long getComment_date() {
            return comment_date;
        }

        public String getName() {
            return name;
        }
    }

    public static class ZhengShuEntity {
        private String img;

        public void setImg(String img) {
            this.img = img;
        }

        public String getImg() {
            return img;
        }
    }
}
