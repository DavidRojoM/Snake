package es.jesusmarin.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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

    protected Texture textura;
    protected static final String MIIMAGEN = "SpriteSnake.png";




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
        textura = new Texture(MIIMAGEN);
    }
    //Constructor copia
    public Pieza(Pieza otraPieza){
        posX = otraPieza.getPosX();
        posY = otraPieza.getPosY();
        ancho = otraPieza.getAncho();
        textura = new Texture(MIIMAGEN);
    }

    public int getPosX(){
        return posX;
    }
    public int getPosY(){
        return posY;
    }

    public int getAncho() {
        return ancho;
    }

//PINTARSE(usará la textura que hay en el estado y necesita un escenario -> batch)
    public void render(SpriteBatch miSB){
        miSB.begin();
        miSB.draw(this.textura,this.posX,this.posY,this.ancho,this.ancho);
        miSB.end();
    }

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
    public void dispose(){
        if (textura!=null){
            textura.dispose();
        }
    }

    //COLISIONA(Le pasamos una pieza y entonces nos dice si estan en el mismo sitio)
    public boolean colisiona(Pieza p){
        boolean colisionX, colisionY,resultado; //colisionRangoX, colisionRangoY;
        colisionX = (posX == p.getPosX());
        colisionY = (posY == p.getPosY());

        resultado = (colisionX && colisionY);// || (colisionRangoX || colisionRangoY);

        return resultado;
    }
}