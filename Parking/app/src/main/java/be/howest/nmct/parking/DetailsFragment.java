package be.howest.nmct.parking;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    TextView info;
    Parking parkingGent;
    TextView aantalBeschikbare;
    TextView totaalAantalPlaatsen;
    TextView adres;
    TextView telefoon;
    TextView openingsUren;
    TextView open;
    Button Kaart;
    public static String LONG_KEY = "be.howest.nmct.parking.LONGITUDE";
    public static String LAT_KEY = "be.howest.nmct.parking.LATITUDE";
    public static String NAAM_KEY = "be.howest.nmct.parking.NAAM";

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
        info = (TextView)detailsView.findViewById(R.id.parkingNaam);
        info.setText(parkingGent.description);
        aantalBeschikbare = (TextView)detailsView.findViewById(R.id.parkingVrijePlaatsen);
        aantalBeschikbare.setText(""+parkingGent.availableCapacity);
        totaalAantalPlaatsen = (TextView)detailsView.findViewById(R.id.tvTotaal);
        totaalAantalPlaatsen.setText(""+parkingGent.totalCapacity);
        adres = (TextView)detailsView.findViewById(R.id.tvAdres);
        adres.setText(parkingGent.address);
        telefoon = (TextView)detailsView.findViewById(R.id.tvTelefoon);
        telefoon.setText(parkingGent.contact);
        openingsUren = (TextView)detailsView.findViewById(R.id.tvOpeningsuren);
        openingsUren.setText(parkingGent.openingsHours);
        open = (TextView)detailsView.findViewById(R.id.tvOpen);
        if(parkingGent.open) {
            open.setText("open");
        }
        else{
            open.setText("Gesloten");
        }
        Kaart = (Button)detailsView.findViewById(R.id.btnKaart);
        Kaart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                KaartFragment kFragment = new KaartFragment();
                Bundle bundel = new Bundle();
                bundel.putString(LONG_KEY, ""+parkingGent.longitude);
                bundel.putString(LAT_KEY, ""+parkingGent.latitude);
                bundel.putString(NAAM_KEY, parkingGent.description);
                kFragment.setArguments(bundel);
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, kFragment, null)
                        .addToBackStack(null)
                        .commit();
            }
        });
        return detailsView;
    }


}
