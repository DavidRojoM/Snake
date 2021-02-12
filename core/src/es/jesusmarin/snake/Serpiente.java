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

    protected int posX,posY,ancho;
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
        this.posX = posX;
        this.posY = posY;
        this.ancho = nancho;
        cuerpo = new ArrayList<>();
        cuerpo.add(nuevaCabeza);

        //imgSerpiente= new Texture(file_serpiente);
    }
    public Serpiente(Serpiente antigua){
        Pieza nuevaCabeza;

        posX = antigua.getPosX();
        posY = antigua.getPosY();
        ancho = antigua.getAncho();

        nuevaCabeza = new Pieza(posX,posY,ancho);

        direccion = Pieza.ARR;// Aqui probablemente se coloca un parámetro con la dirección actual de la serpiente

        cuerpo = new ArrayList<>();
        cuerpo.add(nuevaCabeza);

    }


    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getAncho() {
        return ancho;
    }

    public void moverse(){
        this.crecer();

        cuerpo.remove(cuerpo.size()-1);
    }


    public void crecer(){//Lo mismo que moverse, esto es llamado en el controlador cada x frames
    Pieza nuevaCabeza;
    Pieza cabezaAntigua = cuerpo.get(0);
    nuevaCabeza = new Pieza(cabezaAntigua);
    nuevaCabeza.moverse(direccion);
    cuerpo.add(0,nuevaCabeza);
    }

    //PINTATE
    public void render(SpriteBatch miSB){
        //Si la serpiente es un conjunto de piezas, para dibujar a la serpiente tendre que dibujar todas las piezas

        for (Pieza unaPieza: cuerpo){
            unaPieza.render(miSB);
        }
    }

    public void dispose(){//for y dispose de cada pieza

        for (Pieza unaPieza: cuerpo){
            unaPieza.dispose();
        }
    }
    //Comportamiento hasMuerto
    public boolean hasMuerto(){
        boolean resultado;

        if (cabezaChocaConCuerpo()){
            resultado = true;
        }else if (cabezaChocaConParedes()){

            resultado = true;
        }else{
            resultado = false;
        }
        return resultado;
    }
    //Testear el choque de la serpiente con su cuerpo
    private boolean cabezaChocaConCuerpo(){
        Pieza cabezaSerpiente = cuerpo.get(0);

        if (cuerpo.size()<4) return false;

        for (int i=4;i<cuerpo.size()-1;i++){
            if (cabezaSerpiente.colisiona(cuerpo.get(i))){
                return true;
            }
        }
        return false;
    }
    private boolean cabezaChocaConParedes(){
        //si he chocado con la izquierda true
        //si no he chocado con la izquierda false
        //si he chocado arriba true
        //si no he chocado arriba false
        //si no, false

        Pieza cabezaSerpiente = cuerpo.get(0);
        if (cabezaSerpiente.getPosX()<0) return false;
        else if (cabezaSerpiente.getPosY()<0) return false;
        else if (cabezaSerpiente.getPosX()>elMasChico) return false;
        else if (cabezaSerpiente.getPosY()>elMasChico) return false;

        //PREGUNTAR A ANDRES
    }

}
