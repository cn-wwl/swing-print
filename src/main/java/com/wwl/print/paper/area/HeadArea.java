package com.wwl.print.paper.area;

import com.wwl.print.utils.Utility;

/**
 * @author long
 * @date 2022/6/30 16:21
 * @desc 页眉区域
 */
public class HeadArea extends PaperArea {

    /**
     * 标题
     */
    private String title;


    public HeadArea(double width, double height) {
        super(width, height);

        this.paddingTop = Utility.millimeterToDot(25);
        this.paddingBottom = Utility.millimeterToDot(5);
        this.paddingLeft = Utility.millimeterToDot(25);
        this.paddingRight = Utility.millimeterToDot(25);

        this.repaintImageAbleArea();

    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
