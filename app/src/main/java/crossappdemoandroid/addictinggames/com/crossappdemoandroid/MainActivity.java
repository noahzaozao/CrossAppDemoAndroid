package crossappdemoandroid.addictinggames.com.crossappdemoandroid;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtStatus;
    Button moreBtn;

    private PublisherInterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.txtStatus = (TextView)findViewById(R.id.txtStatus);

        this.moreBtn = (Button)findViewById(R.id.moreBtn);
        this.moreBtn.setOnClickListener(listener);

        loadInterstitial();
    }

    Button.OnClickListener listener = new Button.OnClickListener(){//创建监听对象
        public void onClick(View v){
            showInterstitial();
        }
    };

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadInterstitial() {

        mInterstitialAd = new PublisherInterstitialAd(this.getBaseContext());
        mInterstitialAd.setAdUnitId(" /6087/defy_gaming_apps");
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                moreBtn.setEnabled(true);
                String strTmp="Status: Ready";
                txtStatus.setText(strTmp);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                String strTmp="Status: on ad failed to load";
                txtStatus.setText(strTmp);
            }

            @Override
            public void onAdClosed() {
                String strTmp="Status: Loading";
                txtStatus.setText(strTmp);
                loadInterstitial();
            }
        });

        // Disable the next level button and load the ad.
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder()
                .addCustomTargeting("appid", "com.addictinggames.appid")
                .build();
        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
