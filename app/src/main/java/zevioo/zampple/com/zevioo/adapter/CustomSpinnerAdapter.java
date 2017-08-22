package zevioo.zampple.com.zevioo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import zevioo.zampple.com.zevioo.R;
import zevioo.zampple.com.zevioo.κουτί.entity.SimpleItem;

/**
 * Created by kgiannoulis on 22/8/2017
 */

public class CustomSpinnerAdapter extends ArrayAdapter {

    private Context mContext;
    private List<SimpleItem> itemList;
    public CustomSpinnerAdapter(Context context, int textViewResourceId, List<SimpleItem> itemList) {

        super(context, textViewResourceId);
        this.mContext=context;
        this.itemList=itemList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.spinner_item, parent,
                false);
        TextView make = (TextView) row.findViewById(R.id.text);
        if (position>=itemList.size()){
            make.setText(itemList.get(0).toString());
        } else {
            make.setText(itemList.get(position).toString());
        }
        return row;
    }


    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.spinner_item_drop_down, parent,
                false);
        TextView make = (TextView) row.findViewById(R.id.text);
        make.setText(itemList.get(position).toString());
        return row;
    }

    @Override
    public int getCount() {
        return (itemList == null ? 0 : itemList.size());
    }
}