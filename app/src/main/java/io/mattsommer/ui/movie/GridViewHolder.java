package io.mattsommer.ui.movie;

import android.view.View;
import android.widget.ImageView;

import io.mattsommer.udacity.android.example.R;

/**
 * Holder class for gridview to speed up scrolling
 * Created by Matt Sommer on 6/14/16.
 */
public class GridViewHolder {

  public final ImageView iconView;

  public GridViewHolder(View view) {
    iconView = (ImageView) view.findViewById(R.id.list_item_icon);
  }
}