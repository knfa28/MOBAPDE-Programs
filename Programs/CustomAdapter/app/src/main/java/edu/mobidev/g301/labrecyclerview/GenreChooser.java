package edu.mobidev.g301.labrecyclerview;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by ngoc on 1/27/2016.
 */
public class GenreChooser extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final String[] genreList = new String[]{
                "Classic",
                "Country",
                "EDM",
                "Jazz",
                "Pop",
                "Rap",
                "Rock"
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("Select Genre")
                .setItems(genreList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((AddSongActivity)getActivity()).onGenreSelected(genreList[which]);
                        dismiss();
                    }
                });

        return builder.create();
    }
}
