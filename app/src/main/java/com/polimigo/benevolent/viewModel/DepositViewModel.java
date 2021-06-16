package com.polimigo.benevolent.viewModel;

import android.content.Context;
import android.text.TextUtils;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.polimigo.benevolent.BR;
import com.polimigo.benevolent.models.Customer;
import com.polimigo.benevolent.models.DepositModel;
import com.polimigo.benevolent.repositories.CustomerRepository;
import com.polimigo.benevolent.repositories.DopositRepository;
import com.polimigo.benevolent.views.events.BaseEvent;

public class DepositViewModel extends BaseObservable {
    private Context context;
    private BaseEvent baseEvent;
    private String validateMessage = "Please add all data";
    private String errorMessage = "Saved Failed";
    private DepositModel depositModel;
    private DopositRepository dopositRepository;

    @Bindable
    private String toastMessage = null;

    public DepositViewModel(Context context, DepositModel depositModel, BaseEvent baseEvent) {
        this.context = context;
        this.depositModel = depositModel;
        dopositRepository = DopositRepository.newInstance();
        this.baseEvent = baseEvent;
    }

    public String getToastMessage() {
        return toastMessage;
    }

    private void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    public DepositModel getDepositModel() {
        return depositModel;
    }

    public void setDepositModel(DepositModel depositModel) {
        this.depositModel = depositModel;
    }

    public void onSaveDeposit() {
        if (isInputDataValid()) {
            DepositModel model = new DepositModel();
            model.setComment(depositModel.getComment());
            model.setValue(depositModel.getValue());
            baseEvent.onStartedL();
            dopositRepository.createDocument(model, context);
        } else
            setToastMessage(errorMessage);
    }

    public boolean isInputDataValid() {
        return !TextUtils.isEmpty(depositModel.getValue().toString());
    }


}
