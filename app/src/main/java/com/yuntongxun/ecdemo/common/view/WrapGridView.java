package com.yuntongxun.ecdemo.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by smileklvens on 2017/8/30.
 */

public class WrapGridView extends GridView {


    public WrapGridView(Context context) {
        super(context);
    }

    public WrapGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
