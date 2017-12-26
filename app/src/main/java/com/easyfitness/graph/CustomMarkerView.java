package com.easyfitness.graph;

import android.content.Context;
import android.widget.TextView;

import com.easyfitness.DAO.DAOUtils;
import com.easyfitness.R;
import com.easyfitness.utils.DateConverter;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CustomMarkerView extends MarkerView {

    private TextView tvContent;
    private TextView tvDate;
    private DecimalFormat mFormat = new DecimalFormat("#.##");
    /**
     * Screen width in pixels.
     */
    private int uiScreenWidth;

    public CustomMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);

        // find your layout components
        tvContent = (TextView) findViewById(R.id.tvContent);
        tvDate = (TextView) findViewById(R.id.tvDate);
        uiScreenWidth = getResources().getDisplayMetrics().widthPixels;
    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        //DateFormat dateFormat3 = android.text.format.DateFormat.getDateFormat(getContext().getApplicationContext());
        DateFormat dateFormat3 = android.text.format.DateFormat.getDateFormat(getContext().getApplicationContext());
        dateFormat3.setTimeZone(TimeZone.getTimeZone("GMT"));
        //SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM HH:mm:SS");
        //dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        tvDate.setText(dateFormat3.format(new Date((long) DateConverter.nbMilliseconds(e.getX()))));
        tvContent.setText(mFormat.format(e.getY()));

        // this will perform necessary layouting
        super.refreshContent(e, highlight);
    }

    private MPPointF mOffset;

    @Override
    public MPPointF getOffset() {

        if (mOffset == null) {
            // center the marker horizontally and vertically
            mOffset = new MPPointF(-(getWidth() / 2), -getHeight());
        }

        return mOffset;
    }
}