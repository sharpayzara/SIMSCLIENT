package com.mxst.car.simsclient.entity;

import java.util.List;

/**
 * author   Joy
 * Date:  2016/1/5.
 * version:  V1.0
 * Description:
 */
public class SpecList {

    private String spec;

    private List<XhsEntity> xhs;

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public void setXhs(List<XhsEntity> xhs) {
        this.xhs = xhs;
    }

    public String getSpec() {
        return spec;
    }

    public List<XhsEntity> getXhs() {
        return xhs;
    }

    public static class XhsEntity {
        private String xingHao;

        public void setXingHao(String xingHao) {
            this.xingHao = xingHao;
        }

        public String getXingHao() {
            return xingHao;
        }
    }
}
