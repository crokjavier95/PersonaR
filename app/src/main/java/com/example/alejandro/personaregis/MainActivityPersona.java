package com.example.alejandro.personaregis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivityPersona extends AppCompatActivity {

    TextView menssage;
    EditText user, passs, email, perfil;
    Button btnRegistrar, btnActulizar, btnBuscar, btnEliminar, btnLimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_persona);
        //Instanciar cajas de texto con su ID
        user = (EditText)findViewById(R.id.eT_user);
        passs = (EditText)findViewById(R.id.eT_pass);
        email = (EditText)findViewById(R.id.eT_email);
        perfil = (EditText)findViewById(R.id.eT_perfil);
        //Instanciar botones con su ID
        btnRegistrar = (Button)findViewById(R.id.btnRegis);
        btnActulizar = (Button)findViewById(R.id.btnUpdate);
        btnBuscar = (Button)findViewById(R.id.btnConsultar);
        btnEliminar = (Button)findViewById(R.id.btnDelete);
        btnLimpiar = (Button)findViewById(R.id.btnBorrarTodo);
        //Instanciar label para pruebas
        menssage = (TextView)findViewById(R.id.Mensaje);
        //Desabilitar botones Eliminar y Actulizar
        btnActulizar.setEnabled(false);
        btnEliminar.setEnabled(false);

        //Metodo de accion de los botones, al momento de hacer clic
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String usserr = user.getText().toString();
                final String pass = passs.getText().toString();
                final String emaill = email.getText().toString();
                final String perfill = perfil.getText().toString();

                AcessoRest ar = new AcessoRest();

                String UsuarioWS;
                UsuarioWS = "http://192.168.137.1:8080/UsuarioWS/webresources/RegisUser/Usuario/getInsert/"+usserr+"/"+pass+"/"+emaill+"/"+perfill+"";

                String result = ar.envioGet(UsuarioWS);
                Log.i("JSON",result);

                try {
                    JSONObject jsonObject = new JSONObject(result);
                }catch (JSONException ex) {
                    ex.printStackTrace();
                }
                limpiar();
                //Toast.makeText(getApplicationContext(),"Se registraron los datos correctamente.",Toast.LENGTH_SHORT).show();
            }
        });

        btnActulizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String usserr = user.getText().toString();
                final String pass = passs.getText().toString();
                final String emaill = email.getText().toString();
                final String perfill = perfil.getText().toString();

                AcessoRest ar = new AcessoRest();

                String UsuarioWS;
                UsuarioWS = "http://192.168.137.1:8080/UsuarioWS/webresources/RegisUser/Usuario/getUpdate/"+usserr+"/"+pass+"/"+emaill+"/"+perfill+"";

                String result = ar.envioGet(UsuarioWS);
                Log.i("JSON",result);

                try {
                    JSONObject jsonObject = new JSONObject(result);
                }catch (JSONException ex){
                    ex.printStackTrace();
                }
                //Toast.makeText(getApplicationContext(),"Se actulizaron los datos correctamente.",Toast.LENGTH_SHORT).show();
                btnActulizar.setEnabled(false);
                btnEliminar.setEnabled(false);
                btnRegistrar.setEnabled(true);
                limpiar();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String usserr = user.getText().toString();

                AcessoRest ar = new AcessoRest();

                String UsuarioWS;
                UsuarioWS = "http://192.168.137.1:8080/UsuarioWS/webresources/RegisUser/Usuario/getDelete/"+usserr+"";

                String result = ar.envioGet(UsuarioWS);
                Log.i("JSON",result);

                try {
                    JSONObject jsonObject = new JSONObject(result);
                }catch (JSONException ex){
                    ex.printStackTrace();
                }
                //Toast.makeText(getApplicationContext(),"Se eliminaron los datos correctamente.",Toast.LENGTH_SHORT).show();
                btnActulizar.setEnabled(false);
                btnEliminar.setEnabled(false);
                btnRegistrar.setEnabled(true);
                limpiar();
            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String userr = user.getText().toString();

                AcessoRest ar = new AcessoRest();

                String UsuarioWS;
                UsuarioWS = "http://192.168.137.1:8080/UsuarioWS/webresources/RegisUser/Usuario/getConsulta/"+userr+"";

                String result = ar.envioGet(UsuarioWS);
                Log.i("JSON",result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    passs.setText(jsonObject.getString("contrasena"));
                    email.setText(jsonObject.getString("email"));
                    perfil.setText(jsonObject.getString("perfil"));
                }catch (JSONException ex){
                    ex.printStackTrace();
                }
                btnActulizar.setEnabled(true);
                btnEliminar.setEnabled(true);
                btnRegistrar.setEnabled(false);
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiar();
                btnActulizar.setEnabled(false);
                btnEliminar.setEnabled(false);
                btnRegistrar.setEnabled(true);
            }
        });

    }
    //Metodo para limpiar
    public void limpiar(){
        user.setText("");
        passs.setText("");
        email.setText("");
        perfil.setText("");
    }
}
