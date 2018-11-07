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
import com.example.macosx.ltm.database.models.Notification;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NotificationTab.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NotificationTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationTab extends Fragment {
    private RecyclerView listNoti ;
    private ArrayList<Notification> listNotiData;
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
        listNotiData = new ArrayList<>();
        listNoti = view.findViewById(R.id.list_noti);
        listNoti.setHasFixedSize(true);
        Notification noti1 = new Notification("1","dang quoc viet da binh luan vao bai viet cua ban","12:33:11");
        Notification noti2 = new Notification("1","Do Trung thanh da binh luan vao bai viet cua ban","12:33:11");
        listNotiData.add(noti1);
        listNotiData.add(noti2);
        listNoti.setAdapter(new ListNotiAdapter(this.listNotiData));
        listNoti.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    // TODO: Rename method, update argument and hook method into UI event


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
