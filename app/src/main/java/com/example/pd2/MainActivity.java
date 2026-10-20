package com.example.pd2;

import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final int METRIC_SENTENCES = 0;
    private static final int METRIC_WORDS = 1;
    private static final int METRIC_PUNCTUATION = 2;
    private static final int METRIC_NUMBERS = 3;

    private EditText etInputText;
    private Spinner spinnerMetric;
    private Button btnCalculate;
    private TextView tvResult;
    private TextMetrics textMetrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etInputText = findViewById(R.id.etInputText);
        spinnerMetric = findViewById(R.id.spinnerMetric);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);
        textMetrics = new TextMetrics();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.metric_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMetric.setAdapter(adapter);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateMetric();
            }
        });
    }

    private void calculateMetric() {
        String inputText = etInputText.getText().toString();

        if (inputText.trim().isEmpty()) {
            Toast.makeText(this, R.string.msg_empty_input, Toast.LENGTH_SHORT).show();
            return;
        }

        int resultValue;
        switch (spinnerMetric.getSelectedItemPosition()) {
            case METRIC_SENTENCES:
                resultValue = textMetrics.CountSentences(inputText);
                break;
            case METRIC_WORDS:
                resultValue = textMetrics.CountWords(inputText);
                break;
            case METRIC_PUNCTUATION:
                resultValue = textMetrics.CountPunctuation(inputText);
                break;
            case METRIC_NUMBERS:
            default:
                resultValue = textMetrics.CountNumbers(inputText);
                break;
        }

        String metricName = spinnerMetric.getSelectedItem().toString();
        tvResult.setText(getString(R.string.result_format, metricName, resultValue));
    }
}