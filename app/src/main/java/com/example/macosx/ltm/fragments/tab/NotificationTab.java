package com.example.macosx.ltm.fragments.tab;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.macosx.ltm.R;
import com.example.macosx.ltm.adapters.ListNotiAdapter;
import com.example.macosx.ltm.database.DbContext;
import com.example.macosx.ltm.database.models.Notification;
import com.example.macosx.ltm.network.NetworkManager;
import com.example.macosx.ltm.network.api.NotificationService;
import com.example.macosx.ltm.network.api.PostService;
import com.example.macosx.ltm.network.response.NotificationResponse;
import com.example.macosx.ltm.network.response.PostResponse;
import com.example.macosx.ltm.ultils.Dialog;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NotificationTab extends Fragment {
    public static NotificationTab instance ;
    private RecyclerView listNoti ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notification_tab, container, false);
        setupUI(view);
        return view;
    }

    private void setupUI(View view) {

        listNoti = view.findViewById(R.id.list_noti);
        listNoti.setHasFixedSize(true);

        listNoti.setAdapter(new ListNotiAdapter());
        listNoti.setLayoutManager(new LinearLayoutManager(view.getContext()));
        getAllNoti();
    }

    // TODO: Rename method, update argument and hook method into UI event

    public void getAllNoti( ) {

        NotificationService notificationService = NetworkManager.getInstance().create(NotificationService.class);
        String url = "noti?user_id="+DbContext.getInstance().getCurrentUser().getId();
        notificationService.getAllNotifications(url).enqueue(new Callback<NotificationResponse>() {
            @Override
            public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response) {
                NotificationResponse notificationResponse = response.body();
                if (notificationResponse.getErrorCode().equals("0")) {
                    DbContext.getInstance().setListNotifications(notificationResponse.getNotification());
                    listNoti.getAdapter().notifyDataSetChanged();

                }else{
                    Dialog.instance.showMessageDialog(getActivity(),getActivity().getString(R.string.error),notificationResponse.getMsg());
                }
            }

            @Override
            public void onFailure(Call<NotificationResponse> call, Throwable t) {
                Dialog.instance.showMessageDialog(getActivity(),getActivity().getString(R.string.error),getActivity().getString(R.string.failur_message));

            }

        });
    }

    @Override
    public void onResume() {
        super.onResume();
        instance=this;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
