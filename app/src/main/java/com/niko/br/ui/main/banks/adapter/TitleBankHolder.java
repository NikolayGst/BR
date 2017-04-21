package com.niko.br.ui.main.banks.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.niko.br.Utils;
import com.niko.br.databinding.TitleBankItemBinding;

public class TitleBankHolder extends ParentViewHolder {

  TitleBankItemBinding bind;

  public TitleBankHolder(@NonNull View itemView) {
    super(itemView);
    bind = TitleBankItemBinding.bind(itemView);
  }

  public void bindData(BankTitle parent, int pos){
    bind.titleBank.setText(parent.getTitle());
    bind.logoBank.setImageResource(Utils.getImageBank(pos));
  }

}
