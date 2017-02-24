package com.glt.tictac;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;

/**
 * Created by gltrager on 8/19/16.
 */

public class BoardFragment extends Fragment {
    public interface TicTacListener {
        public void squareClicked(View v);

        public void resetClicked();
    }

    private TicTacListener mListener;

    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, resetButton;
    private Button[] buttons = {btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9};

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mListener = (TicTacListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        GridLayout gl = (GridLayout) v.findViewById(R.id.gridView);
        for (int i = 0; i < gl.getChildCount(); i++) {
            buttons[i] = (Button) gl.getChildAt(i);
        }

        resetButton = (Button) v.findViewById(R.id.resetButton);
        resetBoard();
        return v;
    }

    public void resetBoard() {
        for (Button b : buttons) {
            b.setText("  ");
        }
    }

    public Button getResetButton() {
        return resetButton;
    }

    public void setResetButton(Button resetButton) {
        this.resetButton = resetButton;
    }

    public Button[] getButtons() {
        return buttons;
    }

    public void setButtons(Button[] buttons) {
        this.buttons = buttons;
    }
}