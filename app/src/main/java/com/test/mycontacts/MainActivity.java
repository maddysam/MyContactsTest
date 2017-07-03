package com.test.mycontacts;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.mycontacts.database.AdminDatabaseHelper;
import com.test.mycontacts.model.ContactsModel;
import com.test.mycontacts.response.ContactsResponse;
import com.test.mycontacts.rest.ApiClient;
import com.test.mycontacts.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    RecyclerView recyclerView;
    private ProgressDialog pDialog;
    private static final String TAG = MainActivity.class.getSimpleName();
    AdminDatabaseHelper adminDatabaseHelper;
    private List<ContactsModel> dataArrayList = new ArrayList<>();
Button btn_refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_refresh=(Button)findViewById(R.id.btn_refresh);
        adminDatabaseHelper = new AdminDatabaseHelper(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataArrayList.clear();
        dataArrayList = adminDatabaseHelper.getContactsFromDb();
        getDataFromServer();


        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataFromServer();
            }
        });

     /* if (CheckInternetConection
                .isInternetConnection(this)) {

        } else {
            try {
                CustomDialogUtil.showDialogOk(getResources()
                                .getString(R.string.internet_discription), getResources()
                                .getString(R.string.internet_title),
                        this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }


    public void getDataFromServer() {
        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage(getResources().getString(R.string.dialog_msg));
        pDialog.setCancelable(false);
        pDialog.show();
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ContactsResponse> call = apiService.getData(ApiClient.TOKEN);
        call.enqueue(new Callback<ContactsResponse>() {
            @Override
            public void onResponse(Call<ContactsResponse> call, Response<ContactsResponse> response) {

                Log.e(TAG, response.toString());
                Log.e(TAG, "response.body().getSucess()" + response.body().getSucess());
                Log.e(TAG, "response.body().getDataFromServer()" + response.body().getResult());

                List<ContactsModel> movies = response.body().getResult();
                List<ContactsModel> model = movies;
                for (int i = 0; i < model.size(); i++) {
                    String name = model.get(i).getName();
                    String uid = model.get(i).getUid();
                    adminDatabaseHelper.add_cart(new ContactsModel(name, uid));
                    Log.e("...", "name+uid" + name + uid);
                    Log.e("model", "adminDatabaseHelper.getCategorytemp()" + adminDatabaseHelper.getContactsFromDb().toString());
                }
                dataArrayList=    adminDatabaseHelper.selectContacts();
                //adminDatabaseHelper.getContactsFromDb()
                recyclerView.setAdapter(new ContactsAdapter(dataArrayList, R.layout.list_stories, getApplicationContext()));
                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ContactsResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                pDialog.dismiss();
            }
        });
    }


    public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder> {

        private List<ContactsModel> contacts;
        private int rowLayout;
        private Context context;


        public class ContactViewHolder extends RecyclerView.ViewHolder {
            LinearLayout moviesLayout;
            TextView movieTitle;
            TextView data;
            ImageView delete;

            public ContactViewHolder(View v) {
                super(v);
                moviesLayout = (LinearLayout) v.findViewById(R.id.adapter_layout);
                movieTitle = (TextView) v.findViewById(R.id.title);
                delete = (ImageView) v.findViewById(R.id.delete);


            }
        }

        public ContactsAdapter(List<ContactsModel> contacts, int rowLayout, Context context) {
            this.contacts = contacts;
            this.rowLayout = rowLayout;
            this.context = context;
        }

        @Override
        public ContactViewHolder onCreateViewHolder(ViewGroup parent,
                                                    int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
            return new ContactViewHolder(view);
        }


        @Override
        public void onBindViewHolder(final ContactViewHolder holder, final int position) {

            holder.movieTitle.setText(contacts.get(position).getName());
            Log.e("...", "/////" + contacts.get(position).getName());


            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("delete", "delete" + dataArrayList.get(position).getId());
                    ContactsModel contactsModel= new ContactsModel();
                    contactsModel.setIsDeleted(1);
                    contactsModel.setUid(dataArrayList.get(position).getUid());
                    contactsModel.setName(dataArrayList.get(position).getName());
                    contactsModel.setId(dataArrayList.get(position).getId());
                 //   adminDatabaseHelper.deleteCartValue(contactsModel);
                    adminDatabaseHelper.addSecondTable(contactsModel);
                     contacts.remove(position);
                    notifyDataSetChanged();
                }


            });


        }

        @Override
        public int getItemCount() {
            return contacts.size();
        }
    }

}

