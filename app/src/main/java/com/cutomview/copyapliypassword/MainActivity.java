package com.cutomview.copyapliypassword;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CustomerKeyboard.CustomerKeyboardClickListener, PasswordEditText.PasswordFullListener {

    private CustomerKeyboard mCustomerKeyboard;
    private PasswordEditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPasswordEditText = (PasswordEditText) findViewById(R.id.password_et);
        mCustomerKeyboard = (CustomerKeyboard) findViewById(R.id.custom_key_board);
        // 设置监听
        mCustomerKeyboard.setOnCustomerKeyboardClickListener(this);
        mPasswordEditText.setOnPasswordFullListener(this);

        mPasswordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                    mPasswordEditText.deleteLastPassword();
                    return true;
                }
                return false;
            }
        });
    }


    /**
     * 键盘数字点击监听回调方法
     */
    @Override
    public void click(String number) {
        mPasswordEditText.addPassword(number);
    }

    /**
     * 键盘删除点击监听回调方法
     */
    @Override
    public void delete() {
        mPasswordEditText.deleteLastPassword();
    }

    /**
     * 密码输入完毕回调方法
     */
    @Override
    public void passwordFull(String password) {
        Toast.makeText(this, "密码填充完毕：" + password, Toast.LENGTH_SHORT).show();
    }
}
