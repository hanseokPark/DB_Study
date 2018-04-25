package kr.or.dgit.it.db_study;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import kr.or.dgit.it.db_study.Database.WordDbHelper;
import kr.or.dgit.it.db_study.dao.WordDbDao;
import kr.or.dgit.it.db_study.dto.Dic;

public class WordCursorActivity extends AppCompatActivity {
    private EditText inertEng;
    private EditText insertHan;
    private EditText updateNum;
    private EditText updateEng;
    private EditText updateHan;
    private EditText deleteNum;

    ListView listView;
    WordDbDao dao;
    WordCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_cursor);
        setTitle(getIntent().getStringExtra("title"));

        inertEng = findViewById(R.id.ins_eng);
        insertHan = findViewById(R.id.ins_han);
        updateNum = findViewById(R.id.upate_num);
        updateEng = findViewById(R.id.upate_eng);
        updateHan = findViewById(R.id.upate_han);
        deleteNum = findViewById(R.id.del_num);

        listView = findViewById(R.id.listView_word);
        dao = new WordDbDao(this);
        dao.open();

        Cursor cursor = dao.selectDicAll();
        adapter = new WordCursorAdapter(this, R.layout.dic_row, cursor,
                WordDbDao.SELECTION,
                new int[]{R.id.dic_id, R.id.dic_eng, R.id.dic_han},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(adapter);
    }

    public void mInsertClick(View view) {
        String newEng = inertEng.getText().toString();
        String newHan = insertHan.getText().toString();
        Dic newDic = new Dic(newEng, newHan);
        dao.insertDic(newDic);
        adapter.changeCursor(dao.selectDicAll());
        inertEng.setText("");
        insertHan.setText("");
    }
    public void mupdateClick(View view) {
        int upId = Integer.parseInt(updateNum.getText().toString());
        String upEng = updateEng.getText().toString();
        String upHan = updateHan.getText().toString();
        Dic upDic = new Dic(upId, upEng, upHan);
        dao.updateDic(upDic);
        adapter.changeCursor(dao.selectDicAll());
        updateNum.setText("");
        updateEng.setText("");
        updateHan.setText("");
    }

    public void mDeleteClick(View view) {
        int delId = Integer.parseInt(deleteNum.getText().toString());
        Dic delDic = new Dic(delId);
        dao.deleteDic(delDic);
        adapter.changeCursor(dao.selectDicAll());
        deleteNum.setText("");
    }




    class WordCursorAdapter extends SimpleCursorAdapter{
        LayoutInflater mlnflter;
        int mLayout;
        public WordCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
            super(context, layout, c, from, to, flags);
            mlnflter = LayoutInflater.from(context);
            mLayout = layout;
        }

        class ViewHolder{
            TextView tvId;
            TextView tvEng;
            TextView tvHan;
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            View item_row_layout = mlnflter.inflate(mLayout, null);
            ViewHolder viewHolder = new ViewHolder();

            viewHolder.tvId = item_row_layout.findViewById(R.id.dic_id);
            viewHolder.tvEng = item_row_layout.findViewById(R.id.dic_eng);
            viewHolder.tvHan = item_row_layout.findViewById(R.id.dic_han);
            item_row_layout.setTag(viewHolder);
            return item_row_layout;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            viewHolder.tvId.setText(cursor.getInt(0)+"");
            viewHolder.tvEng.setText(cursor.getString(1));
            viewHolder.tvHan.setText(cursor.getString(2));
        }
    }
}
