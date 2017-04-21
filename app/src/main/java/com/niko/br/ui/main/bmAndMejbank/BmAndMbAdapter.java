package com.niko.br.ui.main.bmAndMejbank;

import static com.niko.br.Utils.getFlag;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.niko.br.R;
import com.niko.br.Utils;
import com.niko.br.databinding.BmItemBinding;
import com.niko.br.databinding.TitleItemBinding;
import com.niko.br.models.gson.CashexRate;
import com.niko.br.ui.converter.ConverterActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class BmAndMbAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private final int green;
  private Context context;

  List<CashexRate> bmList;
  List<CashexRate> mejBanks;

  private List<Object> list;

  BmAndMbAdapter(Context context) {
    green = ContextCompat.getColor(context, R.color.green);
    this.context = context;
    list = new ArrayList<>();
  }

  private final int TITLE = 0, ITEM = 1;

  void addItems(List<Object> list, List<CashexRate> bmList, List<CashexRate> mejBanks) {
    this.bmList = bmList;
    this.mejBanks = mejBanks;
    this.list.addAll(list);
    notifyDataSetChanged();
  }

  @Override
  public int getItemViewType(int position) {
    return list.get(position) instanceof String ? TITLE : ITEM;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    switch (viewType) {
      case TITLE:
        return new TitleHolder(layoutInflater.inflate(R.layout.title_item, parent, false));
      case ITEM:
        return new ItemHolder(layoutInflater.inflate(R.layout.bm_item, parent, false));
    }
    return null;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    switch (holder.getItemViewType()) {
      case TITLE:
        TitleHolder th = (TitleHolder) holder;
        th.bind.title.setText((CharSequence) list.get(position));
        break;
      case ITEM:
        ItemHolder ih = (ItemHolder) holder;
        CashexRate item = (CashexRate) list.get(position);
        changeColor(item, ih);
        ih.bind.flag.setImageResource(getFlag(item.getCurrency()));
        ih.bind.txtCurrency.setText(item.getCurrency());
        ih.bind.txtBuy.setText(String.format(Locale.ENGLISH, "%.3f", item.getBuy()));
        ih.bind.txtBuyDelta.setText(String.format(Locale.ENGLISH, "%.3f", item.getBuyDelta()));
        ih.bind.txtSale.setText(String.format(Locale.ENGLISH, "%.3f", item.getSale()));
        ih.bind.txtSaleDelta.setText(String.format(Locale.ENGLISH, "%.3f", item.getSaleDelta()));
        //отступы для последнего элемента
        if (position == list.size() - 1) {
          int top = context.getResources().getDimensionPixelSize(R.dimen.medium_margin);
          int bottom = context.getResources().getDimensionPixelSize(R.dimen.big_margin);
          int left = context.getResources().getDimensionPixelSize(R.dimen.small_margin);
          int right = context.getResources().getDimensionPixelSize(R.dimen.small_margin);
          Utils.setMargins(ih.bind.card, left, top, right, bottom);
        }
        ih.bind.lrItem.setOnClickListener(view ->
        {
          if (ih.bind.btnsRate.getVisibility() == View.GONE) {

            Animation in = AnimationUtils.loadAnimation(context,
                android.R.anim.fade_in);
            ih.bind.btnsRate.startAnimation(in);
            ih.bind.btnsRate.setVisibility(View.VISIBLE);

            ih.bind.buy.setOnClickListener(v -> {
              Intent intent = new Intent(context, ConverterActivity.class);
              intent.putExtra("mainRate", item.getCurrency());
              intent.putExtra("buyRate", item);
              intent.putExtra("isBuy", true);
              if (item.isBM()) {
                intent.putExtra("title", "ЧР");
              } else {
                intent.putExtra("title", "МБ");
              }
              context.startActivity(intent);
            });

            ih.bind.sale.setOnClickListener(v -> {
              Intent intent = new Intent(context, ConverterActivity.class);
              intent.putExtra("mainRate", item.getCurrency());
              intent.putExtra("isBuy", false);
              if (item.isBM()) {
                intent.putExtra("title", "ЧР");
                intent.putExtra("usd", bmList.get(0));
                intent.putExtra("eur", bmList.get(1));
                intent.putExtra("rub", bmList.get(2));
              } else {
                intent.putExtra("title", "МБ");
                intent.putExtra("usd", mejBanks.get(0));
                intent.putExtra("eur", mejBanks.get(1));
                intent.putExtra("rub", mejBanks.get(2));
              }
              context.startActivity(intent);
            });

          } else {
            Animation out = AnimationUtils.loadAnimation(context,
                android.R.anim.fade_out);
            ih.bind.btnsRate.startAnimation(out);
            ih.bind.btnsRate.setVisibility(View.GONE);
          }
         /* Intent intent = new Intent(context, ConverterActivity.class);
          intent.putExtra("mainRate", item.getCurrency());
          if (item.isBM()) {
            intent.putExtra("title", "ЧР");
            intent.putExtra("usd", bmList.get(0));
            intent.putExtra("eur", bmList.get(1));
            intent.putExtra("rub", bmList.get(2));
          } else {
            intent.putExtra("title", "МБ");
            intent.putExtra("usd", mejBanks.get(0));
            intent.putExtra("eur", mejBanks.get(1));
            intent.putExtra("rub", mejBanks.get(2));
          }
          context.startActivity(intent);*/
        });
        //  context.startActivity(new Intent(context, ConverterActivity.class)));
        break;
    }
  }

  private void changeColor(CashexRate item, ItemHolder holder) {
    if (item.getBuyDelta() > 0 && !Float.toString(item.getBuyDelta()).equals("0.0")) {
      holder.bind.txtBuyDelta.setTextColor(green);
    } else if (item.getBuyDelta() < 0 && !Float.toString(item.getBuyDelta()).equals("0.0")) {
      holder.bind.txtBuyDelta.setTextColor(Color.RED);
    } else {
      holder.bind.txtBuyDelta.setTextColor(Color.BLACK);
    }

    if (item.getSaleDelta() > 0 && !Float.toString(item.getSaleDelta()).equals("0.0")) {
      holder.bind.txtSaleDelta.setTextColor(green);
    } else if (item.getSaleDelta() < 0 && !Float.toString(item.getSaleDelta()).equals("0.0")) {
      holder.bind.txtSaleDelta.setTextColor(Color.RED);
    } else {
      holder.bind.txtSaleDelta.setTextColor(Color.BLACK);
    }
  }

  private class ItemHolder extends ViewHolder {

    BmItemBinding bind;

    ItemHolder(View itemView) {
      super(itemView);
      bind = BmItemBinding.bind(itemView);
    }
  }

  private class TitleHolder extends ViewHolder {

    TitleItemBinding bind;

    TitleHolder(View itemView) {
      super(itemView);
      bind = TitleItemBinding.bind(itemView);
    }
  }

  @Override
  public int getItemCount() {
    return list != null ? list.size() : 0;
  }
}
