package com.wwl.print.ui;

import com.wwl.print.paper.format.ExtPageFormat;
import com.wwl.print.able.TablePrintable;
import com.wwl.print.utils.PaperSetting;
import com.wwl.print.utils.PrintMonitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;

/**
 * @author long
 * @date 2022/7/4 9:44
 * @desc TODO
 */
public class PrintTable extends JPanel {
    //the target table
    JTable table;

    //use as the printable for print
    TablePrintable tablePrintable;

    //use as the pageformat for print
    ExtPageFormat pageFormat;
    JPanel mainPanel = new JPanel();
    BorderLayout thisPanelLayout = new BorderLayout();
    FlowLayout mainPanelLayout = new FlowLayout();
    JButton bDoPrint = new JButton();
    JButton bPrintPreview = new JButton();
    JButton bSetPaper = new JButton();

    //init PrintTable class with only table
    public PrintTable(JTable targetTable) {
        //super();
        //super.repaint();
        table = targetTable;
        pageFormat = new  ExtPageFormat();
        pageFormat.setPaper(PaperSetting.getA4());
    }

    //init PrintTable class with both table and pageformat
    public PrintTable(JTable targetTable, PageFormat newPageFormat) {
        //super();
        //super.repaint();
        table = targetTable;
        pageFormat = (ExtPageFormat)newPageFormat;
    }

    //get PageFormat
    public PageFormat getPageFormat()
    {
        return pageFormat;
    }

    //set or get table
    public void setTable(JTable newTable)
    {
        table = newTable;
    }

    public JTable getTable()
    {
        return table;
    }

    //show print preview dialog
    public void printPreview()
    {
        //get printable from table
        tablePrintable = new TablePrintable(table,pageFormat);

        //build a printPreview dialog to show the paper
        PrintPreview  printPreview = new PrintPreview(tablePrintable,0);

        JDialog newDialog = new JDialog();
        newDialog.setTitle("Print Preview");
        newDialog.getContentPane().add(printPreview);
        newDialog.setSize(900,960);

        newDialog.setLocationRelativeTo(null);
        newDialog.setVisible(true);
    }

    //show a dialog for set paper
    public void paperSetPage()
    {
        //send the ExtPageFormat to it and build a dialog
        PaperSetPage  paperSetPage = new PaperSetPage(pageFormat);

        JDialog newDialog = new JDialog();
        newDialog.setTitle("Paper Setting");
        newDialog.getContentPane().add(paperSetPage);
        newDialog.setSize(675,450);

        newDialog.setLocationRelativeTo(null);
        newDialog.setVisible(true);
    }

    //printing
    public void doPrintWithDialog()
    {
        tablePrintable = new TablePrintable(table,pageFormat);

        PrintMonitor printMonitor = new PrintMonitor(tablePrintable,pageFormat.getJobName());
        try
        {
            printMonitor.performPrint(true);
        }
        catch(PrinterException pe)
        {
            JOptionPane.showMessageDialog(null,"Print Error:" + pe.getMessage());
        }
    }

    public void doPrintWithoutDialog()
    {
        tablePrintable = new TablePrintable(table,pageFormat);

        PrintMonitor printMonitor = new PrintMonitor(tablePrintable,pageFormat.getJobName());
        try
        {
            printMonitor.performPrint(false);
        }
        catch(PrinterException pe)
        {
            JOptionPane.showMessageDialog(null,"Print Error:" + pe.getMessage());
        }
    }

    public PrintTable() {
        try {
            jbInit();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    private void jbInit() throws Exception {
        mainPanel.setLayout(mainPanelLayout);
        this.setLayout(thisPanelLayout);
        this.setBorder(BorderFactory.createEtchedBorder());
        bDoPrint.setText("Print ...");
        bDoPrint.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bDoPrint_actionPerformed(e);
            }
        });
        bPrintPreview.setText("Print With Preview");
        bPrintPreview.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bPrintPreview_actionPerformed(e);
            }
        });
        bSetPaper.setText("SetPaper");
        bSetPaper.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bSetPaper_actionPerformed(e);
            }
        });
        this.add(mainPanel,  BorderLayout.CENTER);
        mainPanel.add(bDoPrint, null);
        mainPanel.add(bPrintPreview, null);
        mainPanel.add(bSetPaper, null);
    }

    void bDoPrint_actionPerformed(ActionEvent e) {
        this.doPrintWithDialog();
    }

    void bPrintPreview_actionPerformed(ActionEvent e) {
        this.printPreview();
    }

    void bSetPaper_actionPerformed(ActionEvent e) {
        this.paperSetPage();
    }
}
