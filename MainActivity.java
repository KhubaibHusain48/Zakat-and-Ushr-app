package com.example.urdu;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;

public class MainActivity extends AppCompatActivity {

    TextView t1, t2, t3;
    EditText am1, am2;
    Button b1, b2, b3;
    CardView c1, c2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        am1 = findViewById(R.id.amount);
        am2 = findViewById(R.id.amountUshr);
        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);
        b3 = findViewById(R.id.btn3);
        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);

        TextView urduText = findViewById(R.id.urduText);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.jameel_noori_nastaleeq);
        urduText.setTypeface(typeface);
        t1.setTypeface(typeface);
        t2.setTypeface(typeface);
        t3.setTypeface(typeface);

        b1.setOnClickListener(v -> {
            String input = am1.getText().toString().trim();

            if (input.isEmpty()) {
                t1.setText("براہ کرم رقم درج کریں");
                return;
            }

            try {
                double amount = Double.parseDouble(input);
                if (amount <= 0) {
                    t1.setText("رقم صفر یا منفی نہیں ہوسکتی");
                    return;
                }

                double zakat = 0.025 * amount;
                String formattedZakat = String.format("%.2f", zakat);
                t1.setText("آپ پر فرض زکٰوة ہے " + formattedZakat + " روپیہ");

            } catch (NumberFormatException e) {
                t1.setText("درست رقم درج کریں");
            }
        });

        b2.setOnClickListener(v -> {
            String input = am2.getText().toString().trim();

            if (input.isEmpty()) {
                t2.setText("براہ کرم رقم درج کریں");
                t3.setText("");
                return;
            }

            try {
                double amount = Double.parseDouble(input);
                if (amount <= 0) {
                    t2.setText("رقم صفر یا منفی نہیں ہوسکتی");
                    t3.setText("");
                    return;
                }

                double NUshr = 0.10 * amount;
                double AUshr = 0.05 * amount;
                t2.setText("نہری پانی پر عشر ہے " + String.format("%.2f", NUshr) + " روپیہ");
                t3.setText("دیگر ذرائع سے پانی پر عشر ہے " + String.format("%.2f", AUshr) + " روپیہ");

            } catch (NumberFormatException e) {
                t2.setText("درست رقم درج کریں");
                t3.setText("");
            }
        });

        b3.setOnClickListener(v -> {
            am1.setText("");
            am2.setText("");
            t1.setText("");
            t2.setText("");
            t3.setText("");
        });
    }
    @SuppressLint("GestureBackNavigation")
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.a)
                .setTitle("نکلیے")
                .setMessage("کیا آپ ایپ بند کرنا چاہتے ہیں؟")
                .setPositiveButton("جی ہاں", (dialog, which) -> super.onBackPressed())
                .setNegativeButton("نہیں", null)
                .show();
    }
}
