package com.houarizegai.gestioncommercial;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SubscribeClientActivity extends AppCompatActivity {

    private EditText editSociete, editNom, editPrenom, editAdresse, editCodePostal, editVille, editPays, editTelephone,
            editMobile, editFax, editEmail, editType, editObs;
    private Spinner spinnerCivilite;
    private CheckBox checkLivreMemeAdr, checkFactureMemeAdr, checkExemptTva;

    AlertDialog.Builder dialogBuilder;

    private final String IP_SERVER = "192.168.43.9";
    private final String REGISTER_CLIENT_URL = "http://" + IP_SERVER + "/GestionCommercial-Server/register_client.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe_client);
        initViews();
    }

    private void initViews() {
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

        dialogBuilder = new AlertDialog.Builder(SubscribeClientActivity.this);
    }

    public void onSave(View view) {
        if (editNom.getText() == null || editNom.getText().toString().trim().isEmpty()) {
            dialogBuilder.setTitle("Erreur Taper information");
            dialogBuilder.setMessage("Nom erreur !");
            displayAlert("input_error_nom");
            return;
        }
        if (editPrenom.getText() == null || editPrenom.getText().toString().trim().isEmpty()) {
            dialogBuilder.setTitle("Erreur Taper information");
            dialogBuilder.setMessage("Prenom erreur !");
            displayAlert("input_error_prenom");
            return;
        }

        // Send data to web service
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_CLIENT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String code = jsonObject.getString("code");
                            String msg = jsonObject.getString("message");
                            dialogBuilder.setTitle("Server Response");
                            dialogBuilder.setMessage(msg);
                            displayAlert(code);
                        } catch (JSONException e) {
                            Toast.makeText(SubscribeClientActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String code = "reg_failed";
                        String msg = "Error Response from server !";
                        dialogBuilder.setTitle("Server Response");
                        dialogBuilder.setMessage(msg);
                        displayAlert(code);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("societe", editSociete.getText().toString());
                params.put("civilite", spinnerCivilite.getSelectedItem().toString());
                params.put("nom", editNom.getText().toString());
                params.put("prenom", editPrenom.getText().toString());
                params.put("adresse", editAdresse.getText().toString());
                params.put("code_postal", editCodePostal.getText().toString());
                params.put("ville", editVille.getText().toString());
                params.put("pays", editPays.getText().toString());
                params.put("telephone", editTelephone.getText().toString());
                params.put("mobile", editMobile.getText().toString());
                params.put("fax", editFax.getText().toString());
                params.put("email", editEmail.getText().toString());
                params.put("type", editType.getText().toString().isEmpty() ? "0" : editType.getText().toString());
                params.put("livre_meme_adresse", String.valueOf(checkLivreMemeAdr.isChecked()));
                params.put("facture_meme_adresse", String.valueOf(checkFactureMemeAdr.isChecked()));
                params.put("exempt_tva", String.valueOf(checkExemptTva.isChecked()));
                params.put("observations", editObs.getText().toString());

                return params;
            }
        };
        MySignleton.getmInstance(SubscribeClientActivity.this).addToRequestQueue(stringRequest);

    }

    public void onClear(View view) {
        editSociete.setText(null);
        editNom.setText(null);
        editPrenom.setText(null);
        editAdresse.setText(null);
        editCodePostal.setText(null);
        editVille.setText(null);
        editPays.setText(null);
        editTelephone.setText(null);
        editMobile.setText(null);
        editFax.setText(null);
        editEmail.setText(null);
        editType.setText(null);
        editObs.setText(null);
        checkLivreMemeAdr.setChecked(false);
        checkFactureMemeAdr.setChecked(false);
        checkExemptTva.setChecked(false);

    }

    private void displayAlert(final String code) {
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (code.equals("input_error_nom")) {
                    editNom.setText("");
                    editNom.setSelected(true);
                } else if (code.equals("input_error_prenom")) {
                    editPrenom.setText("");
                    editPrenom.setSelected(true);
                } else if (code.equals("reg_success")) {
                    onClear(null);
                } else if (code.equals("reg_failed")) {
                    onClear(null);
                }
            }
        });
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }
}
