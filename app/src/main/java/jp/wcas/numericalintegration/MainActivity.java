package jp.wcas.numericalintegration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private TestView testView;
    Button bt_apply;
    EditText x2Text, xText, realText;
    static Editable x2, x, real ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x2Text = (EditText) this.findViewById(R.id.x2edit);
        xText = (EditText) this.findViewById(R.id.xedit);
        realText = (EditText) this.findViewById(R.id.realedit);
        testView = (TestView) this.findViewById(R.id.test_view);
        bt_apply = (Button) findViewById(R.id.bt_apply);
        bt_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x2 = x2Text.getText();
                x = xText.getText();
                real = realText.getText();
                testView.invalidate();
            }
        });
    }
}