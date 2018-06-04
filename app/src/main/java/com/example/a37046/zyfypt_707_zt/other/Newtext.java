package com.example.a37046.zyfypt_707_zt.other;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class Newtext extends android.support.v7.widget.AppCompatTextView{
        private static Typeface __cachedTypeFace = null;

        public Newtext(Context context) {
            super(context);
            initFont();
        }

        public Newtext(Context context, AttributeSet attrs) {
            super(context, attrs);
            initFont();
        }

        public Newtext(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            initFont();
        }

        private void initFont() {
            if (__cachedTypeFace == null) {
                final Typeface iconfont = Typeface.createFromAsset(getContext().getAssets(), "iconfont/iconfont.ttf");
                __cachedTypeFace = iconfont;
            }
            setTypeface(__cachedTypeFace);
        }
}
