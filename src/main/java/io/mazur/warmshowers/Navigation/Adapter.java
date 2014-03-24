package io.mazur.warmshowers.Navigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.devspark.robototextview.widget.RobotoTextView;

import java.util.ArrayList;

import io.mazur.warmshowers.R;

public class Adapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;

    private int resource;

    private ArrayList<Item> data;

    public Adapter(Context context, int resource, ArrayList<Item> data) {
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resource = resource;
        this.data = data;
    }

    public int getCount() {
        return this.data.size();
    }

    public Object getItem(int position) {
        return this.data.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            if(position == 4) {
                view = this.inflater.inflate(R.layout.main_drawer_bottom, parent, false);
            } else if(position == 5) {
                view = this.inflater.inflate(R.layout.main_drawer_last, parent, false);
            } else {
                view = this.inflater.inflate(resource, parent, false);
            }
        } else {
            view = convertView;
        }

        return this.bindData(view, position);
    }

    public View bindData(View view, int position) {
        if (this.data.get(position) == null) {
            return view;
        }

        Item item = this.data.get(position);

        View viewElement = view.findViewById(R.id.title);

        RobotoTextView textView = (RobotoTextView) viewElement;
        textView.setText(item.title);

        return view;
    }
}
