package com.example.macosx.ltm.fragments.tab;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.macosx.ltm.R;
import com.example.macosx.ltm.activities.Chat;
import com.example.macosx.ltm.activities.FriendWallActivity;
import com.example.macosx.ltm.activities.Home;
import com.example.macosx.ltm.adapters.ListFriendAdapter;
import com.example.macosx.ltm.database.DbContext;
import com.example.macosx.ltm.database.models.User;
import com.example.macosx.ltm.network.NetworkManager;
import com.example.macosx.ltm.network.api.FriendService;
import com.example.macosx.ltm.network.response.FriendResponse;
import com.example.macosx.ltm.ultils.Dialog;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FriendTab.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FriendTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FriendTab extends Fragment {
    private static  String TAG = "FRIENDTAB";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static FriendTab instance = new FriendTab();
    RecyclerView listFriends;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_friend_tab, container, false);
        listFriends = view.findViewById(R.id.rv_list_friends);
        listFriends.setAdapter(new ListFriendAdapter());
        listFriends.setLayoutManager(new LinearLayoutManager(view.getContext()));
        listFriends.setHasFixedSize(true);
        Log.d(TAG, "onCreateView: ");
        getFriends(view.getContext());
        return view;

    }

    public void getFriends(final Context context) {
        FriendService friendService = NetworkManager.getInstance().create(FriendService.class);
        String url = "friends?id="+DbContext.getInstance().getCurrentUser().getId();
        if(listFriends!= null) {
            friendService.getAllFriends(url).enqueue(new Callback<FriendResponse>() {
                @Override
                public void onResponse(Call<FriendResponse> call, Response<FriendResponse> response) {
                    FriendResponse friendResponse = response.body();
                    if (friendResponse.getErrorCode().equals("0")) {
                        DbContext.getInstance().setListFriends(friendResponse.getListFriends());
                        listFriends.getAdapter().notifyDataSetChanged();

                    } else {
                        Dialog.instance.showMessageDialog(context, context.getString(R.string.error), friendResponse.getMsg());
                    }
                }

                @Override
                public void onFailure(Call<FriendResponse> call, Throwable throwable) {
                    Dialog.instance.showMessageDialog(context, context.getString(R.string.error), context.getString(R.string.failur_message));

                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        instance = this;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        instance = null;
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

    public void moveToFriendsWall(int id){
        Intent intent = new Intent(this.getContext(),FriendWallActivity.class);
        intent.putExtra("id",id);
       startActivity(intent);
    }

    public void moveToChat(int id_send, int id_receive){
        Intent intent = new Intent(this.getContext(), Chat.class);
        intent.putExtra("id_send",id_send);
        intent.putExtra("id_receive",id_receive);
        startActivity(intent);
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
