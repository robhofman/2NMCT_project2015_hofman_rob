package be.howest.nmct.parking;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    TextView info;
    Parking parkingGent;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parkingGent = LijstParkingsFragment.geselecteerdeParking;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View detailsView = inflater.inflate(R.layout.fragment_details, container, false);
        info = (TextView)detailsView.findViewById(R.id.hello);
        info.setText(parkingGent.description);
        return detailsView;
    }


}
