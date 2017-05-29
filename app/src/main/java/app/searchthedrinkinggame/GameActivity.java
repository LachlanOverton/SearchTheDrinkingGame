package app.searchthedrinkinggame;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

/**
 * Created by Lachlan on 28/05/2017.
 */

public class GameActivity extends AppCompatActivity {
    public String result;
    String res;
    Ret ret = new Ret();
    String [] data;
    int x = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        Button btn4 = (Button) findViewById(R.id.btn4);
        TextView textView1 = (TextView) findViewById(R.id.txtQuestion);

        Random r = new Random();
        Integer x, rnum=0, tnum, numl;
        String resnum, f1="", f2="", f3="";
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);

        Intent intent = getIntent();
        data = intent.getExtras().getStringArray("Q");
        try {
            result = ret.execute(data).get();
            x = 0;
            textView1.setText(textView1.getText() + data[x]);
            x++;
            res = result.replace("t", "");
            resnum = res.replace(",", "");
            resnum = resnum.replace(" ","");
            rnum = Integer.parseInt(resnum);

            numl = resnum.length();
            switch (numl){
                case 2:
                    break;
                case 3:
                    numl = 10;
                    break;
                case 4:
                    numl = 10;
                    break;
                case 5:
                    numl = 100;
                    break;
                case 6:
                    numl = 1000;
                    break;
                case 7:
                    numl = 10000;
                    break;
                case 8:
                    numl = 100000;
                    break;
                case 9:
                    numl = 1000000;
                    break;
                case 10:
                    numl = 10000000;
                    break;
                case 11:
                    numl = 10000000;
                    break;
            }

            tnum = r.nextInt(rnum);
            tnum = ((tnum+5)/numl)*numl;
            f1 = numberFormat.format(tnum);
            tnum = r.nextInt(rnum / 2 + (numl));
            tnum = ((tnum+5)/numl)*numl;
            f2 = numberFormat.format(tnum);
            tnum = r.nextInt(rnum * 2);
            tnum = ((tnum+5)/numl)*numl;
            f3 = numberFormat.format(tnum);
        }catch (Exception e){
            e.printStackTrace();
        }


        x = r.nextInt(3);
        switch (x) {

            case 0:
                btn1.setText(btn1.getText() + res);
                btn2.setText(btn2.getText() + f1);
                btn3.setText(btn3.getText() + f2);
                btn4.setText(btn4.getText() + f3);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Correct();
                    }
                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Incorrect();
                    }
                });
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Incorrect();
                    }
                });
                btn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Incorrect();
                    }
                });
                break;
            case 1:
                btn1.setText(btn1.getText() + f2);
                btn2.setText(btn2.getText() + res);
                btn3.setText(btn3.getText() + f3);
                btn4.setText(btn4.getText() + f1);

                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Incorrect();
                    }
                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Correct();
                    }
                });
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Incorrect();
                    }
                });
                btn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Incorrect();
                    }
                });
                break;
            case 2:
                btn1.setText(btn1.getText() + f1);
                btn2.setText(btn2.getText() + f2);
                btn3.setText(btn3.getText() + res);
                btn4.setText(btn4.getText() + f3);

                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Incorrect();
                    }
                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Incorrect();
                    }
                });
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Correct();
                    }
                });
                btn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Incorrect();
                    }
                });
                break;
            case 3:
                btn1.setText(btn1.getText() + f3);
                btn2.setText(btn2.getText() + f2);
                btn3.setText(btn3.getText() + f1);
                btn4.setText(btn4.getText() + res);

                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Incorrect();
                    }
                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Incorrect();
                    }
                });
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Incorrect();
                    }
                });
                btn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Correct();
                    }
                });
                break;
        }
    }

    Context context() {
        return this;
    }

    void Correct() {
        Intent intent = new Intent(context(), EndActivity.class);
        intent.putExtra("Q", data[x]);
        intent.putExtra("R", "1");
        startActivity(intent);
    }

    void Incorrect() {
        Intent intent = new Intent(context(), EndActivity.class);
        intent.putExtra("Q", data[x]);
        intent.putExtra("R", "0");
        startActivity(intent);
    }

    private class Ret extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute()
        {
            result = "x";

            result.replace("x","y");
        }


        @Override
        protected String doInBackground(String... params) {

            try {
                String r;
                Document doc = Jsoup.connect("https://www.google.com/search?q=" + params[0].toString()).get();
                r = doc.getElementsByAttributeValue("id", "resultStats").text();
                r = r.substring(r.indexOf("t"), r.indexOf("r"));

                return r;
            }catch (Exception e){
                e.printStackTrace();
            }
            return "";
        }


        @Override
        protected void onPostExecute(String r) {

        }
    }

}
