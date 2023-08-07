package com.example.memorybook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> memoriesList;
    private ArrayAdapter<String> adapter;
    private ListView listView;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memoriesList = new ArrayList<>();
        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, memoriesList);
        listView.setAdapter(adapter);
        add=(Button) findViewById(R.id.addButton);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedMemory = memoriesList.get(position);
                openMemoryDetailsActivity(selectedMemory, position);
            }
        });
    }

    public void onAddButtonClick(View view) {
        // Open the AddMemoryActivity to add a new memory
        Intent intent = new Intent(MainActivity.this, AddMemoryActivity.class);
        startActivityForResult(intent, 1);
    }

    private void openMemoryDetailsActivity(String memory, int position) {
        // Open the MemoryDetailsActivity to view memory details
        Intent intent = new Intent(this, MemoryDetailsActivity.class);
        intent.putExtra("memory", memory);
        intent.putExtra("position", position);
        startActivityForResult(intent, 2);
    }

    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 1 && resultCode == RESULT_OK) {
//            // Retrieve the new memory from AddMemoryActivity
//            String newMemory = data.getStringExtra("newMemory");
//
//            // Add the new memory to the list
//            memoriesList.add(newMemory);
//            adapter.notifyDataSetChanged();
//        } else if (requestCode == 2 && resultCode == RESULT_OK) {
//            // Retrieve the updated memory and its position from MemoryDetailsActivity
//            String updatedMemory = data.getStringExtra("updatedMemory");
//            int position = data.getIntExtra("position", -1);
//
//            // Update the memory in the list
//            if (position != -1) {
//                memoriesList.set(position, updatedMemory);
//                adapter.notifyDataSetChanged();
//            }
//        } else if (requestCode == 2 && resultCode == RESULT_CANCELED) {
//            // Retrieve the position of the memory to be deleted from MemoryDetailsActivity
//            int position = data.getIntExtra("position", -1);
//
//            // Delete the memory from the list
//            if (position != -1) {
//                memoriesList.remove(position);
//                adapter.notifyDataSetChanged();
//            }
//        }
//    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Retrieve the new memory from AddMemoryActivity
            String newMemory = data.getStringExtra("newMemory");

            // Add the new memory to the list
            memoriesList.add(newMemory);
            adapter.notifyDataSetChanged();
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            // Retrieve the updated memory and its position from MemoryDetailsActivity
            String updatedMemory = data.getStringExtra("updatedMemory");
            int position = data.getIntExtra("position", -1);

            // Check if the position is within the valid range
            if (position >= 0 && position < memoriesList.size()) {
                // Update the memory in the list
                memoriesList.set(position, updatedMemory);
                adapter.notifyDataSetChanged();
            }
        } else if (requestCode == 2 && resultCode == RESULT_CANCELED) {
            // Retrieve the position of the memory to be deleted from MemoryDetailsActivity
            int position = data.getIntExtra("position", -1);

            // Check if the position is within the valid range
            if (position >= 0 && position < memoriesList.size()) {
                // Delete the memory from the list
                memoriesList.remove(position);
                adapter.notifyDataSetChanged();
            }
        }
    }

}
