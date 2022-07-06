package com.wwl.print.able;

import com.wwl.print.base.OffSet;
import com.wwl.print.base.TableTitleType;
import com.wwl.print.paper.format.ExtPageFormat;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author long
 * @date 2022/7/1 13:28
 * @desc 表格打印接口
 */
public class TablePrintable implements Printable, Pageable {

    private JTable table;

    private ExtPageFormat pageFormat;

    public TablePrintable(JTable table, ExtPageFormat pageFormat){
        this.table = table;
        this.pageFormat = pageFormat;
    }

    @Override
    public int getNumberOfPages() {
        Dimension size = null;
        int tableWidth = table.getWidth();
        int tableHeight = table.getHeight();
        int positionX;
        int positionY = 0;

        int pageIndex = 0;
        while (positionY < tableHeight) {
            positionX = 0;
            while (positionX < tableWidth) {
                size = getPrintSize(positionX, positionY);
                positionX += size.width;
                pageIndex++;
            }
            positionY += size.height;
        }
        return pageIndex;
    }

    protected Dimension getPrintSize(int positionX, int positionY) {
        Rectangle rect;

        int printWidth;
        int printHeight;

        int maxWidth = (int) (pageFormat.getImageableWidth() * 100 / pageFormat.getPaperScale());
        int maxHeight = (int) (pageFormat.getContentArea().getImageAbleArea().getImageAbleHeight() * 100 / pageFormat.getPaperScale());

        if (displayHeaderOnPage(positionY)) {
            maxHeight -= table.getTableHeader().getHeight();
        }

        int lastCol = table.columnAtPoint(new Point(positionX + maxWidth, positionY));
        if (lastCol == -1) {
            printWidth = table.getWidth() - positionX;
        } else {
            rect = table.getCellRect(0, lastCol - 1, true);
            printWidth = rect.x + rect.width - positionX;
        }

        int lastRow = table.rowAtPoint(new Point(positionX, positionY + maxHeight));
        if (lastRow == -1) {
            printHeight = table.getHeight() - positionY;
        } else {
            rect = table.getCellRect(lastRow - 1, 0, true);
            printHeight = rect.y + rect.height - positionY;
        }

        return new Dimension(printWidth, printHeight);
    }

