package com.niko.br.ui.main.nbu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.niko.br.R;
import com.niko.br.Utils;
import com.niko.br.databinding.NbuItemBinding;
import com.niko.br.models.gson.NBU;
import java.util.ArrayList;
import java.util.List;


public class NBUAdapter extends RecyclerView.Adapter<NBUAdapter.ViewHolder> {

  private List<NBU> nbuItems = new ArrayList<>();
  private Context context;

  public NBUAdapter(Context context) {
    this.context = context;
  }

  public void setNBUList(List<NBU> items) {
    nbuItems.clear();
    nbuItems.addAll(items);
    notifyDataSetChanged();
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent,
      int viewType) {
    View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.nbu_item, parent, false);
    return new ViewHolder(v);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    // Позиционирование для первого элемента
    if (position == 0) {
      int top = context.getResources().getDimensionPixelSize(R.dimen.small_margin);
      int bottom = context.getResources().getDimensionPixelSize(R.dimen.medium_margin);
      int left = context.getResources().getDimensionPixelSize(R.dimen.small_margin);
      int right = context.getResources().getDimensionPixelSize(R.dimen.small_margin);
      Utils.setMargins(holder.bind.card, left, top, right, bottom);
    }
    NBU item = nbuItems.get(position);
    holder.bind.txtCurrency.setText(item.getCc());
    holder.bind.txtCurrencyName.setText(item.getTxt());
    holder.bind.txtBuy.setText(Float.toString(item.getRate()));
    //отступы для последнего элемента
    if (position == nbuItems.size() - 1) {
      int top = context.getResources().getDimensionPixelSize(R.dimen.medium_margin);
      int bottom = context.getResources().getDimensionPixelSize(R.dimen.small_margin);
      int left = context.getResources().getDimensionPixelSize(R.dimen.small_margin);
      int right = context.getResources().getDimensionPixelSize(R.dimen.small_margin);
      Utils.setMargins(holder.bind.card, left, top, right, bottom);
    }
  }

  @Override
  public int getItemCount() {
    if (nbuItems == null) {
      return 0;
    }
    return nbuItems.size();
  }


  public class ViewHolder extends RecyclerView.ViewHolder {

    NbuItemBinding bind;

    public ViewHolder(View itemView) {
      super(itemView);

      bind = NbuItemBinding.bind(itemView);

    }
  }
}