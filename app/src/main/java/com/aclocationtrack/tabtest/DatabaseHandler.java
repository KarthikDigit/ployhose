package com.aclocationtrack.tabtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHandler";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "jobmanagement";

    private static final String TABLE_JOB = "job";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "job_name";
    private static final String KEY_DESCRIPTION = "description";

    private static final String TABLE_MATERIAL = "material";
    private static final String KEY_MATERIAL_ID = "id";
    private static final String KEY_ITEM = "item";
    private static final String KEY_QTY = "qty";
    private static final String KEY_COST = "cost";
    private static final String KEY_TAX = "tax";
    private static final String KEY_TOTAL = "total";

    private static final String CREATE_JOB_TABLE = "CREATE TABLE " + TABLE_JOB + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + KEY_NAME + " TEXT,"
            + KEY_DESCRIPTION + " TEXT" + ")";

    private static final String CREATE_MATERIAL_TABLE = "CREATE TABLE " + TABLE_MATERIAL + "("
            + KEY_MATERIAL_ID + " INTEGER," + KEY_ITEM + " TEXT,"
            + KEY_QTY + " TEXT," + KEY_COST + " TEXT," + KEY_TAX + " TEXT," + KEY_TOTAL + " TEXT" + ")";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(CREATE_JOB_TABLE);
        db.execSQL(CREATE_MATERIAL_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JOB);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATERIAL);

        // Create tables again
        onCreate(db);
    }


    void addJob(Job job) {
        SQLiteDatabase db = this.getWritableDatabase();

//        db.beginTransaction();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, job.getJob());
        values.put(KEY_DESCRIPTION, job.getDescription());

        // Inserting Row
        long id = db.insert(TABLE_JOB, null, values);

        addMaterial(id, job.getMaterialList(), db);

//        db.endTransaction();

        db.close(); // Closing database connection


    }

    boolean addJob(List<Job> jobs) {
        SQLiteDatabase db = this.getWritableDatabase();

//        db.beginTransaction();

        for (int i = 0; i < jobs.size(); i++) {

            Job job = jobs.get(i);

            ContentValues values = new ContentValues();
            values.put(KEY_NAME, job.getJob());
            values.put(KEY_DESCRIPTION, job.getDescription());

            // Inserting Row
            long id = db.insert(TABLE_JOB, null, values);

            addMaterial(id, job.getMaterialList(), db);
        }


//        db.endTransaction();

        db.close(); // Closing database connection


        return true;
    }


    // code to add the new contact
    private void addMaterial(long id, List<Material> materialsList, SQLiteDatabase db) {
//        SQLiteDatabase db = this.getWritableDatabase();

//        db.beginTransaction();

        for (int i = 0; i < materialsList.size(); i++) {

            Material material = materialsList.get(i);

            ContentValues values = new ContentValues();
            values.put(KEY_MATERIAL_ID, id);
            values.put(KEY_ITEM, material.getItem());
            values.put(KEY_QTY, material.getQty());
            values.put(KEY_COST, material.getCost());
            values.put(KEY_TAX, material.getTax());
            values.put(KEY_TOTAL, material.getTotal());
            // Inserting Row
            long mId = db.insert(TABLE_MATERIAL, null, values);

            Log.e(TAG, "addMaterial: " + mId);

        }

//        db.endTransaction();

//        db.close();

    }


    // code to get all contacts in a list view
    public List<Job> getAllJobs() {
        List<Job> contactList = new ArrayList<Job>();

        //        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_JOB;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        Log.e(TAG, "getAllJobs: " + cursor.getCount());
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Job job = new Job();
//                contact.setID(Integer.parseInt(cursor.getString(0)));
                job.setJob(cursor.getString(1));
                job.setDescription(cursor.getString(2));

                long id = cursor.getInt(0);

                List<Material> materialList = getAllMaterialsById(id);

                job.setMaterialList(materialList);

                // Adding contact to list
                contactList.add(job);
            } while (cursor.moveToNext());
        }

        cursor.close();

        // return contact list
        return contactList;
    }


    // code to get all contacts in a list view
    public List<Material> getAllMaterialsById(long id) {
        List<Material> materialList = new ArrayList<Material>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MATERIAL + " WHERE " + KEY_MATERIAL_ID + "=" + id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Material material = new Material();
//                contact.setID(Integer.parseInt(cursor.getString(0)));
                material.setItem(cursor.getString(1));
                material.setQty(cursor.getString(2));
                material.setCost(cursor.getString(3));
                material.setTax(cursor.getString(4));
                material.setTotal(cursor.getString(5));
                // Adding contact to list
                materialList.add(material);
            } while (cursor.moveToNext());
        }

        // return contact list
        return materialList;
    }

}
