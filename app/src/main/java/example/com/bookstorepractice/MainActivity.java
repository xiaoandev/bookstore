package example.com.bookstorepractice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText usernameEdit;
    private EditText passwordEdit;
    private Button loginBt;
    private Button registerBt;
    private CheckBox rememberPwd;
    private CheckBox showPwd;
    UserManager userManager = new UserManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initviews();
        registerListener();
        isShowPwd(showPwd);//设置密码显示或不显示
    }

    private void initviews(){
        usernameEdit = (EditText)findViewById(R.id.username);
        passwordEdit = (EditText)findViewById(R.id.password);
        loginBt = (Button)findViewById(R.id.login_button);
        registerBt = (Button)findViewById(R.id.register_button);
        rememberPwd = (CheckBox)findViewById(R.id.remember_password);
        showPwd = (CheckBox)findViewById(R.id.show_password);
    }

    private void registerListener(){
        loginBt.setOnClickListener(this);
        registerBt.setOnClickListener(this);
    }

    //设置密码是否可见
    private void isShowPwd(CheckBox showPwd){
        showPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //显示明文，即设置为可见的密码
                    passwordEdit.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    //将光标移至末尾
                    passwordEdit.setSelection(passwordEdit.getText().length());
                }else{
                    //不显示明文，即设置为不可见的密码
                    passwordEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    //将光标移至末尾
                    passwordEdit.setSelection(passwordEdit.getText().length());
                }
            }
        });
    }

    private void login_judge(){
        if (isUserNameAndPwdValid()){
            String username = usernameEdit.getText().toString().trim();
            String password = passwordEdit.getText().toString().trim();
            boolean flag = userManager.findUserByName(username);
            if (flag){
                flag = userManager.findNameAndPwd(username, password);
                if (flag){
                    Intent intent = new Intent(MainActivity.this, LibraryFunctionActivity.class);
                    startActivity(intent);
                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                    //检查复选框是否被选中
                    if (!rememberPwd.isChecked()){
                        usernameEdit.setText("");
                        passwordEdit.setText("");
                    }
                }else {
                    Toast.makeText(this, "用户名或密码不正确！", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "用户名不存在！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //判断信息是否填写完整
    private boolean isUserNameAndPwdValid(){
        if (usernameEdit.getText().toString().trim().equals("")){
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }else if (passwordEdit.getText().toString().trim().equals("")){
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                login_judge();
                //Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.register_button:
                Intent intent = new Intent(MainActivity.this, RegisetrActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }


}
