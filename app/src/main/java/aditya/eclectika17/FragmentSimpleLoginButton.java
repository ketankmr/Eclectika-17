package aditya.eclectika17;

/**
 * Created by Ketan on 1/24/2017.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


/**
 * A placeholder fragment containing a simple view.
 */
public class FragmentSimpleLoginButton extends Fragment {
    SharedPreferences preferences;
    LoginButton mButtonLogin;
    ProgressDialog progressDialog;
    Button guest,again;
    String token, fb_id,pname;
    private CallbackManager mCallbackManager;
    private AccessTokenTracker mTokenTracker;
    private ProfileTracker mProfileTracker;
    private FacebookCallback<LoginResult> mFacebookCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {

            mButtonLogin.setVisibility(View.GONE);
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Please Wait");
            progressDialog.show();

            final AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();


            if(Profile.getCurrentProfile() == null) {
                mProfileTracker = new ProfileTracker() {
                    @Override
                    protected void onCurrentProfileChanged(Profile profile, Profile profile2) {
                        // profile2 is the new profile
                        Log.v("facebook - profile", profile2.getFirstName());
                        mProfileTracker.stopTracking();
                        token= accessToken.getToken();
                        fb_id=profile2.getId();
                         pname = profile2.getName();
                                  profile2.getProfilePictureUri(200,200);

                        volley();

                    }
                };
                // no need to call startTracking() on mProfileTracker
                // because it is called by its constructor, internally.
            }
            else {
                profile = Profile.getCurrentProfile();
                Log.v("facebook - profile", profile.getFirstName());
                token= accessToken.getToken();
                fb_id=profile.getId();
                pname= profile.getName();
                volley();
            }



        }


        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {

            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();

        }
    };


    public FragmentSimpleLoginButton() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCallbackManager = CallbackManager.Factory.create();
        setupTokenTracker();
        setupProfileTracker();

        mTokenTracker.startTracking();
        mProfileTracker.startTracking();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_simple_login_button, container, false);

        guest = (Button) view.findViewById(R.id.guest);
        again = (Button) view.findViewById(R.id.tryagain);

       //  if (isLoggedIn())
         //    mButtonLogin.setVisibility(View.GONE);


        again.setVisibility(View.GONE);

        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                volley();
            }
        });

        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

                getActivity().finish();

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        setupLoginButton(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        Profile profile = Profile.getCurrentProfile();

    }

    @Override
    public void onStop() {
        super.onStop();
        mTokenTracker.stopTracking();
        mProfileTracker.stopTracking();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void setupTokenTracker() {
        mTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                Log.d("VIVZ", "" + currentAccessToken);
                String m="";
            }
        };
    }



    private void setupProfileTracker() {
        mProfileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                Log.d("VIVZ", "" + currentProfile);

            }
        };
    }

    private void setupLoginButton(View view) {
         mButtonLogin = (LoginButton) view.findViewById(R.id.login_button);
        mButtonLogin.setFragment(this);

        mButtonLogin.registerCallback(mCallbackManager, mFacebookCallback);
    }

    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }


public void  volley (){

    Toast.makeText(getContext(), fb_id, Toast.LENGTH_SHORT).show();

     preferences =getActivity().getSharedPreferences("mypref", Context.MODE_PRIVATE);
     final SharedPreferences.Editor editor= preferences.edit();
     JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET,  "http://eclectika.org/apiv2/api/user/fb/"+fb_id, null,
             new Response.Listener<JSONObject>()
             {
                 @Override
                 public void onResponse(JSONObject p) {
                     // display response
                     Log.d("Response", p.toString());
                     try {



                         if(p.length()==0)
                         {
                             progressDialog.dismiss();
                             ((Register)getActivity()).start_registration(token,fb_id,pname);

                         }

                         else {
                             progressDialog.dismiss();

                             editor.putString("name", p.getString("name"));
                             editor.putString("contact",p.getString("contact"));
                             editor.putString("ec_id", p.getString("ec_id"));
                             editor.putString("fb_id", fb_id);
                             editor.putString("logged_in","true");

                             editor.apply();

                             Intent i = new Intent(getActivity(),MainActivity.class);
                             startActivity(i);

                             getActivity().finish();



                         }

                     } catch (Exception e) {
                         progressDialog.dismiss();
                         again.setVisibility(View.VISIBLE);
                         Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                         // JSON error
                         e.printStackTrace();
                     }

                 }
             },
             new Response.ErrorListener()
             {
                 @Override
                 public void onErrorResponse(VolleyError error) {
                     progressDialog.dismiss();

                     again.setVisibility(View.VISIBLE);
                     Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();

                     Log.d("Error.Response", "");
                 }
             }
     );


     AppController.getInstance().addToRequestQueue(getRequest);

 }

}