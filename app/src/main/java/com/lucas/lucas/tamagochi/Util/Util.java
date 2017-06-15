package com.lucas.lucas.tamagochi.Util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;

import com.lucas.lucas.tamagochi.Tamagochi.Tamagochi;

/**
 * Created by Lucas on 14/06/2017.
 */
public class Util
{
    public void AlertMessage(Context context){
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        final EditText editText = new EditText(context);
        alert.setMessage("Nome para ele");
        alert.setTitle("Digite um nome");
        alert.setView(editText);

        alert.setPositiveButton("Yes Option", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int whichButton)
            {
                String myText = editText.getText().toString();
                Tamagochi tamagochi = Tamagochi.getInstance();
                tamagochi.setName(myText);
            }
        });

        alert.setNegativeButton("No Option", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
            }
        });

        alert.show();
    }
}
