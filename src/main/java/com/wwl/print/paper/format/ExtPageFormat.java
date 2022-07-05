package com.wwl.print.paper.format;

import com.wwl.print.base.OrientationType;
import com.wwl.print.base.TableTitleType;
import com.wwl.print.paper.area.ContentArea;
import com.wwl.print.paper.area.FootArea;
import com.wwl.print.paper.area.HeadArea;

import java.awt.print.PageFormat;
import java.awt.print.Paper;

/**
 * @author long
 * @date 2022/6/30 16:28
 * @desc 扩展的页面格式
 */
public class ExtPageFormat extends PageFormat {

    /**
     * 页眉
     */
    private HeadArea headArea = new HeadArea(this.getPaper().getWidth(), this.getPaper().getHeight() * 15 / 100);

    /**
     * 内容
     */
    private ContentArea contentArea = new ContentArea(this.getPaper().getWidth(), this.getPaper().getHeight() * 70 / 100);

    /**
     * 页脚
     */
    private FootArea footArea = new FootArea(this.getPaper().getWidth(), this.getPaper().getHeight() * 15 / 100);

    /**
     * 表格 标题显示方式
     */
    private TableTitleType tableTitleType=TableTitleType.AllPage;

    /**
     * 页面缩放比例
     */
    private int paperScale = 100;

    /**
     * 打印任务名称
     */
    private String jobName = "zlDesktop";

    /**
     * 打印方向
     */
    private OrientationType orientationType = OrientationType.Portrait;

    //region 属性

    public HeadArea getHeadArea() {
        return headArea;
    }

    public void setHeadArea(HeadArea headArea) {
        this.headArea = headArea;
    }

    public ContentArea getContentArea() {
        return contentArea;
    }

    public void setContentArea(ContentArea contentArea) {
        this.contentArea = contentArea;
    }

    public FootArea getFootArea() {
        return footArea;
    }

    public void setFootArea(FootArea footArea) {
        this.footArea = footArea;
    }


    public TableTitleType getTableTitleType() {
        return tableTitleType;
    }

    public void setTableTitleType(TableTitleType tableTitleType) {
        this.tableTitleType = tableTitleType;
    }

    public int getPaperScale() {
        return paperScale;
    }

    public void setPaperScale(int paperScale) {
        this.paperScale = paperScale;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public OrientationType getOrientationType() {
        return orientationType;
    }

    public void setOrientationType(OrientationType orientationType) {
        if (orientationType ==  this.orientationType){
            return;
        }
        this.orientationType = orientationType;
        this.setOrientationTypeAreaParams();
    }

    private void setOrientationTypeAreaParams() {
        if (this.orientationType == OrientationType.Portrait) {

            this.headArea.setWidth(this.getPaper().getWidth());
            this.footArea.setWidth(this.getPaper().getWidth());
            this.contentArea.setWidth(this.getPaper().getWidth());
            contentArea.setHeight(this.getPaper().getHeight() - headArea.getHeight() - footArea.getHeight());

        } else {
            this.headArea.setWidth(this.getPaper().getHeight());
            this.footArea.setWidth(this.getPaper().getHeight());
            this.contentArea.setWidth(this.getPaper().getHeight());
            contentArea.setHeight(this.getPaper().getWidth() - headArea.getHeight() - footArea.getHeight());
        }

        headArea.repaintImageAbleArea();
        contentArea.repaintImageAbleArea();
        footArea.repaintImageAbleArea();
    }

    //endregion

    //region 方法重写

    @Override
    public double getWidth() {

        if (this.orientationType== OrientationType.Transverse) {
            return this.getPaper().getHeight();
        } else {
            return this.getPaper().getWidth();
        }
    }

    @Override
    public double getHeight() {
        if (this.orientationType== OrientationType.Portrait) {
            return this.getPaper().getHeight();
        } else {
            return this.getPaper().getWidth();
        }
    }

    @Override
    public double getImageableX() {

        return this.getPaper().getImageableX();
    }

    @Override
    public double getImageableY() {
        return this.getPaper().getImageableY();
    }

    @Override
    public double getImageableWidth() {
        return this.contentArea.getImageAbleArea().getImageAbleWidth();
    }

    @Override
    public double getImageableHeight() {
       return this.getPaper().getHeight() - headArea.getPaddingTop() - footArea.getPaddingBottom();
    }

    @Override
    public void setPaper(Paper newPaper) {
        super.setPaper(newPaper);
        this.setOrientationTypeAreaParams();
    }


    //endregion

}
