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
    protected static final String MIIMAGEN = "SpriteSnake.jpg";
    /*
    protected static final String MIIMAGENGRANDE = "SpriteSnakeG.jpg";
    protected static final String MIIMAGENPEQ = "SpriteSnakeP.jpg";

     */




    /////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTO
    //
    ///////////////////////////////////////////////////////////////////////


    //Constructor(necesita posicion de partida,ancho y no necesitamos textura)
    public Pieza(int posNX, int posNY, int nAncho){
        posX = posNX;
        posY = posNY;
        ancho = nAncho;
        textura = new Texture(MIIMAGEN);
    }


    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
    //Pintarse(usara textura y neceita un escenario batch)

    public void render(SpriteBatch miSB){
        miSB.begin();
        miSB.draw(this.textura,this.posX,this.posY,this.ancho,this.ancho);
        miSB.end();
    }

    //Moverse(mover en x o y nunca  a la vez)
    public void moverse(int direccion){
        switch (direccion){
            case DER:
                posX += ancho;
                break;
            case IZQ:
                posX -= ancho;
            case ARR:
                posY += ancho;
                break;
            case ABA:
                posY -= ancho;
                break;
        }
    }
    //Dispose
    public void dispose(){
        textura.dispose();
    }

    //COLISIONA(Le pasamos una pieza y entonces nos dice si estan en el mismo sitio)
    public boolean colisiona(Pieza p){
        boolean colisionX, colisionY,resultado; //colisionRangoX, colisionRangoY;
        colisionX = (posX == p.getPosX());
        colisionY = (posY == p.getPosY());
        /*
        colisionRangoX = posX<0 || posX> Gdx.graphics.getWidth();
        colisionRangoY = posY<0 || posY> Gdx.graphics.getHeight();

         */
        resultado = (colisionX && colisionY);// || (colisionRangoX || colisionRangoY);
        return resultado;
    }

    //COPIATE



}