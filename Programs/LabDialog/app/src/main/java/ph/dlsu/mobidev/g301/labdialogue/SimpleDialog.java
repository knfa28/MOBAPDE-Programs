package ph.dlsu.mobidev.g301.labdialogue;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by G301 on 1/19/2016.
 */
public class SimpleDialog extends DialogFragment {

    View v;

   public Dialog onCreateDialog(Bundle savedInstanceState){

       v = LayoutInflater.from(getActivity())
               .inflate(R.layout.dialog_input,null);

       AlertDialog.Builder diagBuild = new AlertDialog.Builder(getActivity())
               .setTitle("Wassup!")
               .setMessage("Eyow, wuz good boi?")
               .setView(v)
               .setPositiveButton("am aight", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       EditText editText = (EditText) v.findViewById(R.id.nameEdit);
                       ((MainActivity) getActivity()).OnYesSelected(editText.getText().toString());
                   }
               })
               .setNegativeButton("am not your boi, bro", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       EditText editText = (EditText) v.findViewById(R.id.nameEdit);
                       ((MainActivity) getActivity()).OnNoSelected(editText.getText().toString());
                   }
               });

       return diagBuild.create();
   }
}
