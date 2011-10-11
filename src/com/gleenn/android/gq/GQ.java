package com.gleenn.android.gq;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class GQ {
    private View[] views;
    private Dialog[] dialogs;
    private Activity[] activities;

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
        return new GQ(dialog.getWindow().getCurrentFocus());
    }

    public static GQ gQ(Activity activity) {
        return new GQ(activity.getWindow().getCurrentFocus());
    }

    public GQ find(int... ids) {
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

    public GQ click(View.OnClickListener clickListener) {
        for (View view : views) {
            view.setOnClickListener(clickListener);
        }
        return this;
    }
}
