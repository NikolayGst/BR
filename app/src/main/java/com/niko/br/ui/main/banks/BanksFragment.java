package com.niko.br.ui.main.banks;


import static com.niko.br.Utils.BANKS_KEY;
import static com.niko.br.Utils.SAVE_FRAGMENT;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.niko.br.R;
import com.niko.br.databinding.FragmentBanksBinding;
import com.niko.br.di.AppComponent;
import com.niko.br.ui.common.BaseFragment;
import com.niko.br.ui.main.banks.adapter.BankTitle;
import com.niko.br.ui.main.banks.adapter.BanksAdapter;
import java.util.List;
import javax.inject.Inject;

public class BanksFragment extends BaseFragment implements BanksView {

  @InjectPresenter
  BanksPresenter presenter;

  private FragmentBanksBinding bind;
  private BanksAdapter adapter;

  @Inject
  SharedPreferences sharedPreferences;

  public BanksFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    bind = FragmentBanksBinding.inflate(inflater, container, false);
    return bind.getRoot();
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
    bind.recyclerBank.setVisibility(View.GONE);
    bind.contentError.lrError.setVisibility(View.VISIBLE);
    bind.contentError.refresh.setOnClickListener(view -> {
      bind.contentError.lrError.setVisibility(View.GONE);
      bind.recyclerBank.setVisibility(View.VISIBLE);
      presenter.execute();
    });
  }

  @Override
  public void onSuccessLoadBanksData(List<BankTitle> list) {
    System.out.println(list);
    adapter = new BanksAdapter(list, getContext());
    bind.recyclerBank.setAdapter(adapter);
    bind.recyclerBank.setLayoutManager(new LinearLayoutManager(getContext()));
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    sharedPreferences.edit().putString(SAVE_FRAGMENT, BANKS_KEY).apply();
  }

}
