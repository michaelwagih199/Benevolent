package com.polimigo.benevolent.repositories;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.polimigo.benevolent.databinding.ActivityAddRegisyterBindingImpl;
import com.polimigo.benevolent.helpers.SharedPrefrenceHelper;
import com.polimigo.benevolent.helpers.ToastMessage;
import com.polimigo.benevolent.models.Customer;
import com.polimigo.benevolent.viewModel.CustomerViewModel;
import com.polimigo.benevolent.views.CustomersScreen;
import com.polimigo.benevolent.views.adabters.CustomerAdapter;
import com.polimigo.benevolent.views.adabters.CustomerWithDrwalAdapter;
import com.polimigo.benevolent.views.events.BaseEvent;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class CustomerRepository {

    /* ContactsFirestoreManager object **/
    private static CustomerRepository customerRepository;

    private FirebaseFirestore firebaseFirestore;
    private CollectionReference contactsCollectionReference;
    SharedPrefrenceHelper sharedPrefrenceHelper = new SharedPrefrenceHelper();

    public static CustomerRepository newInstance() {
        if (customerRepository == null) {
            customerRepository = new CustomerRepository();
        }
        return customerRepository;
    }


    private CustomerRepository() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        contactsCollectionReference = firebaseFirestore.collection("register");
    }

    public void createDocument(Customer model, final Context context) {
        contactsCollectionReference
                .whereEqualTo("name", model.getName())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.getResult().getDocuments().isEmpty()) {
                        contactsCollectionReference.add(model).addOnCompleteListener(command -> {
                            ToastMessage.addMessage("user saved Succesfully", context);
                            context.startActivity(new Intent(context, CustomersScreen.class));
                            ((Activity) context).finish();
                        });
                    } else {
                        ToastMessage.addMessage("User Already Saved", context);
                        ((Activity) context).finish();
                    }
                });
    }

    public void getAllContacts(OnCompleteListener<QuerySnapshot> onCompleteListener) {
        contactsCollectionReference.get().addOnCompleteListener(onCompleteListener);
    }


    public void getAllCustomer(@NotNull final Context mContext, @NotNull final RecyclerView recycleCustomer) {
        contactsCollectionReference
                .get().addOnCompleteListener((OnCompleteListener<QuerySnapshot>) task -> {
            if (task.isSuccessful()) {
                ArrayList<Customer> list = new ArrayList<>();
                for (DocumentSnapshot document : task.getResult()) {
                    Customer taskItem = document.toObject(Customer.class);
                    list.add(taskItem);
                    CustomerAdapter nurslyRecyclerViewAdapter = new CustomerAdapter(list, mContext);
                    recycleCustomer.setAdapter(nurslyRecyclerViewAdapter);
                }
                Log.d("Tag", list.toString());
            }
        });
    }

    public void getAllCustomerInWithDrawl(@NotNull final Context mContext, @NotNull final RecyclerView recycleCustomer) {
        contactsCollectionReference
                .get().addOnCompleteListener((OnCompleteListener<QuerySnapshot>) task -> {
            if (task.isSuccessful()) {
                ArrayList<Customer> list = new ArrayList<>();
                for (DocumentSnapshot document : task.getResult()) {
                    Customer taskItem = document.toObject(Customer.class);
                    list.add(taskItem);
                    CustomerWithDrwalAdapter customerAdapter  = new CustomerWithDrwalAdapter(list, mContext);
                    recycleCustomer.setAdapter(customerAdapter);
                }
                Log.d("Tag", list.toString());
            }
        });
    }

    public void updateContact(Customer contact , Context context) {
        String documentId = contact.getDocumentId();
        DocumentReference documentReference = contactsCollectionReference.document(documentId);
        documentReference.set(contact);
        context.startActivity(new Intent(context, CustomersScreen.class));
        ((Activity) context).finish();
    }

    public void deleteContact(String documentId) {
        DocumentReference documentReference = contactsCollectionReference.document(documentId);
        documentReference.delete();
    }


    public void getCustomerById(Context context, String stringExtra, BaseEvent baseEvent, ActivityAddRegisyterBindingImpl binding) {
        contactsCollectionReference.document(stringExtra).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                            Customer taskItem = task.getResult().toObject(Customer.class);
                            CustomerViewModel customerViewModel = new CustomerViewModel(context, taskItem, baseEvent, "Update Register");
                            binding.setViewModel(customerViewModel);
                            binding.executePendingBindings();

                    }
                });


    }
}
