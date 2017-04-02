package com.niko.br.ui.converter;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.niko.br.R;
import com.niko.br.databinding.ActivityConverterBinding;

public class ConverterActivity extends AppCompatActivity {

  ActivityConverterBinding binding;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_converter);
  }

    /* @Extra
    BM usd;
    @Extra
    BM eur;
    @Extra
    BM rub;

    @Extra
    String title;

    @ViewById
    TextView txtUAH;

    @ViewById
    TextView txtRUB;

    @ViewById
    TextView txtUSD;

    @ViewById
    TextView txtEUR;

    @ViewById
    EditText editCount;

    @ViewById
    AppCompatSpinner spinRate;

    @ViewById
    Toolbar toolbar;

    String spinSelectedRate;

    IConverter converter;

    String[] rate = new String[]{"USD", "EUR", "UAH", "RUB"};

    private ArrayAdapter<String> rateAdapter;

    @AfterViews
    void init() {

        initToolbar();

        initAdapter();

        initEditCount();

        converter = new BMConverter(usd, rub, eur);
    }

    private void initToolbar() {
        if (title != null && !title.equals(""))
            toolbar.setTitle("Конвертер валют -> " + title);
        else
            toolbar.setTitle("Конвертер валют");
        setSupportActionBar(toolbar);
    }

    private void initAdapter() {
        rateAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.selected_rate, rate);
        rateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinRate.setAdapter(rateAdapter);
        spinRate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinSelectedRate = adapterView.getSelectedItem().toString();
                convert(Float.parseFloat(editCount.getText().toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initEditCount() {
        editCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    editCount.setText("0");
                    convert(0);
                } else convert(Float.parseFloat(charSequence.toString()));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void convert(float count) {

        float usd = 0;
        float eur = 0;
        float uah = 0;
        float rub = 0;

        switch (spinSelectedRate) {
            case "USD":
                usd = converter.convertUsdIn(count, "USD");
                eur = converter.convertUsdIn(count, "EUR");
                uah = converter.convertUsdIn(count, "UAH");
                rub = converter.convertUsdIn(count, "RUB");
                break;
            case "EUR":
                usd = converter.convertEurIn(count, "USD");
                eur = converter.convertEurIn(count, "EUR");
                uah = converter.convertEurIn(count, "UAH");
                rub = converter.convertEurIn(count, "RUB");
                break;
            case "UAH":
                usd = converter.convertUahIn(count, "USD");
                eur = converter.convertUahIn(count, "EUR");
                uah = converter.convertUahIn(count, "UAH");
                rub = converter.convertUahIn(count, "RUB");
                break;
            case "RUB":
                usd = converter.convertRubIn(count, "USD");
                eur = converter.convertRubIn(count, "EUR");
                uah = converter.convertRubIn(count, "UAH");
                rub = converter.convertRubIn(count, "RUB");
                break;
        }

        txtUAH.setText(String.format(Locale.ENGLISH, "%.3f", uah));
        txtRUB.setText(String.format(Locale.ENGLISH, "%.3f", rub));
        txtUSD.setText(String.format(Locale.ENGLISH, "%.3f", usd));
        txtEUR.setText(String.format(Locale.ENGLISH, "%.3f", eur));
    }
*/

}
