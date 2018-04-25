package kr.or.dgit.it.db_study;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String[] activityLists;
    private String[] titles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityLists = getResources().getStringArray(R.array.activityList);
        titles = getResources().getStringArray(R.array.titles);
    }

    public void mOnClick(View view) {
        if(view.getId()==R.id.mBtn01){  start(0);        }
        if(view.getId()==R.id.mBtn02){  start(1);        }
        if(view.getId()==R.id.mBtn03){  start(2);        }


    }

    private void start(int idx) {
        Intent intent = new Intent();
        intent.setClassName(getPackageName(), getPackageName()+"."+activityLists[idx]);
        intent.putExtra("title",  titles[idx]);
        startActivity(intent);
    }
}
