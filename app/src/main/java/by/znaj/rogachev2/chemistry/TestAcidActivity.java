package by.znaj.rogachev2.chemistry;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.SubscriptSpan;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestAcidActivity extends AppCompatActivity {

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor cursorAcids;
    Cursor cursorAnswers;

    Button but1;
    Button but2;
    Button val1;
    Button val2;
    Button val3;
    Button ans;
    Button closeTest;
    Button againTest;

    LinearLayout type1;
    LinearLayout header;
    LinearLayout reslayout;
    LinearLayout vals;
    LinearLayout valText;
    LinearLayout table;
    LinearLayout answer;
    TextView name;
    TextView textres1;
    TextView textres2;
    TextView textQuestion;


    int countQuestions = 0;
    int totalQuestions = 10;
    int correctAnswers = 0;

    int flag1 = 0;
    int flag2 = 0;

    int check1 = 0;
    int check2 = 0;

    int flagval = 0;
    int correctVal = 0;

    long acidId;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_acid);

        //getSupportActionBar().setTitle(R.string.app_name_short);
        getSupportActionBar().show();

        textQuestion = (TextView) findViewById(R.id.textQuestion);
        textres1 = (TextView) findViewById(R.id.textres1);
        textres2 = (TextView) findViewById(R.id.textres2);
        but1 = (Button) findViewById(R.id.but1);
        but2 = (Button) findViewById(R.id.but2);
        val1 = (Button) findViewById(R.id.val1);
        val2 = (Button) findViewById(R.id.val2);
        val3 = (Button) findViewById(R.id.val3);
        ans = (Button) findViewById(R.id.ans);
        againTest = (Button) findViewById(R.id.againTest);
        closeTest = (Button) findViewById(R.id.closeTest);
        type1 = (LinearLayout) findViewById(R.id.type1);
        header = (LinearLayout) findViewById(R.id.header);
        vals = (LinearLayout) findViewById(R.id.vals);
        valText = (LinearLayout) findViewById(R.id.valText);
        table = (LinearLayout) findViewById(R.id.table);
        answer = (LinearLayout) findViewById(R.id.answer);
        name = (TextView) findViewById(R.id.name);

        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        photoView.setImageResource(R.drawable.table);

        but1.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        but2.setBackgroundColor(getResources().getColor(R.color.colorGrey));

        reslayout = (LinearLayout) findViewById(R.id.reslayout);
        reslayout.setVisibility(View.GONE);

        handler = new Handler();

        sqlHelper = new DatabaseHelper(this);
        sqlHelper.createDatabase();
        db = sqlHelper.open();

        cursorAcids = db.rawQuery("select * from " + DatabaseHelper.TABLE_ACIDS + " ORDER BY RANDOM() LIMIT 10", null);

        if (cursorAcids.getCount() != 0 && cursorAcids.getCount() == 10) {
            cursorAcids.moveToFirst();
            nextQuestion();
        } else {
            textQuestion.setText("Нет вопросов или их недостаточно");
            //TODO:сделать убирание layouts
        }

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check1 == 0) {
                    check1 = 1;
                    but1.setBackgroundColor(getResources().getColor(R.color.colorHighlight));
                    check2 = 0;
                    but2.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                } else {
                    check1 = 0;
                    but1.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                    check2 = 1;
                    but2.setBackgroundColor(getResources().getColor(R.color.colorHighlight));
                }
            }
        });
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check2 == 0) {
                    check2 = 1;
                    but2.setBackgroundColor(getResources().getColor(R.color.colorHighlight));
                    check1 = 0;
                    but1.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                } else {
                    check2 = 0;
                    but2.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                    check1 = 1;
                    but1.setBackgroundColor(getResources().getColor(R.color.colorHighlight));
                }
            }
        });

        ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flagval == 0) return;
                if (check1 == 0 && check2 == 0) return;

                if (flag1 == check1 && flag2 == check2 && flagval == correctVal) {
                    correctAnswers++;
                    //Toast.makeText(getBaseContext(),"+",Toast.LENGTH_SHORT).show();
                }
                if (flag1 == 0 && check1 == 1) {
                    but1.setBackgroundColor(getResources().getColor(R.color.colorRed));
                }
                if (flag1 == 1 && check1 == 1) {
                    but1.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                }

                if (flag2 == 0 && check2 == 1) {
                    but2.setBackgroundColor(getResources().getColor(R.color.colorRed));
                }
                if (flag2 == 1 && check2 == 1) {
                    but2.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                }

                if (correctVal == flagval){
                    if (flagval == 1) val1.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    if (flagval == 2) val2.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    if (flagval == 3) val3.setBackgroundColor(getResources().getColor(R.color.colorGreen));

                } else {
                    if (flagval == 1) val1.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    if (flagval == 2) val2.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    if (flagval == 3) val3.setBackgroundColor(getResources().getColor(R.color.colorRed));
                }

                disableButtons();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (countQuestions < totalQuestions) {
                            cursorAcids.moveToNext();
                            nextQuestion();
                        } else {
                            showResults();
                        }
                    }
                }, 1000);
            }
        });

        val1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorValButtons();
                val1.setBackgroundColor(getResources().getColor(R.color.colorHighlight));
                flagval = 1;
            }
        });

        val2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorValButtons();
                val2.setBackgroundColor(getResources().getColor(R.color.colorHighlight));
                flagval = 2;
            }
        });

        val3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorValButtons();
                val3.setBackgroundColor(getResources().getColor(R.color.colorHighlight));
                flagval = 3;
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
                Intent intent = new Intent(getApplicationContext(), TestAcidActivity.class);
                startActivity(intent);
            }
        });
    }

    public void colorValButtons() {
        val1.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        val2.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        val3.setBackgroundColor(getResources().getColor(R.color.colorGrey));
    }

    public void nextQuestion() {
        flag1 = 0;
        flag2 = 0;

        check1 = 0;
        check2 = 0;

        flagval = 0;

        textQuestion.setText(makeStringLikeFormula(cursorAcids.getString(1)), TextView.BufferType.SPANNABLE);
        acidId = cursorAcids.getLong(0);
        correctVal = cursorAcids.getInt(2);

        cursorAnswers = db.rawQuery("select * from " + DatabaseHelper.TABLE_ACIDS_ANSWERS + " where " + DatabaseHelper.COLUMN_ID_ACID + "=? ORDER BY RANDOM()", new String[]{String.valueOf(acidId)});

        but1.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        but2.setBackgroundColor(getResources().getColor(R.color.colorGrey));

        val1.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        val2.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        val3.setBackgroundColor(getResources().getColor(R.color.colorGrey));


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

        val1.setClickable(false);
        val2.setClickable(false);
        val3.setClickable(false);

        ans.setClickable(false);
    }

    public void enableButtons() {
        but1.setClickable(true);
        but2.setClickable(true);

        val1.setClickable(true);
        val2.setClickable(true);
        val3.setClickable(true);

        ans.setClickable(true);
    }

    public void showResults() {
        reslayout.setVisibility(View.VISIBLE);
        vals.setVisibility(View.GONE);
        valText.setVisibility(View.GONE);
        type1.setVisibility(View.GONE);
        header.setVisibility(View.GONE);
        table.setVisibility(View.GONE);
        answer.setVisibility(View.GONE);
        name.setVisibility(View.GONE);
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
            cursorAcids.close();
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
