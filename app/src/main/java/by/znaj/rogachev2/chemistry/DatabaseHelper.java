package by.znaj.rogachev2.chemistry;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_PATH;
    private static final String DATABASE_NAME = "chem.db";
    private static final int SCHEMA = 1;
    public static final String TABLE_OXIDES = "oxides";
    public static final String TABLE_ACIDS = "acids_res";
    public static final String TABLE_ACIDS2 = "acids";
    public static final String TABLE_ACIDS3 = "acid_res2";
    public static final String TABLE_SALT = "salt";
    public static final String TABLE_BASE = "base";
    public static final String TABLE_TEST = "test";
    public static final String TABLE_OXIDES_ANSWERS = "oxides_answers";
    public static final String TABLE_ACIDS_ANSWERS = "acids_res_answers";
    public static final String TABLE_ACIDS2_ANSWERS = "acids_answers";
    public static final String TABLE_ACIDS3_ANSWERS = "acid_res2_answers";
    public static final String TABLE_SALT_ANSWERS = "salt_answer";
    public static final String TABLE_BASE_ANSWERS = "base_answer";




    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_OXIDE = "name";

    public static final String COLUMN_ID_OXIDE = "id_oxide";
    public static final String COLUMN_ID_ACID = "id_acid";
    public static final String COLUMN_ID_SALT = "id_salt";
    public static final String COLUMN_ID_BASE = "id_base";
    public static final String COLUMN_ANSWER = "answer";
    public static final String COLUMN_ISCORRECT = "isCorrect";
    private Context myContext;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
        this.myContext = context;
        DB_PATH = context.getFilesDir().getPath() + DATABASE_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void createDatabase() {
        InputStream myInput = null;
        OutputStream myOutput = null;
        try {
            File file = new File(DB_PATH);
            if (!file.exists()) {
                this.getReadableDatabase();
                myInput = myContext.getAssets().open(DATABASE_NAME);
                String outFileName = DB_PATH;
                myOutput = new FileOutputStream(outFileName);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                myInput.close();
            }
        } catch (FileNotFoundException e) {
            Log.d("DatabaseHelper", e.getMessage());
        } catch (IOException e) {
            Log.d("DatabaseHelper", e.getMessage());
        }
    }

    public SQLiteDatabase open() throws SQLException {
        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }
}
