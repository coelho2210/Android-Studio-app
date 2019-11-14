package silva.diceout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // field to hold the roll result text
    TextView rollResult;



    // field to hold the score
    int score;

   // field to hold the random number generator
    Random rand;


 //fields to hold  the dice value
    int die1;
    int die2;
    int die3;


    //field to hold the score text
    TextView scoreText;



    //arrayList to hold all three dice values
    ArrayList<Integer> dice;

    //ArrayList to holt all three dice images
    ArrayList<ImageView> diceImageViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               rollDice(view);
            }
        });

        //set initial score
        score = 0;


        // linked an instance in Java to the widget in my activity layout
        rollResult = findViewById(R.id.rollResult);

        scoreText = (TextView) findViewById(R.id.scoreText);


        //initialize the random number generator
        rand = new Random();

        //Create ArrayList container for the dice value
        dice = new ArrayList<Integer>();

        ImageView die1Image = findViewById(R.id. die1Image);
        ImageView die2Image = findViewById(R.id. die2Image);
        ImageView die3Image = findViewById(R.id. die3Image);


        diceImageViews = new ArrayList<ImageView>();

        diceImageViews.add(die1Image);
        diceImageViews.add(die2Image);
        diceImageViews.add(die3Image);

        //create greeting
        Toast.makeText(getApplicationContext(),"Welcome to DiceOut",Toast.LENGTH_SHORT).show();

     }

       public void rollDice(View  v) {
           rollResult.setText("clicked!");

           //Roll dice
           die1 = rand.nextInt(6) + 1;
           die2 = rand.nextInt(6) + 1;
           die3 = rand.nextInt(6) + 1;


           //set dice in the arrayList
           dice.clear();
           dice.add(die1);
           dice.add(die2);
           dice.add(die3);


           for(int dieOfSet = 0; dieOfSet < 3; dieOfSet++){

               String imageName =  "die_" +  dice.get(dieOfSet) + ".png";

               //get asset based o nthe file name
               //create a drawable  based on the asset
               // and set the drawable as the image for the image view in the array list  of image view

               try{
                   InputStream stream = getAssets().open(imageName);
                   Drawable d = Drawable.createFromStream(stream,null);
                   diceImageViews.get(dieOfSet).setImageDrawable(d);

               }catch (IOException e){
                   e.printStackTrace();
               }
           }

           //build message with the result
           String msg;

           if (die1 == die2 && die1 == die3) {

               //triples
               int scoreDelta = die1 *100;
               msg = "You rolled a teriple " + die1 + "! You score " + scoreDelta + " points!";
               score += scoreDelta;

           } else if( die1 == die2 || die1 == die3 || die2 == die3){

               //Doubles
               msg = "You rolled doubles for 50 points!";
               score +=50;
           } else {
               msg ="You didn't socrew this roll. Try again!";
           }


           //update the app to display the result message
           rollResult.setText(msg);
           scoreText.setText("Score: " + score);

      }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
