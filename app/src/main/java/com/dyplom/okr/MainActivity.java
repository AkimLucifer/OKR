package com.dyplom.okr;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация элементов
        EditText inputA = findViewById(R.id.inputA);
        EditText inputB = findViewById(R.id.inputB);
        EditText inputAngle = findViewById(R.id.inputAngle);
        Button calculateButton = findViewById(R.id.calculateButton);
        TextView result = findViewById(R.id.result);

        // Обработчик нажатия на кнопку
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получаем введенные данные
                String aText = inputA.getText().toString();
                String bText = inputB.getText().toString();
                String angleText = inputAngle.getText().toString();

                // Проверяем, что все поля заполнены
                if (aText.isEmpty() || bText.isEmpty() || angleText.isEmpty()) {
                    showToast("Все поля должны быть заполнены");
                    return;
                }

                // Проверяем, что введенные значения можно преобразовать в числа
                double a = parseDouble(aText);
                double b = parseDouble(bText);
                double angle = parseDouble(angleText);

                if (a == -1 || b == -1 || angle == -1) {
                    showToast("Введите корректные числовые значения");
                    return;
                }

                // Проверка угла
                if (angle <= 0 || angle >= 180) {
                    showToast("Угол должен быть в пределах от 0 до 180");
                    return;
                }

                // Рассчитываем площадь
                double angleInRadians = Math.toRadians(angle);
                double area = a * b * Math.sin(angleInRadians);

                // Выводим результат
                result.setText(String.format("Результат: %.2f", area));
            }
        });
    }

    // Метод для обработки ошибок при преобразовании строки в число
    private double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Метод для отображения Toast-сообщений
    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
