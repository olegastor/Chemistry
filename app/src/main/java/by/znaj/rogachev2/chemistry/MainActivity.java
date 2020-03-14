package by.znaj.rogachev2.chemistry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    DatabaseHelper sqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().setTitle(R.string.app_name_short);
        getSupportActionBar().show();

        findViewById(R.id.beginButton).setOnClickListener(this);
        findViewById(R.id.acidButton).setOnClickListener(this);
        findViewById(R.id.acid2Button).setOnClickListener(this);
        findViewById(R.id.acid3Button).setOnClickListener(this);
        findViewById(R.id.saltButton).setOnClickListener(this);
        findViewById(R.id.baseButton).setOnClickListener(this);

        findViewById(R.id.testButton).setOnClickListener(this);

        sqlHelper = new DatabaseHelper(this);
        sqlHelper.createDatabase();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.beginButton: {
                Intent intent = new Intent(getApplicationContext(), TestActivity.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }
            case R.id.acid2Button: {
                Intent intent = new Intent(getApplicationContext(), TestAcid2Activity.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }
            case R.id.acidButton: {
                Intent intent = new Intent(getApplicationContext(), TestAcidActivity.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }
            case R.id.acid3Button: {
                Intent intent = new Intent(getApplicationContext(), TestAcid3Activity.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }
            case R.id.saltButton: {
                Intent intent = new Intent(getApplicationContext(), TestSaltActivity.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }
            case R.id.baseButton: {
                Intent intent = new Intent(getApplicationContext(), TestBaseActivity.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }
            case R.id.testButton: {
                Intent intent = new Intent(getApplicationContext(), TestResActivity.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }
        }
    }
}
