package be.howest.nmct.parking;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LijstParkingsFragment extends Fragment {


    HttpClient client;
    String Url = "http://datatank.gent.be/Mobiliteitsbedrijf/Parkings.json";
    JSONObject json;
    JSONObject json2;
    JSONObject json3;
    JSONArray array;
    List<Parking> parkingArray = new ArrayList<Parking>();

    public LijstParkingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        client = new DefaultHttpClient();
        new Read().execute("parkings");
        return inflater.inflate(R.layout.fragment_lijst_parkings, container, false);

    }

    public JSONObject inlezenURL(String url) throws IOException, JSONException
    {
        StringBuilder urll = new StringBuilder(url);
        HttpGet get = new HttpGet(urll.toString());
        HttpResponse r = client.execute(get);
        int status = r.getStatusLine().getStatusCode();
        if(status == 200)
        {
            HttpEntity e = r.getEntity();
            String data = EntityUtils.toString(e);
            JSONObject result = new JSONObject(data);
            return result;
        }
        return null;
    }



    public class Read extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                json = inlezenURL(Url);
                json2 = json.getJSONObject("Parkings");
                array = json2.getJSONArray("parkings");

                for(int i = 0; i<array.length(); i++)
                {
                    Parking p = maakParking(array.getJSONObject(i));
                    //parkingArray.add(p);
                    try {
                        parkingArray.add(p);
                    }
                    catch (Exception e)
                    {
                        Log.v("toevoegfout", "kon item niet aan array toevoegen");
                    }
                }
                int x = array.length();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    public Parking maakParking(JSONObject obj)
    {
        Parking p = new Parking();
        try {
            p.name = obj.getString("name");
            p.description = obj.getString("description");
            p.address = obj.getString("address");
            p.openingsHours = obj.getString("openingHours");
            p.open = obj.getBoolean("open");
            p.availableCapacity = obj.getInt("availableCapacity");
            p.latitude = obj.getDouble("latitude");
            p.longitude = obj.getDouble("longitude");
            p.covered = obj.getBoolean("covered");
            p.totalCapacity = obj.getInt("totalCapacity");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return p;
    }

}
