package me.alhaz.movieconsumer.data;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import me.alhaz.movieconsumer.views.movies.list.LoadNotesCallback;

import java.lang.ref.WeakReference;

public class GetData extends AsyncTask<Void, Void, Cursor> {

    private final WeakReference<Context> weakContext;
    private final WeakReference<LoadNotesCallback> weakCallback;

    public GetData(Context context, LoadNotesCallback callback) {
        weakContext = new WeakReference<>(context);
        weakCallback = new WeakReference<>(callback);
    }

    @Override
    protected Cursor doInBackground(Void... voids) {
        return weakContext.get().getContentResolver().query(DataConnector.getContentURI(), null, null, null, null);
    }

    @Override
    protected void onPostExecute(Cursor data) {
        super.onPostExecute(data);
        weakCallback.get().postExecute(data);
    }
}
