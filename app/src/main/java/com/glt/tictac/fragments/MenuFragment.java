package com.glt.tictac.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.glt.tictac.R;
import com.glt.tictac.utils.Constants;

/**
 * Created by gltrager on 3/7/17.
 */

public class MenuFragment extends Fragment {

    public final String TAG = this.getClass().getSimpleName();

    public interface MenuListener{
        void gameTypeChosen(int gameType);
    }
    MenuListener listener;

    private Button humanHumanButton;
    private Button humanDroidButton;
    private Button droidDroidButton;

    public static final int HUMAN_HUMAN_GAME = 0;
    public static final int HUMAN_DROID_GAME = 1;
    public static final int DROID_DROID_GAME = 2;
    public static String gameTypeExtra = "gameType";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_menu,container, false);

        humanHumanButton = (Button)view.findViewById(R.id.humanHumanbutton);
        humanDroidButton = (Button)view.findViewById(R.id.humDroidbutton);
        droidDroidButton = (Button)view.findViewById(R.id.droidDroidButton);

        humanHumanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.gameTypeChosen(HUMAN_HUMAN_GAME);
            }
        });
        humanDroidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.gameTypeChosen(HUMAN_DROID_GAME);
            }
        });
        droidDroidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.gameTypeChosen(DROID_DROID_GAME);
            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener = (MenuListener)context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()
                    +" must implement MenuListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
