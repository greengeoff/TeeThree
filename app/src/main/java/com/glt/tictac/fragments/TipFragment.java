package com.glt.tictac.fragments;

import android.app.Activity;

import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.glt.tictac.R;

import java.util.Random;

/**
 * Created by gltrager on 2/24/17.
 */

public class TipFragment extends Fragment {
    private static final Random RAND = new Random();
    private TextSwitcher switcher;
    private String[] ticTacTips ;
    private int switcherIndex = 0;
    private int tipCount;

    private Context context;

    public interface SwitchListener{
        public void touchedTip();
    }
    private SwitchListener listener;
    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_tip_switcher, container, false);
        switcher = (TextSwitcher)v.findViewById(R.id.textSwitcher);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.context = getActivity();

        switcher.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
                // TODO Auto-generated method stub
                // create new textView and set the properties like clolr, size etc
                TextView myText = new TextView(context);
                myText.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                myText.setTextSize(24);
                myText.setTextColor(Color.BLUE);
                myText.setBackgroundColor(getResources().getColor(R.color.switcherGray));
                return myText;
            }
        });
        // Declare the in and out animations and initialize them
        Animation in = AnimationUtils.loadAnimation(context,android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(context,android.R.anim.fade_out);

        // set the animation type of textSwitcher
        switcher.setInAnimation(in);
        switcher.setOutAnimation(out);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                switcherIndex++;
                // If index reaches maximum reset it
                if(switcherIndex ==  tipCount)
                    switcherIndex = 0;
                if(switcher !=null) {
                    switcher.setText(ticTacTips[switcherIndex]);
                }

            }
        });



    }

    @Override
    public void onStart() {
        ticTacTips = context.getResources()
                .getStringArray(R.array.ticTips);
        tipCount = ticTacTips.length;
        super.onStart();
    }

    @Override
    public void onStop() {

        super.onStop();
    }
}
