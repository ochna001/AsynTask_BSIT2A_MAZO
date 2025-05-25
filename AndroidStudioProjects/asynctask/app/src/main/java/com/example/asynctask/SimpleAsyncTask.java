package com.example.simpleasynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

/**
 * SimpleAsyncTask extends AsyncTask to perform a background operation
 * (sleeping for a random amount of time) and then updates the UI when done.
 */
public class SimpleAsyncTask extends AsyncTask<Void, Void, String> {

    // Weak reference to the TextView to avoid memory leaks
    private WeakReference<TextView> mTextView;

    /**
     * Constructor that takes a TextView to update
     * @param tv The TextView to update when the task is complete
     */
    public SimpleAsyncTask(TextView tv) {
        mTextView = new WeakReference<>(tv);
    }

    /**
     * Override to perform a computation on a background thread
     */
    @Override
    protected String doInBackground(Void... voids) {
        // Generate a random number between 0 and 10
        Random r = new Random();
        int n = r.nextInt(11);

        // Make it a longer sleep by multiplying by 200
        int s = n * 200;

        // Sleep for the random amount of time
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Return a String result
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

    /**
     * Runs on the UI thread after doInBackground()
     */
    @Override
    protected void onPostExecute(String result) {
        // Get a reference to the TextView and update it
        TextView tv = mTextView.get();

        // Check if the reference is still valid
        if (tv != null) {
            tv.setText(result);
        }
    }
}