package com.wwl.print.paper.area;

/**
 * @author long
 * @date 2022/6/30 16:18
 * @desc 可成像区域
 */
public class ImageAbleArea {

    private double imageAbleX;
    private double imageAbleY;
    private double imageAbleWidth;
    private double imageAbleHeight;

    public ImageAbleArea() {
    }

    public ImageAbleArea(double imageAbleX, double imageAbleY, double imageAbleWidth, double imageAbleHeight) {
        this.imageAbleX = imageAbleX;
        this.imageAbleY = imageAbleY;
        this.imageAbleWidth = imageAbleWidth;
        this.imageAbleHeight = imageAbleHeight;
    }

    public double getImageAbleX() {
        return imageAbleX;
    }

    public void setImageAbleX(double imageAbleX) {
        this.imageAbleX = imageAbleX;
    }

    public double getImageAbleY() {
        return imageAbleY;
    }

    public void setImageAbleY(double imageAbleY) {
        this.imageAbleY = imageAbleY;
    }

    public double getImageAbleWidth() {
        return imageAbleWidth;
    }

    public void setImageAbleWidth(double imageAbleWidth) {
        this.imageAbleWidth = imageAbleWidth;
    }

    public double getImageAbleHeight() {
        return imageAbleHeight;
    }

    public void setImageAbleHeight(double imageAbleHeight) {
        this.imageAbleHeight = imageAbleHeight;
    }
}
