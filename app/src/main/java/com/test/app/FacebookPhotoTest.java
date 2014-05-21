package com.test.app;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;

public class FacebookPhotoTest extends Fragment {
    Context context;

    ImageView imageView;
    ProgressBar progressBar;

    String currentUrl = "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-ash3/t1.0-1/s200x200/252231_1002029915278_1941483569_n.jpg";
    String filename;
    private Target target = new Target() {
        @Override
        public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    File file = new File(filename);
                    try {
                        file.createNewFile();
                        FileOutputStream ostream = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, ostream);
                        ostream.close();
                        Log.d("PHOTO SAVED", "PHOTO SAVED");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
            if (placeHolderDrawable != null) {
            }
        }
    };

    public static Bitmap loadFromFile(String filename) {
        try {
            File f = new File(filename);
            if (!f.exists()) {
                return null;
            }
            Bitmap tmp = BitmapFactory.decodeFile(filename);
            return tmp;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("PHOTO SAVED", "ON CREATE");
        View rootView = inflater.inflate(R.layout.facebook_photo, container, false);
        imageView = (ImageView) rootView.findViewById(R.id.image);

        filename = Environment.getExternalStorageDirectory().getPath() + "/actress_wallpaper.jpg";

        /*
        Picasso.with(context)
                .load(currentUrl)
                .into(imageView, new Callback.EmptyCallback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        progressBar.setVisibility(View.GONE);
                    }
                });

        Picasso.with(context)
                .load(currentUrl)
                .into(target);
                */
        //Bitmap bmp = loadFromFile(filename);

        //BitmapFactory.Options options = new BitmapFactory.Options();
        //options.inSampleSize = 2;
        //Bitmap bm = BitmapFactory.decodeFile(filename, options);
        //imageView.setImageBitmap(bm);
        File file = new File(filename);
        Picasso.with(context).load(file).into(imageView);

        return rootView;
    }
}
