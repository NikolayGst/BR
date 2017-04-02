package com.niko.br.ui.main.bmAndMejbank;


import static com.niko.br.Utils.BM_AND_MB_KEY;
import static com.niko.br.Utils.SAVE_FRAGMENT;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.niko.br.R;
import com.niko.br.databinding.FragmentBmAndMbBinding;
import com.niko.br.di.AppComponent;
import com.niko.br.ui.common.BaseFragment;
import java.util.List;
import javax.inject.Inject;


public class BmAndMbFragment extends BaseFragment implements BmAndMbView {

  @InjectPresenter
  BmAndMbPresenter presenter;

  @Inject
  SharedPreferences sharedPreferences;

  private FragmentBmAndMbBinding binding;

  private BmAndMbAdapter adapter;

  public BmAndMbFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentBmAndMbBinding.inflate(inflater, container, false);

    initView();

    return binding.getRoot();
  }

  private void initView() {
    adapter = new BmAndMbAdapter(getContext());
    binding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));
  }

  @Override
  public void injectFragment(AppComponent component) {
    component.inject(this);
  }

  @Override
  public void showProgressBar() {
    progressBar.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideProgressBar() {
    progressBar.setVisibility(View.INVISIBLE);
  }

  @Override
  public void onFailure(Throwable throwable) {
    showToast(getString(R.string.error_internet));
    binding.recycler.setVisibility(View.GONE);
    binding.contentError.lrError.setVisibility(View.VISIBLE);
    binding.contentError.refresh.setOnClickListener(view -> {
      binding.contentError.lrError.setVisibility(View.GONE);
      binding.recycler.setVisibility(View.VISIBLE);
      presenter.execute();
    });
  }

  @Override
  public void onSuccessLoadBmANdMb(List<Object> list) {
    adapter.addItems(list);
    binding.recycler.setAdapter(adapter);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    sharedPreferences.edit().putString(SAVE_FRAGMENT, BM_AND_MB_KEY).apply();
  }
}
