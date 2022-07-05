package com.wwl.print.utils;


import javax.swing.*;
import java.awt.print.*;

/**
 * @author wwl
 * @date 2022/6/2 17:08
 * @desc TODO
 */
public class PrintMonitor  implements Pageable {
    private PrinterJob printerJob;
    private Pageable pageable;
    private JOptionPane optionPane;
    private JDialog statusDialog;

    public PrintMonitor(Pageable newPageable, String jobName) {
        pageable = newPageable;
        printerJob = PrinterJob.getPrinterJob();
        printerJob.setJobName(jobName);
        String[] options = { "取消" };
        optionPane = new JOptionPane("", JOptionPane.INFORMATION_MESSAGE, JOptionPane.CANCEL_OPTION, null, options);
        statusDialog = optionPane.createDialog(null, "打印状态");
    }

    public void performPrint(boolean showDialog) throws PrinterException {
        printerJob.setPageable(this);
        if (showDialog) {
            boolean isOK = printerJob.printDialog();
            if (!isOK) {
                return;
            }
        }
        optionPane.setMessage("初始化打印 ...");
        Thread newThread = new Thread(() -> {
            try {
                printerJob.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
            statusDialog.setVisible(false);
        });
        newThread.start();

        statusDialog.setVisible(true);
    }

    @Override
    public int getNumberOfPages() {
        return pageable.getNumberOfPages();
    }

    @Override
    public Printable getPrintable(int index) {
        optionPane.setMessage("正在打印：" + (index + 1) + "/" + pageable.getNumberOfPages());
        if (optionPane.getValue() != JOptionPane.UNINITIALIZED_VALUE) {
            printerJob.cancel();
        }
        return pageable.getPrintable(index);
    }

    @Override
    public PageFormat getPageFormat(int index) {
        return pageable.getPageFormat(index);
    }
}
