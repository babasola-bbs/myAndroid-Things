package com.scientist.languagepreferences;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView textView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.english) {
            setLanguage("English");
        }
        else if (item.getItemId() == R.id.spanish) {
            setLanguage("Spanish");
        }
        return true;
    }

    public void setLanguage(String language) {
        sharedPreferences.edit().putString("language", language).apply();
        textView.setText(language);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = this.getSharedPreferences("com.example.mike.languagepreferences", Context.MODE_PRIVATE);
        textView = (TextView) findViewById(R.id.textView);
        String language = sharedPreferences.getString("language", "");

        if (language == "") {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_btn_speak_now)
                    .setTitle("Choose a language")
                    .setMessage("Which language would you like?")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setLanguage("English");
                        }
                    })
                    .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setLanguage("Spanish");
                        }
                    })
                    .show();
        }
        else {
            textView.setText(language);
        }

//                        SQLiteDatabase
//        try {
//            SQLiteDatabase eventsDB = this.openOrCreateDatabase("Events", MODE_PRIVATE, null);
//            eventsDB.execSQL("CREATE TABLE IF NOT EXISTS events (event VARCHAR, year INT(4))");
//            eventsDB.execSQL("INSERT INTO events (event, year) VALUES ('End Of WW2', 1945)");
//            eventsDB.execSQL("INSERT INTO events (event, year) VALUES ('Wham split up', 1986)");
//
//            Cursor c = eventsDB.rawQuery("SELECT * FROM events", null);
//
//            int eventIndex = c.getColumnIndex("event");
//            int yearIndex = c.getColumnIndex("year");
//
//            c.moveToFirst();
//            while (c != null) {
//                Log.i("Results - event", c.getString(eventIndex));
//                Log.i("Results - year", Integer.toString(c.getInt(yearIndex)));
//                c.moveToNext();
//            }
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//        }


//                           WebView
//        WebView webView = (WebView) findViewById(R.id.webview);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.setWebViewClient(new WebViewClient());
//                    //webView.loadUrl("https://www.google.com");
//        webView.loadData("<html><body><h1>Hi There!</h1><p>This Is My Website</></body></html>>", "text/html", "UTF-8");
    }
}