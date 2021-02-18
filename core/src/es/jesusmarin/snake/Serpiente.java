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

    protected int posX,posY,ancho,anchoReal,altoReal,anchoAltoPantalla;

    //private Pieza piezaSerpiente;
    //protected Texture imgSerpiente;
    //protected String file_serpiente = "serpiente.png";



    /////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTO
    //
    ///////////////////////////////////////////////////////////////////////

    //CONSTRUCTOR/ES
    public Serpiente(int posX, int posY, int nancho, int anchoAltoPantalla,int anchoReal,int altoReal){
        Pieza nuevaCabeza;
        nuevaCabeza = new Pieza(posX,posY,nancho);

        this.anchoAltoPantalla = anchoAltoPantalla;

        this.anchoReal = anchoReal;
        this.altoReal = altoReal;

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

        anchoAltoPantalla=antigua.getAnchoAltoPantalla();
        anchoReal=antigua.getAnchoReal();
        altoReal=antigua.getAltoReal();

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

    public int getAnchoAltoPantalla() {
        return anchoAltoPantalla;
    }

    public int getAnchoReal() {
        return anchoReal;
    }

    public int getAltoReal() {
        return altoReal;
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

        return (cabezaChocaConCuerpo() || cabezaChocaConParedes());
    }
    //Testear el choque de la serpiente con su cuerpo
    private boolean cabezaChocaConCuerpo(){
        Pieza cabezaSerpiente = cuerpo.get(0);

        if (cuerpo.size()<4) return false;

        for (int i=4;i<cuerpo.size()-1;i++){
            if (cuerpo.get(i).colisiona(cabezaSerpiente)){
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

        /*Pieza cabezaSerpiente = cuerpo.get(0);
        if (cabezaSerpiente.getPosX()<0) return false;
        else if (cabezaSerpiente.getPosY()<0) return false;
        else if (cabezaSerpiente.getPosX()>anchoAltoPantalla) return false;
        else if (cabezaSerpiente.getPosY()>anchoAltoPantalla) return false;
        else return true;

         */
        //PREGUNTAR A ANDRES
        float limiteIzq,limiteDer;
        float limiteArr,limiteAba;

        Pieza cabeza= cuerpo.get(0);

        /*limiteIzq = (anchoReal-anchoAltoPantalla)/2;
        limiteDer = limiteIzq + anchoAltoPantalla;
        limiteArr = (altoReal-anchoAltoPantalla)/2;
        limiteAba = limiteArr + anchoAltoPantalla;

         */

        limiteIzq = 0;
        limiteAba = Gdx.graphics.getHeight();
        limiteDer = Gdx.graphics.getWidth();
        limiteArr = 0;
        return (cabeza.getPosX()<limiteIzq || cabeza.getPosX()>limiteDer ||
                cabeza.getPosY()<limiteArr || cabeza.getPosY()>limiteAba);

    }

    public void cambiaDireccion(EstadoTeclado miTeclado){
        //En funcion de la direccion de la serpiente, mirando el teclado , decido que direccion nueva tomar

        switch (this.direccion){
            case Pieza.ABA:
            case Pieza.ARR:
                if (miTeclado.isTeclaDer()){
                this.direccion = Pieza.DER;
            }else {
                this.direccion = Pieza.IZQ;
            }
            break;
            case Pieza.DER:
            case Pieza.IZQ:
                if (miTeclado.isTeclaAbajo()){
                    direccion = Pieza.ABA;
                }else{
                    direccion = Pieza.ARR;
                }
                break;
        }
    }

}
