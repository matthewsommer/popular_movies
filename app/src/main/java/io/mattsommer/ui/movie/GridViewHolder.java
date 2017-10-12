package io.mattsommer.ui.movie;

import android.view.View;
import android.widget.ImageView;

import io.mattsommer.popularmovies.R;

/**
 * Created by Matt Sommer on 6/14/16.
 */
public class GridViewHolder {
    public final ImageView iconView;

    public GridViewHolder(View view) {
        iconView = (ImageView) view.findViewById(R.id.list_item_icon);
    }
}