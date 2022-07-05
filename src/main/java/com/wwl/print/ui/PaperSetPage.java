package com.wwl.print.ui;

import com.wwl.print.base.ComboBoxItem;
import com.wwl.print.base.OrientationType;
import com.wwl.print.base.TableTitleType;
import com.wwl.print.paper.PaperDefine;
import com.wwl.print.paper.format.ExtPageFormat;
import com.wwl.print.utils.PaperSetting;
//import com.wwl.print.utils.PaperSetting2;
import com.wwl.print.utils.Utility;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.print.Paper;

/**
 * @author long
 * @date 2022/7/4 9:50
 * @desc TODO
 */
public class PaperSetPage extends JPanel {
    //declare if the combo is clicked
    boolean isDirectClicked = false;
    boolean isTableHeadClicked = false;
    boolean isTableAlignClicked = false;
    boolean isTableHeadBorderClicked = false;
    boolean isTableFootBorderClicked = false;

    //declare the pageFormat
    ExtPageFormat pageFormat;

    //declare the buffer for the text

    //declare thisLayout which include just mainPanel
    BorderLayout thisLayout = new BorderLayout();

    //declare mainPanel which include paperPanel and controlPanel
    JPanel mainPanel = new JPanel();
    GridLayout mainPanelLayout = new GridLayout();

    //declare paperPanel where to draw the graphics
    JPanel paperPanel = new JPanel();
    BorderLayout paperPanelLayout = new BorderLayout();
    PaperComponent paperComponent;

    //declare marginPanel where to edit the margin of the paper
    JPanel marginPanel = new JPanel();

    JLabel lTopMargin = new JLabel();
    JTextField tTopMargin = new JTextField(5);

    JLabel lBottomMargin = new JLabel();
    JTextField tBottomMargin = new JTextField(5);

    JLabel lLeftMargin = new JLabel();
    JTextField tLeftMargin = new JTextField(5);

    JLabel lRightMargin = new JLabel();
    JTextField tRightMargin = new JTextField(5);

    //declare sizePanel where to set the size of the paper
    JPanel sizePanel = new JPanel();

    JComboBox paperType = new JComboBox();

    JLabel lPaperWidth = new JLabel();
    JTextField tPaperWidth = new JTextField(3);

    JLabel lPaperHeight = new JLabel();
    JTextField tPaperHeight = new JTextField(3);

    //declare directPanel where to set the direct of the paper

    ButtonGroup directChoiceGroup = new ButtonGroup();


    //declare the label with millimeter
    JLabel mm1 = new JLabel();
    JLabel mm2 = new JLabel();
    JLabel mm3 = new JLabel();
    JLabel mm4 = new JLabel();
    JLabel mm5 = new JLabel();
    JLabel mm6 = new JLabel();
    JLabel lSettingMargin = new JLabel();
    JPanel otherPanel = new JPanel();
    JRadioButton landscape = new JRadioButton();
    JRadioButton portrait = new JRadioButton();
    JLabel mm16 = new JLabel();
    JLabel lHeadRightMargin = new JLabel();
    JLabel mm13 = new JLabel();
    JLabel mm12 = new JLabel();
    JLabel mm14 = new JLabel();
    JLabel mm15 = new JLabel();
    JLabel lHeadLeftContent = new JLabel();
    JLabel lHeadLeftMargin = new JLabel();
    JTextField tHeadLeftContent = new JTextField(5);
    JTextField tHeadHeight = new JTextField(5);
    JTextField tHeadRightMargin = new JTextField(5);
    FlowLayout flowLayout1 = new FlowLayout();
    JLabel lHeadHeight = new JLabel();
    JTextField tHeadBottomMargin = new JTextField(5);
    JLabel lHeadTopMargin = new JLabel();
    JTextField tHeadLeftMargin = new JTextField(5);
    JLabel lHeadBottomMargin = new JLabel();
    JTextField tHeadTopMargin = new JTextField(5);
    JPanel showHeadPanel = new JPanel();
    JPanel headPanel = new JPanel();
    JPanel headInfoPanel = new JPanel();
    BorderLayout borderLayout1 = new BorderLayout();
    JLabel mm11 = new JLabel();
    JLabel lFootRightContent = new JLabel();
    JLabel lFootRightMargin = new JLabel();
    JLabel mm8 = new JLabel();
    JLabel mm7 = new JLabel();
    JLabel mm9 = new JLabel();
    JLabel mm10 = new JLabel();
    JLabel lFootLeftContent = new JLabel();
    JLabel lFootLeftMargin = new JLabel();
    JTextField tFootRightContent = new JTextField(5);
    JTextField tFootLeftContent = new JTextField(5);
    JTextField tFootHeight = new JTextField(5);
    JTextField tFootLeftMargin = new JTextField(5);
    JLabel lFootHeight = new JLabel();
    FlowLayout showFootPanelLayout = new FlowLayout();
    JTextField tFootRightMargin = new JTextField(5);
    JLabel lFootTopMargin = new JLabel();
    JTextField tFootBottomMargin = new JTextField(5);
    JLabel lFootBottomMargin = new JLabel();
    JTextField tFootTopMargin = new JTextField(5);
    JPanel showFootPanel = new JPanel();
    JPanel footPanel = new JPanel();
    JPanel footInfoPanel = new JPanel();
    BorderLayout footInfoPanelLayout = new BorderLayout();
    JLabel lTableHeadModel = new JLabel();
    JPanel jPanel1 = new JPanel();
    JPanel jPanel2 = new JPanel();
    JPanel jPanel3 = new JPanel();
    JPanel jPanel4 = new JPanel();
    ButtonGroup tableHeadGroup = new ButtonGroup();
    ButtonGroup tableAlignGroup = new ButtonGroup();
    JComboBox sTableHead = new JComboBox();
    JLabel lJobName = new JLabel();
    JTextField tJobName = new JTextField();

