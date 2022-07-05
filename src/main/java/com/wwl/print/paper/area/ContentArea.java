package com.wwl.print.paper.area;

import com.wwl.print.utils.Utility;

import java.awt.*;

/**
 * @author long
 * @date 2022/6/30 16:25
 * @desc 内容区域
 */
public class ContentArea extends PaperArea {

    private Component component;

    public ContentArea(double width, double height) {
        super(width, height);

        this.paddingTop = Utility.millimeterToDot(5);
        this.paddingBottom = Utility.millimeterToDot(5);
        this.paddingLeft = Utility.millimeterToDot(25);
        this.paddingRight = Utility.millimeterToDot(25);
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }
}
