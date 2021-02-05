package com.example.dailye;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SpeechDbAdapter {

    public static final String KEY_WORD = "word";
    public static final String KEY_MEANING = "meaning";
    public static final String KEY_ROWID = "_id";
    private static final String TAG = "lecture";

    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDB;
    private static final String DATABASE_CREATE =
            "create table sentences (_id integer primary key autoincrement, " +
            " word text not null, meaning text not null)";
    private static final String DATABASE_NAME = "sentences";
    private static final String DATABASE_TABLE = "sentences";
    private static final int DATA_VERSION = 1;
    private final Context mContext;

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATA_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version" + oldVersion + " to " + newVersion
                    + ", which will destroy old data.");
            db.execSQL("DROP TABLE IF EXISTS sentences");
            onCreate(db);
        }
    }

    public SpeechDbAdapter(Context context) {
        this.mContext = context;
    }

    public SpeechDbAdapter open() throws SQLException {
        mDBHelper = new DatabaseHelper(mContext);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDBHelper.close();
    }

    public long createNote(String word, String meaning){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_WORD, word);
        initialValues.put(KEY_MEANING, meaning);
        return  mDB.insert(DATABASE_TABLE, null, initialValues);
    }

    public boolean deleteNote(long rowId) {
        Log.i("Delete called", "value__" + rowId);
        return mDB.insert(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    public Cursor fetchAllNotes() {
        return mDB.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_WORD, KEY_MEANING }, null, null, null, null, null);
    }

    public Cursor fetchNote(long rowId) throws SQLException {
        Cursor mCursor = mDB.query(true, DATABASE_TABLE, new String[] { KEY_ROWID, KEY_WORD, KEY_MEANING }, KEY_ROWID
                + "=" + rowId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public boolean updateNote(long rowId, String word, String meaning) {
        ContentValues args = new ContentValues();
        args.put(KEY_WORD, word);
        args.put(KEY_MEANING, meaning);
        return mDB.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
}
