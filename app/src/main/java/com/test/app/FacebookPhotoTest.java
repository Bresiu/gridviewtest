package com.test.app;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;

public class FacebookPhotoTest extends Fragment {
    private static final String FACEBOOK_GRAPH_URL_START = "https://graph.facebook.com/";
    private static final String FACEBOOK_USER_ID = "100004473465451";
    private static final String FACEBOOK_GRAPH_URL_END = "/picture?height=";
    private static final int FACEBOOK_USER_PHOTO_SIZE = 100;
    private static final String FACEBOOK_PHOTO_NAME = "fbUserPhoto";
    Context context;
    ImageView imageView;
    ProgressBar progressBar;
    private Target target = new Target() {
        @Override
        public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.d("PHOTO TEST", "trying to save photo from fb into storage");
                    File file = new File(getStoragePathToPhoto(FACEBOOK_PHOTO_NAME));
                    try {
                        // file.createNewFile();
                        FileOutputStream outputStream = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, outputStream);
                        outputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        @Override
        public void onBitmapFailed(Drawable drawable) {
        }

        @Override
        public void onPrepareLoad(Drawable drawable) {
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.facebook_photo, container, false);
        initViews(rootView);
        new DownloadFilesTask().execute();

        return rootView;
    }

    private void initViews(View rootView) {
        imageView = (ImageView) rootView.findViewById(R.id.image);
        progressBar = (ProgressBar) rootView.findViewById(R.id.loading);
    }

    private void loadPhotoInto(final ImageView imageView, final ProgressBar progressBar) {
        File file = new File(getStoragePathToPhoto(FACEBOOK_PHOTO_NAME));
        Picasso.with(context).load(file).into(imageView, new Callback.EmptyCallback() {
            @Override
            public void onSuccess() {
                Log.d("PHOTO TEST", "user photo loaded from storage");
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                Log.e("PHOTO TEST", "error (probably no photo on storage)");
                downloadPhoto(imageView, progressBar);
            }
        });
    }

    private void downloadPhoto(final ImageView imageView, final ProgressBar progressBar) {
        Log.d("PHOTO TEST", "downloading new photo from fb");
        final String photoURL = buildFbPhotoURL(FACEBOOK_USER_ID);
        Log.d("PHOTO TEST", "path to fb photo: " + photoURL);

        Picasso.with(context)
                .load(photoURL)
                .into(imageView, new Callback.EmptyCallback() {
                    @Override
                    public void onSuccess() {
                        Log.d("PHOTO TEST", "user photo loaded from fb");
                        progressBar.setVisibility(View.GONE);
                        Picasso.with(context).load(photoURL).into(target);
                    }

                    @Override
                    public void onError() {
                        Log.e("PHOTO TEST", "error downloading photo from fb");
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    private String buildFbPhotoURL(String userId) {
        return FACEBOOK_GRAPH_URL_START +
                userId +
                FACEBOOK_GRAPH_URL_END +
                FACEBOOK_USER_PHOTO_SIZE;
    }

    private String getStoragePathToPhoto(String photoName) {
        return Environment.getExternalStorageDirectory().getPath() + "/" + photoName + ".jpg";
    }

    private class DownloadFilesTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            loadPhotoInto(imageView, progressBar);
            return null;
        }
    }
}
