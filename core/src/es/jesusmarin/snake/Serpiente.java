package es.jesusmarin.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class Serpiente {

    /*
    * Sabemos que una serpiente es un conjunto de piezas, por lo tanto tenemos que usar la
    * clase pieza en algun momento.
    *
    *
    * */




    /////////////////////////////////////////////////////////////////////////
    //
    //ESTADO
    //
    ///////////////////////////////////////////////////////////////////////

    protected ArrayList<Pieza> cuerpo;
    protected int direccion;
    //private Pieza piezaSerpiente;
    //protected Texture imgSerpiente;
    //protected String file_serpiente = "serpiente.png";



    /////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTO
    //
    ///////////////////////////////////////////////////////////////////////

    //CONSTRUCTOR/ES
    public Serpiente(int PosX, int PosY, int nancho){
        cuerpo = new ArrayList();
        cuerpo.add(new Pieza(PosX,PosY, nancho));
        //imgSerpiente= new Texture(file_serpiente);
    }

    //PINTATE
    public void render(SpriteBatch miSB){
        //Si la serpiente es un conjunto de piezas, para dibujar a la serpiente tendre que dibujar todas las piezas

        for (int i=0;i< cuerpo.size();i++){
            cuerpo.get(i).render(miSB);
        }
    }

    public void moverse(int direccion){
        switch (direccion){
            case Pieza.DER:
                /* 1.Crear una pieza exactamente en la misma posicion que la cabeza
            2.Mover esa pieza a la derecha(Solo esto dentro del switch, lo demás fuera)
            3.Añadir esa pieza a mi conjunto
            4.Eliminar la ultima posicion del array(acordarnos de hacer el dispose)
            */
                break;
            case IZQ:posX-= ancho; //IZQ
                break;
            case ARR: posY+= ancho; //ARR
                break;
            case ABA:posY-=ancho; //ABAJ
                break;
        }
    }

    public void crecer(){//Lo mismo que moverse, esto es llamado en el controlador cada x frames

    }

    public void dispose(){//for y dispose de cada pieza
        for (int i=0;i<cuerpo.size();i++){
            cuerpo.get(i).dispose();
        }
    }
}
