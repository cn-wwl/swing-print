package com.wwl.print.utils;

/**
 * @author wwl
 * @date 2022/6/2 14:18
 * @desc 页面尺寸 像素处理工具类
 */
public class Utility {

    public static final double INCHES_PRE_MILLIMETER = 0.0394;
    public static final int DPI = 72;

    public Utility() {

    }

    /**
     * 英寸 转 毫米(精准)
     *
     * @param inch 英寸
     */
    public static double inchToMillimeter(double inch) {
        double millimeter;
        millimeter = inch / INCHES_PRE_MILLIMETER;
        return millimeter;
    }

    /**
     * 英寸 转 毫米
     *
     * @param inch 英寸
     */
    public static int inchToMillimeter(int inch) {
        int millimeter;
        millimeter = (int) ((double) inch / INCHES_PRE_MILLIMETER);
        return millimeter;
    }

    /**
     * 毫米 转 英寸(精准)
     *
     * @param millimeter 毫米
     */
    public static double millimeterToInch(double millimeter) {
        double inch;
        inch = millimeter * INCHES_PRE_MILLIMETER;
        return inch;
    }

    /**
     * 毫米 转 英寸
     *
     * @param millimeter 毫米
     */
    public static int millimeterToInch(int millimeter) {
        int inch;
        inch = (int) ((double) millimeter * INCHES_PRE_MILLIMETER);
        return inch;
    }

    /**
     * 像素 转 英寸
     *
     * @param dot 像素
     */
    public static int dotToInch(int dot) {
        int inch;
        inch = (int) ((double) dot / DPI);
        return inch;
    }

    /**
     * 像素 转 英寸(精准)
     *
     * @param dot 像素
     */
    public static double dotToInch(double dot) {
        double inch;
        inch = dot / DPI;
        return inch;
    }

    /**
     * 英寸 转 像素
     *
     * @param inch 英寸
     */
    public static int inchToDot(int inch) {
        int dot;
        dot = inch * DPI;
        return dot;
    }

    /**
     * 英寸 转 像素(精准)
     *
     * @param inch 英寸
     */
    public static double inchToDot(double inch) {
        double dot;
        dot = inch * DPI;
        return dot;
    }

    /**
     * 毫米 转 像素
     *
     * @param millimeter 毫米
     */
    public static int millimeterToDot(int millimeter) {
        int dot;
        double inch;
        inch = Utility.millimeterToInch((double) millimeter);
        dot = (int) Utility.inchToDot(inch);
        return dot;
    }

    /**
     * 毫米 转 像素(精准)
     *
     * @param millimeter 毫米
     */
    public static double millimeterToDot(double millimeter) {
        double dot;
        double inch;
        inch = Utility.millimeterToInch(millimeter);
        dot = Utility.inchToDot(inch);
        return dot;
    }

    /**
     * 像素 转 毫米
     *
     * @param dot 像素
     */
    public static int dotToMillimeter(int dot) {
        int millimeter;
        double inch;
        inch = Utility.dotToInch((double) dot);
        millimeter = (int) Utility.inchToMillimeter(inch);
        return millimeter;
    }

    /**
     * 像素 转 毫米(精准)
     *
     * @param dot 像素
     */
    public static double dotToMillimeter(double dot) {
        double millimeter;
        double inch;
        inch = Utility.dotToInch(dot);
        millimeter = Utility.inchToMillimeter(inch);
        return millimeter;
    }
}
