package es.jesusmarin.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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

    protected int posX,posY,ancho,anchoAltoPantalla,borde,resuLittle;


    /////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTO
    //
    ///////////////////////////////////////////////////////////////////////

    //CONSTRUCTOR/ES
    public Serpiente(int PosX, int PosY, int nancho, int elMasChico,int borde,int resuLittle){
        Pieza nuevaCabeza;
        nuevaCabeza = new Pieza(PosX,PosY,nancho);

        this.anchoAltoPantalla = elMasChico;
        this.resuLittle = resuLittle;

        direccion = Pieza.DER;
        this.posX = PosX;
        this.posY = PosY;
        this.ancho = nancho;
        this.borde = borde;
        cuerpo = new ArrayList<>();
        cuerpo.add(nuevaCabeza);

    }
    public Serpiente(Serpiente antigua){
        Pieza nuevaCabeza;

        posX = antigua.getPosX();
        posY = antigua.getPosY();
        ancho = antigua.getAncho();
        borde = antigua.getBorde();
        resuLittle = antigua.getResuLittle();

        anchoAltoPantalla=antigua.getAnchoAltoPantalla();


        nuevaCabeza = new Pieza(posX,posY,ancho);

        direccion = Pieza.DER;// Aqui probablemente se coloca un parámetro con la dirección actual de la serpiente

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

    public int getBorde() {
        return borde;
    }

    public int getResuLittle() {
        return resuLittle;
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

        float limiteIzq,limiteDer;
        float limiteArr,limiteAba;

        Pieza cabeza= cuerpo.get(0);

        //multiplicamos elMasChico por el 100% de la pantalla menos el porcentaje equivalente al tamaño de la pieza (100/20)=0.05==0.95;
        limiteIzq = borde;
        limiteAba = resuLittle-borde;
        limiteDer = resuLittle-borde;
        limiteArr = borde;

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
