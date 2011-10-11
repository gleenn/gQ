package com.gleenn.android.gq;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GQ {
    private static View lastContext;
    private View[] views;

    public GQ(View... views) {
        this.views = views;
    }

    public static GQ gQ(View... views) {
        return new GQ(views);
    }

//    public static GQ gQ(View view, int... ids) {
//        View[] views = new View[ids.length];
//        for (int i=0; i<ids.length; i++) {
//            views[i] = view.findViewById(ids[i]);
//        }
//        return new GQ(views);
//    }

    public static GQ gQ(Dialog dialog) {
        lastContext = dialog.getCurrentFocus();
        return new GQ(dialog.getWindow().getCurrentFocus());
    }

    public static GQ gQ(Activity activity) {
        lastContext = activity.getWindow().getCurrentFocus();
        return new GQ(activity.getWindow().getCurrentFocus());
    }

    public GQ find(int... ids) {
        if(views.length == 0 && lastContext != null) views = new View[]{lastContext};

        List<View> views = new ArrayList<View>();
        for (int i=0; i<ids.length; i++) {
            for(View view : views) {
                View matchedView = view.findViewById(ids[i]);
                if(matchedView != null) {
                    views.add(matchedView);
                }
            }
        }
        this.views = (View[]) views.toArray();
        return this;
    }

    public GQ toggle() {
        for (View view : views) {
            view.setVisibility(view.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
        }
        return this;
    }

    public GQ show() {
        for (View view : views) {
            view.setVisibility(View.VISIBLE);
        }
        return this;
    }

    public GQ hide() {
        for (View view : views) {
            view.setVisibility(View.INVISIBLE);
        }
        return this;
    }

    public GQ gone() {
        for (View view : views) {
            view.setVisibility(View.GONE);
        }
        return this;
    }

    public GQ text(String text) {
        for (View view : views) {
            if(view instanceof TextView)
                ((TextView) view).setText(text);
        }
        return this;
    }

    public GQ draw(int id) {
        for (View view : views) {
            if(view instanceof ImageView)
                ((ImageView) view).setImageResource(id);
        }
        return this;
    }

    public GQ click(View.OnClickListener clickListener) {
        for (View view : views) {
            view.setOnClickListener(clickListener);
        }
        return this;
    }
}
