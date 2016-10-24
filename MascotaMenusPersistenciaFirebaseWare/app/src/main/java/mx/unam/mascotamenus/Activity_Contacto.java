package mx.unam.mascotamenus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import mx.unam.mascotamenus.util.SendMail;

public class Activity_Contacto extends AppCompatActivity implements View.OnClickListener {
    private EditText input_name;
    private  EditText input_email;
    private EditText input_mensaje;
    private Button btn_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__contacto);

        //Initializing the views
        input_name = (EditText) findViewById(R.id.input_name);
        input_email = (EditText) findViewById(R.id.input_email);
        input_mensaje = (EditText) findViewById(R.id.input_mensaje);

        btn_signup= (Button) findViewById(R.id.btn_signup);

        //Adding click listener
        btn_signup.setOnClickListener(this);
    }

    private void sendEmail() {
        //Getting content for email
        String email = input_email.getText().toString().trim();
        String subject = input_name.getText().toString().trim();
        String message = input_mensaje.getText().toString().trim();

        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }

    @Override
    public void onClick(View v) {
        sendEmail();
    }
}
