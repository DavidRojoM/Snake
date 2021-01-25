package es.jesusmarin.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class Serpiente {
    /////////////////////////////////////////////////////////////////////////
    //
    //ESTADO
    //
    ///////////////////////////////////////////////////////////////////////

    private ArrayList<Pieza> Serpiente;
    private Pieza piezaSerpiente;
    private int posX;
    private int posY;
    private Texture imgSerpiente;
    private String file_serpiente = "serpiente.png";



    /////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTO
    //
    ///////////////////////////////////////////////////////////////////////

    //CONSTRUCTOR/ES
    public Serpiente(int PosX, int PosY, int nancho){
        Serpiente = new ArrayList();
        Serpiente.add(new Pieza(PosX,PosY, nancho));
        imgSerpiente= new Texture(file_serpiente);
    }

    //PINTATE
    public void pintate(SpriteBatch miSB){
        miSB.begin();
        miSB.draw(imgSerpiente, Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        miSB.end();
    }

    public void moverse(){

    }

    public void crecer(){

    }

    public void dispose(){

    }
}
