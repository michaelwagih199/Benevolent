package com.polimigo.benevolent.repositories;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.polimigo.benevolent.databinding.ActivityAddRegisyterBindingImpl;
import com.polimigo.benevolent.databinding.ActivityWithDrwalDialogBinding;
import com.polimigo.benevolent.helpers.SharedPrefrenceHelper;
import com.polimigo.benevolent.helpers.ToastMessage;
import com.polimigo.benevolent.models.Customer;
import com.polimigo.benevolent.models.DepositModel;
import com.polimigo.benevolent.models.WithdrawalModel;
import com.polimigo.benevolent.viewModel.CustomerViewModel;
import com.polimigo.benevolent.viewModel.WithDrawlsViewModel;
import com.polimigo.benevolent.views.CustomersScreen;
import com.polimigo.benevolent.views.SafeScreen;
import com.polimigo.benevolent.views.events.BaseEvent;

import org.jetbrains.annotations.NotNull;

public class WithDrawlsRepository {

    private static WithDrawlsRepository withDrawlsRepository;
    private FirebaseFirestore firebaseFirestore;
    private CollectionReference contactsCollectionReference;

    public static WithDrawlsRepository newInstance() {
        if (withDrawlsRepository == null) {
            withDrawlsRepository = new WithDrawlsRepository();
        }
        return withDrawlsRepository;
    }

    private WithDrawlsRepository() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        contactsCollectionReference = firebaseFirestore.collection("withDraw");
    }

    public void createDocument(WithdrawalModel model, final Context context) {
        contactsCollectionReference.add(model).addOnCompleteListener(command -> {
            if (command.isSuccessful()) {
                ToastMessage.addMessage("Data Saved", context);
                Intent i = new Intent(context.getApplicationContext(), SafeScreen.class);
                context.startActivity(i);
                ((Activity) context).finish();
            } else
                ToastMessage.addMessage("Saved Faild", context);
        });
    }

    public void getByCustomerId(Context context, String stringExtra, BaseEvent baseEvent, ActivityWithDrwalDialogBinding binding, double value) {
        contactsCollectionReference
                .whereEqualTo("customerId", stringExtra)
                .get().addOnCompleteListener(task -> {
            if (task.getResult().getDocuments().isEmpty()) {
                WithdrawalModel withdrawalModel = new WithdrawalModel();
                withdrawalModel.setDate("-");
                withdrawalModel.setMonth_name("-");
                WithDrawlsViewModel withDrawlsViewModel = new WithDrawlsViewModel(context,stringExtra,value,withdrawalModel);
                binding.setViewModel(withDrawlsViewModel);
                binding.executePendingBindings();
            }
            if (task.isSuccessful()) {
                for (DocumentSnapshot document : task.getResult()) {
                    WithdrawalModel taskItem = document.toObject(WithdrawalModel.class);
                    WithDrawlsViewModel withDrawlsViewModel = new WithDrawlsViewModel(context,stringExtra,value,taskItem);
                    binding.setViewModel(withDrawlsViewModel);
                    binding.executePendingBindings();
                }
            }
        });
    }


    public void getAllContacts(OnCompleteListener<QuerySnapshot> onCompleteListener) {
        contactsCollectionReference.get().addOnCompleteListener(onCompleteListener);
    }

}
