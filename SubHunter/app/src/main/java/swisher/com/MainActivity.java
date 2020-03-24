package swisher.com;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.util.Log;
import android.widget.ImageView;

import java.util.Random;


public class MainActivity extends Activity {


    int numberHorizontalPixels;
    int numberVerticalPixels;
    int blockSize;
    int gridWidth = 40;
    int gridHeight;
    float horizontalTouched;
    float verticalTouched;
    int subHorizontalPosition;
    int subVerticalPosition;
    boolean hit = false;
    int shotsTaken;
    int distanceFromSub;
    boolean debugging = false;

    ImageView gameView;
    Bitmap blankBitmap;
    Canvas canvas;
    Paint paint;

    /*runs this code just before
     the player sees the app.*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_main);

        //Get the current device's screen resolution
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        //Initialize our size based variables
        //based on the screen res
        numberHorizontalPixels = size.x;
        numberVerticalPixels = size.y;
        blockSize = numberHorizontalPixels / gridWidth;
        gridHeight = numberVerticalPixels / blockSize;

        //Initialize objects ready for drawing
        blankBitmap = Bitmap.createBitmap(numberHorizontalPixels, numberVerticalPixels,
                Bitmap.Config.ARGB_8888);
        canvas = new Canvas(blankBitmap);
        gameView = new ImageView(this);
        paint = new Paint();

        //Tell Android to set our drawing as the view for this app
        setContentView(gameView);


        Log.d("Debugging", "In onCreate");
        newGame();
        draw();
    }


    /*
    this code will execute whe a new
    game starts, when a the app launches
    and after player restarts
     */
    void newGame() {
        Random random = new Random();
        subHorizontalPosition = random.nextInt(gridWidth);
        subVerticalPosition = random.nextInt(gridHeight);
        shotsTaken = 0;

        Log.d("Debugging", "In newGame");


    }

    /*
    this will do all the drawing
     grid, HUD, and touch indicator, and boom/hit
     */
    void draw() {
        gameView.setImageBitmap(blankBitmap);

        //wipe the screen with white
        canvas.drawColor(Color.argb(255, 255, 255, 255));

        //Change paint color to black
        paint.setColor(Color.argb(255, 0, 0, 0));

        //draw long lines
        for (int i = 0; i < gridWidth; i++) {
            canvas.drawLine(blockSize * i, 0, blockSize * i,
                    numberVerticalPixels - 1, paint);
        }

        //draw latitude lines
        for (int i = 0; i < gridHeight; i++) {
            canvas.drawLine(0, blockSize * i, numberHorizontalPixels - 1,
                    blockSize * i, paint);
        }


        //Resize text for score and distance
        paint.setTextSize(blockSize * 2);
        paint.setColor(Color.argb(255, 0, 0, 255));
        canvas.drawText(
                "Shots Taken: " + shotsTaken +
                        " Distance: " + distanceFromSub, blockSize, blockSize * 1.75f, paint);

        //draw shot on grid
        for (int i = 0; i < gridHeight; i++) {
            canvas.drawLine(0, blockSize * i, numberHorizontalPixels, blockSize * i, paint);
        }

        //draw players shot
        canvas.drawRect(horizontalTouched * blockSize, verticalTouched * blockSize,
                (horizontalTouched * blockSize) + blockSize,
                (verticalTouched * blockSize) + blockSize, paint);

        Log.d("Debugging", "In draw");

        if(debugging == true) {
            printDebuggingText();
        }

    }


    /*
    the will handle touch detection
     */
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Log.d("Debugging", "In onTouchEvent");
        boolean touch = false;


        //has the player removed their finger from the screen
        if ((motionEvent.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            takeShot(motionEvent.getX(), motionEvent.getY());
            touch = true;
        }
        return touch;

    }


    /*
    This will handle what happens after a touch
    calculate distance from sub and
    decide if was a hit or a miss
     */
    void takeShot(float touchX, float touchY) {
        Log.d("Debugging", "In takeshot");

        shotsTaken++;


        horizontalTouched = (int) touchX / blockSize;
        verticalTouched = (int) touchY / blockSize;

        //hit the sub?
        hit = (horizontalTouched == subHorizontalPosition)
                && (verticalTouched == subVerticalPosition);


        //how far away was shot from sub
        int horizontalGap = (int) horizontalTouched - subHorizontalPosition;
        int verticalGap = (int) verticalTouched - subVerticalPosition;

        //use Pythag to get distance
        distanceFromSub = (int) Math.sqrt(
                ((horizontalGap * horizontalGap) + (verticalGap * verticalGap)));

        //if theres a hit call BOOM
        if (hit == true) {
            boom();
        } else draw();//else call draw

    }


    /*
    says BOOM!
     */
    void boom() {
        gameView.setImageBitmap(blankBitmap);
        // Wipe the screen with a red color
        canvas.drawColor(Color.argb(255, 255, 0, 0));
        // Draw some huge white text
        paint.setColor(Color.argb(255, 255, 255, 255));
        paint.setTextSize(blockSize * 10);
        canvas.drawText("BOOM!", blockSize * 4, blockSize * 14, paint);
        // Draw some text to prompt restarting
        paint.setTextSize(blockSize * 2);
        canvas.drawText("Take a shot to start again", blockSize * 8, blockSize * 18, paint);
        // Start a new game
        newGame();
    }


    /* debugging text
     */
    void printDebuggingText() {

        paint.setTextSize(blockSize);
        canvas.drawText("numberHorizontalPixels = " + numberHorizontalPixels, 50, blockSize * 3, paint);
        canvas.drawText("numberVerticalPixels = " + numberVerticalPixels, 50, blockSize * 4, paint);
        canvas.drawText("blockSize = " + blockSize, 50, blockSize * 5, paint);
        canvas.drawText("gridWidth = " + gridWidth, 50, blockSize * 6, paint);
        canvas.drawText("gridHeight = " + gridHeight, 50, blockSize * 7, paint);
        canvas.drawText("horizontalTouched = " + horizontalTouched, 50, blockSize * 8, paint);
        canvas.drawText("verticalTouched = " + verticalTouched, 50, blockSize * 9, paint);
        canvas.drawText("subHorizontalPosition = " + subHorizontalPosition, 50, blockSize * 10, paint);
        canvas.drawText("subVerticalPosition = " + subVerticalPosition, 50, blockSize * 11, paint);
        canvas.drawText("hit = " + hit, 50, blockSize * 12, paint);
        canvas.drawText("shotsTaken = " + shotsTaken, 50, blockSize * 13, paint);
        canvas.drawText("debugging = " + debugging, 50, blockSize * 14, paint);

    }

}
