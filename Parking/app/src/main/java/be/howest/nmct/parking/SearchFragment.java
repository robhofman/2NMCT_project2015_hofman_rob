package be.howest.nmct.parking;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link -.} interface
 * to handle interaction events.
 * Use the {@link SearchFragment#} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    TextView label;
    AutoCompleteTextView trefwoord;
    Button dichtste;
    Button kaart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        label = (TextView)getActivity().findViewById(R.id.lblZoek);
        trefwoord = (AutoCompleteTextView)getActivity().findViewById(R.id.tvZoek);
        dichtste = (Button)getActivity().findViewById(R.id.btnDichtste);
        kaart = (Button)getActivity().findViewById(R.id.btnKaart);
    }

    public interface searchFragmentListener{
        public void showDichtsteSteden();
        public void showKaart();
    }
}