    public PaperSetPage(ExtPageFormat newPageFormat) {
        try {
            Paper paper = newPageFormat.getPaper();
            pageFormat = newPageFormat;

            paperComponent = new PaperComponent(pageFormat);
            paperComponent.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            jbInit();

            paperType.setSelectedIndex(PaperSetting.getIndex(
                    paper.getWidth(),paper.getHeight()));

           // paperType.setSelectedItem(PaperSetting2.paperList().stream().filter(s->s.getName().equals("A4")).findFirst().get());

            //set paper margin
            areaMarginRepaint();

            System.out.println("上："+tTopMargin.getText());
            System.out.println("下："+tBottomMargin.getText());
            System.out.println("左："+tLeftMargin.getText());
            System.out.println("右："+tRightMargin.getText());

            //set paper size
            tPaperWidth.setText(String.valueOf((int)(Utility.dotToMillimeter(pageFormat.getWidth())) + 1));
            tPaperHeight.setText(String.valueOf((int)(Utility.dotToMillimeter(pageFormat.getHeight())) + 1));

            //set head margin
            tHeadTopMargin.setText(String.valueOf((int)(Utility.dotToMillimeter(pageFormat.getHeadArea().getImageAbleArea().getImageAbleY())) + 1));
            tHeadBottomMargin.setText(String.valueOf((int)(Utility.dotToMillimeter(pageFormat.getHeadArea().getHeight() - pageFormat.getHeadArea().getImageAbleArea().getImageAbleY() - pageFormat.getHeadArea().getImageAbleArea().getImageAbleHeight())) + 1));
            tHeadLeftMargin.setText(String.valueOf((int)(Utility.dotToMillimeter(pageFormat.getHeadArea().getImageAbleArea().getImageAbleX())) + 1));
            tHeadRightMargin.setText(String.valueOf((int)(Utility.dotToMillimeter( pageFormat.getHeadArea().getWidth() - pageFormat.getHeadArea().getImageAbleArea().getImageAbleX() - pageFormat.getHeadArea().getImageAbleArea().getImageAbleWidth())) + 1));

            //set head height
            tHeadHeight.setText(String.valueOf((int)(Utility.dotToMillimeter(pageFormat.getHeadArea().getHeight())) + 1));

            tHeadLeftContent.setText(pageFormat.getHeadArea().getTitle());

            //set foot margin
            tFootTopMargin.setText(String.valueOf((int)(Utility.dotToMillimeter(pageFormat.getFootArea().getImageAbleArea().getImageAbleY())) + 1));
            tFootBottomMargin.setText(String.valueOf((int)(Utility.dotToMillimeter(pageFormat.getFootArea().getHeight() - pageFormat.getFootArea().getImageAbleArea().getImageAbleY() - pageFormat.getFootArea().getImageAbleArea().getImageAbleHeight())) + 1));
            tFootLeftMargin.setText(String.valueOf((int)(Utility.dotToMillimeter(pageFormat.getFootArea().getImageAbleArea().getImageAbleX())) + 1));
            tFootRightMargin.setText(String.valueOf((int)(Utility.dotToMillimeter( pageFormat.getFootArea().getWidth() - pageFormat.getFootArea().getImageAbleArea().getImageAbleX() - pageFormat.getFootArea().getImageAbleArea().getImageAbleWidth())) + 1));

            //set foot height
            tFootHeight.setText(String.valueOf((int)(Utility.dotToMillimeter(pageFormat.getFootArea().getHeight())) + 1));

            //set foot content
            tFootLeftContent.setText(pageFormat.getFootArea().getLeftContent());
            tFootRightContent.setText(pageFormat.getFootArea().getRightContent());



            this.directChoiceGroup.add(this.portrait);
            this.directChoiceGroup.add(this.landscape);

            //set paper direct
            if(pageFormat.getOrientation() == 0) {
                landscape.setSelected(true);
            } else {
                portrait.setSelected(true);
            }
            tJobName.setText(pageFormat.getJobName());

            repaint();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    void jbInit() throws Exception {

        paperType.addItem("A4:210mm X 297mm");
        paperType.addItem("A5:148mm X 210mm");
        paperType.addItem("B5:182mm X 257mm");
        paperType.addItem("Devolop C5:162mm X 229mm");
        paperType.addItem("Devolop DL:110mm X 220mm");
        paperType.addItem("Devolop B5:176mm X 250mm");
        paperType.addItem("Devolop Monarch:3.875inch X 7.5inch");
        paperType.addItem("Devolop 9:3.875inch X 8.875inch");
        paperType.addItem("Devolop 10:4.125inch X 9.5inch");
        paperType.addItem("Letter:8.5inch X 11inch");
        paperType.addItem("Legal:8.5inch X 14inch");
        paperType.addItem("self define ...");

        // for (PaperDefine paperDefine : PaperSetting2.paperList()) {
        //     paperType.addItem(new ComboBoxItem(paperDefine.getName(),paperDefine));
        // }

        //Layout
        this.setLayout(thisLayout);

        tJobName.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tJobName_keyReleased(e);
            }
        });
        this.add(mainPanel, BorderLayout.CENTER);

        mainPanelLayout.setHgap(10);
        mainPanelLayout.setVgap(10);
        mainPanelLayout.setRows(2);
        mainPanelLayout.setColumns(3);

        //Layout
        mainPanel.setLayout(mainPanelLayout);

