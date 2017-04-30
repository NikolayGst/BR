package com.niko.br.ui.main.author;


import static com.niko.br.Utils.AUTHOR_KEY;
import static com.niko.br.Utils.SAVE_FRAGMENT;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.niko.br.databinding.FragmentAuthorBinding;
import com.niko.br.di.AppComponent;
import com.niko.br.ui.common.BaseFragment;
import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthorFragment extends BaseFragment {

  @Inject
  SharedPreferences sharedPreferences;

  FragmentAuthorBinding binding;

  public AuthorFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentAuthorBinding.inflate(inflater, container, false);

    binding.vk.setOnClickListener(view -> {
      try {
        Intent intent = new Intent(Intent.ACTION_VIEW,
            Uri.parse("vkontakte://profile/nikodroid"));
        startActivity(intent);
      } catch (ActivityNotFoundException ex) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
            Uri.parse("https://vk.com/nikodroid"));
        startActivity(intent);
      }
    });

    binding.git.setOnClickListener(view -> {
      Intent intent = new Intent(Intent.ACTION_VIEW,
          Uri.parse("https://github.com/NikolayGst"));
      startActivity(intent);
    });

    return binding.getRoot();
  }

  @Override
  public void injectFragment(AppComponent component) {
    component.inject(this);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    sharedPreferences.edit().putString(SAVE_FRAGMENT, AUTHOR_KEY).apply();
  }
}
