package es.jesusmarin.snake;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/*public class Fondo {
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

    private static final String FILE_FONDO1 = "fondo1.png"; //Nombre del archivo.

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
        ancho = imgFondo.getWidth();
        alto = imgFondo.getHeight();
    }

    //método para pintar el fondo
    public void pintate(SpriteBatch miSB) {

        TextureRegion ventana;

        ventana = new TextureRegion(imgFondo,posX,posY,anchoVentana,altoVentana);

        miSB.begin();
        miSB.draw(ventana,0,0);
        miSB.end();
    }

    //DISPOSE
    public void dispose() {
        imgFondo.dispose();
    }

    //Metodos para obtener el alto y ancho de la imagen del fondo para las colisiones.
    public int getAltoFondo() {
        return (int)imgFondo.getHeight();
    }

    public int getAnchoFondo() {
        return (int)imgFondo.getWidth();
    }
}

 */
