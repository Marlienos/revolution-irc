package io.mrarm.irc.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;

public class SimpleChipSpan extends ImageSpan {

    public SimpleChipSpan(Context context, String text, boolean transparent) {
        super(new SimpleChipDrawable(context, text, transparent));
        getDrawable().setBounds(0, 0, getDrawable().getIntrinsicWidth(), getDrawable().getIntrinsicHeight());
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        Paint myPaint = ((SimpleChipDrawable) getDrawable()).getPaint();
        if (text instanceof Spannable) {
            Spanned spannable = (Spannable) text;
            for (ForegroundColorSpan o : spannable.getSpans(start, end, ForegroundColorSpan.class))
                myPaint.setColor(o.getForegroundColor());
            int style = 0;
            for (StyleSpan o : spannable.getSpans(start, end, StyleSpan.class))
                style |= o.getStyle();
            myPaint.setTypeface(Typeface.create(Typeface.DEFAULT, style));
        }
        super.draw(canvas, text, start, end, x, top, y, bottom, paint);
    }
}
