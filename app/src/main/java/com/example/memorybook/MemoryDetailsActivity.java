package com.example.memorybook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MemoryDetailsActivity extends AppCompatActivity {
    private TextView memoryTextView;
    private int memoryPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_details);

        memoryTextView = findViewById(R.id.memoryTextView);

        // Retrieve the memory from the intent
        String memory = getIntent().getStringExtra("memory");
        memoryPosition = getIntent().getIntExtra("position", -1);
        memoryTextView.setText(memory);
    }

    public void onUpdateButtonClick(View view) {
        String updatedMemory = memoryTextView.getText().toString();

        // Pass the updated memory and its position back to MainActivity
        Intent intent = new Intent();
        intent.putExtra("updatedMemory", updatedMemory);
        intent.putExtra("position", memoryPosition);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onDeleteButtonClick(View view) {
        // Pass the memory position back to MainActivity for deletion
        Intent intent = new Intent();
        intent.putExtra("position", memoryPosition);
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}

