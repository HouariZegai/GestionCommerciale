package com.houarizegai.gestioncommercial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import android.widget.Toast;
import com.houarizegai.gestioncommercial.models.Client;
import com.houarizegai.gestioncommercial.models.ClientBuilder;

public class SubscribeClientActivity extends AppCompatActivity {

    /* field */
    private EditText editSociete, editNom, editPrenom, editAdresse, editCodePostal, editVille, editPays, editTelephone,
            editMobile, editFax, editEmail, editType, editObs;
    private Spinner spinnerCivilite;
    private CheckBox checkLivreMemeAdr, checkFactureMemeAdr, checkExemptTva;
    /* */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe_client);
        initView();
    }

    private void initView() {
        editSociete = findViewById(R.id.et_societe);
        spinnerCivilite = findViewById(R.id.spinner_civilite);
        editNom = findViewById(R.id.et_nom);
        editPrenom = findViewById(R.id.et_prenom);
        editAdresse = findViewById(R.id.et_adr);
        editCodePostal = findViewById(R.id.et_code_postal);
        editVille = findViewById(R.id.et_ville);
        editPays = findViewById(R.id.et_pays);
        editTelephone = findViewById(R.id.et_telephone);
        editMobile = findViewById(R.id.et_mobile);
        editFax = findViewById(R.id.et_fax);
        editEmail = findViewById(R.id.et_email);
        editType = findViewById(R.id.et_type);

        checkLivreMemeAdr = findViewById(R.id.check_livre_meme_adr);
        checkFactureMemeAdr = findViewById(R.id.check_facture_meme_adr);
        checkExemptTva = findViewById(R.id.check_exempt_tva);

        editObs = findViewById(R.id.et_obs);
    }

    public void onSave(View view) {
        if(editNom.getText() == null || editNom.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Nom erreur !", Toast.LENGTH_LONG).show();
            return;
        }
        if(editPrenom.getText() == null || editPrenom.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Prenom erreur !", Toast.LENGTH_LONG).show();
            return;
        }

    }

    public void onClear(View view) {
    }
}
