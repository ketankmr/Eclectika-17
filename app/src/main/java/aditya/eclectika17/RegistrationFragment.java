package aditya.eclectika17;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {
    SharedPreferences preferences;

    EditText name, phone, email,college,branch, sem;
    String fb_id,token,pname;
    Button signup;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_registration, container, false);

        preferences = getActivity().getSharedPreferences("mypref", Context.MODE_PRIVATE);

        name = (EditText) view.findViewById(R.id.name);
        phone = (EditText) view.findViewById(R.id.contact);
        email = (EditText) view.findViewById(R.id.email);
        college = (EditText) view.findViewById(R.id.college);
        branch = (EditText) view.findViewById(R.id.branch);
        sem = (EditText) view.findViewById(R.id.sem);
        signup = (Button) view.findViewById(R.id.register);

        fb_id = getArguments().getString("fb_id");
        token = getArguments().getString("access");
        pname = getArguments().getString("pname");

        name.setText(pname);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Registering ....");
                progressDialog.show();

                final String nam= name.getText().toString();
                final String email1= email.getText().toString();
                final String col= college.getText().toString();
                final String semester= sem.getText().toString();
                final String bch= branch.getText().toString();
                final String num= phone.getText().toString();

                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("fb_id",fb_id);
                editor.putString("token",token);
                editor.apply();


                String url ="http://eclectika.org/apiv2/api/register" ;
                StringRequest strReq = new StringRequest(Request.Method.POST,
                        url, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.e("", "Login Response: " + response.toString());

                         progressDialog.dismiss();

                        try {
                            JSONObject jObj = new JSONObject(response);


                            SharedPreferences.Editor editor = preferences.edit();

                            editor.putString("ec_id",jObj.getString("ec_id"));
                            editor.putString("contact",jObj.getString("contact"));
                            editor.putString("fb_id",fb_id);

                            editor.putString("logged_in","true");
                            editor.apply();

                            Intent i = new Intent(getActivity(),MainActivity.class);
                            startActivity(i);
                             getActivity().finish();

                        } catch (Exception e) {
                            // JSON error

                            progressDialog.dismiss();
                            e.printStackTrace();

                            Log.e("Json error: ",e.getMessage());
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                      int j=  error.networkResponse.statusCode;
                        progressDialog.dismiss();
                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() {
                        // Posting parameters to login url
                        Map<String, String> params = new HashMap<String, String>();


                        params.put("name",  nam);
                        params.put("email",email1);
                        params.put("college",col);
                        params.put("branch",bch);
                        params.put("semester",semester);
                        params.put("contact",num);
                        params.put("fb_id",fb_id);
                        params.put("accessToken",token);




                        return params;
                    }

                };
                AppController.getInstance().addToRequestQueue(strReq, "");

            }
        });

        return view;
    }

}
