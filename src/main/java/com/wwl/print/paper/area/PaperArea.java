package com.wwl.print.paper.area;

import com.wwl.print.utils.Utility;

import java.awt.print.Paper;

/**
 * @author long
 * @date 2022/6/30 16:15
 * @desc 页面区域
 */
public abstract class PaperArea {

    protected double width;
    protected double height;

    protected double paddingTop;
    protected double paddingBottom;
    protected double paddingLeft;
    protected double paddingRight;

    /**
     * 可成像区域
     */
    protected ImageAbleArea imageAbleArea = new ImageAbleArea();

    public PaperArea(double width, double height){
        this.width = width;
        this.height = height;
    }


    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getPaddingTop() {
        return paddingTop;
    }

    public void setPaddingTop(double paddingTop) {
        this.paddingTop = paddingTop;
    }

    public double getPaddingBottom() {
        return paddingBottom;
    }

    public void setPaddingBottom(double paddingBottom) {
        this.paddingBottom = paddingBottom;
    }

    public double getPaddingLeft() {
        return paddingLeft;
    }

    public void setPaddingLeft(double paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    public double getPaddingRight() {
        return paddingRight;
    }

    public void setPaddingRight(double paddingRight) {
        this.paddingRight = paddingRight;
    }

    public ImageAbleArea getImageAbleArea() {
        return imageAbleArea;
    }

    public void repaintImageAbleArea(){
        this.imageAbleArea.setImageAbleX(this.paddingLeft);
        this.imageAbleArea.setImageAbleWidth(this.width - this.paddingLeft - this.paddingRight);
        this.imageAbleArea.setImageAbleY(this.paddingTop);
        this.imageAbleArea.setImageAbleHeight(this.height - this.paddingTop - this.paddingBottom);
    }
}
