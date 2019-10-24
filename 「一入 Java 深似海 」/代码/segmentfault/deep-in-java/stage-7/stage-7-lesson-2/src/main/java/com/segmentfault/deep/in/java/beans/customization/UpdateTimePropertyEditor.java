package com.segmentfault.deep.in.java.beans.customization;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UpdateTimePropertyEditor extends PropertyEditorSupport {

    public void setAsText(String text) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            setValue(format.parse(text));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
