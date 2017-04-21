package com.niko.br.ui.main.banks.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.niko.br.R;
import com.niko.br.models.gson.CashexRate;
import com.niko.br.ui.converter.ConverterActivity;
import java.util.List;

/**
 * Created by Николай on 19.04.2017.
 */

public class BanksAdapter extends
    ExpandableRecyclerAdapter<BankTitle, CashexRate, TitleBankHolder, RateHolder> {


  private Context context;

  /**
   * Primary constructor. Sets up {@link #mParentList} and {@link #mFlatItemList}.
   * <p>
   * Any changes to {@link #mParentList} should be made on the original instance, and notified via
   * {@link #notifyParentInserted(int)}
   * {@link #notifyParentRemoved(int)}
   * {@link #notifyParentChanged(int)}
   * {@link #notifyParentRangeInserted(int, int)}
   * {@link #notifyChildInserted(int, int)}
   * {@link #notifyChildRemoved(int, int)}
   * {@link #notifyChildChanged(int, int)}
   * methods and not the notify methods of RecyclerView.Adapter.
   *
   * @param parentList List of all parents to be displayed in the RecyclerView that this adapter is
   * linked to
   */
  public BanksAdapter(
      @NonNull List<BankTitle> parentList, Context context) {
    super(parentList);
    this.context = context;
  }

  @NonNull
  @Override
  public TitleBankHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup,
      int viewType) {
    return new TitleBankHolder(LayoutInflater.from(parentViewGroup.getContext())
        .inflate(R.layout.title_bank_item, parentViewGroup, false));
  }

  @NonNull
  @Override
  public RateHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
    return new RateHolder(LayoutInflater.from(childViewGroup.getContext())
        .inflate(R.layout.rate_bank_item, childViewGroup, false));
  }

  @Override
  public void onBindParentViewHolder(@NonNull TitleBankHolder parentViewHolder, int parentPosition,
      @NonNull BankTitle parent) {
    parentViewHolder.bindData(parent, parentPosition);
  }

  @Override
  public void onBindChildViewHolder(@NonNull RateHolder childViewHolder, int parentPosition,
      int childPosition, @NonNull CashexRate child) {
    childViewHolder.bindData(child);
    childViewHolder.bind.lrItem.setOnClickListener(view -> {

      String title = getParentList().get(parentPosition).getTitle();
      List<CashexRate> rates = getParentList().get(parentPosition).getChildList();
      CashexRate item = rates.get(childPosition);

      if (childViewHolder.bind.btnsRate.getVisibility() == View.GONE) {

        Animation in = AnimationUtils.loadAnimation(context,
            android.R.anim.fade_in);
        childViewHolder.bind.btnsRate.startAnimation(in);
        childViewHolder.bind.btnsRate.setVisibility(View.VISIBLE);

        childViewHolder.bind.buy.setOnClickListener(v -> {
          Intent intent = new Intent(context, ConverterActivity.class);
          intent.putExtra("mainRate", item.getCurrency());
          intent.putExtra("title", title);
          intent.putExtra("buyRate", item);
          intent.putExtra("isBuy", true);
          context.startActivity(intent);
        });

        childViewHolder.bind.sale.setOnClickListener(v -> {
          Intent intent = new Intent(context, ConverterActivity.class);
          intent.putExtra("mainRate", item.getCurrency());
          intent.putExtra("isBuy", false);
          intent.putExtra("title", title);
          intent.putExtra("usd", rates.get(0));
          intent.putExtra("eur", rates.get(1));
          intent.putExtra("rub", rates.get(2));
          context.startActivity(intent);
        });

      } else {
        Animation out = AnimationUtils.loadAnimation(context,
            android.R.anim.fade_out);
        childViewHolder.bind.btnsRate.startAnimation(out);
        childViewHolder.bind.btnsRate.setVisibility(View.GONE);
      }

    /*  String title = getParentList().get(parentPosition).getTitle();
      List<CashexRate> rates = getParentList().get(parentPosition).getChildList();
      CashexRate item = rates.get(childPosition);

      Intent intent = new Intent(context, ConverterActivity.class);
      intent.putExtra("mainRate", item.getCurrency());
      intent.putExtra("title", title);
      intent.putExtra("usd", rates.get(0));
      intent.putExtra("eur", rates.get(1));
      intent.putExtra("rub", rates.get(2));
      context.startActivity(intent);*/
    });
  }
}
