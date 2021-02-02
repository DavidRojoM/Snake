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
        Pieza nuevaCabeza;
        nuevaCabeza = new Pieza(PosX,PosY,nancho);
        direccion = Pieza.ARR;
        cuerpo = new ArrayList();
        cuerpo.add(nuevaCabeza);
        //imgSerpiente= new Texture(file_serpiente);
    }

    //PINTATE
    public void render(SpriteBatch miSB){
        //Si la serpiente es un conjunto de piezas, para dibujar a la serpiente tendre que dibujar todas las piezas

        for (Pieza unaPieza: cuerpo){
            unaPieza.render(miSB);
        }
    }

    public void moverse(int direccion){
        this.crecer();

        cuerpo.remove(cuerpo.size()-1);
        /*
        switch (direccion){
            case Pieza.DER:
                1.Crear una pieza exactamente en la misma posicion que la cabeza
            2.Mover esa pieza a la derecha(Solo esto dentro del switch, lo demás fuera)
            3.Añadir esa pieza a mi conjunto
            4.Eliminar la ultima posicion del array(acordarnos de hacer el dispose)

                break;
            case IZQ:posX-= ancho; //IZQ
                break;
            case ARR: posY+= ancho; //ARR
                break;
            case ABA:posY-=ancho; //ABAJ
                break;

        }

         */
    }


    public void crecer(){//Lo mismo que moverse, esto es llamado en el controlador cada x frames
    Pieza nuevaCabeza;
    nuevaCabeza = new Pieza(cuerpo.get(0));
    nuevaCabeza.moverse(direccion);
    cuerpo.add(0,nuevaCabeza);
    }

    public void dispose(){//for y dispose de cada pieza

        for (Pieza unaPieza: cuerpo){
            unaPieza.dispose();
        }
    }
}
