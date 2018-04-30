package kr.or.dgit.it.datapersistenceapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SharedPrefActivity extends AppCompatActivity {
    EditText etId;
    EditText etPwd;
    SharedPreferences mActivitySharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_pref);
        setTitle(getIntent().getStringExtra("title"));

        etId = findViewById(R.id.loginId);
        etPwd = findViewById(R.id.loginPwd);

        mActivitySharedPref = getPreferences(MODE_PRIVATE);

        loadSharedPref();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveSharedPref();
    }

    private void saveSharedPref() {
        SharedPreferences.Editor editor = mActivitySharedPref.edit();
        String id = etId.getText().toString();
        String pwd = etPwd.getText().toString();

        editor.putString("id",id);
        editor.putString("pwd", pwd);

        editor.commit();
    }

    private void loadSharedPref() {
        etId.setText(mActivitySharedPref.getString("id","저장된 아이디가 없음"));
        etPwd.setText(mActivitySharedPref.getString("pwd","저장된 비밀번호가 없음"));
    }

    public void mLoginClick(View view) {
    }
}
