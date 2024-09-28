package id.ac.binus.simplecalcapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;
    private String currentInput = "";
    private String operator = "";
    private double firstOperand = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);

        // Number buttons
        setNumberButtonListener(findViewById(R.id.btn0), "0");
        setNumberButtonListener(findViewById(R.id.btn1), "1");
        setNumberButtonListener(findViewById(R.id.btn2), "2");
        setNumberButtonListener(findViewById(R.id.btn3), "3");
        setNumberButtonListener(findViewById(R.id.btn4), "4");
        setNumberButtonListener(findViewById(R.id.btn5), "5");
        setNumberButtonListener(findViewById(R.id.btn6), "6");
        setNumberButtonListener(findViewById(R.id.btn7), "7");
        setNumberButtonListener(findViewById(R.id.btn8), "8");
        setNumberButtonListener(findViewById(R.id.btn9), "9");

        // Operation buttons
        findViewById(R.id.btnAdd).setOnClickListener(v -> handleOperator("+"));
        findViewById(R.id.btnSubtract).setOnClickListener(v -> handleOperator("-"));
        findViewById(R.id.btnMultiply).setOnClickListener(v -> handleOperator("*"));
        findViewById(R.id.btnDivide).setOnClickListener(v -> handleOperator("/"));

        // Clear button
        findViewById(R.id.btnClear).setOnClickListener(v -> clearInput());

        // Equal button
        findViewById(R.id.btnEqual).setOnClickListener(v -> calculateResult());
    }
    private void setNumberButtonListener(Button button, String number) {
        button.setOnClickListener(v -> appendToInput(number));
    }

    private void appendToInput(String value) {
        currentInput += value;
        tvResult.setText(currentInput);
    }

    private void handleOperator(String op) {
        if (!currentInput.isEmpty()) {
            firstOperand = Double.parseDouble(currentInput);
            operator = op;
            currentInput = "";
        }
    }

    private void calculateResult() {
        if (!currentInput.isEmpty()) {
            double secondOperand = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "*":
                    result = firstOperand * secondOperand;
                    break;
                case "/":
                    if (secondOperand != 0) {
                        result = firstOperand / secondOperand;
                    } else {
                        tvResult.setText("Error");
                        return;
                    }
                    break;
            }

            tvResult.setText(String.valueOf(result));
            currentInput = "";
        }
    }

    private void clearInput() {
        currentInput = "";
        firstOperand = 0;
        operator = "";
        tvResult.setText("0");
    }
}