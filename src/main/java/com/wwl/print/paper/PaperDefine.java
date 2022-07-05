package com.wwl.print.paper;

/**
 * @author long
 * @date 2022/7/5 11:28
 * @desc 纸张定义
 */
public class PaperDefine {

    private String name;

    private int width;

    private int height;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public static final class PaperDefineBuilder {
        private String name;
        private int width;
        private int height;

        private PaperDefineBuilder() {
        }

        public static PaperDefineBuilder aPaperDefine() {
            return new PaperDefineBuilder();
        }

        public PaperDefineBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public PaperDefineBuilder withWidth(int width) {
            this.width = width;
            return this;
        }

        public PaperDefineBuilder withHeight(int height) {
            this.height = height;
            return this;
        }

        public PaperDefine build() {
            PaperDefine paperDefine = new PaperDefine();
            paperDefine.setName(name);
            paperDefine.setWidth(width);
            paperDefine.setHeight(height);
            return paperDefine;
        }
    }
}
