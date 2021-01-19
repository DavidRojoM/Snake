package es.jesusmarin.snake;

import com.badlogic.gdx.Gdx;

public class Pieza {

    /////////////////////////////////////////////////////////////////////////
    //
    //ESTADO
    //
    ///////////////////////////////////////////////////////////////////////
    public final static int DER = 1;
    public final static int IZQ = 2;
    public final static int ARR = 3;
    public final static int ABA = 4;

    protected int posX;

    protected int posY;

//ALTO (ANCHO = ALTO)
    protected int ancho;

//TEXTURA




    /////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTO
    //
    ///////////////////////////////////////////////////////////////////////


    //CONSTRUCTOR(necesita posiciones de partida, ancho y no necesitamos textura)

    public Pieza(int posNX, int posNY, int nAncho){
        posX=posNX;
        posY=posNY;
        ancho=nAncho;
    }
    public int getPosX(){
        return posX;
    }
    public int getPosY(){
        return posY;
    }

    //PINTARSE(usará la textura que hay en el estado y necesita un escenario -> batch)

    //MOVERSE (afectará a posX y posY, pero nunca a las 2 a la vez)

    public void moverse(int direccion){
        switch (direccion){
            case DER:posX += ancho; //DER
            break;
            case IZQ:posX-= ancho; //IZQ
            break;
            case ARR: posY+= ancho; //ARR
            break;
            case ABA:posY-=ancho; //ABAJ
            break;
        }
    }

    //DISPOSE

    //COLISIONA(Le pasamos una pieza y entonces nos dice si estan en el mismo sitio)
    public boolean colisiona(Pieza p){
        boolean colisionX, colisionY,resultado, colisionRangoX, colisionRangoY;
        colisionX = posX == p.getPosX();
        colisionY = posY == p.getPosY();
        colisionRangoX = posX<0 || posX> Gdx.graphics.getWidth();
        colisionRangoY = posY<0 || posY> Gdx.graphics.getHeight();
        resultado = (colisionX && colisionY) || (colisionRangoX || colisionRangoY);
        return resultado;
    }

    //COPIATE





}