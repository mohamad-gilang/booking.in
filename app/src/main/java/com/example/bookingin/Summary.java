package com.example.bookingin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bookingin.confirm.ConfirmBooking;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class Summary extends AppCompatActivity implements View.OnClickListener {

    private static final int RC_Take_From_Gallery_Card = 1;
    private static final int RC_Take_From_Gallery_Invoice = 2;
    private StorageReference mStorageRef;

    String name, phone, date, paket, passenger;
    Integer total;
    TextView _txtSummaryName, _txtSummaryPhone, _txtSummaryDate, _txtSummaryPackage, _txtSummaryPassenger, _txtSummaryTotal;
    Button _btnSummaryClear, _btnSummaryContinue, _btnUploadInvoice, _btnUploadCard;
    ImageView _imgPreviewInvoice, _imgPreviewCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        _txtSummaryName = findViewById(R.id.txtSummaryName);
        _txtSummaryPhone = findViewById(R.id.txtSummaryPhone);
        _txtSummaryDate = findViewById(R.id.txtSummaryDate);
        _txtSummaryPackage = findViewById(R.id.txtSummaryPackage);
        _txtSummaryPassenger = findViewById(R.id.txtSummaryPassenger);
        _txtSummaryTotal = findViewById(R.id.txtSummaryTotal);
        _imgPreviewInvoice = findViewById(R.id.imgPreviewInvoice);
        _imgPreviewCard = findViewById(R.id.imgPreviewCard);
        _btnUploadInvoice = findViewById(R.id.btnUploadInvoice);
        _btnUploadCard = findViewById(R.id.btnUploadCard);
        _btnSummaryClear = findViewById(R.id.btnSummaryClear);
        _btnSummaryContinue = findViewById(R.id.btnSummaryContinue);

        _btnUploadInvoice.setOnClickListener(this);
        _btnUploadCard.setOnClickListener(this);
        _btnSummaryClear.setOnClickListener(this);
        _btnSummaryContinue.setOnClickListener(this);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            name = extras.getString("name");
            phone = extras.getString("phone");
            date = extras.getString("date");
            passenger = extras.getString("passenger");
            paket = extras.getString("paket");
            switch (paket) {
                case "Paket 1":
                    total = Integer.parseInt(passenger) * 100000;
                    break;
                case "Paket 2":
                    total = Integer.parseInt(passenger) * 200000;
                    break;
                case "Paket 3":
                    total = Integer.parseInt(passenger) * 300000;
                    break;
            }
            _txtSummaryName.setText(name);
            _txtSummaryPhone.setText(phone);
            _txtSummaryDate.setText(date);
            _txtSummaryPackage.setText(paket);
            _txtSummaryPassenger.setText(passenger);
            _txtSummaryTotal.setText(String.valueOf("Rp." + total + ",-"));
        }
    }

    private void selectImgCard(final Context context) {
        final CharSequence[] options = {"Choose from Gallery", "Cancel"};

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, RC_Take_From_Gallery_Card);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void selectImgInvoice(final Context context) {
        final CharSequence[] options = {"Choose from Gallery", "Cancel"};

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, RC_Take_From_Gallery_Invoice);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case RC_Take_From_Gallery_Card:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();
                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                Glide.with(this).load(new File(picturePath)).centerCrop().into(_imgPreviewCard);
                                cursor.close();
                            }
                            uploadCardToStorage(selectedImage);
                        }
                    }
                    break;

                case RC_Take_From_Gallery_Invoice:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();
                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                Glide.with(this).load(new File(picturePath)).centerCrop().into(_imgPreviewInvoice);
                                cursor.close();
                            }
                            uploadInvoiceToStorage(selectedImage);
                        }
                    }
                    break;
            }
        }
    }

    public void uploadCardToStorage(Uri file) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UploadTask uploadTask;
        mStorageRef = FirebaseStorage.getInstance().getReference();
        StorageReference fotoRef = mStorageRef.child(user.getUid() + "/card/" + user.getUid() + ".jpg");
        uploadTask = fotoRef.putFile(file);
        Toast.makeText(Summary.this, "Uploading image", Toast.LENGTH_SHORT).show();

        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(Summary.this, "Can't upload image, " + exception.getMessage(), Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(Summary.this, "Image uploaded", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void uploadInvoiceToStorage(Uri file) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UploadTask uploadTask;
        mStorageRef = FirebaseStorage.getInstance().getReference();
        StorageReference fotoRef = mStorageRef.child(user.getUid() + "/invoice/" + user.getUid() + ".jpg");
        uploadTask = fotoRef.putFile(file);
        Toast.makeText(Summary.this, "Uploading image", Toast.LENGTH_SHORT).show();

        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(Summary.this, "Can't upload image, " + exception.getMessage(), Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(Summary.this, "Image uploaded", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onClick(View view) {
        if (view.getId() == _btnUploadCard.getId()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    String[] Permisions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    requestPermissions(Permisions, 100);
                } else {
                    selectImgCard(Summary.this);
                }
            } else {
                selectImgCard(Summary.this);
            }
        } else if (view.getId() == _btnUploadInvoice.getId()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    String[] Permisions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    requestPermissions(Permisions, 100);
                } else {
                    selectImgInvoice(Summary.this);
                }
            } else {
                selectImgInvoice(Summary.this);
            }
        } else if (view.getId() == _btnSummaryContinue.getId()) {
            Intent summaryContinue = new Intent(this, Confirm.class);
            startActivity(summaryContinue);
        } else if (view.getId() == _btnSummaryClear.getId()) {
            _imgPreviewCard.setImageResource(android.R.color.transparent);
            _imgPreviewInvoice.setImageResource(android.R.color.transparent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            selectImgInvoice(Summary.this);
            selectImgCard(Summary.this);
        } else {
            Toast.makeText(this, "denied", Toast.LENGTH_LONG).show();
        }
    }
}