    protected boolean displayHeaderOnPage(int positionY) {
        if ((pageFormat.getTableTitleType() == TableTitleType.AllPage) || ((pageFormat.getTableTitleType() == TableTitleType.FirstPage) && positionY == 0)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public PageFormat getPageFormat(int pageIndex) throws IndexOutOfBoundsException {
        return pageFormat;
    }

    @Override
    public Printable getPrintable(int pageIndex) throws IndexOutOfBoundsException {
        return this;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        Dimension size = null;

        if ((table.getWidth() == 0) || (table.getHeight() == 0)) {
            table.setSize(table.getPreferredSize());
        }

        int tableWidth = table.getWidth();
        int tableHeight = table.getHeight();
        int positionX = 0;
        int positionY = 0;

        int index = 0;

        while (positionY < tableHeight) {
            positionX = 0;
            while (positionX < tableWidth) {
                size = getPrintSize(positionX, positionY);
                if (index == pageIndex) {
                    paintTable(graphics, positionX, positionY, size);
                    return Printable.PAGE_EXISTS;
                }

                index++;
                positionX += size.width;
            }
            positionY += size.height;
        }
        return Printable.NO_SUCH_PAGE;
    }

    private void paintTable(Graphics graphics, int positionX, int positionY, Dimension size) {

        OffSet allOffSet = new OffSet();
        this.printHeadArea(graphics, allOffSet);
        this.printContentArea(graphics, allOffSet, positionX, positionY, size);
        this.printFootArea(graphics, positionX, positionY, allOffSet);
        graphics.translate(-allOffSet.getX(), -allOffSet.getY());
    }

    private void printHeadArea(Graphics graphics,OffSet offSet) {
        Font oldFont = graphics.getFont();
        graphics.setFont(new Font("宋体", Font.BOLD, 28));
        graphics.setColor(Color.black);

        int headWidth = (int) pageFormat.getHeadArea().getWidth();
        int headHeight = (int) pageFormat.getHeadArea().getHeight();

        int headImageAbleX = (int) pageFormat.getHeadArea().getImageAbleArea().getImageAbleX();
        int headImageAbleY = (int) pageFormat.getHeadArea().getImageAbleArea().getImageAbleY();
        int headImageAbleWidth = (int) pageFormat.getHeadArea().getImageAbleArea().getImageAbleWidth();
        int headImageAbleHeight = (int) pageFormat.getHeadArea().getImageAbleArea().getImageAbleHeight();

        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, headWidth, headHeight);
        if (pageFormat.getHeadArea().getTitle()==null || Objects.equals(pageFormat.getHeadArea().getTitle(), "")){
            pageFormat.getHeadArea().setTitle("测试表");
        }

        if (pageFormat.getHeadArea().getTitle() != null) {

            graphics.setColor(Color.black);
            graphics.drawString(pageFormat.getHeadArea().getTitle(), headImageAbleX + headImageAbleWidth / 2 - pageFormat.getHeadArea().getTitle().length() * 15, headImageAbleY + (headImageAbleHeight / 2 + 6));

            graphics.drawLine(headImageAbleX + headImageAbleWidth / 2 - pageFormat.getHeadArea().getTitle().length() * 15-1,headImageAbleY + (headImageAbleHeight / 2 + 14),
                    (int)(headImageAbleX + headImageAbleWidth / 2 +pageFormat.getHeadArea().getTitle().length()*15.2),
                    headImageAbleY + (headImageAbleHeight / 2 + 14)
                    );
            graphics.drawLine(headImageAbleX + headImageAbleWidth / 2 - pageFormat.getHeadArea().getTitle().length() * 15-1,headImageAbleY + (headImageAbleHeight / 2 + 16),
                    (int)(headImageAbleX + headImageAbleWidth / 2 +pageFormat.getHeadArea().getTitle().length()*15.2) ,
                    headImageAbleY + (headImageAbleHeight / 2 + 16)
            );
        }

        graphics.setFont(oldFont);
        graphics.translate(0, headHeight);
        offSet.setY(offSet.getY()+headHeight);
    }

    private void printContentArea(Graphics graphics,OffSet allOffSet , int positionX, int positionY, Dimension size){
        int contentWidth = (int) (pageFormat.getContentArea().getWidth());
        int contentHeight = (int) (pageFormat.getContentArea().getHeight());
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, contentWidth, contentHeight);

        Rectangle clipRect = graphics.getClipBounds();
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.scale((double) (pageFormat.getPaperScale()) / 100.0, (double) (pageFormat.getPaperScale()) / 100.0);

        OffSet offSet = new OffSet();
        offSet.setX((int) (pageFormat.getContentArea().getImageAbleArea().getImageAbleX()));
        offSet.setY((int) (pageFormat.getContentArea().getImageAbleArea().getImageAbleY()));
        if (displayHeaderOnPage(positionY)){
            // 打印表头
            this.printTableAndHeader(graphics,offSet,positionX,positionY,size);
        }else {
            // 不打印表头
            this.printTable(graphics,offSet,positionX,positionY,size);
        }

        graphics2D.scale(100.0 / pageFormat.getPaperScale(), 100.0 / pageFormat.getPaperScale());

       // graphics.translate((int) (((double) (size.width *  pageFormat.getPaperScale() / 100) - pageFormat.getImageableWidth()) / 2), 0);
        //restore the clip
        graphics.setClip(clipRect);

        //translate for the main paper
        graphics.translate(0, contentHeight);
        allOffSet.setY(offSet.getY()+contentHeight);
    }

    private void printTableAndHeader(Graphics graphics,OffSet offSet, int positionX, int positionY, Dimension size){
        JTableHeader header = table.getTableHeader();
        if ((header.getHeight() == 0) || (header.getWidth() == 0)) {
            header.setSize(header.getPreferredSize());
        }

        int headerHeight = header.getHeight();

        //show the offset as the real size,so multiply the table scale with offset
        graphics.translate((int) (offSet.getX() * 100.0 / pageFormat.getPaperScale()) - positionX, (int) (offSet.getY() * 100.0 / pageFormat.getPaperScale()));

        graphics.clipRect(positionX, 0, size.width, size.height + headerHeight);

        header.paint(graphics);

        //draw v(|) line with table head
        graphics.setColor(Color.gray);
        graphics.drawLine(positionX, 0, positionX, headerHeight);

        //draw h(-) line with table head
        graphics.setColor(Color.gray);
        graphics.drawLine(positionX, 0, positionX + size.width, 0);

        //draw the table
        graphics.translate(0, headerHeight - positionY);
        graphics.clipRect(positionX, positionY, size.width, size.height);

        table.paint(graphics);

        //draw h-line with table head
        graphics.setColor(Color.gray);
        graphics.drawLine(positionX, positionY, positionX, size.height + positionY);

        //restore the translate
        graphics.translate(0, positionY - headerHeight);
        graphics.translate((int) (positionX - offSet.getX() * (100.0 / pageFormat.getPaperScale())), (int) (-offSet.getY() * (100.0 / pageFormat.getPaperScale())));
    }

