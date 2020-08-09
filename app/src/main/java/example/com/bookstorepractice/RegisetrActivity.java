package example.com.bookstorepractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class RegisetrActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText newUserName;
    private EditText newPwd;
    private EditText newPwdCheck;
    private Button newRegisterBt;
    private Button registerBack;
    private CheckBox registerShowPwd;
    UserManager userManager = new UserManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //初始化控件
        initviews();
        registerListener();
        isShowPwd(registerShowPwd);
    }

    private void initviews(){
        newUserName = (EditText)findViewById(R.id.new_username);
        newPwd = (EditText)findViewById(R.id.new_password);
        newPwdCheck = (EditText)findViewById(R.id.new_again_password);
        newRegisterBt = (Button)findViewById(R.id.register_ok_button);
        registerBack = (Button)findViewById(R.id.register_back_button);
        registerShowPwd = (CheckBox) findViewById(R.id.register_show_password);

    }

    private void registerListener(){
        newRegisterBt.setOnClickListener(this);
        registerBack.setOnClickListener(this);
    }

    //设置密码是否可见
    private void isShowPwd(CheckBox showPwd){
        showPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //显示明文，即设置为可见的密码
                    newPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    newPwdCheck.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    //将光标移至末尾
                    newPwd.setSelection(newPwd.getText().length());
                    newPwdCheck.setSelection(newPwdCheck.getText().length());
                }else{
                    //不显示明文，即设置为不可见的密码
                    newPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    newPwdCheck.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    //将光标移至末尾
                    newPwd.setSelection(newPwd.getText().length());
                    newPwdCheck.setSelection(newPwdCheck.getText().length());
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_ok_button:
                register_check();
                break;
            case R.id.register_back_button:
                Intent loginIntent = new Intent(RegisetrActivity.this, MainActivity.class);
                startActivity(loginIntent);
                break;
            default:
                break;
        }
    }

    //对填写的注册信息进行检查
    private void register_check(){
        if (isUserNameAndPwdValid()){
            String name = newUserName.getText().toString().trim();
            String password = newPwd.getText().toString().trim();
            String rePwd = newPwdCheck.getText().toString().trim();

            boolean flag = userManager.findUserByName(name);
            if (flag){
                Toast.makeText(this, "用户名已存在", Toast.LENGTH_SHORT).show();
                return;
            }else {
                if (!password.equals(rePwd)){
                    Toast.makeText(this, "两次输入的密码不匹配！", Toast.LENGTH_SHORT).show();
                }else {
                    flag = userManager.insertUser(name, password);
                    if (flag){
                        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                        Intent backIntent = new Intent(RegisetrActivity.this, MainActivity.class);
                        startActivity(backIntent);
                        //finish();
                    }else {
                        Toast.makeText(this, "注册失败！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        }
    }

    //判断信息是否填写完整
    private boolean isUserNameAndPwdValid(){
        if (newUserName.getText().toString().trim().equals("")){
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }else if (newPwd.getText().toString().trim().equals("")){
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }else if (newPwdCheck.getText().toString().trim().equals("")){
            Toast.makeText(this, "再次输入密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }
}
