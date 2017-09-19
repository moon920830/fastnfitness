package com.easyfitness.fonte;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.easyfitness.DAO.Machine;
import com.easyfitness.DAO.bodymeasures.BodyPart;
import com.easyfitness.R;

import java.util.ArrayList;

public class MachineArrayAdapter extends ArrayAdapter<Machine> implements View.OnClickListener{

    private ArrayList<Machine> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtID;
        TextView txtName;
        ImageButton btFavorite;
    }

    public MachineArrayAdapter(ArrayList<Machine> data, Context context) {
        super(context, R.layout.bodypart_row, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Machine dataModel=(Machine)object;

        /*switch (v.getId())
        {
            case R.id.item_info:
                Snackbar.make(v, "Release date " +dataModel.getFeature(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }*/
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        //BodyPart dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.bodypart_row, parent, false);
            viewHolder.txtID = (TextView) convertView.findViewById(R.id.LIST_BODYPART_ID);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.LIST_BODYPART);
            //viewHolder.btFavorite = (ImageButton) convertView.findViewById(R.id.LIST_BODYPART_LOGO);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        //Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        //result.startAnimation(animation);
        //lastPosition = position;
        /*viewHolder.txtID.setText(String.valueOf(dataModel.getId()));
        viewHolder.txtName.setText(this.getContext().getResources().getText(dataModel.getResourceNameID()));
        if (dataModel.getLastMeasure() != null )
            viewHolder.txtLastMeasure.setText(String.valueOf(dataModel.getLastMeasure().getBodyMeasure()));
        else
            viewHolder.txtLastMeasure.setText("-");
        viewHolder.logo.setImageResource(dataModel.getResourceLogoID());
        // Return the completed view to render on screen*/
        return convertView;
    }
}
