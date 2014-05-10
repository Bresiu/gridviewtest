package com.test.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class DeleteActivityDialogFragment extends android.app.DialogFragment {

    protected OnDialogClickedListener callback = null;
    private String name;

    public static DeleteActivityDialogFragment newInstance(String name) {
        DeleteActivityDialogFragment deleteActivityDialogFragment =
                new DeleteActivityDialogFragment();

        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        deleteActivityDialogFragment.setArguments(bundle);

        return deleteActivityDialogFragment;
    }

    public void setOnDialogClickedListener(OnDialogClickedListener l) {
        callback = l;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name = getArguments().getString("name");
        Log.d("DIALOG TEST", "Bundle: " + name);
        Toast.makeText(MainActivity.getContext(), "BUNDLE NAME: " + name, Toast.LENGTH_SHORT);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Usunac Aktywnosc?" + name)
                .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d("DIALOG TEST", "On Positive Click");
                        callback.onDialogClicked(true, name);
                        dismiss();
                    }
                })
                .setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d("DIALOG TEST", "On Negative Click");
                        callback.onDialogClicked(false, name);
                        dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public interface OnDialogClickedListener {
        public abstract void onDialogClicked(boolean deleted, String name);
    }
}
