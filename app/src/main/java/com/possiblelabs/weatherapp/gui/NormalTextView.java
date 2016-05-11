package com.possiblelabs.weatherapp.gui;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by possiblelabs on 5/10/16.
 */
public class NormalTextView extends BaseTextView {

    public NormalTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public NormalTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NormalTextView(Context context) {
        super(context);
    }

    @Override
    protected String getFontPath() {
        return "fonts/AdventPro-Regular.ttf";
    }
}
