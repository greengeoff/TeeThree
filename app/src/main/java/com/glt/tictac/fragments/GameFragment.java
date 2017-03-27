package com.glt.tictac.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.glt.tictac.R;
import com.glt.tictac.TTTView;
import com.glt.tictac.utils.Constants;

/**
 * Fragment displays a title and a TTTView which presents a tic-tac-toe board
 * and handles game logic.
 *
 * There are 3 game types - Human v. Human, Human v. Droid, and Droid v. Droid
 */

public class GameFragment extends Fragment {

    public String TAG = this.getClass().getSimpleName();

    public static String ARG_GAME_TYPE = "game_type";

    private TTTView boardView;
    private TextView gameTitle;

    private int[][] board;
    private int gameType;

    /**
     * Prepare board via game type arg from enclosing activity
     */
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_game, container, false);
        boardView = (TTTView)v.findViewById(R.id.ttt_View);

        // check for gameType argument and set game type accordingly
        Bundle arg = getArguments();
        if(arg != null){
            gameType = arg.getInt(ARG_GAME_TYPE);
            boardView.setGameType(gameType);
            boardView.clearBoard();
            gameTitle = (TextView)v.findViewById(R.id.gameTitle);
            setGameTitle(gameType);

        }
        return v;
    }

    private void setGameTitle(int gameType) {
        switch(gameType){
            case Constants.GAME_TYPE_HUMAN_HUMAN:
                gameTitle.setText(R.string.human_v_human);
                break;
            case Constants.GAME_TYPE_HUMAN_DROID:
                gameTitle.setText(R.string.human_v_droid);
                break;
            case Constants.GAME_TYPE_DROID_DROID:
                gameTitle.setText(R.string.droid_v_droid);
                break;
        }
    }

}
