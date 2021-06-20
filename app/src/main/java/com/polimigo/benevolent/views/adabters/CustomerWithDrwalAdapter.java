package com.polimigo.benevolent.views.adabters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.polimigo.benevolent.BR;
import com.polimigo.benevolent.R;
import com.polimigo.benevolent.databinding.WithdrawlItemRowBindingImpl;
import com.polimigo.benevolent.helpers.Constants;
import com.polimigo.benevolent.models.Customer;
import com.polimigo.benevolent.views.WithDrwalDialog;
import com.polimigo.benevolent.views.events.CustomClickListener;
import java.util.List;


public class CustomerWithDrwalAdapter extends RecyclerView.Adapter<CustomerWithDrwalAdapter.ViewHolder> implements CustomClickListener {

    private List<Customer> dataModelList;
    private Context context;

    public CustomerWithDrwalAdapter(List<Customer> dataModelList, Context ctx) {
        this.dataModelList = dataModelList;
        context = ctx;
    }

    @Override
    public CustomerWithDrwalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        WithdrawlItemRowBindingImpl binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.withdrawl_item_row, parent, false);
        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Customer dataModel = dataModelList.get(position);
        holder.itemRowBinding.setModel(dataModel);
        holder.bind(dataModel);
        holder.itemRowBinding.setItemClickListener(this);
    }


    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public WithdrawlItemRowBindingImpl itemRowBinding;
        public ViewHolder(WithdrawlItemRowBindingImpl itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }
        public void bind(Object obj) {
            itemRowBinding.setVariable(BR.model, obj);
            itemRowBinding.executePendingBindings();
        }
    }

    public void cardClicked(Customer f) {
        Intent i = new Intent(context, WithDrwalDialog.class);
        i.putExtra(Constants.Person_Id, f.getDocumentId());
        i.putExtra(Constants.Person_VALUE,f.getValue());
        context.startActivity(i);
    }

}


