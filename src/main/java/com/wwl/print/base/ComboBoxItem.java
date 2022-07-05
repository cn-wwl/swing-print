package com.wwl.print.base;

/**
 * @author long
 * @date 2022/7/5 14:56
 * @desc TODO
 */
public class ComboBoxItem {

    private Object value = null;
    private String label = null;

    public ComboBoxItem(String label, Object value)
    {
        this.label = label;
        this.value = value;
    }

    public Object getValue()
    {
        return value;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString()
    {
        return label == null ? "" : label;
    }
}
