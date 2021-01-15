package by.znaj.rogachev2.chemistry;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.SubscriptSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoefActivity extends AppCompatActivity implements View.OnClickListener {

    TextView text0, text1, text2, text3, text4;
    EditText edit1, edit2, edit3, edit4;
    Button butvar1, butvar2, butvar3, butvar4, buttonNext;

    int correctAnswer;
    int flag;
    int counter;

    ArrayList<TextView> arrayListTextView;
    ArrayList<EditText> arrayListEditText;
    ArrayList<Button> arrayButtons;
    ArrayList<Integer> arrayListCoefs;

    ArrayList<String> formulas;
    ArrayList<String> arrayCoefs;
    ArrayList<String> arrayTypes;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coef);

        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);

        text0 = findViewById(R.id.textView7);
        text1 = findViewById(R.id.textView);
        text2 = findViewById(R.id.textView2);
        text3 = findViewById(R.id.textView3);
        text4 = findViewById(R.id.textView4);

        edit1 = findViewById(R.id.editTextNumber1);
        edit2 = findViewById(R.id.editTextNumber2);
        edit3 = findViewById(R.id.editTextNumber3);
        edit4 = findViewById(R.id.editTextNumber7);

        butvar1 = findViewById(R.id.button);
        butvar2 = findViewById(R.id.button2);
        butvar3 = findViewById(R.id.button3);
        butvar4 = findViewById(R.id.button4);

        arrayListTextView = new ArrayList<>();
        arrayListTextView.clear();
        arrayListTextView.add(text0);
        arrayListTextView.add(text1);
        arrayListTextView.add(text2);
        arrayListTextView.add(text3);
        arrayListTextView.add(text4);

        arrayListEditText = new ArrayList<>();
        arrayListEditText.clear();
        arrayListEditText.add(edit1);
        arrayListEditText.add(edit2);
        arrayListEditText.add(edit3);
        arrayListEditText.add(edit4);

        arrayButtons = new ArrayList<>();
        arrayButtons.add(butvar1);
        arrayButtons.add(butvar2);
        arrayButtons.add(butvar3);
        arrayButtons.add(butvar4);

        arrayListCoefs = new ArrayList<>();
        arrayListCoefs.clear();

        formulas = new ArrayList<>();
        arrayCoefs = new ArrayList<>();
        arrayTypes = new ArrayList<>();

        formulas.clear();
        arrayCoefs.clear();
        arrayTypes.clear();


        formulas.add("!KBr + Cl2 → !KCl + Br2");
        arrayCoefs.add("2,2");
        arrayTypes.add("3");
        formulas.add("!Zn + O2 → !ZnO");
        arrayCoefs.add("2,2");
        arrayTypes.add("1");
        formulas.add("!Fe + !Cl2 → !FeCl3");
        arrayCoefs.add("2,3,2");
        arrayTypes.add("1");
        formulas.add("H2 + Cl2→!HCl");
        arrayCoefs.add("2");
        arrayTypes.add("1");
        formulas.add("!P + !O2 → !P2O5");
        arrayCoefs.add("4,5,2");
        arrayTypes.add("1");
        formulas.add("!N2 + !O2 → !N2O5");
        arrayCoefs.add("2,5,2");
        arrayTypes.add("1");
        formulas.add("Mg + !HCl → MgCl2 + H2");
        arrayCoefs.add("2");
        arrayTypes.add("3");
        formulas.add("!Al(OH)3 → Al2O3 + !H2O");
        arrayCoefs.add("2,3");
        arrayTypes.add("2");
        formulas.add("!HNO3→ !H2O + !NO2 + O2");
        arrayCoefs.add("4,2,4");
        arrayTypes.add("2");
        formulas.add("Fe2(SO4)3 + !NaOH → !Na2SO4 + !Fe(OH)3");
        arrayCoefs.add("6,3,2");
        arrayTypes.add("4");
        formulas.add("!CuOH → Cu2O + H2O");
        arrayCoefs.add("2");
        arrayTypes.add("2");
        formulas.add("!CuCI2 + !Al → !AlCl3 + !Cu");
        arrayCoefs.add("3,2,2,3");
        arrayTypes.add("3");
        formulas.add("!FeS2 + !O2 → !Fe2O3 + !SO2");
        arrayCoefs.add("4,11,2,8");
        arrayTypes.add("3");
        formulas.add("!NaOH → Na2O + H2O");
        arrayCoefs.add("2");
        arrayTypes.add("2");
        formulas.add("BaCl2 + !HNO3 → Ba(NO3)2 + !HCl");
        arrayCoefs.add("2,2");
        arrayTypes.add("4");
        formulas.add("!Al + !H2SO4 → Al2(SO4)3 + !H2");
        arrayCoefs.add("2,3,3");
        arrayTypes.add("3");
        formulas.add("CS2 + !O2 → CO2 + !SO2");
        arrayCoefs.add("4,2");
        arrayTypes.add("3");
        formulas.add("CaO + !HCl → H2O + CaCl2");
        arrayCoefs.add("2");
        arrayTypes.add("4");
        formulas.add("!H2 + O2 → !H2O");
        arrayCoefs.add("2,2");
        arrayTypes.add("1");
        formulas.add("Fe2O3 + !HCl → !FeCl3 + !H2O");
        arrayCoefs.add("6,2,3");
        arrayTypes.add("4");
        formulas.add("!Al(OH)3 → Al2O3 + !H2O");
        arrayCoefs.add("2,3");
        arrayTypes.add("2");
        formulas.add("!H2S + !O2 → !H2O + !SO2");
        arrayCoefs.add("2,3,2,2");
        arrayTypes.add("3");
        formulas.add("!Na + !H3PO4 → !Na3PO4 + !H2");
        arrayCoefs.add("6,2,2,3");
        arrayTypes.add("3");
        formulas.add("!Al + !O2 → !Al2O3");
        arrayCoefs.add("4,3,2");
        arrayTypes.add("1");
        formulas.add("!BaCl2 + Al2(SO4)3 → !AlCl3 + !BaSO4");
        arrayCoefs.add("3,2,3");
        arrayTypes.add("4");
        formulas.add("C2H8 + !O2 → !CO2 + !H2O");
        arrayCoefs.add("4,2,4");
        arrayTypes.add("3");
        formulas.add("!Fe(OH)3 → Fe2O3 + 3H2O");
        arrayCoefs.add("2");
        arrayTypes.add("2");
        formulas.add("!Al + !HNO3 → !Al(NO3)3 + !H2");
        arrayCoefs.add("2,6,2,3");
        arrayTypes.add("3");
        formulas.add("!S + !O2 → !SO3");
        arrayCoefs.add("2,3,2");
        arrayTypes.add("1");
        formulas.add("CuCl2 + !KOH → !KCl + Cu(OH)2");
        arrayCoefs.add("2,2");
        arrayTypes.add("4");
        formulas.add("!H3PO4 → !H2O + P2O5");
        arrayCoefs.add("2,3");
        arrayTypes.add("2");
        formulas.add("!NaOH + H2SO4 → !H2O + Na2SO4");
        arrayCoefs.add("2,2");
        arrayTypes.add("4");
        formulas.add("Al2O3 + !HCl → !AlCl3 + !H2O");
        arrayCoefs.add("6,2,3");
        arrayTypes.add("4");
        formulas.add("Na2O + H2O → !NaOH");
        arrayCoefs.add("2");
        arrayTypes.add("1");
        formulas.add("BaCl2 + K2CO3 → BaCO3 + !KCl");
        arrayCoefs.add("2");
        arrayTypes.add("4");
        formulas.add("!H2O2 → !H2O + O2");
        arrayCoefs.add("2,2");
        arrayTypes.add("2");
        formulas.add("!Na + O2→ !Na2O");
        arrayCoefs.add("4,2");
        arrayTypes.add("1");
        formulas.add("K2O + H2O→ !KOH");
        arrayCoefs.add("2");
        arrayTypes.add("1");
        formulas.add("Cu(OH)2 + H2SO4 → CuSO4 + !H2O");
        arrayCoefs.add("2");
        arrayTypes.add("4");
        formulas.add("Ca+ !H2O → Cа(OH)2 + H2");
        arrayCoefs.add("2");
        arrayTypes.add("3");
        formulas.add("P2O5 + !H2O → !H3PO4");
        arrayCoefs.add("3,2");
        arrayTypes.add("1");
        formulas.add("!CaO + P2O5 → Са3(PO4)2");
        arrayCoefs.add("3");
        arrayTypes.add("1");
        formulas.add("MgO + !HCl → MgCl2 + !H2O");
        arrayCoefs.add("2,2");
        arrayTypes.add("4");
        formulas.add("P2O5 + !NaOH → !Nа3PO4 + !H2O");
        arrayCoefs.add("6,2,3");
        arrayTypes.add("4");
        formulas.add("ZnO + !HNO3 → Zn(NO3)2 + H2O");
        arrayCoefs.add("2");
        arrayTypes.add("4");
        formulas.add("CaCO3 + !HCl → CaCl2 + CO2 + H2O");
        arrayCoefs.add("2");
        arrayTypes.add("4");
        formulas.add("!KOH + FeSO4 → Fe(OH)2 +K2SO4");
        arrayCoefs.add("2");
        arrayTypes.add("4");
        formulas.add("Ca(OH)2 + !HCl → CaCl2 + !H2O");
        arrayCoefs.add("2,2");
        arrayTypes.add("4");
        formulas.add("P2O5 + !Ca(OH)2 → Са3(PO4)2 + !H2O");
        arrayCoefs.add("3,3");
        arrayTypes.add("4");
        formulas.add("!KOH + H3PO4 → K3PO4 + !H2O");
        arrayCoefs.add("3,3");
        arrayTypes.add("4");
        formulas.add("!AgNO3 + FeCl2 → !AgCl + Fe(NO3)2");
        arrayCoefs.add("2,2");
        arrayTypes.add("4");
        formulas.add("ZnSO4 + !KOH → Zn(OH)2 + K2SO4");
        arrayCoefs.add("2");
        arrayTypes.add("4");
        formulas.add("Li2O + H2O → !LiOH");
        arrayCoefs.add("2");
        arrayTypes.add("1");
        formulas.add("!Li + O2 → !Li2O");
        arrayCoefs.add("4,2");
        arrayTypes.add("1");
        formulas.add("!LiOH + H2SO4  → Li2SO4 + !H2O");
        arrayCoefs.add("2,2");
        arrayTypes.add("4");
        formulas.add("CaCl2 + Na2CO3 → CaCO3 + !NaCl");
        arrayCoefs.add("2");
        arrayTypes.add("4");
        formulas.add("Ca + !HCl → CaCl2 + H2");
        arrayCoefs.add("2");
        arrayTypes.add("3");
        formulas.add("!Mg + O2 → !MgO");
        arrayCoefs.add("2,2");
        arrayTypes.add("1");
        formulas.add("!H2 + O2 → !H2O");
        arrayCoefs.add("2,2");
        arrayTypes.add("1");
        formulas.add("N2 + !H2 → !NH3");
        arrayCoefs.add("3,2");
        arrayTypes.add("1");
        formulas.add("CuBr2 + !NaOH → Cu(OH)2 + !NaBr");
        arrayCoefs.add("2,2");
        arrayTypes.add("4");
        formulas.add("!Mg + N2 → Mg3N2");
        arrayCoefs.add("3");
        arrayTypes.add("1");
        formulas.add("!Al + !S → Al2S3");
        arrayCoefs.add("2,3");
        arrayTypes.add("1");
        formulas.add("С + !H2 → CH4");
        arrayCoefs.add("2");
        arrayTypes.add("1");
        formulas.add("!Na + S → Na2S");
        arrayCoefs.add("2");
        arrayTypes.add("1");
        formulas.add("Н2 + Сl2 → !НСl");
        arrayCoefs.add("2");
        arrayTypes.add("1");
        formulas.add("!HCl+Na2CO3 → !NaCl + CO2 + H2O");
        arrayCoefs.add("2,2");
        arrayTypes.add("4");
        formulas.add("BaCl2 + Na2SO4 → BaSO4 + !NaCl");
        arrayCoefs.add("2");
        arrayTypes.add("4");
        formulas.add("AlCl3 + !NaOH → Al(OH)3 + !NaCl");
        arrayCoefs.add("3,3");
        arrayTypes.add("4");
        formulas.add("!Fe(OH)3 → Fe2O3 + !H2O");
        arrayCoefs.add("2,3");
        arrayTypes.add("3");
        formulas.add("P2O5 + !Na2O → !Nа3PO4");
        arrayCoefs.add("3,2");
        arrayTypes.add("1");
        formulas.add("Al2(SO4)3 + !Ba(NO3)2 → !Al(NO3)3 + !BaSO4");
        arrayCoefs.add("3,2,3");
        arrayTypes.add("4");
        formulas.add("!HNO3 + CuO → Cu(NO3)2 + H2O");
        arrayCoefs.add("2");
        arrayTypes.add("4");
        formulas.add("H3PO4 + !KOH → K3PO4+ !H2O");
        arrayCoefs.add("3,3");
        arrayTypes.add("4");
        formulas.add("H2SO3 + Ca(OH)2 → CaSO3 + !H2O");
        arrayCoefs.add("2");
        arrayTypes.add("4");
        formulas.add("!HNO3 + Al(OH)3 → Al(NO3)3 + !H2O");
        arrayCoefs.add("3,3");
        arrayTypes.add("4");
        formulas.add("!H3PO4 + !Ba(OH)2 → Bа3(PO4)2 + !H2O");
        arrayCoefs.add("2,3,6");
        arrayTypes.add("4");
        formulas.add("P2O5 + !K2O → !K3PO4");
        arrayCoefs.add("3,2");
        arrayTypes.add("1");
        formulas.add("!HCl + Fe(OH)2 → FeCl2 + !H2O");
        arrayCoefs.add("2,2");
        arrayTypes.add("4");
        formulas.add("!NaOH + SO3 → Na2SO4 + H2O");
        arrayCoefs.add("2");
        arrayTypes.add("4");
        formulas.add("!Al(OH)3 → Al2O3 + !H2O");
        arrayCoefs.add("2,3");
        arrayTypes.add("2");
        formulas.add("!Al(OH)3 + !H2SO4 → Al2(SO4)3 + !H2O");
        arrayCoefs.add("2,3,6");
        arrayTypes.add("4");
        formulas.add("Fe2O3 + !HCl → !FeCl3 + !H2O");
        arrayCoefs.add("6,2,3");
        arrayTypes.add("4");
        formulas.add("Fe2O3 + P2O5 → !FePO4");
        arrayCoefs.add("2");
        arrayTypes.add("1");
        formulas.add("!KOH + CO2 → K2CO3 + H2O");
        arrayCoefs.add("2");
        arrayTypes.add("4");
        formulas.add("!P + !O2 → !P2O5");
        arrayCoefs.add("4,5,2");
        arrayTypes.add("1");
        formulas.add("KClO4 → KCl + !O2");
        arrayCoefs.add("2");
        arrayTypes.add("2");
        formulas.add("CuO + !HCl → CuCl2 + H2O");
        arrayCoefs.add("2");
        arrayTypes.add("4");
        formulas.add("!Ca + O2 → !CaO");
        arrayCoefs.add("2,2");
        arrayTypes.add("1");
        formulas.add("Mg(OH)2 + !HNO3 → Mg(NO3)2 + !H2O");
        arrayCoefs.add("2,2");
        arrayTypes.add("4");
        formulas.add("!Li + N2 → !Li3N");
        arrayCoefs.add("6,2");
        arrayTypes.add("1");
        formulas.add("CO2 + !NaOH → Na2CO3 + H2O");
        arrayCoefs.add("2");
        arrayTypes.add("4");
        formulas.add("!Ca(OH)2 + !H3PO4 → Ca3(PO4)2 + !H2O");
        arrayCoefs.add("3,2,6");
        arrayTypes.add("4");
        formulas.add("!KMnO4 → K2MnO4 + MnO2 + O2");
        arrayCoefs.add("2");
        arrayTypes.add("2");
        formulas.add("!Cl2 + !O2 → !Cl2O7");
        arrayCoefs.add("2,7,2");
        arrayTypes.add("1");
        formulas.add("Fe(OH)3 + !HNO3 → Fe(NO3)3 + !H2O");
        arrayCoefs.add("3,3");
        arrayTypes.add("4");
        formulas.add("SO2 + !KOH → K2SO3 + H2O");
        arrayCoefs.add("2");
        arrayTypes.add("4");
        formulas.add("Al2(SO4)3 + !NaOH → !Al(OH)3 + !Na2SO4");
        arrayCoefs.add("6,2,3");
        arrayTypes.add("4");
        formulas.add("H2S + !NaOH → Na2S + !H2O");
        arrayCoefs.add("2,2");
        arrayTypes.add("4");
        formulas.add("!Br2 + !O2 → !Br2O5");
        arrayCoefs.add("2,5,2");
        arrayTypes.add("1");
        formulas.add("FeCl2 + !KOH → Fe(OH)2 + !KCl");
        arrayCoefs.add("2,2");
        arrayTypes.add("4");
        formulas.add("N2O5 + H2O → !HNO3");
        arrayCoefs.add("2");
        arrayTypes.add("1");
        formulas.add("Fe2O3 + !H2SO4 → Fe2(SO4)3 + !H2O");
        arrayCoefs.add("3,3");
        arrayTypes.add("4");
        formulas.add("!HgO → !Hg + O2");
        arrayCoefs.add("2,2");
        arrayTypes.add("2");
        formulas.add("!Ba(OH)2 + !H3PO4 → Ba3(PO4)2 + !H2O");
        arrayCoefs.add("3,2,6");
        arrayTypes.add("4");
        formulas.add("!Ca + !P → Ca3P2");
        arrayCoefs.add("3,2");
        arrayTypes.add("1");
        formulas.add("!CaCl2  + !H3PO4 → Ca3(PO4)2 + !HCl");
        arrayCoefs.add("3,2,6");
        arrayTypes.add("4");
        formulas.add("!KNO3 → !KNO2 + O2");
        arrayCoefs.add("2,2");
        arrayTypes.add("2");
        formulas.add("CaCl2 + !AgNO3 → !AgCl + Ca(NO3)2");
        arrayCoefs.add("2,2");
        arrayTypes.add("4");
        formulas.add("Fe + !HCl → FeCl2 + H2");
        arrayCoefs.add("2");
        arrayTypes.add("3");
        formulas.add("Fe2(SO4)3 + !KOH → !Fe(OH)3 + !K2SO4");
        arrayCoefs.add("6,2,3");
        arrayTypes.add("4");
        formulas.add("!NaOH + H3PO4 → Na3PO4 + !H2O");
        arrayCoefs.add("3,3");
        arrayTypes.add("4");
        formulas.add("!KClO3 → !KCl + !O2");
        arrayCoefs.add("2,2,3");
        arrayTypes.add("2");


        /*String s = "Fe2(SO4)3 + !NaOH → !Na2SO4 + !Fe(OH)3";
        String sCoef = "6,3,2";*/
        handler = new Handler();
        counter = 0;
        setEquation(formulas.get(counter), arrayCoefs.get(counter), arrayTypes.get(counter));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button: {
                resetButtons();
                butvar1.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                flag = 1;
                break;
            }
            case R.id.button2: {
                //findViewById(R.id.editTextNumber2).setVisibility(View.GONE);
                resetButtons();
                butvar2.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                flag = 2;
                break;
            }
            case R.id.button3: {
                //findViewById(R.id.editTextNumber2).setVisibility(View.GONE);
                resetButtons();
                butvar3.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                flag = 3;
                break;
            }
            case R.id.button4: {
                //findViewById(R.id.editTextNumber2).setVisibility(View.GONE);
                resetButtons();
                butvar4.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                flag = 4;
                break;
            }
            case R.id.button5: {
                boolean b = check();
                if (b) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (counter < formulas.size()) {
                                counter++;
                            } else {
                                counter = 0;
                            }
                            clearEdits();
                            resetButtons();
                            flag = 0;
                            setEquation(formulas.get(counter), arrayCoefs.get(counter), arrayTypes.get(counter));
                        }
                    }, 1000);
                }
                break;
            }
            case R.id.button7: {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
            }

        }

    }

    private void resetButtons() {
        findViewById(R.id.button).setBackgroundColor(getResources().getColor(R.color.colorTransparent));
        findViewById(R.id.button2).setBackgroundColor(getResources().getColor(R.color.colorTransparent));
        findViewById(R.id.button3).setBackgroundColor(getResources().getColor(R.color.colorTransparent));
        findViewById(R.id.button4).setBackgroundColor(getResources().getColor(R.color.colorTransparent));

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

    private void setEquation(String s_eq, String s_coef, String s_type) {
        String sr[] = s_eq.split("!", -1);
        String sc[] = s_coef.split(",");
        correctAnswer = Integer.valueOf(s_type);
        arrayListCoefs.clear();
        flag = 0;
        for (int i = 0; i < sc.length; i++) {
            arrayListCoefs.add(Integer.valueOf(sc[i]));
        }
        hideFormula();
        for (int i = 0; i < sr.length; i++) {
            arrayListTextView.get(i).setVisibility(View.VISIBLE);
            arrayListTextView.get(i).setText(makeStringLikeFormula(sr[i]), TextView.BufferType.SPANNABLE);
        }
        for (int i = 0; i < sc.length; i++) {
            arrayListEditText.get(i).setVisibility(View.VISIBLE);
        }
    }

    private void hideFormula() {
        for (EditText a : arrayListEditText) {
            a.setVisibility(View.GONE);
        }
        for (TextView a : arrayListTextView) {
            a.setVisibility(View.GONE);
        }
    }

    private boolean check() {
        int l = arrayListCoefs.size();
        //Log.d("ter1", String.valueOf(arrayListCoefs.size()));
        int a1, a2;
        boolean bool = true;
        for (int i = 0; i < l; i++) {
            a1 = (int) arrayListCoefs.get(i);
            try {
                a2 = Integer.valueOf(arrayListEditText.get(i).getText().toString());
            } catch (Exception e) {
                a2 = -1;
            }
            //Log.d("ter1", String.valueOf(arrayListEditText.get(i).getText().toString()));
            if (a1 == a2) {
                arrayListEditText.get(i).setBackgroundColor(getResources().getColor(R.color.colorGreen));
            } else {
                arrayListEditText.get(i).setBackgroundColor(getResources().getColor(R.color.colorRed));
                bool = false;
            }
        }
        if (correctAnswer == flag) {
            arrayButtons.get(flag - 1).setBackgroundColor(getResources().getColor(R.color.colorGreen));
        } else {
            if (flag == 0) {
                Toast.makeText(this, "Выберите тип реакции", Toast.LENGTH_SHORT).show();
            } else {
                arrayButtons.get(flag - 1).setBackgroundColor(getResources().getColor(R.color.colorRed));
                bool = false;
            }
        }
        return bool;
    }

    private void clearEdits() {
        for (EditText a : arrayListEditText) {
            a.setBackgroundColor(getResources().getColor(R.color.colorGrey));
            a.setText("");
        }
    }
}