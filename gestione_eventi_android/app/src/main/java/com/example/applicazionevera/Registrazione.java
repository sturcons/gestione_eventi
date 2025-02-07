package com.example.applicazionevera;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicazionevera.retrofit.MyApiEndpointInterface;
import com.example.applicazionevera.retrofit.Utente;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Registrazione extends AppCompatActivity {

    private Button dateButton;
    private DatePickerDialog datePickerDialog;
    private EditText nome, cognome, username,  email , password;
    Button data_nascita ;
    private Utente user;
    private int ID_utente;
    List<Utente> allusers=new ArrayList<>();
    TextView log;
    int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registrazione);
        nome = (EditText) findViewById(R.id.nome);
        cognome = (EditText) findViewById(R.id.cognome);
        username = (EditText) findViewById(R.id.usernameReg);
        //data_nascita = (Button) findViewById(R.id.dataNascita);
        email = (EditText) findViewById(R.id.emailReg);
        password = (EditText) findViewById(R.id.passwordReg);
        EditText passConf = findViewById(R.id.passwordConf);
        MaterialButton button;
        initDatePicker();
        log = (TextView) findViewById(R.id.login);
        Controllo();

        button = (MaterialButton) findViewById(R.id.buttonReg);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Fnome = nome.getText().toString();
                String Fcognome = cognome.getText().toString();
                String Funame = username.getText().toString();
//                String Fdata_nascita = data_nascita.getText().toString();
                String emailString = email.getText().toString();
                String passwordString = password.getText().toString();
                String passConfString = passConf.getText().toString();
                if (emailString.contains("@")) {
                    if (passwordString.length() >= 5) {
                        if (passwordString.equals(passConfString)) {
                            for (int i = 0; i < allusers.size(); i++) {
                                String uss=allusers.get(i).getUsername();
                                if (Funame.equals(uss)) {
                                    flag=0;
                                }
                                else{
                                    flag=1;
                                }
                            }
                            if(flag==1){
                                user = new Utente(ID_utente,Funame, Fcognome, Fnome, emailString, passwordString);
                                registerMe();
                                openLogin();
                            }
                            else{
                                Toast.makeText(Registrazione.this, "Username già esistente!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        else
                            Toast.makeText(Registrazione.this, "la conferma password deve essere uguale alla password", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(Registrazione.this, "La password deve essere almeno di cinque caratteri", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(Registrazione.this, "email non valida", Toast.LENGTH_SHORT).show();

            }
        });

        /*dateButton = (Button) findViewById(R.id.dataNascita);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker(v);
            }
        });

         */
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogin();
            }
        });
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
               month = month +1;
               String date = makeDateString(day, month, year);
               dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener,year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private String makeDateString(int day, int month, int year) {
                return day + " " +  getMonthFormat(month) + " " + year;
            }private String getMonthFormat(int month) {
                if(month==1)
                    return "GEN";
                if(month==2)
                    return "FEB";
                if(month==3)
                    return "MAR";
                if(month==4)
                    return "APR";
                if(month==5)
                    return "MAG";
                if(month==6)
                    return "GIU";
                if(month==7)
                    return "LUG";
                if(month==8)
                    return "AGO";
                if(month==9)
                    return "SET";
                if(month==10)
                    return "OTT";
                if(month==11)
                    return "NOV";
                if(month==12)
                    return "DIC";
                return "GEN";
            }
    public void openDatePicker(View v){
        datePickerDialog.show();
    }
    public void registerMe() {
        MyApiEndpointInterface apiService = retrofit.create(MyApiEndpointInterface.class);
        Call<Utente> call = apiService.createUser(user);
        call.enqueue(new Callback<Utente>() {
            @Override
            public void onResponse(Call<Utente> call, Response<Utente> response) {
                int statusCode = response.code();
                user = response.body();
            }
            @Override
            public void onFailure(Call<Utente> call, Throwable t) {
            }
        });
    }
    public void openLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
    public static final String BASE_URL = "http://10.0.2.2:8080/";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void Controllo() {
        MyApiEndpointInterface apiService = retrofit.create(MyApiEndpointInterface.class);
        Call<List<Utente>> call = apiService.geteAllusername();
        call.enqueue(new Callback<List<Utente>>() {
            @Override
            public void onResponse(Call<List<Utente>> call, Response<List<Utente>> response) {
                    int statusCode = response.code();
                    allusers = response.body();
            }
            @Override
            public void onFailure(Call<List<Utente>> call, Throwable t) {
                Toast.makeText(Registrazione.this, "Errore", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
