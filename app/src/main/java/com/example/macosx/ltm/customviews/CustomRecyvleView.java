package com.example.macosx.ltm.customviews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.example.macosx.ltm.activities.Chat;

public class CustomRecyvleView extends RecyclerView {
    public interface ScrollViewListener {
        void onScrollChanged(CustomRecyvleView scrollView,
                             int x, int y, int oldx, int oldy);
    }
    private ScrollViewListener scrollViewListener = null;
    public CustomRecyvleView(Context context) {
        super(context);
    }

    public CustomRecyvleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CustomRecyvleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }
}