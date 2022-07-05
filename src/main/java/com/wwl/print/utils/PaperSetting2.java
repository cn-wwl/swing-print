package com.wwl.print.utils;

import com.wwl.print.paper.PaperDefine;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.print.Paper;

/**
 * @author wwl
 * @date 2022/6/2 17:05
 * @desc TODO
 */
public class PaperSetting2 {

    private static Paper paper = new Paper();

    protected static final double inchesPerMillimeter = 0.0394;

    protected static final int margin = (int)(0.0394*25*72);

    private static List<PaperDefine> paperList =new ArrayList<>();

    static  {

        paperList.add(PaperDefine.PaperDefineBuilder.aPaperDefine().withName("A0").withWidth((int) (841 * inchesPerMillimeter * 72)).withHeight((int) (1189 * inchesPerMillimeter * 72)).build());
        paperList.add(PaperDefine.PaperDefineBuilder.aPaperDefine().withName("A1").withWidth((int) (594 * inchesPerMillimeter * 72)).withHeight((int) (841 * inchesPerMillimeter * 72)).build());
        paperList.add(PaperDefine.PaperDefineBuilder.aPaperDefine().withName("A2").withWidth((int) (420 * inchesPerMillimeter * 72)).withHeight((int) (594 * inchesPerMillimeter * 72)).build());
        paperList.add(PaperDefine.PaperDefineBuilder.aPaperDefine().withName("A3").withWidth((int) (297 * inchesPerMillimeter * 72)).withHeight((int) (420 * inchesPerMillimeter * 72)).build());
        paperList.add(PaperDefine.PaperDefineBuilder.aPaperDefine().withName("A4").withWidth((int) (210 * inchesPerMillimeter * 72)).withHeight((int) (297 * inchesPerMillimeter * 72)).build());
        paperList.add(PaperDefine.PaperDefineBuilder.aPaperDefine().withName("A5").withWidth((int) (148 * inchesPerMillimeter * 72)).withHeight((int) (210 * inchesPerMillimeter * 72)).build());
        paperList.add(PaperDefine.PaperDefineBuilder.aPaperDefine().withName("A6").withWidth((int) (105 * inchesPerMillimeter * 72)).withHeight((int) (148 * inchesPerMillimeter * 72)).build());
        paperList.add(PaperDefine.PaperDefineBuilder.aPaperDefine().withName("A7").withWidth((int) (74 * inchesPerMillimeter * 72)).withHeight((int) (105 * inchesPerMillimeter * 72)).build());
        paperList.add(PaperDefine.PaperDefineBuilder.aPaperDefine().withName("A8").withWidth((int) (52 * inchesPerMillimeter * 72)).withHeight((int) (74 * inchesPerMillimeter * 72)).build());

        paperList.add(PaperDefine.PaperDefineBuilder.aPaperDefine().withName("B0").withWidth((int) (1030 * inchesPerMillimeter * 72)).withHeight((int) (1456 * inchesPerMillimeter * 72)).build());
        paperList.add(PaperDefine.PaperDefineBuilder.aPaperDefine().withName("B1").withWidth((int) (728 * inchesPerMillimeter * 72)).withHeight((int) (1030 * inchesPerMillimeter * 72)).build());
        paperList.add(PaperDefine.PaperDefineBuilder.aPaperDefine().withName("B2").withWidth((int) (515 * inchesPerMillimeter * 72)).withHeight((int) (728 * inchesPerMillimeter * 72)).build());
        paperList.add(PaperDefine.PaperDefineBuilder.aPaperDefine().withName("B3").withWidth((int) (364 * inchesPerMillimeter * 72)).withHeight((int) (515 * inchesPerMillimeter * 72)).build());
        paperList.add(PaperDefine.PaperDefineBuilder.aPaperDefine().withName("B4").withWidth((int) (257 * inchesPerMillimeter * 72)).withHeight((int) (364 * inchesPerMillimeter * 72)).build());
        paperList.add(PaperDefine.PaperDefineBuilder.aPaperDefine().withName("B5").withWidth((int) (182 * inchesPerMillimeter * 72)).withHeight((int) (257 * inchesPerMillimeter * 72)).build());
        paperList.add(PaperDefine.PaperDefineBuilder.aPaperDefine().withName("B6").withWidth((int) (128 * inchesPerMillimeter * 72)).withHeight((int) (182 * inchesPerMillimeter * 72)).build());
        paperList.add(PaperDefine.PaperDefineBuilder.aPaperDefine().withName("B7").withWidth((int) (91 * inchesPerMillimeter * 72)).withHeight((int) (128 * inchesPerMillimeter * 72)).build());
        paperList.add(PaperDefine.PaperDefineBuilder.aPaperDefine().withName("B8").withWidth((int) (64 * inchesPerMillimeter * 72)).withHeight((int) (91 * inchesPerMillimeter * 72)).build());

    }

    public static List<PaperDefine> paperList(){
        return paperList;
    }


    public static Paper getPaper(PaperDefine define) {
        paper.setSize(define.getWidth(),define.getHeight());
        paper.setImageableArea(margin,margin,paper.getWidth() - 2*margin,paper.getHeight() - 2*margin);
        return paper;
    }

}
