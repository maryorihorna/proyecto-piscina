package com.proyecto.piscina.web.app.utils;

import java.beans.PropertyEditorSupport;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CustomTimeEditor extends PropertyEditorSupport {
    private final DateFormat timeFormat;
    private final boolean allowEmpty;

    public CustomTimeEditor(String format, boolean allowEmpty) {
        this.timeFormat = new SimpleDateFormat(format);
        this.allowEmpty = allowEmpty;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (this.allowEmpty && (text == null || text.trim().isEmpty())) {
            setValue(null);
        } else {
            try {
                setValue(new Time(this.timeFormat.parse(text).getTime()));
            } catch (ParseException e) {
                throw new IllegalArgumentException("Could not parse time: " + e.getMessage(), e);
            }
        }
    }

    @Override
    public String getAsText() {
        Time time = (Time) getValue();
        return time != null ? this.timeFormat.format(time) : "";
    }
}