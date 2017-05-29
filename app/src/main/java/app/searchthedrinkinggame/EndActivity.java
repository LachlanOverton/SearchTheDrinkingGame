package app.searchthedrinkinggame;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Lachlan on 28/05/2017.
 */

public class EndActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        String res = getIntent().getExtras().getString("R");
        final String data = getIntent().getExtras().getString("Q");
        TextView txtRes = (TextView) findViewById(R.id.txtRes);
        Button btnWeb = (Button) findViewById(R.id.btnWeb);
        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnWeb.setText("Google - " + data);



        if(res.equals("0")) {
            txtRes.setText("Incorrect! :(");
        }else {
            txtRes.setText("Correct!");
        }

        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url  = "https://www.google.com/search?q=" + data;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context(), MainActivity.class);
                startActivity(i);
                System.gc();
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        System.gc();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            System.gc();
            finish();
            return true;
        } else {
            return false;
        }
    }
    Context context(){
        return this;
    }
}