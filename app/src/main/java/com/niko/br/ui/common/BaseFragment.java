package com.niko.br.ui.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.niko.br.App;
import com.niko.br.R;
import com.niko.br.di.AppComponent;

public abstract class BaseFragment extends MvpAppCompatFragment {

  public ProgressBar progressBar;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    injectFragment(App.getAppComponent());
    progressBar = (ProgressBar) getActivity().findViewById(R.id.progressBar);
    super.onCreate(savedInstanceState);
  }

  public void setTitle(String title) {
    TextView titleToolbar = (TextView) getActivity().findViewById(R.id.titleToolbar);
    titleToolbar.setText(title);
  }

  public void showToast(String msg) {
    Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
  }


  public abstract void injectFragment(AppComponent component);


  @Override
  public void onDestroyView() {
    super.onDestroyView();
    if (progressBar != null && progressBar.getVisibility() == View.VISIBLE) {
      progressBar.setVisibility(View.INVISIBLE);
    }
  }

  //Проверка на активность фрагмента в активности
  public boolean isFragmentUIActive() {
    return isAdded() && !isDetached() && !isRemoving();
  }
}
