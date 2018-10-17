package com.example.macosx.ltm.fragments.tab;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.macosx.ltm.R;
import com.example.macosx.ltm.activities.PostStatus;
import com.example.macosx.ltm.adapters.ListPostAdapter;
import com.example.macosx.ltm.database.models.Post;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeTab.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeTab extends Fragment {
    private static final String TAG = "HomeTab";
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
        Post post1 = new Post(1,"1","Quoc Viet Dang","12:32:11","chan qua",12,32);
        Post post2 = new Post(2,"2","Vuong Van huy","04:52:11","chan qua",3,16);
        Post post3 = new Post(3,"3","Do Trung Thanh","01:22:14","chan qua",5,8);
        Post post4 = new Post(4,"4","Le Thanh Minh","03:32:33","chan qua",1,9);
        listPostData.add(post1);
        listPostData.add(post2);
        listPostData.add(post3);
        listPostData.add(post4);
        listPost = view.findViewById(R.id.list_post);
        listPost.hasFixedSize();
        listPost.setAdapter(new ListPostAdapter(listPostData));
        listPost.setLayoutManager(new LinearLayoutManager(view.getContext()));

        statusPost = view.findViewById(R.id.status_post);
        statusPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.getContext().startActivity(new Intent(view.getContext(),PostStatus.class));
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
