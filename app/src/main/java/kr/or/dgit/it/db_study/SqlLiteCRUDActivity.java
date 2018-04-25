package kr.or.dgit.it.db_study;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import kr.or.dgit.it.db_study.Database.WordDbHelper;

public class SqlLiteCRUDActivity extends AppCompatActivity {
    private static String Table_NAME = "dic";

    WordDbHelper mHelper;
    TextView mTextView;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_lite_crud);
        setTitle(getIntent().getStringExtra("title"));

        mTextView = findViewById(R.id.tvResult);
        mHelper = WordDbHelper.getInstance(this);
        db = mHelper.getWritableDatabase();

    }

    @Override
    protected void onPause() {
        super.onPause();
        db.close();
    }

    public void mInsertClick(View view) {
        Log.d(WordDbHelper.TAG, "mInsertClick: ");
        ContentValues row = new ContentValues();
        row.put("eng","boy");
        row.put("han","머스마");
        db.insert(Table_NAME, null, row);
        mTextView.setText("insert Success");
    }

    public void mDeleteClick(View view) {
        db.delete(Table_NAME,null,null);
        mTextView.setText("Delete Success");
    }

    public void mUpdateClick(View view) {
        ContentValues row = new ContentValues();
        row.put("han","소년");
        db.update(Table_NAME, row,"eng=?",new String[]{"boy"});
        mTextView.setText("Update Success");
    }

    public void mSelectClick(View view) {
        //select eng, han from dic
        Cursor cursor = db.query(Table_NAME, new String[]{"eng", "han"}, null,null,null,null,null);
        StringBuffer sb =  new StringBuffer();
        while(cursor.moveToNext()){
            sb.append(String.format("%s = %s%n", cursor.getString(0),cursor.getString(1)));
        }
        if(sb.length()==0){
            mTextView.setText("Empty Set");

        }else{
            mTextView.setText(sb.toString());
        }
        cursor.close();
    }
}
