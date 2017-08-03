package cookmap.cookandroid.com.googlemap_sample01;

import android.content.Context;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        TextView output = (TextView)findViewById(R.id.output);

        //제공자 목록 구해서 출력
        List<String> providers = manager.getAllProviders();
        String s = "";

        for(int i = 0; i<providers.size(); i++){
            s += "위치 제공자 " + providers.get(i) + "의 상태 : " +
                    manager.isProviderEnabled(providers.get(i)) + "\n";
        }

        output.setText(s);
    }
}
