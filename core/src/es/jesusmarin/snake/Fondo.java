package es.jesusmarin.snake;

import com.badlogic.gdx.graphics.Texture;

public class Fondo {
    /////////////////////////////////////////////////////////////////////////////////////
    //
    //ESTADO
    //
    /////////////////////////////////////////////////////////////////////////////////////
    private Texture imgFondo; //Nuestra imagen a pintar.

    private int posX;
    private int posY;
    private int anchoVentana;
    private int altoVentana;
    private int ancho;
    private int alto;

    private static final String FILE_FONDO1 = "fondo1.png";
    /////////////////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTOS
    //
    /////////////////////////////////////////////////////////////////////////////////////


    //CONSTRUCTOR/ES
    public Fondo(String fichero, int anV, int alV) {

        altoVentana = alV;
        anchoVentana = anV;
        posX = anchoVentana/2;
        posY = altoVentana/2;
        imgFondo = new Texture(fichero);
        Fondo fondo1 = new Fondo(FILE_FONDO1,anV,alV);


    }
}
