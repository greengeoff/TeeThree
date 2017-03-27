package com.glt.tictac;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import com.glt.tictac.utils.BoardUtils;
import com.glt.tictac.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import com.glt.tictac.utils.Constants;

/**
 * Class that runs and displays a tic-tac-toe game
 */

public class TTTView extends View {

    // used for checking game status
    private GameChecker gameChecker;

    //provides an droip opponent
    private DroidPlayer droid;

    // creates delay for more natural droid play
    private Handler handler;

    private int gameType;

    private Paint textPaint;
    private Paint gridPaint;
    private Paint winPaint;




    /**
     * encapsulates coordinates and character for drawing marks on board
     */
    class DrawData {
        int x,y;
        char c;

        public DrawData(int x, int y, char c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }

    private int[][] board = {
            {0,0,0},
            {0,0,0},
            {0,0,0}};

    // drawable data
    private List<DrawData> drawData;

    //used to convert int to char token
    private final char[] charMap = {' ','X','O'};

    // constants for building a UI based on ratios rather
    // than fixed values
    public final float LETTER_PADDING = 0.07f;
    public final float SQUARE_LENGTH = 1.0f / 3.0f;
    public final float LETTER_SIDE_LENGTH = 0.26f;

    // constructor
    public TTTView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }



    private void init(){

        setSaveEnabled(true);

        gameChecker = new GameChecker();

        // init array list of drawable marks, X's and O's
        drawData = new ArrayList<>();

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.DKGRAY);


        gridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        gridPaint.setStrokeWidth(8);

        winPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        winPaint.setStrokeCap(Paint.Cap.ROUND);
        winPaint.setStrokeWidth(32);
        winPaint.setColor(getResources().getColor(R.color.winningRed));
    }

    public void setGameType(int gameType) {
        //System.out.println(gameType + "  game type");
        this.gameType = gameType;
        clearBoard();


        //GAME_TYPE_HUMAN_HUMAN has lowest value, 0 of {0,1,2}
        //a droid player will play at least one side in this case
        if (gameType > Constants.GAME_TYPE_HUMAN_HUMAN) {
            handler = new Handler();
            droid = new DroidPlayer();
        }
        // no humans, begin asking AI for moves
        if (gameType == Constants.GAME_TYPE_DROID_DROID) {
                getDroidMove();
            }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // user touch events should not interfere with a droid-droid
        if(gameType == Constants.GAME_TYPE_DROID_DROID)
            return true;

        // user touch should only interfere if current player is 'X'
        if(gameType == Constants.GAME_TYPE_HUMAN_DROID
                && gameChecker.getNextPlayerToken(board) == 2)
            return true;

        float x = event.getX();
        float y = event.getY();

        // String s = " Touched at ("+x+","+y+")";
        // Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();;
        int xInd = (int) ((y / getHeight()) * board.length);
        int yInd = (int) ((x/ getWidth()) * board.length);

        if( xInd > 2 ) xInd = 1;
        if( yInd > 2 ) yInd = 2;

        // ignore touches on occupied squares
        if (board[xInd][yInd] != 0){
            //System.out.println("skip touch");
            return true;
        }

        //System.out.println("now playing " + gameChecker.getNextPlayerToken(board) + " at " + xInd+" , "+yInd);
        board[xInd][yInd] = gameChecker.getNextPlayerToken(board);

        //BoardUtils.printBoard(board);
        inflateDrawData();
        postInvalidate();

        if ( gameType == Constants.GAME_TYPE_HUMAN_DROID){
            getDroidMove();
        }
        return true;

    }

    private void getDroidMove() {
        //System.out.println("droid move");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                board = droid.makeMoveFor(gameChecker.getNextPlayerToken(board) , board);
                inflateDrawData();
                postInvalidate();

                BoardUtils.printBoard(board);
                if(gameChecker.getNextPlayerToken(board) != 0
                        && gameType == Constants.GAME_TYPE_DROID_DROID) {
                    getDroidMove();
                }
            }
        }, 500);


    }
    // unused
    private void swapBoard(int[][] board){
        this.board = board;
        inflateDrawData();
    }
    public void clearBoard() {
        this.board = new int[][]{
                {0,0,0},
                {0,0,0},
                {0,0,0}};
        inflateDrawData();
        postInvalidate();
    }

    // convert int board into a list of drawables
    private void inflateDrawData(){
        char aChar;
        drawData.clear();
        if(board != null){
            for (int i = 0; i < board.length; i ++){
                for(int j = 0; j < board[i].length; j++){
                    aChar = charMap[  board[i][j]  ];
                    drawData.add(new DrawData( i , j, aChar ));
                }
            }
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    // set Measured dimensions (width,height) equal to create a square shape
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int size = width > height ? height : width;
        setMeasuredDimension(size, size);

    }



    @Override
    protected void onDraw(Canvas canvas) {


        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        float nSquareLength = width * SQUARE_LENGTH;
        float nLetterPadding = width * LETTER_PADDING;

        float nLetterSideLength = width * LETTER_SIDE_LENGTH;
        textPaint.setTextSize(width * LETTER_SIDE_LENGTH);

        //System.out.println("width is " + width);

        canvas.drawLine(nSquareLength, 0, nSquareLength, height, gridPaint);
        canvas.drawLine(nSquareLength * 2, 0, nSquareLength * 2, height, gridPaint);

        canvas.drawLine(0, nSquareLength, width, nSquareLength, gridPaint);
        canvas.drawLine(0, nSquareLength * 2, width, nSquareLength * 2, gridPaint);

        //System.out.println(drawData);
        for (DrawData d : drawData){
            // TODO change data type from char
            canvas.drawText(String.valueOf(d.c), d.y * nSquareLength + nLetterPadding
                                ,( 1 + d.x) * nSquareLength - nLetterPadding,textPaint);
            //System.out.println(d);

        }
        int status = gameChecker.gameStatus(board);
        int winType = -1;

        if (status == GameChecker.XplayerWins)
                winType = GameChecker.getWinType(1,board);
        else if (status == GameChecker.OplayerWins)
            winType = GameChecker.getWinType(2,board);

        if( winType > -1){
            switch (winType){
                case Constants.RESULT_WIN_0_2:
                    canvas.drawLine(0, nSquareLength/2, width, nSquareLength/2, winPaint);
                    break;
                case Constants.RESULT_WIN_3_5:
                    canvas.drawLine(0, nSquareLength + (nSquareLength/2),
                            width, nSquareLength + (nSquareLength/2), winPaint);
                    break;
                case Constants.RESULT_WIN_6_8:
                    canvas.drawLine(0, nSquareLength*2 + (nSquareLength/2),
                            width, nSquareLength*2 + (nSquareLength/2), winPaint);
                    break;
                case Constants.RESULT_WIN_0_6:
                    canvas.drawLine(nSquareLength/2, 0, nSquareLength/2, height, winPaint);
                    break;
                case Constants.RESULT_WIN_1_7:
                    canvas.drawLine(nSquareLength + (nSquareLength/2),
                            0, nSquareLength + (nSquareLength/2), height, winPaint);
                    break;
                case Constants.RESULT_WIN_2_8:
                    canvas.drawLine(nSquareLength*2 + (nSquareLength/2),
                            0, nSquareLength*2 + (nSquareLength/2), height, winPaint);
                    break;
                case Constants.RESULT_WIN_0_8:
                    canvas.drawLine(0, 0, width, height, winPaint);
                    break;
                case Constants.RESULT_WIN_6_2:
                    canvas.drawLine(0, height, width, 0, winPaint);
                    break;
            }
        }



    }
}
