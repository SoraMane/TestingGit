package aacci.sourabh.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int i = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(;i<10;i++){
            i++;
        }
    }
}
