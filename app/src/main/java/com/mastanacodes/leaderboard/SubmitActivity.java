package com.mastanacodes.leaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mastanacodes.leaderboard.Interface.JsonPlaceHolderApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitActivity extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private EditText emailAddress;
    private EditText projectLink;
    private String fName;
    private String lName;
    private String eAddress;
    private String pLink;
    private Button submitButton;
    private Button yesButton;
    private ImageButton closeDialogButton;
    private Dialog confirmDialog;
    private Dialog responseDialog;
    private ImageView responseImage;
    private TextView responseText;
    private JsonPlaceHolderApi apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        apiService = ApiClient.getClient().create(JsonPlaceHolderApi.class);

        responseDialog = new Dialog(this);
        responseDialog.setContentView(R.layout.response_dialog);
        responseDialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_background));
        responseImage = responseDialog.findViewById(R.id.response_image);
        responseText = responseDialog.findViewById(R.id.response_title);

        confirmDialog = new Dialog(this);
        confirmDialog.setContentView(R.layout.confirm_dialog);
        confirmDialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_background));
        confirmDialog.setCancelable(false);

        closeDialogButton = confirmDialog.findViewById(R.id.close_dialog_btn);
        yesButton = confirmDialog.findViewById(R.id.yes_btn);

        firstName = findViewById(R.id.first_name_et);
        lastName = findViewById(R.id.last_name_et);
        emailAddress = findViewById(R.id.email_et);
        projectLink = findViewById(R.id.project_link_et);
        submitButton = findViewById(R.id.submit_button);

        firstName.addTextChangedListener(textWatcher);
        lastName.addTextChangedListener(textWatcher);
        emailAddress.addTextChangedListener(textWatcher);
        projectLink.addTextChangedListener(textWatcher);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog.show();
            }
        });

        closeDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog.dismiss();
            }
        });

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog.dismiss();
                createPost();
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            checkInputs();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void checkInputs() {
        if (!TextUtils.isEmpty(firstName.getText())) {
            if (!TextUtils.isEmpty(lastName.getText())) {
                if (!TextUtils.isEmpty(emailAddress.getText())) {
                    if (!TextUtils.isEmpty(projectLink.getText())) {
                        submitButton.setEnabled(true);
                        submitButton.getBackground().setAlpha(255);
                    } else {
                        submitButton.setEnabled(false);
                        submitButton.getBackground().setAlpha(125);
                    }
                } else {
                    submitButton.setEnabled(false);
                    submitButton.getBackground().setAlpha(125);
                }
            } else {
                submitButton.setEnabled(false);
                submitButton.getBackground().setAlpha(125);
            }
        } else {
            submitButton.setEnabled(false);
            submitButton.getBackground().setAlpha(125);
        }
    }

    private void createPost() {
        fName = firstName.getText().toString().trim();
        lName = lastName.getText().toString().trim();
        eAddress = emailAddress.getText().toString().trim();
        pLink = projectLink.getText().toString().trim();
        Call<SubmitModel> call = apiService.submit(fName,lName,eAddress,pLink);
        call.enqueue(new Callback<SubmitModel>() {
            @Override
            public void onResponse(Call<SubmitModel> call, Response<SubmitModel> response) {
                if (response.isSuccessful()) {
                    responseImage.setImageResource(R.drawable.ic_check_circle_green);
                    responseText.setText("Submission Successful");
                    responseDialog.show();
                } else {
                    responseImage.setImageResource(R.drawable.ic_warning_red);
                    responseText.setText("Submission not Successful");
                    responseDialog.show();
                }
            }

            @Override
            public void onFailure(Call<SubmitModel> call, Throwable t) {

            }
        });
    }
}
