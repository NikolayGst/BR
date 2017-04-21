package com.niko.br.ui.main.nbu;


import static com.niko.br.Utils.NBU_KEY;
import static com.niko.br.Utils.SAVE_FRAGMENT;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.niko.br.R;
import com.niko.br.databinding.FragmentNbuBinding;
import com.niko.br.di.AppComponent;
import com.niko.br.models.gson.NBU;
import com.niko.br.ui.common.BaseFragment;
import java.util.List;
import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class NbuFragment extends BaseFragment implements NbuView {

  @InjectPresenter
  NbuPresenter nbuPresenter;

  @Inject
  SharedPreferences sharedPreferences;

  private FragmentNbuBinding binding;
  private NBUAdapter nbuAdapter;

  public NbuFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentNbuBinding.inflate(inflater, container, false);

    binding.recyclerNbu.setLayoutManager(new LinearLayoutManager(getContext()));
    nbuAdapter = new NBUAdapter(getContext());

    binding.btnShowOtherRate.setOnClickListener(view -> {
      nbuPresenter.showOtherRate();
    });

    return binding.getRoot();
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
    binding.rlNBU.setVisibility(View.GONE);
    binding.contentError.lrError.setVisibility(View.VISIBLE);
    binding.contentError.refresh.setOnClickListener(view -> {
      binding.contentError.lrError.setVisibility(View.GONE);
      nbuPresenter.execute();
    });
  }

  @Override
  public void onNbuDataLoadSuccess(List<NBU> nbuList) {
    binding.rlNBU.setVisibility(View.VISIBLE);
    nbuAdapter.setNBUList(nbuList);
    binding.recyclerNbu.setAdapter(nbuAdapter);
  }

  @Override
  public void onFormatFullNBUDataSuccess(List<NBU> nbuList) {
    nbuAdapter.setNBUList(nbuList);
    binding.recyclerNbu.setAdapter(nbuAdapter);
    binding.btnShowOtherRate.setVisibility(View.GONE);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    sharedPreferences.edit().putString(SAVE_FRAGMENT, NBU_KEY).apply();
  }
}
