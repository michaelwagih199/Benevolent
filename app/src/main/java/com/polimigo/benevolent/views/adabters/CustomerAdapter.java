package com.polimigo.benevolent.views.adabters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.polimigo.benevolent.BR;
import com.polimigo.benevolent.R;
import com.polimigo.benevolent.databinding.RegisterItemRowBinding;
import com.polimigo.benevolent.helpers.Constants;
import com.polimigo.benevolent.models.Customer;
import com.polimigo.benevolent.views.AddRegister;
import com.polimigo.benevolent.views.events.CustomClickListener;

import java.util.List;


public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> implements CustomClickListener {

    private List<Customer> dataModelList;
    private Context context;

    public CustomerAdapter(List<Customer> dataModelList, Context ctx) {
        this.dataModelList = dataModelList;
        context = ctx;
    }

    @Override
    public CustomerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RegisterItemRowBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.register_item_row, parent, false);
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
        public RegisterItemRowBinding itemRowBinding;
        public ViewHolder(RegisterItemRowBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }
        public void bind(Object obj) {
            itemRowBinding.setVariable(BR.model, obj);
            itemRowBinding.executePendingBindings();
        }
    }

    public void cardClicked(Customer f) {
        Intent i = new Intent(context, AddRegister.class);
        i.putExtra(Constants.UPDATE_Person_Id, f.getDocumentId());
        context.startActivity(i);
        ((Activity) context).finish();
    }

}


