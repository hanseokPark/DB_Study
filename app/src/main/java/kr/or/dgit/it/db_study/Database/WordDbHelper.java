package kr.or.dgit.it.db_study.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class WordDbHelper extends SQLiteOpenHelper {
    public static final String TAG = WordDbHelper.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;
    private static final String DB_NAME = "BasicEngWord.db";

    private final Context context;
    private static WordDbHelper instance;

    public synchronized static WordDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new WordDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    private WordDbHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: ");
        applySqlFile(db, "BasicEngWord.sql");
        applySqlFile(db, "data.sql");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == DATABASE_VERSION) {
            db.execSQL("drop table dic");
            onCreate(db);
        }
    }

    private void applySqlFile(SQLiteDatabase db, String fileName) {
        BufferedReader reader = null;
        String filename = fileName;

        try {
            final InputStream inputStream = context.getAssets().open(filename);
            reader = new BufferedReader(new InputStreamReader(inputStream));
            final StringBuilder statement = new StringBuilder();
            for (String line; (line = reader.readLine()) != null; ) {
                if (!TextUtils.isEmpty(line) && !line.startsWith("--")) {//빈문자열 무시
                    statement.append(line.trim());
                }
                if (line.endsWith(";")) {
                    db.execSQL(statement.toString());
                    statement.setLength(0);
                }
            }
        } catch (IOException e) {
            Log.d(TAG, "applySqlFile: ");
            Log.e(TAG, "Could not apply SQL file -> " + e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.w(TAG, "Could not close reader", e);
                }
            }
        }
    }

}

