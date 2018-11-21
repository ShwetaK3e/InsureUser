package com.shwetak3e.loading.CustomWidgets;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by shwetakumar on 7/27/17.
 */

public class ZentelloTextViewBold extends android.support.v7.widget.AppCompatTextView {


    public ZentelloTextViewBold(Context context) {
        super(context);
        Typeface tf=Typeface.createFromAsset(context.getAssets(),"fonts/roboto_bold.ttf");
        this.setTypeface(tf);

    }

    public ZentelloTextViewBold(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Typeface tf=Typeface.createFromAsset(context.getAssets(),"fonts/roboto_bold.ttf");
        this.setTypeface(tf);
    }

    public ZentelloTextViewBold(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Typeface tf=Typeface.createFromAsset(context.getAssets(),"fonts/roboto_bold.ttf");
        this.setTypeface(tf);
    }


}
