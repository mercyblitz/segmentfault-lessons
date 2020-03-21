package com.segmentfault.deep.in.java.beans.customization;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

import static com.segmentfault.deep.in.java.beans.properties.Person.isNumeric;

/**
 * Person id 属性编辑器
 * Id String -> Long
 */
public class IdPropertyEditor extends PropertyEditorSupport {

    public void setAsText(String text) {
        if (isNumeric(text)) {
            setValue(Long.valueOf(text));
        }
    }

    /**
     * 覆盖父类方法
     *
     * @return
     */
    @Override
    public Long getValue() {
        return (Long) super.getValue();
    }
}
