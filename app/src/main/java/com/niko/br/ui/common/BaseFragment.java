package com.niko.br.ui.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.widget.TextView;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.niko.br.App;
import com.niko.br.R;
import com.niko.br.di.AppComponent;

public abstract class BaseFragment extends MvpAppCompatFragment {

  private ContentLoadingProgressBar progressBar;

  public ContentLoadingProgressBar getProgressBar() {
    return progressBar;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    injectFragment(App.getAppComponent());
    progressBar = (ContentLoadingProgressBar) getActivity().findViewById(R.id.progressBar);
    super.onCreate(savedInstanceState);
  }

  public void setTitle(String title) {
    TextView titleToolbar = (TextView) getActivity().findViewById(R.id.titleToolbar);
    titleToolbar.setText(title);
  }


  public abstract void injectFragment(AppComponent component);


  @Override
  public void onDestroyView() {
    super.onDestroyView();
    if (progressBar != null && progressBar.isShown()) {
      progressBar.hide();
    }
  }

  //Проверка на активность фрагмента в активности
  public boolean isFragmentUIActive() {
    return isAdded() && !isDetached() && !isRemoving();
  }
}
