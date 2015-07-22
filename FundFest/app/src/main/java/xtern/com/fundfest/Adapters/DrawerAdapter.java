package xtern.com.fundfest.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import xtern.com.fundfest.R;

/**
 * Created by sean.phillips on 7/21/15.
 */
public class DrawerAdapter extends BaseAdapter {

    ArrayList<String> list;
    LayoutInflater inflater;

    public DrawerAdapter(ArrayList<String> list, Context context){
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
    }

    public DrawerAdapter(String[] list, Context context){
        ArrayList<String> strings = new ArrayList<>();
        for(String s : list) strings.add(s);

        this.list = strings;
        inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        StringHolder holder;
        if(convertView == null) {
            view = inflater.inflate(R.layout.drawer_list_item, parent, false);
            holder = new StringHolder();
            holder.text = (TextView)view.findViewById(R.id.text);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (StringHolder)view.getTag();
        }

        String text = list.get(position);
        holder.text.setText(text);

        return view;
    }

    public class StringHolder{
        public TextView text;
    }
}
