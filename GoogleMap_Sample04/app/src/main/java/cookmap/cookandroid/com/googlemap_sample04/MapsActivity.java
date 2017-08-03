package cookmap.cookandroid.com.googlemap_sample04;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    Button btn;
    EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        final Geocoder geocoder = new Geocoder(this);

        btn = (Button)findViewById(R.id.button);
        edt = (EditText)findViewById(R.id.edt1);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<Address> list = null;

                String str = edt.getText().toString();
                try {
                    list = geocoder.getFromLocationName(str,10);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("test","입출력 오류 - 서버에서 주소변환시 에러발생");
                }

                if (list != null) {
                    if (list.size() == 0) {

                    } else {
                        Address addr = list.get(0);
                        double lat = addr.getLatitude();
                        double lon = addr.getLongitude();

                        mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lon)).title("찾았어요"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 15));

                    }
                }

            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(35.134004, 129.103702);
        mMap.addMarker(new MarkerOptions().position(new LatLng(35.134004, 129.103702)).title("부경대"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(35.134004, 129.103702), 15));
    }
}
