package kr.or.dgit.it.db_study.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.CursorAdapter;

import kr.or.dgit.it.db_study.Database.WordDbHelper;
import kr.or.dgit.it.db_study.dto.Dic;

public class WordDbDao {
    private String tableName = "dic";

    static final String COL_ID = "_id";
    static final String COL_ENG = "eng";
    static final String COL_HAN = "han";

    public static final String[] SELECTION = new String[]{COL_ID, COL_ENG, COL_HAN};

    WordDbHelper mDbHelper;
    SQLiteDatabase mDb;
    Context mContext;

    public WordDbDao(Context mContext) {
        this.mContext = mContext;
    }
    public void close(){
        if(mDbHelper != null){
            mDbHelper.close();
        }
    }
    public void open(){
        mDbHelper = WordDbHelper.getInstance(mContext);
        mDb = mDbHelper.getWritableDatabase();
    }
    public void insertDic(Dic item){
        ContentValues row= new ContentValues();
        row.put(COL_ENG, item.getEngStr());
        row.put(COL_HAN, item.getHanStr());
        mDb.insert(tableName, null, row);
    }

    public void deleteDic(Dic item){
        mDb.delete(tableName, COL_ID+"=?", new String[]{String.valueOf(item.getId())});
    }
    public void updateDic(Dic item){
        ContentValues row = new ContentValues();
        row.put(COL_ENG, item.getEngStr());
        row.put(COL_HAN, item.getHanStr());
        mDb.update(tableName, row, COL_ID+"=?", new String[]{String.valueOf(item.getId())});
    }
    public Cursor selectDicAll(){
        Cursor mCursor = mDb.query(tableName, SELECTION, null ,null,null,null,null,null);
        if(mCursor != null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }
}
