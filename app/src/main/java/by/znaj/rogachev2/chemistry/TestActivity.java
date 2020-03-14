package by.znaj.rogachev2.chemistry;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.SubscriptSpan;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestActivity extends AppCompatActivity {

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor cursorOxides;
    Cursor cursorAnswers;

    Button but1;
    Button but2;
    Button closeTest;
    Button againTest;

    LinearLayout type1;
    LinearLayout header;
    LinearLayout reslayout;
    LinearLayout table;
    TextView textres1;
    TextView name;
    TextView textres2;
    TextView textQuestion;



    int countQuestions = 0;
    int totalQuestions = 10;
    int correctAnswers = 0;

    int flag1 = 0;
    int flag2 = 0;

    long oxideId;

    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //getSupportActionBar().setTitle(R.string.app_name_short);
        getSupportActionBar().show();

        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        photoView.setImageResource(R.drawable.table);

        textQuestion = (TextView) findViewById(R.id.textQuestion);
        textres1 = (TextView) findViewById(R.id.textres1);
        textres2 = (TextView) findViewById(R.id.textres2);
        name = (TextView) findViewById(R.id.name);
        but1 = (Button) findViewById(R.id.but1);
        but2 = (Button) findViewById(R.id.but2);
        againTest = (Button) findViewById(R.id.againTest);
        closeTest = (Button) findViewById(R.id.closeTest);
        type1 = (LinearLayout) findViewById(R.id.type1);
        header = (LinearLayout) findViewById(R.id.header);
        table = (LinearLayout) findViewById(R.id.table);

        but1.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        but2.setBackgroundColor(getResources().getColor(R.color.colorGrey));

        reslayout = (LinearLayout) findViewById(R.id.reslayout);
        reslayout.setVisibility(View.GONE);

        handler = new Handler();

        sqlHelper = new DatabaseHelper(this);
        sqlHelper.createDatabase();
        db = sqlHelper.open();

        cursorOxides = db.rawQuery("select * from " + DatabaseHelper.TABLE_OXIDES + " ORDER BY RANDOM() LIMIT 10", null);

        if (cursorOxides.getCount() != 0 && cursorOxides.getCount() == 10) {
            cursorOxides.moveToFirst();
            nextQuestion();
        } else {
            textQuestion.setText("Нет вопросов или их недостаточно");
        }

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag1 == 1) {
                    but1.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    disableButtons();
                    correctAnswers++;
                } else {
                    but1.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    colorButtonGreen();
                    disableButtons();
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (countQuestions < totalQuestions) {
                            cursorOxides.moveToNext();
                            nextQuestion();
                        } else {
                            showResults();
                        }
                    }
                }, 1000);
            }
        });
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag2 == 1) {
                    but2.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    disableButtons();
                    correctAnswers++;
                } else {
                    but2.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    colorButtonGreen();
                    disableButtons();
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (countQuestions < totalQuestions) {
                            cursorOxides.moveToNext();
                            nextQuestion();
                        } else {
                            showResults();
                        }
                    }
                }, 1000);
            }
        });

        closeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });

        againTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TestActivity.class);
                startActivity(intent);
            }
        });
    }

    public void colorButtonGreen() {
        if (flag1 == 1) {
            but1.setBackgroundColor(getResources().getColor(R.color.colorGreen));
        }
        if (flag2 == 1) {
            but2.setBackgroundColor(getResources().getColor(R.color.colorGreen));
        }
    }

    public void nextQuestion() {
        flag1 = 0;
        flag2 = 0;

        textQuestion.setText(makeStringLikeFormula(cursorOxides.getString(1)), TextView.BufferType.SPANNABLE);
        oxideId = cursorOxides.getLong(0);

        cursorAnswers = db.rawQuery("select * from " + DatabaseHelper.TABLE_OXIDES_ANSWERS + " where " + DatabaseHelper.COLUMN_ID_OXIDE + "=? ORDER BY RANDOM()", new String[]{String.valueOf(oxideId)});

        but1.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        but2.setBackgroundColor(getResources().getColor(R.color.colorGrey));

        cursorAnswers.moveToPosition(0);
        if (cursorAnswers.getInt(3) == 1) {
            flag1 = 1;
        } else {
            flag1 = 0;
        }
        but1.setText(cursorAnswers.getString(2));

        cursorAnswers.moveToPosition(1);
        if (cursorAnswers.getInt(3) == 1) {
            flag2 = 1;
        } else {
            flag2 = 0;
        }
        but2.setText(cursorAnswers.getString(2));

        countQuestions++;

        enableButtons();
    }

    public void disableButtons() {
        but1.setClickable(false);
        but2.setClickable(false);
    }

    public void enableButtons() {
        but1.setClickable(true);
        but2.setClickable(true);
    }

    public void showResults() {
        reslayout.setVisibility(View.VISIBLE);
        type1.setVisibility(View.GONE);
        header.setVisibility(View.GONE);
        name.setVisibility(View.GONE);
        table.setVisibility(View.GONE);
        textres1.setText("Результат: " + Integer.toString(correctAnswers) + " из 10");
        textres2.setText("Поздравляю!");
    }

    private void goHome() {
        db.close();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void onDestroy() {
        super.onDestroy();
        try {
            db.close();
            cursorOxides.close();
            cursorAnswers.close();
        } catch (Exception ex) {

        }
    }

    private static CharSequence makeStringLikeFormula(String str) {
        if (str == null) return "";
        final SpannableString spannable = new SpannableString(str);
        final Matcher matcher = Pattern.compile("\\d+").matcher(str);
        while (matcher.find()) {
            spannable.setSpan(new SubscriptSpan(), matcher.start(), matcher.end(), 0);
        }
        return spannable;
    }

}
