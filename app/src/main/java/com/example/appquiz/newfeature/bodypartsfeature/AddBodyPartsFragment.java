package com.example.appquiz.newfeature.bodypartsfeature;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appquiz.R;
import com.example.appquiz.newfeature.bodypartsfeature.data.BodyPart;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;


public class AddBodyPartsFragment extends DialogFragment {
    private ImageView button_back,button_add_body_image;
    private Button button_save;
    private TextInputLayout input_name,input_desc;
    private FirebaseDatabase firebaseDatabase;
    private Uri imageURI;
    private StorageReference storage;
    private StorageTask mUploadTask;
    ProgressDialog progressDialog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL,R.style.ShapeAppearanceOverlay_MaterialComponents_MaterialCalendar_Window_Fullscreen);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_body_parts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        progressDialog = new ProgressDialog(requireContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance().getReference("bodyparts");
        button_add_body_image.setOnClickListener(v -> pickImage.launch(null));
        button_back.setOnClickListener(v -> dismiss());
        button_save.setOnClickListener(v -> {
            String name = input_name.getEditText().getText().toString();
            String desc = input_desc.getEditText().getText().toString();
            if (name.isEmpty()){
                input_name.setError("Please enter your name");
            } else if (desc.isEmpty()){
                input_desc.setError("Please enter description");
            }else{
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(getActivity(), "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    uploadProfile(imageURI);
                }
            }
        });

    }

    private void initViews(View view) {

        button_back = view.findViewById(R.id.button_back);
        button_add_body_image = view.findViewById(R.id.button_add_profile);
        button_save = view.findViewById(R.id.button_save);
        input_name = view.findViewById(R.id.input_name);
        input_desc = view.findViewById(R.id.input_description);
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = requireContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    //Pick Image From Gallery
    private final ActivityResultContract<String,Uri> activityResultContract = new ActivityResultContract<String, Uri>() {
        @NonNull
        @NotNull
        @Override
        public Intent createIntent(@NonNull @NotNull Context context, String input) {
            return CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).setAspectRatio(6,5).getIntent(context);
        }

        @Override
        public Uri parseResult(int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent intent) {
            return CropImage.getActivityResult(intent).getUri();
        }
    };
        private final ActivityResultLauncher<String> pickImage = registerForActivityResult(activityResultContract, result -> {
        imageURI = result;
        Picasso.get().load(imageURI).into(button_add_body_image);
    });

    //TODO: upload file in firestore and storage
    private void uploadProfile(Uri uri){
        if (uri != null){
            StorageReference fileReference = storage.child(System.currentTimeMillis() +"."+getFileExtension(uri));
            mUploadTask = fileReference.putFile(uri).addOnSuccessListener(taskSnapshot -> {
                fileReference.getDownloadUrl().addOnSuccessListener(uri1 -> {
                    String name = input_name.getEditText().getText().toString();
                    String desc = input_desc.getEditText().getText().toString();
                    String id = firebaseDatabase.getReference().push().getKey();
                    BodyPart bodyPart = new BodyPart(id,uri1.toString(),name,desc);
                    addBodyPart(bodyPart);
                });
            });
        }
    }
    private void addBodyPart(BodyPart bodyPart){
        progressDialog.setMessage("Uploading body part please wait...");
        progressDialog.setTitle("Loading");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        firebaseDatabase.getReference("bodyparts").child(bodyPart.getBodyPartID()).setValue(bodyPart).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Toast.makeText(requireContext(),"Body Parts Added!",Toast.LENGTH_SHORT).show();
                dismiss();
            } else {
                Toast.makeText(requireContext(),"Failed",Toast.LENGTH_SHORT).show();
            }
            progressDialog.dismiss();
        });
    }
}