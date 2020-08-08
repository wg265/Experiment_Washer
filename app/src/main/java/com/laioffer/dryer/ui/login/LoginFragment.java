package com.laioffer.dryer.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

import com.laioffer.dryer.R;
import com.laioffer.dryer.database.MySQLConnection;
import com.laioffer.dryer.databinding.FragmentLoginBinding;
import com.laioffer.dryer.WasherActivity;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private EditText username;
    private EditText password;
    private Button loginButton;
    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       binding = FragmentLoginBinding.inflate(inflater, container, false);
       View view=  binding.getRoot();
       username = (EditText)view.findViewById(R.id.login_username);
       password = (EditText) view.findViewById(R.id.login_password);
       loginButton = (Button)view.findViewById(R.id.login);
       loginButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               final String name = username.getText().toString();
               final String pwd = password.getText().toString();
               MutableLiveData<Boolean> success = new MutableLiveData<>();
//               MySQLConnection conn = new MySQLConnection();
//               boolean flag = conn.verifyLogin(name, pwd);
               new LoginAsyncTask(success).execute(name, pwd);
           }
       });
       return view;
    }
    protected int getLayout() {
        return R.layout.fragment_login;
    }

    private class LoginAsyncTask extends AsyncTask<String, Void, Boolean> {
        private MutableLiveData<Boolean> liveData;
        ProgressDialog p;
        private LoginAsyncTask(MutableLiveData<Boolean> success) {
            liveData = success;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p = new ProgressDialog(getContext());
            p.setMessage("Doing login... Please Wait");
            p.setIndeterminate(false);
            p.setCancelable(false);
            p.show();
        }
        @Override
        protected Boolean doInBackground(String... info) {
            String username = info[0];
            String password = info[1];
            try {
                MySQLConnection conn = new MySQLConnection();
                boolean flag = conn.verifyLogin(username, password);
                if (flag) {
                    Config.username = username;
                    Log.d("aaaa", Config.username);
                }
                conn.close();
                Log.d("have conn", "flag->" + flag);
                Log.d("aaaa","doInBackground");
                return flag;
            }
            catch (Exception e){
                return false;
            }

        }
        @Override
        protected void onPostExecute(Boolean success) {
            Log.d("aaaa","onPostExecute");
            p.cancel();
            if (!success) {
                Toast.makeText(getActivity(), "Login Failed. Please try again", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getActivity(), "Login success", Toast.LENGTH_SHORT).show();
                username.setText("");
                password.setText("");
                startActivity(new Intent(getActivity(), WasherActivity.class));
            }
        }
    }
}
