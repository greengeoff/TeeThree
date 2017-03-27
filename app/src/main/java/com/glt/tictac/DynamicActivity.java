package com.glt.tictac;

import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.glt.tictac.fragments.GameFragment;
import com.glt.tictac.fragments.MenuFragment;

/**
 * Activity that encloses two fragments: Game Fragment and MenuFragment.
 * On larger screens, both are displayed simultaneously;
 * on smaller, only one.
 */
public class DynamicActivity extends AppCompatActivity
implements MenuFragment.MenuListener {

    GameFragment gameFragment;
    MenuFragment menuFragment;

    private static String GAME_FRAGMENT = "game_fragment";
    private static String MENU_FRAGMENT = "menu_fragment";

    private static String EXTRA_GAME_IN_PROGRESS = "progress_extra";

    private int gameType;
    private boolean gameInProgress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // checks device specks and locks portrait on phone or small tablet
        if(getResources().getBoolean(R.bool.portrait_only)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        setContentView(R.layout.activity_dynamic);


        if (findViewById(R.id.fragment_container) != null // portrait, and first load
                && savedInstanceState == null) {

            menuFragment = new MenuFragment();

            getSupportFragmentManager().beginTransaction()

                    .add(R.id.fragment_container, menuFragment, MENU_FRAGMENT)
                    .commit();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy(); // Add this line
    }

    // implement the MenuFragment listener
    @Override
    public void gameTypeChosen(int gameType) {
        this.gameType = gameType;
        this.gameInProgress = true;

        // portrait orientation - engage a fragment transaction to display GameFragment
        if(findViewById(R.id.fragment_container) != null){

            GameFragment gameFragment = new GameFragment();
            Bundle args = new Bundle();

            args.putInt(GameFragment.ARG_GAME_TYPE, gameType);
            gameFragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, gameFragment)
                    .addToBackStack(null)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
        }else{
            TTTView board = (TTTView)findViewById(R.id.ttt_View);
            board.clearBoard();
            board.setGameType(gameType);

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(EXTRA_GAME_IN_PROGRESS, gameInProgress);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // if a game is in progress, display game fragment
        if(findViewById(R.id.fragment_container )!= null) {
            gameInProgress = savedInstanceState.getBoolean(EXTRA_GAME_IN_PROGRESS, false);

            if (gameInProgress) {
                //if(getSupportFragmentManager().findFragmentByTag(GAME_FRAGMENT)== null) {
                gameFragment = new GameFragment();
                //}
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, gameFragment, GAME_FRAGMENT)
                        .addToBackStack(null)
                        .commit();
            }
        }

    }
}