    private void printTable(Graphics graphics,OffSet offSet, int positionX, int positionY, Dimension size){
        //show the offset as the real size,so multiply the table scale with offset
        graphics.translate((int) (offSet.getX() * 100.0 / pageFormat.getPaperScale() - positionX), (int) (offSet.getY() * 100.0 / pageFormat.getPaperScale() - positionY));
        //as i said,size has been measured with table scale,so there needn't
        graphics.clipRect(positionX, positionY, size.width, size.height);

        table.paint(graphics);

        //draw v-line without table head
        graphics.setColor(Color.gray);
        graphics.drawLine(positionX, positionY, positionX + size.width, positionY);

        //draw h-line without table head
        graphics.setColor(Color.gray);
        graphics.drawLine(positionX, positionY, positionX, size.height + positionY);

        //restore the translate
        graphics.translate((int) (positionX - offSet.getX() * (100.0 / pageFormat.getPaperScale())), (int) (positionY - offSet.getY() * (100.0 / pageFormat.getPaperScale())));
    }

    private void printFootArea(Graphics graphics, int positionX, int positionY,OffSet offSet){
        int footWidth = (int) pageFormat.getFootArea().getWidth();
        int footHeight = (int) pageFormat.getFootArea().getHeight();

        int footImageAbleX = (int) pageFormat.getFootArea().getImageAbleArea().getImageAbleX();
        int footImageAbleY = (int) pageFormat.getFootArea().getImageAbleArea().getImageAbleY();
        int footImageAbleWidth = (int) pageFormat.getFootArea().getImageAbleArea().getImageAbleWidth();
        int footImageAbleHeight = (int) pageFormat.getFootArea().getImageAbleArea().getImageAbleHeight();

        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, footWidth, footHeight);

        String footLeftContent;

        //print string of foot
        if (pageFormat.getFootArea().getLeftContent() == null || pageFormat.getFootArea().getLeftContent().isEmpty()) {
            footLeftContent = String.format("页数：%s/%s",this.getCurrentNumberOfPages(positionX, positionY),this.getNumberOfPages());
        }else {
            footLeftContent = pageFormat.getFootArea().getLeftContent();
        }

        graphics.setColor(Color.black);
        graphics.drawString(footLeftContent, footImageAbleX + 10, footImageAbleY + (footImageAbleHeight / 2 + 6));

        String footRightContent;
        if (pageFormat.getFootArea().getRightContent() == null || pageFormat.getFootArea().getRightContent().isEmpty()) {
            Date localDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            footRightContent = "日期："+ sdf.format(localDate);
        }else {
            footRightContent = pageFormat.getFootArea().getRightContent();
        }
        graphics.setColor(Color.black);

        switch (pageFormat.getOrientationType()){
            case Portrait:
                graphics.drawString(footRightContent, footImageAbleX + footImageAbleWidth - (int) (footRightContent.length() * 8.6), footImageAbleY + (footImageAbleHeight / 2 + 6));
                break;
            case Transverse:
                graphics.drawString(footRightContent, footImageAbleX + footImageAbleWidth - (int)(footRightContent.length() * 10.2), footImageAbleY + (footImageAbleHeight / 2 + 6));
                break;
            default:
                break;
        }
        graphics.translate(0, footHeight);
        offSet.setY(offSet.getY()+footHeight);
    }

    public int getCurrentNumberOfPages(int currentPositionX, int currentPositionY) {
        Dimension size = null;
        int tableWidth = table.getWidth();
        int tableHeight = table.getHeight();
        int positionX = 0;
        int positionY = 0;

        int pageIndex = 0;
        while (positionY < tableHeight) {
            positionX = 0;
            while (positionX < tableWidth) {
                size = getPrintSize(positionX, positionY);
                pageIndex++;
                System.out.println(currentPositionX);
                System.out.println(currentPositionY);
                if ((currentPositionX == positionX) && (currentPositionY == positionY)) {
                    return pageIndex;
                }
                positionX += size.width;
            }
            positionY += size.height;
        }
        return 0;
    }

}
