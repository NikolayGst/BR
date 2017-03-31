package com.niko.br.ui.common;

import android.databinding.BindingAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.niko.br.R;
import com.vstechlab.easyfonts.EasyFonts;

public class BaseActivity extends AppCompatActivity {

  public void initToolbar(String title) {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    TextView textView = (TextView) findViewById(R.id.titleToolbar);
    textView.setText(title);
    toolbar.setTitle("");
    setSupportActionBar(toolbar);
  }

  @BindingAdapter("app:setFontRobotoLight")
  public static void setFontRobotoLight(TextView textView, boolean b) {
    if (b) {
      textView.setTypeface(EasyFonts.robotoLight(textView.getContext()));
    }
  }

  @BindingAdapter("app:setFont")
  public static void setFontRobotoRegular(TextView textView, boolean b) {
    if (b) {
      textView.setTypeface(EasyFonts.robotoRegular(textView.getContext()));
    }
  }

}
