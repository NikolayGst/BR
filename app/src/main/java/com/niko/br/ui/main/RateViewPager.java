package com.niko.br.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import java.util.List;

class RateViewPager extends FragmentStatePagerAdapter{

  private List<String> titleList = new ArrayList<>();
  private List<Fragment> fragmentList = new ArrayList<>();

   RateViewPager(FragmentManager fm) {
    super(fm);
  }

   void addFrag(Fragment fragment, String title) {
    fragmentList.add(fragment);
    titleList.add(title);
  }



  @Override
  public Fragment getItem(int position) {
    return fragmentList.get(position);
  }

  @Override
  public int getCount() {
    return fragmentList.size();
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return titleList.get(position);
  }

}
