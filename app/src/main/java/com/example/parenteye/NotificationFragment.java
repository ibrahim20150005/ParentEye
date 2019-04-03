package com.example.parenteye;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {

    private RecyclerView recyclerView;
    private NotificationAdapter notificationAdapter;
    private List<Notifications> notificationsList;
    public boolean HAVE_NOTIFICATIONS;


    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.notification_fragment, container, false);


        //recyclerView hold notification_recycler_view
        recyclerView = view.findViewById(R.id.notification_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        notificationsList = new ArrayList<>();

        notificationAdapter = new NotificationAdapter(getContext(), notificationsList);

        recyclerView.setAdapter(notificationAdapter);

        readNotifications();


        //Toast.makeText(getActivity(), "you have no notifications yet", Toast.LENGTH_LONG).show();


        return view;

    }



    //********************* read notifications current user from fire base and but in notification list
    //********************* put it in adapter

    private void readNotifications() {
        //FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        //String userId = "EzH9MI0WJ7N0AwZNUidC45e2wiP2";


        DatabaseReference notification_reference = database.getReference("notifications").child(user.getUid());
        //if (notification_reference.getDatabase()!=null) {
        ChildEventListener nchildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.exists()) {
                    HAVE_NOTIFICATIONS = true;


                    Notifications notification = dataSnapshot.getValue(Notifications.class);
                    notificationsList.add(notification);
                    Collections.reverse(notificationsList);
                    notificationAdapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };


        notification_reference.addChildEventListener(nchildEventListener);



       /* notification_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                notificationsList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Notifications notifications = snapshot.getValue(Notifications.class);
                    notificationsList.add(notifications);
                }

                Collections.reverse(notificationsList);
                notificationAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
*/


        // }else{
        //HAVE_NOTIFICATIONS=false;

        // }
    }
}










  /*                              /// function to push notification

    //user id <who commented >  , publisher Id < who publish the post>

    // add notification on likes
    private  void addNotificationsOfLikes(String userid,String postid)
    {
       FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference notification_reference = database.getReference("notifications/likes").child(userid);

        HashMap<String, Object> hashMap =new HashMap<>();
        hashMap.put("userId ", userid); // or hashMap.put("userid ", firebaseUser.getUid());
        hashMap.put("NotificationMessage","liked your post");
        hashMap.put("postId ", postid);
        hashMap.put("isPost ", true);


        notification_reference.push().setValue(hashMap);

        //to call ----->  addNotificationsOfLikes(post.getpublisher() //getUserId\\ , post.getPostId())

    }


    //*************************************

    // add notification on Comments
        // needs to comment content and <who commented > and < who publish the post>
    // parameter can be comment text , user id <who commented >  , publisher Id < who publish the post>

    private  void addNotificationsOfComments()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference notification_reference = database.getReference("notifications/comments").child(PublisherId);

        HashMap<String, Object> hashMap =new HashMap<>();
        hashMap.put("userId ", userid); // or hashMap.put("userid ", firebaseUser.getUid());
        hashMap.put("NotificationMessage","commented:"+addcomment.getText().toString());
        hashMap.put("postId ", "");

        hashMap.put("isPost ", false);

        notification_reference.push().setValue(hashMap);


        //to call ----->  addNotificationsOfFollowers(post.getpublisher() //getUserId\\ )
    }


    //*************************************

    // add notification on followers putton on post
    private  void addNotificationsOfFollowers(String userid)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference notification_reference = database.getReference("notifications/follow").child(publisherid);

        HashMap<String, Object> hashMap =new HashMap<>();
        hashMap.put("userId ", userid); // or hashMap.put("userid ", firebaseUser.getUid());
        hashMap.put("NotificationMessage","started follows you ");
        hashMap.put("postId ", "");

        hashMap.put("isPost ", false);

        notification_reference.push().setValue(hashMap);


        //to call ----->  addNotificationsOfFollowers(post.getpublisher() //getUserId\\ )
    }

// add notification on followers putton on profile
    private  void addNotificationsOfFollowers(String userid)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference notification_reference = database.getReference("notifications").child(profile id );

        HashMap<String, Object> hashMap =new HashMap<>();
        hashMap.put("userId ", userid); // or hashMap.put("userid ", firebaseUser.getUid());
        hashMap.put("NotificationMessage","started follows you ");
        hashMap.put("postId ", "");

        hashMap.put("isPost ", false);

        notification_reference.push().setValue(hashMap);


        //to call ----->  addNotificationsOfFollowers(post.getpublisher() //getUserId\\ )
    }

*/




/************************************************** notification icon click listener*/
