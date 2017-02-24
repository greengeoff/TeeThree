package com.glt.tictac;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity
        implements BoardFragment.TicTacListener {
    public static final String X_TOKEN = "X";
    public static final String Y_TOKEN = "O";
    public static final String EMPTY = "..";

    private boolean playerXTurn = true;
    private Button[] squares;
    private Button resetButton;
    private BoardFragment fragment;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getFragmentManager();
        fragment = (BoardFragment)fm.findFragmentById(R.id.tictacFragment);
        resetButton = fragment.getResetButton();
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetClicked();
            }
        });
        squares = fragment.getButtons();

        for( Button square : squares){
            square.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button b = (Button)view;
                    String str = b.getText().toString();
                    switch(str){
                        case X_TOKEN:
                        case Y_TOKEN:
                            break;
                        case EMPTY:
                            b.setText(playerXTurn ? X_TOKEN : Y_TOKEN);
                            nextTurn();
                    }
                }
            });
        }

        tv = (TextView)findViewById(R.id.turnPrompt);
        updateTurnPrompt();



    }

    @Override
    public void squareClicked(View v) {

    }

    @Override
    public void resetClicked() {
        playerXTurn = true;
        updateTurnPrompt();
        fragment.resetBoard();
    }
    private void nextTurn(){
        playerXTurn = ! playerXTurn;
        updateTurnPrompt();
    }
    private void updateTurnPrompt(){
        String str = getString(R.string.format_playerturn, playerXTurn? "X":"O");
        tv.setText(str);
    }
}
