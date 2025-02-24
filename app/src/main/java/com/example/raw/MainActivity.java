package com.example.raw;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    TextView tv1;
    EditText eD1;
    private final String FILENAME = "text.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weddings();

    }

    public void weddings()
    {
        tv1 = findViewById(R.id.tv1);
        eD1 = findViewById(R.id.eD1);
    }


    public void btn_raw_file(View view) {
        String fileName = FILENAME.substring(0,FILENAME.length()-4);
        int resourceId = this.getResources().getIdentifier(fileName,
                "raw",this.getPackageName());

        try
        {
            InputStream iS = this.getResources().openRawResource(resourceId);
            InputStreamReader iSR = new InputStreamReader(iS);
            BufferedReader bR = new BufferedReader(iSR);
            StringBuffer sB = new StringBuffer();
            String line = bR.readLine();
            while (line != null) {
                sB.append(line + '\n');
                line = bR.readLine();
            }
            bR.close();
            iSR.close();
            iS.close();
            tv1.setText(sB.toString());
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void brn_text(View view)
    {
        String data = eD1.getText().toString();
        tv1.setText(data);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected (MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.it2)
        {
            Intent si = new Intent(this, MainActivity2.class);
            startActivity(si);
        }
        return true;
    }

}