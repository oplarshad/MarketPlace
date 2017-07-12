package com.example.gabbygiordano.marketplace;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddItemActivity extends AppCompatActivity {

    private static final int ACTIVITY_START_CAMERA = 0;

    public EditText etItemName;
    public EditText etItemDescription;
    public EditText etItemPrice;
    public ImageButton ibAddImage;
    public ImageButton ibPostItem;
    public ImageView imageLocation;

    final ArrayList<ImageView> addImage = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Spinner spinner = (Spinner) findViewById(R.id.conditionOptions);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.condition_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        etItemName = (EditText) findViewById(R.id.etItemName);
        etItemDescription = (EditText) findViewById(R.id.etItemDescription);
        etItemPrice = (EditText) findViewById(R.id.etItemPrice);
        ibAddImage = (ImageButton) findViewById(R.id.ibAddImage);
        ibPostItem = (ImageButton) findViewById(R.id.ibPostItem);
        imageLocation= (ImageView) findViewById(R.id.ivItemPhoto);
    }

    public void onPostSuccess(View view){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }


    public void takeItemPhoto(View view)
    {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, ACTIVITY_START_CAMERA);
    }

    protected void onActivityResult(int requestcode, int resultCode, Intent data)
    {
        if(requestcode == ACTIVITY_START_CAMERA && resultCode == RESULT_OK)
        {
            //Toast.makeText(this, "picture was taken", Toast.LENGTH_SHORT).show();
            Bundle extras = data.getExtras();
            Bitmap photoCaptured = (Bitmap) extras.get("data");
            imageLocation.setImageBitmap(photoCaptured);

        }
    }
}