        //Layout
        mainPanel.add(paperPanel, null);
        mainPanel.add(sizePanel, null);
        mainPanel.add(otherPanel, null);
        mainPanel.add(marginPanel, null);
        mainPanel.add(headInfoPanel, null);
        mainPanel.add(footInfoPanel, null);

        //Layout
        paperPanel.setLayout(paperPanelLayout);
        paperPanel.add(paperComponent,BorderLayout.CENTER);

        lSettingMargin.setText("Set Margin:");
        lSettingMargin.setBounds(new Rectangle(37, 13, 102, 25));
        tTopMargin.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tTopMargin_keyReleased(e);
            }
        });

        tBottomMargin.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tBottomMargin_keyReleased(e);
            }
        });

        tLeftMargin.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tLeftMargin_keyReleased(e);
            }
        });

        tRightMargin.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tRightMargin_keyReleased(e);
            }
        });

        tPaperWidth.setBounds(new Rectangle(48, 50, 33, 19));
        tPaperWidth.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tPaperWidth_keyReleased(e);
            }
        });

        tPaperHeight.setBounds(new Rectangle(138, 50, 33, 19));
        tPaperHeight.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tPaperHeightFootLeftContentHeadHeight_keyReleased(e);
            }
        });

        paperType.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paperType_actionPerformed(e);
            }
        });
        paperType.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                paperType_mouseClicked(e);
            }
        });

        otherPanel.setLayout(null);

        landscape.setText("横向打印 ");
        landscape.setBounds(new Rectangle(30, 80, 147, 26));
        landscape.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                landscape_actionPerformed(e);
            }
        });

        portrait.setText("纵向打印");
        portrait.setBounds(new Rectangle(30, 112, 127, 26));
        portrait.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                portraitFootLeftContentHeadHeight_actionPerformed(e);
            }
        });


        mm16.setBounds(new Rectangle(100, 69, 22, 18));
        mm16.setText("mm");

        mm13.setText("mm");
        mm13.setBounds(new Rectangle(67, 40, 22, 18));
        mm12.setText("mm");
        mm12.setBounds(new Rectangle(67, 10, 22, 18));
        mm14.setText("mm");
        mm14.setBounds(new Rectangle(170, 10, 22, 18));
        mm15.setText("mm");
        mm15.setBounds(new Rectangle(170, 41, 22, 18));
        lHeadLeftContent.setBounds(new Rectangle(14, 98, 63, 18));
        lHeadLeftContent.setText("Title:");
        lHeadLeftMargin.setText("L:");
        lHeadLeftMargin.setBounds(new Rectangle(115, 10, 19, 18));
        tHeadLeftContent.setBounds(new Rectangle(55, 98, 119, 20));
        tHeadLeftContent.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tHeadLeftContent_keyReleased(e);
            }
        });
        tHeadHeight.setBounds(new Rectangle(59, 68, 33, 20));
        tHeadHeight.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tHeadHeight_keyReleased(e);
            }
        });
        tHeadRightMargin.setBounds(new Rectangle(138, 8, 33, 20));
        tHeadRightMargin.setBounds(new Rectangle(130, 40, 33, 20));
        tHeadRightMargin.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tHeadRightMargin_keyReleased(e);
            }
        });
        flowLayout1.setHgap(0);
        flowLayout1.setVgap(0);
        lHeadHeight.setBounds(new Rectangle(14, 68, 47, 18));
        lHeadHeight.setText("Height:");
        tHeadBottomMargin.setBounds(new Rectangle(138, 38, 33, 20));
        tHeadBottomMargin.setBounds(new Rectangle(30, 37, 33, 20));
        tHeadBottomMargin.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tHeadBottomMargin_keyReleased(e);
            }
        });
        lHeadTopMargin.setText("T:");
        lHeadTopMargin.setBounds(new Rectangle(14, 11, 24, 18));
        tHeadLeftMargin.setBounds(new Rectangle(31, 42, 33, 20));
        tHeadLeftMargin.setBounds(new Rectangle(131, 11, 33, 20));
        tHeadLeftMargin.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tHeadLeftMargin_keyReleased(e);
            }
        });
        lHeadBottomMargin.setText("B:");
        lHeadBottomMargin.setBounds(new Rectangle(14, 41, 22, 18));
        tHeadTopMargin.setBounds(new Rectangle(31, 10, 33, 20));
        tHeadTopMargin.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tHeadTopMargin_keyReleased(e);
            }
        });
        showHeadPanel.setLayout(flowLayout1);
        headPanel.setLayout(null);
        headInfoPanel.setBorder(BorderFactory.createEtchedBorder());
        headInfoPanel.setLayout(borderLayout1);
        mm11.setText("mm");
        mm11.setBounds(new Rectangle(100, 69, 22, 18));
        lFootRightContent.setText("R Content:");
        lFootRightContent.setBounds(new Rectangle(14, 136, 67, 18));
        lFootRightMargin.setBounds(new Rectangle(115, 40, 27, 18));
        lFootRightMargin.setText("R:");
        mm8.setBounds(new Rectangle(67, 40, 22, 18));
        mm8.setText("mm");
        mm7.setBounds(new Rectangle(67, 10, 22, 18));
        mm7.setText("mm");
        mm9.setBounds(new Rectangle(178, 10, 22, 18));
        mm9.setText("mm");
        mm10.setBounds(new Rectangle(178, 41, 22, 18));
        mm10.setText("mm");
        lFootLeftContent.setText("L Content:");
        lFootLeftContent.setBounds(new Rectangle(14, 98, 63, 18));
        lFootLeftMargin.setBounds(new Rectangle(115, 10, 19, 18));
        lFootLeftMargin.setText("L:");
        tFootRightContent.setBounds(new Rectangle(75, 136, 119, 20));
        tFootRightContent.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tFootRightContent_keyReleased(e);
            }
        });
        tFootLeftContent.setBounds(new Rectangle(75, 98, 119, 20));
        tFootLeftContent.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tFootLeftContent_keyReleased(e);
            }
        });
        tFootHeight.setBounds(new Rectangle(59, 68, 33, 20));
        tFootHeight.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tFootHeight_keyReleased(e);
            }
        });
        tFootLeftMargin.setBounds(new Rectangle(130, 40, 33, 20));
        tFootLeftMargin.setBounds(new Rectangle(138, 8, 33, 20));
        tFootLeftMargin.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tFootLeftMargin_keyReleased(e);
            }
        });
        lFootHeight.setText("Height:");
        lFootHeight.setBounds(new Rectangle(14, 68, 47, 18));
        showFootPanelLayout.setVgap(0);
        showFootPanelLayout.setHgap(0);
        tFootRightMargin.setBounds(new Rectangle(30, 37, 33, 20));
        tFootRightMargin.setBounds(new Rectangle(138, 38, 33, 20));
        tFootRightMargin.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tFootRightMargin_keyReleased(e);
            }
        });
        lFootTopMargin.setBounds(new Rectangle(14, 11, 24, 18));
        lFootTopMargin.setText("T:");
        tFootBottomMargin.setBounds(new Rectangle(131, 11, 33, 20));
        tFootBottomMargin.setBounds(new Rectangle(31, 42, 33, 20));
        tFootBottomMargin.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tFootBottomMargin_keyReleased(e);
            }
        });
        lFootBottomMargin.setBounds(new Rectangle(14, 41, 22, 18));
        lFootBottomMargin.setText("B:");
        tFootTopMargin.setBounds(new Rectangle(31, 10, 33, 20));
        tFootTopMargin.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tFootTopMargin_keyReleased(e);
            }
        });
        showFootPanel.setLayout(showFootPanelLayout);
        footPanel.setLayout(null);
        footInfoPanel.setBorder(BorderFactory.createEtchedBorder());
        footInfoPanel.setLayout(footInfoPanelLayout);
        otherPanel.setBorder(BorderFactory.createEtchedBorder());
        tRightMargin.setBounds(new Rectangle(87, 143, 55, 22));
        mm4.setBounds(new Rectangle(147, 143, 22, 18));
        lRightMargin.setBounds(new Rectangle(50, 143, 27, 18));
        tLeftMargin.setBounds(new Rectangle(87, 113, 55, 22));
        mm3.setBounds(new Rectangle(147, 113, 22, 18));
        lLeftMargin.setBounds(new Rectangle(58, 113, 19, 18));
        tBottomMargin.setBounds(new Rectangle(87, 82, 55, 22));
        mm2.setBounds(new Rectangle(147, 82, 22, 18));
        lBottomMargin.setBounds(new Rectangle(35, 82, 42, 18));
        mm1.setBounds(new Rectangle(147, 52, 22, 18));
        tTopMargin.setBounds(new Rectangle(87, 50, 55, 22));
        lTopMargin.setBounds(new Rectangle(53, 52, 24, 18));
        mm6.setBounds(new Rectangle(172, 50, 22, 18));
        lPaperHeight.setBounds(new Rectangle(120, 50, 33, 18));
        mm5.setBounds(new Rectangle(84, 50, 22, 18));
        lPaperWidth.setBounds(new Rectangle(29, 50, 33, 18));
        paperType.setBounds(new Rectangle(28, 20, 166, 22));

        lTableHeadModel.setText("Table Head:");
        lTableHeadModel.setBounds(new Rectangle(20, 12, 79, 24));

        lJobName.setText("Job Name:");
        lJobName.setBounds(new Rectangle(25, 155, 63, 25));
        tJobName.setBounds(new Rectangle(94, 156, 97, 24));

        footInfoPanel.add(showFootPanel, BorderLayout.NORTH);
        footInfoPanel.add(footPanel, BorderLayout.CENTER);
        footPanel.add(lFootTopMargin, null);
        footPanel.add(lFootHeight, null);
        footPanel.add(lFootBottomMargin, null);
        footPanel.add(tFootTopMargin, null);
        footPanel.add(mm7, null);
        footPanel.add(mm8, null);
        footPanel.add(lFootLeftContent, null);
        footPanel.add(tFootRightMargin, null);
        footPanel.add(tFootHeight, null);
        footPanel.add(mm11, null);
        footPanel.add(lFootLeftMargin, null);
        footPanel.add(tFootBottomMargin, null);
        footPanel.add(tFootLeftMargin, null);
        footPanel.add(lFootRightMargin, null);
        footPanel.add(mm10, null);
        footPanel.add(mm9, null);
        footPanel.add(tFootLeftContent, null);
        footPanel.add(lFootRightContent, null);
        footPanel.add(tFootRightContent, null);

        headInfoPanel.add(showHeadPanel, BorderLayout.NORTH);
        headInfoPanel.add(headPanel, BorderLayout.CENTER);
        headPanel.add(lHeadTopMargin, null);
        headPanel.add(lHeadHeight, null);
        headPanel.add(lHeadBottomMargin, null);
        headPanel.add(tHeadTopMargin, null);
        headPanel.add(mm12, null);
        headPanel.add(mm13, null);
        headPanel.add(lHeadLeftContent, null);
        headPanel.add(tHeadBottomMargin, null);
        headPanel.add(tHeadHeight, null);
        headPanel.add(mm16, null);
        headPanel.add(tHeadLeftContent, null);
        headPanel.add(lHeadLeftMargin, null);
        headPanel.add(tHeadLeftMargin, null);
        headPanel.add(mm14, null);
        headPanel.add(mm15, null);
        headPanel.add(tHeadRightMargin, null);
        headPanel.add(lHeadRightMargin, null);
        this.add(jPanel1, BorderLayout.SOUTH);
        this.add(jPanel2, BorderLayout.WEST);
        this.add(jPanel3, BorderLayout.EAST);
        this.add(jPanel4, BorderLayout.NORTH);

        //Layout
        marginPanel.setBorder(BorderFactory.createEtchedBorder());
        marginPanel.setLayout(null);

        lTopMargin.setText("Top:");
        mm1.setText("mm");

        lBottomMargin.setText("Bottom:");
        mm2.setText("mm");

        lLeftMargin.setText("left:");
        mm3.setText("mm");

        lRightMargin.setText("right:");
        mm4.setText("mm");
        marginPanel.add(mm1, null);
        marginPanel.add(mm4, null);
        marginPanel.add(tRightMargin, null);
        marginPanel.add(lRightMargin, null);
        marginPanel.add(lLeftMargin, null);
        marginPanel.add(tLeftMargin, null);
        marginPanel.add(mm3, null);
        marginPanel.add(mm2, null);
        marginPanel.add(tBottomMargin, null);
        marginPanel.add(lBottomMargin, null);
        marginPanel.add(lTopMargin, null);
        marginPanel.add(tTopMargin, null);
        marginPanel.add(lSettingMargin, null);

        //Layout
        sizePanel.setBorder(BorderFactory.createEtchedBorder());
        sizePanel.setLayout(null);

        lPaperWidth.setText("W:");
        mm5.setText("mm");

        lPaperHeight.setText("H:");
        mm6.setText("mm");
        sizePanel.add(paperType, null);
        sizePanel.add(lPaperWidth, null);
        sizePanel.add(tPaperWidth, null);
        sizePanel.add(tPaperHeight, null);
        sizePanel.add(lPaperHeight, null);
        sizePanel.add(mm5, null);
        sizePanel.add(mm6, null);
        sizePanel.add(landscape, null);
        sizePanel.add(portrait, null);
        sizePanel.add(lJobName, null);

        //Layout


        //Layout
        sTableHead.addItem("All Page");
        sTableHead.addItem("First Page");
        sTableHead.addItem("None");


        //Layout
        otherPanel.add(sTableHead, null);
        otherPanel.add(lTableHeadModel, null);
        sizePanel.add(tJobName, null);


        switch (pageFormat.getTableTitleType()){
            case FirstPage:
                sTableHead.setSelectedIndex(1);
                break;
            case None:
                sTableHead.setSelectedIndex(2);
                break;
            case AllPage:
            default:
                sTableHead.setSelectedIndex(0);
                break;
        }


        sTableHead.setBounds(new Rectangle(17, 42, 71, 22));
        sTableHead.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sTableHead_mouseClicked(e);
            }
        });
        sTableHead.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sTableHead_actionPerformed(e);
            }
        });
    }

    void landscape_actionPerformed(ActionEvent e) {
        pageFormat.setOrientation(0);
        pageFormat.setOrientationType(OrientationType.Transverse);

        areaMarginRepaint();
        repaint();
    }

    void portraitFootLeftContentHeadHeight_actionPerformed(ActionEvent e) {
        pageFormat.setOrientation(1);
        pageFormat.setOrientationType(OrientationType.Portrait);

        areaMarginRepaint();
        repaint();
    }


    private void areaMarginRepaint(){
        tTopMargin.setText(String.valueOf((int)(Utility.dotToMillimeter(pageFormat.getContentArea().getPaddingTop())) + 1));
        tBottomMargin.setText(String.valueOf((int)(Utility.dotToMillimeter(pageFormat.getContentArea().getPaddingBottom())) + 1));
        tLeftMargin.setText(String.valueOf((int)(Utility.dotToMillimeter(pageFormat.getContentArea().getPaddingLeft())) + 1));
        tRightMargin.setText(String.valueOf((int)(Utility.dotToMillimeter(pageFormat.getContentArea().getPaddingRight())) + 1));

        tPaperWidth.setText(String.valueOf((int)(Utility.dotToMillimeter(pageFormat.getWidth())) + 1));
        tPaperHeight.setText(String.valueOf((int)(Utility.dotToMillimeter(pageFormat.getHeight())) + 1));
    }

    void tTopMargin_keyReleased(KeyEvent e) {
        Paper newPaper = pageFormat.getPaper();

        double x,y,w,h;
        double text;
        double newIn;

        try
        {
            text = ((tTopMargin.getText().equals("")?0.0:Double.parseDouble(tTopMargin.getText())));
            newIn = Utility.millimeterToDot(text);
        }
        catch(NumberFormatException nfe)
        {
            return;
        }

        if(landscape.isSelected())
        {
            x = newPaper.getImageableX();
            y = newPaper.getImageableY();
            w = newPaper.getImageableWidth();
            h = newPaper.getImageableHeight();

            w = w + x - newIn;
            x = newIn;

            newPaper.setImageableArea(x,y,w,h);
        }
        else
        {
            x = newPaper.getImageableX();
            y = newPaper.getImageableY();
            w = newPaper.getImageableWidth();
            h = newPaper.getImageableHeight();

            h = h + y - newIn;
            y = newIn;

            newPaper.setImageableArea(x,y,w,h);
        }
       // pageFormat.setPaper(newPaper);

        pageFormat.getContentArea().setPaddingTop(newIn);
        pageFormat.getContentArea().repaintImageAbleArea();
        repaint();
    }

    void tBottomMargin_keyReleased(KeyEvent e) {
        Paper newPaper = pageFormat.getPaper();

        double x,y,w,h;
        double text;
        double newIn;

        try
        {
            text = ((tBottomMargin.getText().equals("")?0.0:Double.parseDouble(tBottomMargin.getText())));
            newIn = Utility.millimeterToDot(text);
        }
        catch(NumberFormatException nfe)
        {
            return;
        }

        if(landscape.isSelected())
        {
            x = newPaper.getImageableX();
            y = newPaper.getImageableY();
            w = newPaper.getImageableWidth();
            h = newPaper.getImageableHeight();

            w = newPaper.getWidth() - x - newIn;

            newPaper.setImageableArea(x,y,w,h);
        }
        else
        {
            x = newPaper.getImageableX();
            y = newPaper.getImageableY();
            w = newPaper.getImageableWidth();
            h = newPaper.getImageableHeight();

            h = newPaper.getHeight() - y - newIn;

            newPaper.setImageableArea(x,y,w,h);
        }
       // pageFormat.setPaper(newPaper);

        pageFormat.getContentArea().setPaddingBottom(newIn);
        pageFormat.getContentArea().repaintImageAbleArea();

        repaint();
    }

    void tLeftMargin_keyReleased(KeyEvent e) {
        Paper newPaper = pageFormat.getPaper();

        double x,y,w,h;
        double text;
        double newIn;

        try
        {
            text = ((tLeftMargin.getText().equals("")?0.0:Double.parseDouble(tLeftMargin.getText())));
            newIn = Utility.millimeterToDot(text);
        }
        catch(NumberFormatException nfe)
        {
            return;
        }

        if(landscape.isSelected())
        {
            x = newPaper.getImageableX();
            y = newPaper.getImageableY();
            w = newPaper.getImageableWidth();
            h = newPaper.getImageableHeight();

            h = y + h - newIn;
            y = newIn;

            newPaper.setImageableArea(x,y,w,h);
        }
        else
        {
            x = newPaper.getImageableX();
            y = newPaper.getImageableY();
            w = newPaper.getImageableWidth();
            h = newPaper.getImageableHeight();

            w = x + w - newIn;
            x = newIn;

            newPaper.setImageableArea(x,y,w,h);
        }
       // pageFormat.setPaper(newPaper);

        pageFormat.getContentArea().setPaddingLeft(newIn);
        pageFormat.getContentArea().repaintImageAbleArea();
        repaint();
    }

    void tRightMargin_keyReleased(KeyEvent e) {
        Paper newPaper = pageFormat.getPaper();

        double x,y,w,h;
        double text;
        double newIn;

        try
        {
            text = ((tRightMargin.getText().equals("")?0.0:Double.parseDouble(tRightMargin.getText())));
            newIn = Utility.millimeterToDot(text);
        }
        catch(NumberFormatException nfe)
        {
            return;
        }

        if(landscape.isSelected())
        {
            x = newPaper.getImageableX();
            y = newPaper.getImageableY();
            w = newPaper.getImageableWidth();
            h = newPaper.getImageableHeight();

            h = newPaper.getHeight() - y - newIn;

            newPaper.setImageableArea(x,y,w,h);
        }
        else
        {
            x = newPaper.getImageableX();
            y = newPaper.getImageableY();
            w = newPaper.getImageableWidth();
            h = newPaper.getImageableHeight();

            w = newPaper.getWidth() - x - newIn;

            newPaper.setImageableArea(x,y,w,h);
        }
       // pageFormat.setPaper(newPaper);

        pageFormat.getContentArea().setPaddingRight(newIn);
        pageFormat.getContentArea().repaintImageAbleArea();
        repaint();
    }

    void paperType_actionPerformed(ActionEvent e) {
        if(!isDirectClicked) {
            return;
        }

        // ComboBoxItem item = (ComboBoxItem)paperType.getSelectedItem();
        //
        // tPaperWidth.setEnabled(false);
        // tPaperHeight.setEnabled(false);
        // PaperDefine define = (PaperDefine)item.getValue();
        // pageFormat.setPaper(PaperSetting2.getPaper(define));
        // repaint();


        int index = paperType.getSelectedIndex();

        switch(index)
        {
            case 0:
                tPaperWidth.setEnabled(false);
                tPaperHeight.setEnabled(false);
                pageFormat.setPaper(PaperSetting.getA4());
                repaint();
                break;
            case 1:
                tPaperWidth.setEnabled(false);
                tPaperHeight.setEnabled(false);
                pageFormat.setPaper(PaperSetting.getA5());
                repaint();
                break;
            case 2:
                tPaperWidth.setEnabled(false);
                tPaperHeight.setEnabled(false);
                pageFormat.setPaper(PaperSetting.getB5());
                repaint();
                break;
            case 3:
                tPaperWidth.setEnabled(false);
                tPaperHeight.setEnabled(false);
                pageFormat.setPaper(PaperSetting.getDevelopC5());
                repaint();
                break;
            case 4:
                tPaperWidth.setEnabled(false);
                tPaperHeight.setEnabled(false);
                pageFormat.setPaper(PaperSetting.getDevelopDl());
                repaint();
                break;
            case 5:
                tPaperWidth.setEnabled(false);
                tPaperHeight.setEnabled(false);
                pageFormat.setPaper(PaperSetting.getDevelopB5());
                repaint();
                break;
            case 6:
                tPaperWidth.setEnabled(false);
                tPaperHeight.setEnabled(false);
                pageFormat.setPaper(PaperSetting.getDevelopMonarch());
                repaint();
                break;
            case 7:
                tPaperWidth.setEnabled(false);
                tPaperHeight.setEnabled(false);
                pageFormat.setPaper(PaperSetting.getDevelop9());
                repaint();
                break;
            case 8:
                tPaperWidth.setEnabled(false);
                tPaperHeight.setEnabled(false);
                pageFormat.setPaper(PaperSetting.getDevelop10());
                repaint();
                break;
            case 9:
                tPaperWidth.setEnabled(false);
                tPaperHeight.setEnabled(false);
                pageFormat.setPaper(PaperSetting.getLetter());
                repaint();
                break;
            case 10:
                tPaperWidth.setEnabled(false);
                tPaperHeight.setEnabled(false);
                pageFormat.setPaper(PaperSetting.getLegal());
                repaint();
                break;
            default:
                tPaperWidth.setEnabled(true);
                tPaperHeight.setEnabled(true);
                repaint();
                break;
        }

        tTopMargin.setText(String.valueOf((int)(Utility.dotToMillimeter(pageFormat.getImageableY())) + 1));
        tBottomMargin.setText(String.valueOf((int)(Utility.dotToMillimeter(pageFormat.getHeight() - pageFormat.getImageableY() - pageFormat.getImageableHeight())) + 1));
        tLeftMargin.setText(String.valueOf((int)(Utility.dotToMillimeter(pageFormat.getImageableX())) + 1));
        tRightMargin.setText(String.valueOf((int)(Utility.dotToMillimeter( pageFormat.getWidth() - pageFormat.getImageableX() - pageFormat.getImageableWidth())) + 1));

        tPaperWidth.setText(String.valueOf((int)(Utility.dotToMillimeter(pageFormat.getWidth())) + 1));
        tPaperHeight.setText(String.valueOf((int)(Utility.dotToMillimeter(pageFormat.getHeight())) + 1));
    }

    void tPaperWidth_keyReleased(KeyEvent e) {
        Paper newPaper = pageFormat.getPaper();

        double pw,ph;
        double x,y,w,h;
        double text;
        double newIn;

        try
        {
            text = ((tPaperWidth.getText().equals("")?0.0:Double.parseDouble(tPaperWidth.getText())));
            newIn = Utility.millimeterToDot(text);
        }
        catch(NumberFormatException nfe)
        {
            return;
        }

        if(pageFormat.getOrientation() == 1)
        {
            pw = newIn;
            ph = newPaper.getHeight();

            newPaper.setImageableArea(newPaper.getImageableX(),newPaper.getImageableY(),
                    newPaper.getImageableWidth() + newIn - newPaper.getWidth(),newPaper.getImageableHeight());

            newPaper.setSize(pw,ph);
        }
        else
        {
            ph = newIn;
            pw = newPaper.getWidth();

            newPaper.setImageableArea(newPaper.getImageableX(),newPaper.getImageableY(),
                    newPaper.getImageableWidth(),newPaper.getImageableHeight() - newPaper.getHeight() + newIn);

            newPaper.setSize(pw,ph);
        }

        pageFormat.setPaper(newPaper);

        repaint();
    }

    void tPaperHeightFootLeftContentHeadHeight_keyReleased(KeyEvent e) {
        Paper newPaper = pageFormat.getPaper();

        double ph,pw;
        double text;
        double newIn;

        try
        {
            text = ((tPaperHeight.getText().equals("")?0.0:Double.parseDouble(tPaperHeight.getText())));
            newIn = Utility.millimeterToDot(text);
        }
        catch(NumberFormatException nfe)
        {
            return;
        }

        if(pageFormat.getOrientation() == 1)
        {
            ph = newIn;
            pw = newPaper.getWidth();

            newPaper.setImageableArea(newPaper.getImageableX(),newPaper.getImageableY(),
                    newPaper.getImageableWidth(),newPaper.getImageableHeight() - newPaper.getHeight() + newIn);

            newPaper.setSize(pw,ph);
        }
        else
        {
            pw = newIn;
            ph = newPaper.getHeight();

            newPaper.setImageableArea(newPaper.getImageableX(),newPaper.getImageableY(),
                    newPaper.getImageableWidth() + newIn - newPaper.getWidth(),newPaper.getImageableHeight());

            newPaper.setSize(pw,ph);
        }

        pageFormat.setPaper(newPaper);

        repaint();
    }


    void paperType_mouseClicked(MouseEvent e) {
        isDirectClicked = true;
    }

    ExtPageFormat getPageFormat()
    {
        return this.pageFormat;
    }


    //set foot infomation
    void tFootTopMargin_keyReleased(KeyEvent e) {
        double text;
        double newIn;

        try
        {
            text = (tFootTopMargin.getText().equals(""))?0.0:Double.parseDouble(tFootTopMargin.getText());
            newIn = Utility.millimeterToDot(text);
        }
        catch(NumberFormatException nfe)
        {
            return;
        }

        pageFormat.getFootArea().setPaddingTop(newIn);
        pageFormat.getFootArea().repaintImageAbleArea();
        repaint();
    }

    void tFootBottomMargin_keyReleased(KeyEvent e) {
        double text;
        double newIn;

        try
        {
            text = (tFootBottomMargin.getText().equals(""))?0.0:Double.parseDouble(tFootBottomMargin.getText());
            newIn = Utility.millimeterToDot(text);
        }
        catch(NumberFormatException nfe)
        {
            return;
        }

        pageFormat.getFootArea().setPaddingBottom(newIn);
        pageFormat.getFootArea().repaintImageAbleArea();
        repaint();
    }

    void tFootLeftMargin_keyReleased(KeyEvent e) {
        double text;
        double newIn;

        try
        {
            text = (tFootLeftMargin.getText().equals(""))?0.0:Double.parseDouble(tFootLeftMargin.getText());
            newIn = Utility.millimeterToDot(text);
        }
        catch(NumberFormatException nfe)
        {
            return;
        }

        pageFormat.getFootArea().setPaddingLeft(newIn);
        pageFormat.getFootArea().repaintImageAbleArea();
        repaint();
    }

    void tFootRightMargin_keyReleased(KeyEvent e) {
        double text;
        double newIn;

        try
        {
            text = (tFootRightMargin.getText().equals(""))?0.0:Double.parseDouble(tFootRightMargin.getText());
            newIn = Utility.millimeterToDot(text);
        }
        catch(NumberFormatException nfe)
        {
            return;
        }

        pageFormat.getFootArea().setPaddingRight(newIn);
        pageFormat.getFootArea().repaintImageAbleArea();
        repaint();
    }

    void tFootHeight_keyReleased(KeyEvent e) {
        double text;
        double newIn;

        try
        {
            text = (tFootHeight.getText().equals(""))?0.0:Double.parseDouble(tFootHeight.getText());
            newIn = Utility.millimeterToDot(text);
        }
        catch(NumberFormatException nfe)
        {
            return;
        }

        pageFormat.getFootArea().setHeight(newIn);
        pageFormat.getFootArea().repaintImageAbleArea();
        repaint();
    }

    void tFootLeftContent_keyReleased(KeyEvent e) {
        pageFormat.getFootArea().setLeftContent(tFootLeftContent.getText());
    }

    void sFootLeftContent_actionPerformed(ActionEvent e) {
        pageFormat.getFootArea().setLeftContent(tFootLeftContent.getText());
    }

    void tFootMidContent_keyReleased(KeyEvent e) {
    }


    void tFootRightContent_keyReleased(KeyEvent e) {
        pageFormat.getFootArea().setRightContent(tFootLeftContent.getText());
    }

    void sFootRightContent_actionPerformed(ActionEvent e) {
        pageFormat.getFootArea().setRightContent(tFootLeftContent.getText());
    }

    //set head infomation
    void tHeadTopMargin_keyReleased(KeyEvent e) {
        double text;
        double newIn;

        try
        {
            text = (tHeadTopMargin.getText().equals(""))?0.0:Double.parseDouble(tHeadTopMargin.getText());
            newIn = Utility.millimeterToDot(text);
        }
        catch(NumberFormatException nfe)
        {
            return;
        }


        pageFormat.getHeadArea().setPaddingTop(newIn);
        pageFormat.getHeadArea().repaintImageAbleArea();

        repaint();
    }

    void tHeadBottomMargin_keyReleased(KeyEvent e) {
        double text;
        double newIn;

        try
        {
            text = (tHeadBottomMargin.getText().equals(""))?0.0:Double.parseDouble(tHeadBottomMargin.getText());
            newIn = Utility.millimeterToDot(text);
        }
        catch(NumberFormatException nfe)
        {
            return;
        }

        pageFormat.getHeadArea().setPaddingBottom(newIn);
        pageFormat.getHeadArea().repaintImageAbleArea();
        repaint();
    }

    void tHeadLeftMargin_keyReleased(KeyEvent e) {
        double text;
        double newIn;

        try
        {
            text = (tHeadLeftMargin.getText().equals(""))?0.0:Double.parseDouble(tHeadLeftMargin.getText());
            newIn = Utility.millimeterToDot(text);
        }
        catch(NumberFormatException nfe)
        {
            return;
        }

        pageFormat.getHeadArea().setPaddingLeft(newIn);
        pageFormat.getHeadArea().repaintImageAbleArea();

        repaint();
    }

    void tHeadRightMargin_keyReleased(KeyEvent e) {
        double text;
        double newIn;

        try
        {
            text = (tHeadRightMargin.getText().equals(""))?0.0:Double.parseDouble(tHeadRightMargin.getText());
            newIn = Utility.millimeterToDot(text);
        }
        catch(NumberFormatException nfe)
        {
            return;
        }


        pageFormat.getHeadArea().setPaddingRight(newIn);
        pageFormat.getHeadArea().repaintImageAbleArea();

        repaint();
    }

    void tHeadHeight_keyReleased(KeyEvent e) {
        double text;
        double newIn;

        try
        {
            text = (tHeadHeight.getText().equals(""))?0.0:Double.parseDouble(tHeadHeight.getText());
            newIn = Utility.millimeterToDot(text);
        }
        catch(NumberFormatException nfe)
        {
            return;
        }

        pageFormat.getHeadArea().setHeight(newIn);

        repaint();
    }

    void tHeadLeftContent_keyReleased(KeyEvent e) {
        pageFormat.getHeadArea().setTitle(tHeadLeftContent.getText());
    }

    void sHeadLeftContent_actionPerformed(ActionEvent e) {
        pageFormat.getHeadArea().setTitle(tHeadLeftContent.getText());
    }


    void sTableHead_actionPerformed(ActionEvent e) {
        int index = sTableHead.getSelectedIndex();

        switch(index)
        {
            case 0:
                pageFormat.setTableTitleType(TableTitleType.AllPage);
                break;
            case 1:
                pageFormat.setTableTitleType(TableTitleType.FirstPage);
                break;
            case 2:
            default:
                pageFormat.setTableTitleType(TableTitleType.None);
                break;
        }
    }


    void sTableHead_mouseClicked(MouseEvent e) {
        isTableHeadClicked = true;
    }

    void sTableAlign_mouseClicked(MouseEvent e) {
        isTableAlignClicked = true;
    }

    void sTableHeadBorder_mouseClicked(MouseEvent e) {
        isTableHeadBorderClicked = true;
    }

    void sTableFootBorder_mouseClicked(MouseEvent e) {
        isTableFootBorderClicked = true;
    }

    void tJobName_keyReleased(KeyEvent e) {
        pageFormat.setJobName(tJobName.getText());
    }
}
