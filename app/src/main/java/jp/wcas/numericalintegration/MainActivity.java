package jp.wcas.numericalintegration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static Float x2, x, real;
    private TestView testView;
    private EditText x2Text;
    private EditText xText;
    private EditText realText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x2Text = (EditText) this.findViewById(R.id.x2edit);
        xText = (EditText) this.findViewById(R.id.xedit);
        realText = (EditText) this.findViewById(R.id.realedit);
        testView = (TestView) this.findViewById(R.id.test_view);
        Button bt_apply = (Button) findViewById(R.id.bt_apply);
        bt_apply.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.optionsMenu_01:
                Intent preference = new android.content.Intent(this, Preferences.class);
                startActivity(preference);
                return true;
            case R.id.optionsMenu_02:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_apply: {
                try {
                    x2 = Float.valueOf(x2Text.getText().toString());
                } catch (NullPointerException n) {
                    x2Text.setText(0);
                    x2 = Float.valueOf(xText.getText().toString());
                } catch (NumberFormatException f) {
                    return;
                }
                try {
                    x = Float.valueOf(xText.getText().toString());
                } catch (NullPointerException n) {
                    xText.setText(0);
                    x = Float.valueOf(xText.getText().toString());
                } catch (NumberFormatException f) {
                    return;
                }
                try {
                    real = Float.valueOf(realText.getText().toString());
                } catch (NullPointerException n) {
                    realText.setText(0);
                    real = Float.valueOf(xText.getText().toString());
                } catch (NumberFormatException f) {
                    return;
                }
                testView.invalidate();
            }
            break;
        }
    }
}