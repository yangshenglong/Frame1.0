package com.android.frame.glide;


import android.content.Context;
import android.widget.ImageView;

import com.android.frame.R;
import com.bumptech.glide.Glide;


public class GlideUtil  {

    public static void showImg(Context context, String imgUrl, ImageView imgView) {
        Glide.with(context)
                .load(imgUrl)
                .centerCrop()
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(imgView);
    }
}

