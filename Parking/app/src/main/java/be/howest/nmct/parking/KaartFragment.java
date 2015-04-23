package be.howest.nmct.parking;


import android.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class KaartFragment extends Fragment {


    double lat, longi;
    LatLng latLng;
    String naam;
    GoogleMap  gMap;


    public KaartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kaart, container, false);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundel = this.getArguments();
        lat = Double.parseDouble(bundel.getString(DetailsFragment.LAT_KEY));
        longi = Double.parseDouble(bundel.getString(DetailsFragment.LONG_KEY));
        naam = bundel.getString(DetailsFragment.NAAM_KEY);
        latLng = new LatLng(lat, longi);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try{

            gMap = ((MapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();
            gMap.addMarker(new MarkerOptions().position(latLng).title(naam));
            gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

        }
        catch (Exception e)
        {
            System.out.println("fout: " + e.getMessage());
            Log.v("kaart fout ", "de kaart is niet ingeladen");
        }


    }
}
