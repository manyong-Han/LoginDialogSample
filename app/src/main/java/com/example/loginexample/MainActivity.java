package com.example.loginexample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String DIALOG_ID = "admin";
    private static final String DIALOG_PW = "1111";
    private static final int DIALOG_INT = 1;

    Button login_dialog_btn;
    EditText login_edit;
    EditText pw_edit;

    TextView login_text;

    String input_id, input_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        login_text = (TextView) findViewById(R.id.textView) ;
        login_dialog_btn = (Button) findViewById(R.id.login_dialog_btn);
        login_dialog_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_INT);
            }
        });
    }

    public Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_INT:

                // 레이아웃 XML파일을 View객체로 만들기 위해 inflater 사용
                LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.activity_main, (ViewGroup) findViewById(R.id.login_id_page));

                final AlertDialog.Builder builder = new AlertDialog.Builder(this);

                pw_edit = (EditText) view.findViewById(R.id.user_pw_edit);
                login_edit = (EditText)view.findViewById(R.id.user_id_edit);

                builder.setView(view)
                        .setPositiveButton("로그인하기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                input_id = login_edit.getText().toString().trim();
                                input_pw = pw_edit.getText().toString().trim();

                                if (DIALOG_ID.equals(input_id) && DIALOG_PW.equals(input_pw)) {
                                    Toast.makeText(MainActivity.this, "로그인 완료", Toast.LENGTH_SHORT).show();
                                    login_edit.setText("");
                                    pw_edit.setText("");
                                    dialog.dismiss();
                                    login_text.setText(input_id + "님이 로그인 하셨습니다.");
                                } else {
                                    Toast.makeText(MainActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                                    login_edit.setText("");
                                    pw_edit.setText("");

                                }
                            }
                        })
                        .setNegativeButton("취소하기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("buttonevent", input_id+"/"+input_pw);
                                Toast.makeText(MainActivity.this, "취소하였습니다.", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                return alert;
        }
        return null;
    }
}