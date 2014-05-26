package com.test.app;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DummyDownloadService extends Service {

    private static final String TAG = "BUS";
    private static final String URL = "http://en.wikipedia.org/w/api.php?action=query&generator=" +
            "random&prop=extracts&exchars=500&format=json&callback=onWikipedia";

    private OkHttpClient client = new OkHttpClient();

    @Override
    public void onCreate() { //
        super.onCreate();
        Log.d(TAG, "Service onCreated");
        work();
    }

    private void work() {
        new BackgroundWebRunner().execute();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Service onDestroyed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class BackgroundWebRunner extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... arg0) {
            String result = null;
            try {
                result = get(new URL(URL));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            post(result);
        }

        private void run(String url) throws IOException {
            String result = get(new URL(url));
            post(result);
        }

        private void post(String result) {
            BusProvider.getInstance().register(this);
            BusProvider.getInstance().post(result);
            BusProvider.getInstance().unregister(this);
        }

        String get(URL url) throws IOException {
            HttpURLConnection connection = client.open(url);
            InputStream in = null;
            try {
                // Read the response.
                in = connection.getInputStream();
                byte[] response = readFully(in);
                return new String(response, "UTF-8");
            } finally {
                if (in != null) in.close();
            }
        }

        byte[] readFully(InputStream in) throws IOException {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            for (int count; (count = in.read(buffer)) != -1; ) {
                out.write(buffer, 0, count);
            }
            return out.toByteArray();
        }
    }
}