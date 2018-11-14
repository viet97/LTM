package com.example.macosx.ltm.fragments.tab;

import android.app.Fragment;
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
import android.widget.EditText;
import android.widget.TextView;

import com.example.macosx.ltm.R;
import com.example.macosx.ltm.activities.Home;
import com.example.macosx.ltm.activities.PostStatus;
import com.example.macosx.ltm.adapters.ListPostAdapter;
import com.example.macosx.ltm.database.DbContext;
import com.example.macosx.ltm.database.models.Post;
import com.example.macosx.ltm.network.NetworkManager;
import com.example.macosx.ltm.network.api.FriendService;
import com.example.macosx.ltm.network.api.PostService;
import com.example.macosx.ltm.network.response.FriendResponse;
import com.example.macosx.ltm.network.response.PostResponse;
import com.example.macosx.ltm.ultils.Dialog;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeTab.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeTab extends Fragment {
    public int id = -1;
    private static final String TAG = "HomeTab";
    public static HomeTab instance = new HomeTab();
    private RecyclerView listPost;
    private ArrayList<Post> listPostData;
    EditText statusPost;
    TextView pickImage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void setupUI(final View view) {
        listPostData = new ArrayList<>();

        listPost = view.findViewById(R.id.list_post);
        listPost.setAdapter(new ListPostAdapter());
        listPost.setLayoutManager(new LinearLayoutManager(view.getContext()));

        statusPost = view.findViewById(R.id.status_post);
        statusPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.getContext().startActivity(new Intent(view.getContext(),PostStatus.class));
            }
        });
        if (DbContext.getInstance().getListFriends().size() == 0) {
            getFriends(view.getContext());
        }else {
            getAllPost(view.getContext(), id);
        }


    }
    private void getFriends(final Context context) {

            FriendService friendService = NetworkManager.getInstance().create(FriendService.class);
            String url = "friends?id=" + DbContext.getInstance().getCurrentUser().getId();
            friendService.getAllFriends(url).enqueue(new Callback<FriendResponse>() {
                @Override
                public void onResponse(Call<FriendResponse> call, Response<FriendResponse> response) {
                    FriendResponse friendResponse = response.body();
                    if (friendResponse.getErrorCode().equals("0")) {
                        DbContext.getInstance().setListFriends(friendResponse.getListFriends());
                        getAllPost(context, id);
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
    @Override
    public void onResume() {
        super.onResume();
        instance = this;
        getAllPost(getActivity(),id);
    }

    public void getAllPost(final Context context, int id ) {
        if (id == -1){
            id = DbContext.getInstance().getCurrentUser().getId();
        }
        PostService postService = NetworkManager.getInstance().create(PostService.class);
        String url = "send_posts?id="+id;
        postService.getAllPosts(url).enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                PostResponse postResponse = response.body();
                if (postResponse.getErrorCode().equals("0")) {
                    DbContext.getInstance().setListPosts(postResponse.getPosts());
                    listPost.getAdapter().notifyDataSetChanged();

                }else{
                    Dialog.instance.showMessageDialog(context,context.getString(R.string.error),postResponse.getMsg());
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                Dialog.instance.showMessageDialog(context,context.getString(R.string.error),context.getString(R.string.failur_message));

            }

        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home_tab, container, false);
        setupUI(view);
        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
