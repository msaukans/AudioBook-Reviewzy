package gq.codester.maris.audiobookreviewzy;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomList extends ArrayAdapter<String> {

    private final Activity context;
    private final int[] pic;
    private final String[] name;
    private final String[] author;
    public CustomList(Activity context, int[] pic, String[] name, String[] author) {
        super(context, R.layout.list_single, name);
        this.context = context;
        this.pic = pic;
        this.name = name;
        this.author = author;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);

        ImageView txtPic = (ImageView) rowView.findViewById(R.id.pic);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.name);
        TextView imageView = (TextView) rowView.findViewById(R.id.author);

        txtPic.setImageResource(pic[position]);
        txtTitle.setText(name[position]);

        imageView.setText(author[position]);
        return rowView;
    }
}
