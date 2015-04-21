package be.howest.nmct.parking;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by robhofman on 20/04/15.
 */
public class ParkingAdapter extends BaseAdapter {

    //http://ocddevelopers.com/2014/extend-baseadapter-instead-of-arrayadapter-for-custom-list-items/

    ArrayList<Parking> alleParkings;
    private LayoutInflater mijnInflater;

    public ParkingAdapter(Context context, ArrayList<Parking> parkingetjes) {
        mijnInflater = LayoutInflater.from(context);
        alleParkings = parkingetjes;
    }

    @Override
    public int getCount() {
        return alleParkings.size();
    }

    @Override
    public Object getItem(int position) {
        return alleParkings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder holder;
        if(convertView == null)
        {
            view = mijnInflater.inflate(R.layout.row_parking, parent, false);
            holder = new ViewHolder();
            holder.naamParking = (TextView)view.findViewById(R.id.tvNaam);
            holder.aantalBeschikbarePlaatsen = (TextView)view.findViewById(R.id.tvPlaatsen);
            view.setTag(holder);
        }
        else
        {
            view = convertView;
            holder = (ViewHolder)view.getTag();
        }
        Parking parking = alleParkings.get(position);
        holder.aantalBeschikbarePlaatsen.setText(""+parking.availableCapacity);
        holder.naamParking.setText(parking.description);
        if(parking.availableCapacity <= 0)
        {
            holder.aantalBeschikbarePlaatsen.setBackgroundColor(Color.RED);
        }
        else
        {
            holder.aantalBeschikbarePlaatsen.setBackgroundColor(Color.GREEN);
        }
        return view;
    }

    private class ViewHolder {
        public TextView naamParking;
        public TextView aantalBeschikbarePlaatsen;
    }
}
