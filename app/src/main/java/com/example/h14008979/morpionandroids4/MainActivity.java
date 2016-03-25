package com.example.h14008979.morpionandroids4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonname;
    private ImageButton case00, case01, case02, case10, case11, case12, case20, case21, case22;
    private Button boutonRejouer;
    private ImageButton[] tabBouton;
    boolean part = true;
    // X = true ; O = false;
    int part_count = 0;
   /* private TableRow partie;
    private TextView vict;
    private TextView def;
    private TextView nul;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.jeu);
        setContentView(R.layout.jeu2);

        boutonRejouer = (Button)findViewById(R.id.boutonRejouer);

        //on recupère tous les boutons case00 partir de leurs ids
        this.case00 = (ImageButton)findViewById(R.id.case00);
        this.case01 = (ImageButton)findViewById(R.id.case01);
        this.case02 = (ImageButton)findViewById(R.id.case02);
        this.case10 = (ImageButton)findViewById(R.id.case10);
        this.case11 = (ImageButton)findViewById(R.id.case11);
        this.case12 = (ImageButton)findViewById(R.id.case12);
        this.case20 = (ImageButton)findViewById(R.id.case20);
        this.case21 = (ImageButton)findViewById(R.id.case21);
        this.case22 = (ImageButton)findViewById(R.id.case22);

        //on insère tout les button dans un tableau
        this.tabBouton = new ImageButton[]{case00, case01, case02, case10, case11, case12, case20, case21, case22};

        //on asscocie le clic case00 chaque bouton

        for (ImageButton b : tabBouton){
            b.setOnClickListener(this);
        }

        boutonRejouer.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                part = true;
                part_count = 0;
                zero(true);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify case00 parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onClick(View v) {

        Button b = (Button)v;
        buttonClicked((ImageButton) v);
    }

    public void buttonClicked(ImageButton b){

        if(part){
            b.setImageResource(R.drawable.croix.png);
        }
        else {
            b.setImageResource(R.drawable.cercle.png);
        }

        part_count++;
            /*case01.setBackgroundColor(Color.YELLOW);*/
        b.setClickable(false);
        part=!part;

        gagner();

    }

    private void gagner(){

        //on verifie l'horizontale
        boolean gagne = false;
        if(case00.getText() == case01.getText() && case01.getText() == case02.getText() && !case00.isClickable()) {
            gagne = true;
        }
        else if(case10.getText() == case11.getText() && case11.getText() == case12.getText() && !case10.isClickable()) {
            gagne = true;
        }

        else if(case20.getText() == case21.getText() && case21.getText() == case22.getText() && !case20.isClickable()) {
            gagne = true;
        }


        //on verifie la verticale

        else if(case00.getText() == case10.getText() && case10.getText() == case20.getText() && !case00.isClickable()){

            gagne = true;
        }

        else if(case01.getText() == case11.getText() && case11.getText() == case21.getText() && !case11.isClickable()){

            gagne = true;
        }

        else if(case02.getText() == case12.getText() && case12.getText() == case22.getText() && !case22.isClickable()){

            gagne = true;
        }

        //on verifie la diagonale

        else if(case00.getText() == case11.getText() && case11.getText() == case22.getText() && !case00.isClickable()){

            gagne = true;
        }

        else if(case02.getText() == case11.getText() && case11.getText() == case20.getText() && !case11.isClickable()){

            gagne = true;
        }

        if(gagne){

            if(!part){

                toast("X case00 gagné !");
            }
            else{
                toast("O case00 gagné !");
            }

            //remettre à zero tous les bouttons
            zero(false);

        }else if (part_count == 9 ){

            toast("match nul... ");
        }
    }

    private void zero(boolean choix){

        for (Button b: tabBouton){

            b.setClickable(choix);


            if (choix){

                b.setText("");
            }
            else {

                /*case01.setBackgroundColor(Color.);*/
            }
        }
    }

    private void toast(String message){

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }



}