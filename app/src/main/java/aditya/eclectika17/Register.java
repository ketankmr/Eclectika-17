package aditya.eclectika17;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FragmentSimpleLoginButton frg=new FragmentSimpleLoginButton();

        FragmentManager manager=getSupportFragmentManager();//create an instance of fragment manager

        FragmentTransaction transaction=manager.beginTransaction();


        transaction.add(R.id.My_Container_1_ID, frg, "Frag_Top_tag");

        transaction.commit();
    }

    public void guest (View v){
        Intent intent = new Intent(Register.this, MainActivity.class);

        startActivity(intent);

        finish();
    }

    public void start_registration(String token, String fb_id,String pname){
        RegistrationFragment registration = new RegistrationFragment();
        Bundle bundle = new Bundle();
        bundle.putString("access",token);
        bundle.putString("fb_id",fb_id);
        bundle.putString("pname",pname);
        registration.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.My_Container_1_ID,registration).commit();}
    }

