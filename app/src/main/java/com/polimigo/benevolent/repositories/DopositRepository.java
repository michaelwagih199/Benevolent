package com.polimigo.benevolent.repositories;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.polimigo.benevolent.helpers.ToastMessage;
import com.polimigo.benevolent.models.DepositModel;
import com.polimigo.benevolent.views.SafeScreen;

public class DopositRepository {

    private static DopositRepository dopositRepository;
    private FirebaseFirestore firebaseFirestore;
    private CollectionReference contactsCollectionReference;

    public static DopositRepository newInstance() {
        if (dopositRepository == null) {
            dopositRepository = new DopositRepository();
        }
        return dopositRepository;
    }

    private DopositRepository() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        contactsCollectionReference = firebaseFirestore.collection("Deposit");
    }

    public void createDocument(DepositModel model, final Context context) {
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

    public void getAllContacts(OnCompleteListener<QuerySnapshot> onCompleteListener) {
        contactsCollectionReference.get().addOnCompleteListener(onCompleteListener);
    }

}
