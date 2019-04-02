package com.example.parenteye;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ChildAdapter extends ArrayAdapter<custom_posts_returned> {
    private StorageReference userStorageRef= FirebaseStorage.getInstance().getReference("UserImages/");
    final long ONE_MEGABYTE = 1024 * 1024;
    public ChildAdapter(Activity context, ArrayList<custom_posts_returned> childreturned){

        super(context,0,childreturned);
    }


    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View childrenlist=convertView;
        if(childrenlist==null){
            childrenlist = LayoutInflater.from(getContext()).inflate(
                    R.layout.child_list, parent, false);

        }

        custom_posts_returned child= getItem(position);

        TextView childname=(TextView) childrenlist.findViewById(R.id.childname);
        childname.setText(child.getPost_owner_name());



       final  ImageView profileimage=(ImageView) childrenlist.findViewById(R.id.childphoto);

        if(child.getpost_owner_ID()!=null) {
            userStorageRef.child(child.getpost_owner_ID()).getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    final Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    profileimage.setImageBitmap(bm);

                }
            });

        }
        else{
            profileimage.setImageResource(R.drawable.defaultprofile);

        }





        return childrenlist;


    }

}