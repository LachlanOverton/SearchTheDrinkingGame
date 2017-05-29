package app.searchthedrinkinggame;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void nT(){
        String x = getString(R.string.IOErrorToast);
        Toast.makeText(this, x, Toast.LENGTH_LONG).show();
    }

    Context context(){
        return this;
    }
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SeekBar sbPlayers  = (SeekBar) findViewById(R.id.seekBar);
        final SeekBar sbRounds  = (SeekBar) findViewById(R.id.seekBar2);
        final CheckBox cbAnimals = (CheckBox) findViewById(R.id.cbAnimals);
        final CheckBox cbResearch = (CheckBox) findViewById(R.id.cbResearch);
        final CheckBox cbCountries = (CheckBox) findViewById(R.id.cbCountries);
        final CheckBox cbCompanies = (CheckBox) findViewById(R.id.cbCompanies);
        Button btnStart = (Button) findViewById(R.id.btnStart);
        final List<String> querys = null;

        AdRequest request = new AdRequest.Builder().build();
        String x = getString(R.string.AdBanner);
        MobileAds.initialize(getApplicationContext(), x);
        mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
        if(isNetworkAvailable()) {
            mAdView.loadAd(request);
        }else{
            nT();
        }

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cbAnimals.isChecked() || !cbCompanies.isChecked() || !cbCountries.isChecked() || !cbResearch.isChecked()){
                    Toast.makeText(MainActivity.this, "You must select at least 1 category", Toast.LENGTH_LONG);
                }else
                { if(cbAnimals.isChecked()){
                    querys.addAll(Arrays.asList(getResources().getStringArray(R.array.Animals)));
                }if(cbCompanies.isChecked()){
                    querys.addAll(Arrays.asList(getResources().getStringArray(R.array.Companies)));
                }if(cbCountries.isChecked()){
                    querys.addAll(Arrays.asList(getResources().getStringArray(R.array.Countries)));
                }if(cbResearch.isChecked()){
                    querys.addAll(Arrays.asList(getResources().getStringArray(R.array.Research)));
                }
                    Random r = new Random();

                    int a;
                    String[] an = (String[]) querys.toArray();
                    String[] result = new String[sbRounds.getProgress()];
                    for(int i = 0; i <= sbRounds.getProgress();) {
                        a = r.nextInt(querys.size());
                        result[i] = an[a];
                        i++;
                    }
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    intent.putExtra("Q",result);
                    try{
                        startActivity(intent);
                        System.gc();
                        finish();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    void pass(List<String> s)
    {
        Random r = new Random();
        int a = r.nextInt(s.size());
        String[] an = (String[]) s.toArray();
        String result = an[a];
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("Q",result);
        try{
            startActivity(intent);
            System.gc();
            finish();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }



}

