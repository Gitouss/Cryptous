package com.doplgangr.secrecy.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.doplgangr.secrecy.R;
import com.takwolf.android.lock9.Lock9View;

public class LoginActivity extends ActionBarActivity {


    Database database;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        database = new Database(this);
        final EditText question = new EditText(this);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        Lock9View lock9View = (Lock9View)findViewById(R.id.lock_9_view);
        lock9View.setCallBack(new Lock9View.CallBack() {
            @Override
            public void onFinish(final String password) {

                if(database.GetCountTable()==0){
                    alertDialog.setTitle("Complétez l'inscription");
                    alertDialog.setView(question);
                    alertDialog.setMessage("Quel est le nom de votre enseignante du primaire ?");
                    alertDialog.setCancelable(false);
                    alertDialog.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if(database.InsertCustomer("Admin", password, question.getText().toString())){
                                Toast.makeText(LoginActivity.this, "Inscription terminée", Toast.LENGTH_SHORT).show();
                            }else Toast.makeText(LoginActivity.this, "Erreur inscription", Toast.LENGTH_SHORT).show();
                        }
                    });
                    alertDialog.show();
                }else{
                    if (database.VerifyPassword(password)){
                        Toast.makeText(LoginActivity.this, password, Toast.LENGTH_SHORT).show();
                        Intent launcher = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(launcher);
                    }else Toast.makeText(LoginActivity.this, "Schéma incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
