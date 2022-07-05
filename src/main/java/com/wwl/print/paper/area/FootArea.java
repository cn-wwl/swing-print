package com.wwl.print.paper.area;

import com.wwl.print.utils.Utility;

/**
 * @author long
 * @date 2022/6/30 16:24
 * @desc 页脚区域
 */
public class FootArea extends PaperArea {

    protected String leftContent;
    protected String rightContent;

    public FootArea(double width, double height) {
        super(width, height);

        this.paddingTop = Utility.millimeterToDot(5);
        this.paddingBottom = Utility.millimeterToDot(25);
        this.paddingLeft = Utility.millimeterToDot(25);
        this.paddingRight = Utility.millimeterToDot(25);
    }



    public String getLeftContent() {
        return leftContent;
    }

    public void setLeftContent(String leftContent) {
        this.leftContent = leftContent;
    }

    public String getRightContent() {
        return rightContent;
    }

    public void setRightContent(String rightContent) {
        this.rightContent = rightContent;
    }
}
