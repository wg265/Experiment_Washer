package com.laioffer.dryer.ui.login;

import android.app.Activity;
import android.app.ProgressDialog;
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

import com.laioffer.dryer.LoginActivity;
import com.laioffer.dryer.R;
import com.laioffer.dryer.database.MySQLConnection;
import com.laioffer.dryer.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {
    private FragmentRegisterBinding binding;
    private EditText username;
    private EditText password;
    private EditText confirmPassword;
    private EditText phoneNum;
    private Button register;
    public static RegisterFragment newInstance() {
        Bundle args = new Bundle();
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public RegisterFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        username = view.findViewById(R.id.register_username);
        password = view.findViewById(R.id.register_password);
        confirmPassword = view.findViewById(R.id.register_confirm);
        phoneNum = view.findViewById(R.id.phone_num);
        register = view.findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String pwd = password.getText().toString();
                String confirm = confirmPassword.getText().toString();
                String num = phoneNum.getText().toString();
                if (!confirm.equals(pwd)) {
                    Toast.makeText(getActivity(), "Confirm Password is not equal to Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                MutableLiveData<Boolean> success = new MutableLiveData<>();
                new RegisterAsyncTask(success).execute(name, pwd, confirm, num);
            }
        });
        return view;
    }
    protected int getLayout() {
        return R.layout.fragment_register;
    }
    private class RegisterAsyncTask extends AsyncTask<String, Void, Boolean> {
        private MutableLiveData<Boolean> liveData;
        ProgressDialog p;
        private RegisterAsyncTask(MutableLiveData<Boolean> success) {
            liveData = success;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p = new ProgressDialog(getContext());
            p.setMessage("Doing Register... Please Wait");
            p.setIndeterminate(false);
            p.setCancelable(false);
            p.show();
        }
        @Override
        protected Boolean doInBackground(String... info) {
            String username = info[0];
            String password = info[1];
            String confirm = info[2];
            String num = info[3];
            try {
                MySQLConnection conn = new MySQLConnection();
                boolean flag = conn.addUser(username, password);
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
                Toast.makeText(getActivity(), "Register Failed. Maybe the user name is already used. Please try again", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getActivity(), "Register success", Toast.LENGTH_SHORT).show();
                username.setText("");
                password.setText("");
                confirmPassword.setText("");
                phoneNum.setText("");
                Activity activity = getActivity();
                if (activity != null && !activity.isFinishing()) {
                    ((LoginActivity)activity).setCurrentPage(0);
                }
            }
        }
    }

}
