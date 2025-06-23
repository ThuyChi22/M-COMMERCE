package connector;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.*;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SQLiteDatabase.db";
    private static final int DATABASE_VERSION = 1;
    private static String DATABASE_PATH = "";
    private final Context context;
    private SQLiteDatabase database;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        DATABASE_PATH = context.getApplicationInfo().dataDir + "/databases/";
        copyDatabaseIfNeeded();
    }

    private void copyDatabaseIfNeeded() {
        File dbFile = new File(DATABASE_PATH + DATABASE_NAME);
        if (!dbFile.exists()) {
            this.getReadableDatabase(); // Tạo thư mục nếu chưa có
            close();
            try {
                copyDatabaseFromAssets();
                Log.d("Database", "Database copied successfully");
            } catch (IOException e) {
                throw new RuntimeException("Error copying database: " + e.getMessage());
            }
        }
    }

    private void copyDatabaseFromAssets() throws IOException {
        InputStream input = context.getAssets().open(DATABASE_NAME);
        String outFileName = DATABASE_PATH + DATABASE_NAME;
        OutputStream output = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }

        output.flush();
        output.close();
        input.close();
    }

    @Override
    public synchronized void close() {
        if (database != null) database.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Không cần vì bạn đã có sẵn database trong assets
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Có thể xử lý nâng cấp nếu cần
    }

    public SQLiteDatabase openDatabase() {
        String path = DATABASE_PATH + DATABASE_NAME;
        database = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        return database;
    }
}
