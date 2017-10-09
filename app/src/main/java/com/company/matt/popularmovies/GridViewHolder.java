package com.company.matt.popularmovies;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by Matt on 6/14/16.
 */
public class GridViewHolder {
    public final ImageView iconView;

    public GridViewHolder(View view) {
        iconView = (ImageView) view.findViewById(R.id.list_item_icon);
    }
}