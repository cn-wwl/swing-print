package com.wwl.print.ui;

import com.wwl.print.base.OffSet;
import com.wwl.print.paper.format.ExtPageFormat;

import javax.swing.*;
import java.awt.*;

/**
 * @author long
 * @date 2022/7/4 9:34
 * @desc 纸张设置显示组件
 */
public class PaperComponent extends JPanel {

    /**
     * 当前显示的页面
     */
    protected ExtPageFormat pageFormat;

    /**
     * 显示的缩放比例(1-100)
     */
    private double scaleFactor;

    public PaperComponent(ExtPageFormat pageFormat) {
        this.pageFormat =pageFormat;
        this.scaleFactor = 20;
        this.setBackground(Color.gray);
    }

    public void setPageFormat(ExtPageFormat pageFormat) {
        this.pageFormat = pageFormat;
        revalidate();
    }
    public void setScaleFactor(int scale) {
        this.scaleFactor = scale;
        revalidate();
    }

    public Dimension getSizeWithScale(double scale) {
        int width = ((int) (pageFormat.getWidth() * scale / 100));
        int height = ((int) (pageFormat.getHeight() * scale / 100));

        return new Dimension(width, height);
    }

    @Override
    public Dimension getPreferredSize() {
        return getSizeWithScale(scaleFactor);
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }


    @Override
    public void paintComponent(Graphics newGraphics) {
        super.paintComponent(newGraphics);
        Graphics2D newGraphics2D = (Graphics2D) newGraphics;

        OffSet allOffset = new OffSet();

        int headHeight = (int) (pageFormat.getHeadArea().getHeight() * scaleFactor / 100);
        int footHeight = (int) (pageFormat.getFootArea().getHeight() * scaleFactor / 100);

        //get paperSize
        int paperWidth = (int) (pageFormat.getWidth() * scaleFactor / 100);
        int paperHeight = (int) (pageFormat.getHeight() * scaleFactor / 100);

        //get head/foot infomation
        int headWidth = (int) paperWidth;
        int footWidth = (int) paperWidth;

        //set the paper in the mid of the printComponent Panel
        int thisWidth = this.getWidth();
        int thisHeight = this.getHeight();

        int offsetX = (thisWidth - paperWidth) / 2;
        int offsetY = (thisHeight - paperHeight) / 2;

        //offset to enable area
        newGraphics2D.translate(offsetX, offsetY);
        allOffset.setX(allOffset.getX() + offsetX);
        allOffset.setY(allOffset.getY() + offsetY);

        //print head area
        int headImageAbleX = (int) (pageFormat.getHeadArea().getImageAbleArea().getImageAbleX() * scaleFactor / 100);
        int headImageAbleY = (int) (pageFormat.getHeadArea().getImageAbleArea().getImageAbleY() * scaleFactor / 100);
        int headImageAbleWidth = (int) (pageFormat.getHeadArea().getImageAbleArea().getImageAbleWidth() * scaleFactor / 100);
        int headImageAbleHeight = (int) (pageFormat.getHeadArea().getImageAbleArea().getImageAbleHeight() * scaleFactor / 100);

        drawHeadAndFoot(newGraphics2D, headWidth, headHeight, headImageAbleX, headImageAbleY, headImageAbleWidth, headImageAbleHeight);
        allOffset.setY(allOffset.getY() + headHeight);
        this.drawContent(newGraphics2D, allOffset);

        //print foot area
        int footImageAbleX = (int) (pageFormat.getFootArea().getImageAbleArea().getImageAbleX() * scaleFactor / 100);
        int footImageAbleY = (int) (pageFormat.getFootArea().getImageAbleArea().getImageAbleY() * scaleFactor / 100);
        int footImageAbleWidth = (int) (pageFormat.getFootArea().getImageAbleArea().getImageAbleWidth() * scaleFactor / 100);
        int footImageAbleHeight = (int) (pageFormat.getFootArea().getImageAbleArea().getImageAbleHeight() * scaleFactor / 100);

        drawHeadAndFoot(newGraphics2D, footWidth, footHeight, footImageAbleX, footImageAbleY, footImageAbleWidth, footImageAbleHeight);
        allOffset.setY(allOffset.getY() + footHeight);
        newGraphics2D.translate(-allOffset.getX(), -allOffset.getY());
    }


    private void drawContent(Graphics2D newGraphics2D,OffSet allOffset ){

        int contentWidth = (int)(pageFormat.getContentArea().getWidth() * scaleFactor / 100);
        int contentHeight = (int)(pageFormat.getContentArea().getHeight() * scaleFactor / 100);

        //draw the area of the paper
        newGraphics2D.setColor(Color.white);
        newGraphics2D.fillRect(0, 0, contentWidth  ,contentHeight);

        //draw the border of the paper
        newGraphics2D.setColor(Color.black);
        newGraphics2D.drawRect(-1, -1, contentWidth+ 1 , contentHeight+1);

        //get offset infomation
        int imageAbleX = (int) (pageFormat.getContentArea().getImageAbleArea().getImageAbleX() * scaleFactor / 100d);
        int imageAbleY = (int) (pageFormat.getContentArea().getImageAbleArea().getImageAbleY() * scaleFactor / 100d);
        int imageAbleWidth = (int) (pageFormat.getContentArea().getImageAbleArea().getImageAbleWidth() * scaleFactor / 100d);
        int imageAbleHeight =  (int) (pageFormat.getContentArea().getImageAbleArea().getImageAbleHeight() * scaleFactor / 100d);

        //draw the area of the imageable
        newGraphics2D.setColor(Color.lightGray);
        newGraphics2D.fillRect(imageAbleX, imageAbleY, imageAbleWidth, imageAbleHeight);

        //draw the border of the imageable
        newGraphics2D.setColor(Color.black);
        newGraphics2D.drawRect(imageAbleX - 1, imageAbleY - 1, imageAbleWidth + 1, imageAbleHeight + 1);

        newGraphics2D.translate(0, contentHeight);
        allOffset.setY(allOffset.getY()+ contentHeight);
    }

    private void drawHeadAndFoot(Graphics2D newGraphics2D, int areaWidth, int areaHeight, int imageAbleX, int imageAbleY, int imageAbleWidth, int imageAbleHeight) {
        newGraphics2D.setColor(Color.black);
        newGraphics2D.drawRect(-1, -1, areaWidth + 1, areaHeight + 1);

        newGraphics2D.setColor(Color.white);
        newGraphics2D.fillRect(0, 0, areaWidth, areaHeight);

        newGraphics2D.setColor(Color.black);
        newGraphics2D.drawRect(imageAbleX - 1, imageAbleY - 1, imageAbleWidth + 1, imageAbleHeight + 1);

        newGraphics2D.setColor(Color.blue);
        newGraphics2D.fillRect(imageAbleX, imageAbleY, imageAbleWidth, imageAbleHeight);

        newGraphics2D.translate(0, areaHeight);
    }



}
