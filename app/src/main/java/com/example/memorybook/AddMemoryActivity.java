package com.example.memorybook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddMemoryActivity extends AppCompatActivity {
    private EditText memoryEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memory);

        memoryEditText = findViewById(R.id.memoryEditText);
    }

    public void onSaveButtonClick(View view) {
        String newMemory = memoryEditText.getText().toString();

        // Pass the new memory back to MainActivity
        Intent intent = new Intent();
        intent.putExtra("newMemory", newMemory);
        setResult(RESULT_OK, intent);
        finish();
    }
}
