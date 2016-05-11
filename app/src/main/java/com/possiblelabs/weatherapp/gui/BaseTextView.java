package com.possiblelabs.weatherapp.gui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by possiblelabs on 5/10/16.
 */
public abstract class BaseTextView extends TextView {

    public BaseTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public BaseTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseTextView(Context context) {
        super(context);
        init();
    }


    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), getFontPath());
        setTypeface(tf);
    }

    protected abstract String getFontPath();
}

