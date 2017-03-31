package com.niko.br;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.TextView;
import com.vstechlab.easyfonts.EasyFonts;


public class Utils {
  public static void applyFontedTab(Activity activity, ViewPager viewPager, TabLayout tabLayout) {
    for (int i = 0; i < viewPager.getAdapter().getCount(); i++) {
      TextView tv = (TextView) activity.getLayoutInflater().inflate(R.layout.tab_item, null);
      tv.setTypeface(EasyFonts.robotoRegular(activity.getApplicationContext()));
      if (i == viewPager.getCurrentItem()) {
        tv.setSelected(true);
      }
      tv.setText(viewPager.getAdapter().getPageTitle(i));
      tabLayout.getTabAt(i).setCustomView(tv);
    }
  }
}
