package es.jesusmarin.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import javax.xml.soap.Text;

/**
 * Clase que implementa el controlador de nuestro videojuego. Realizará la gestión de la entrada del teclado,
 * la gestión de la inicialización, del control del estado del videojuego
 */

public class ControladorJuego {


    /////////////////////////////////////////////////////////////////////////
    //
    //ESTADO
    //
    ///////////////////////////////////////////////////////////////////////

    //CONSTANTES
    protected final static String IMAGEN_INICIO = "imgInicio.png";
    protected final static String IMAGEN_FIN = "imgFin.png";
    protected final int TIEMPO_CRECER = 240;
    protected final int TIEMPO_MOVER = 60;


    //Esto es un truco que pone un objeto ControladorJuego en la memoria ram en el espacio asignado a la clase ControladorJuego
    private static ControladorJuego miControlador;



    //Objetos que controla el controlador

    //Una serpiente
    protected Serpiente snake;

    //un batch
    protected SpriteBatch batch;

    //Imagenes splash para inicio y final (Hay que meter una imagen en assets y en el estado para el inicio del juego y otra para game over)
    protected Texture imgInicial;
    protected Texture imgFinal;


    //El simulador de teclado

    EstadoTeclado et;

    //Enumeracion para la "maquina de estados" del controlador

    enum VideoJuego {
            INICIO,
            JUGANDO,
            FINALIZADO
    }
    //Estado del controlador
    protected VideoJuego controladorVG;

    //Contador
    protected int controlTiempo;

    protected int anchoPantalla,altoPantalla;
    /////////////////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTOS
    //
    /////////////////////////////////////////////////////////////////////////////////////
private ControladorJuego(){
    controladorVG = VideoJuego.INICIO;
    batch = new SpriteBatch();
    imgInicial = new Texture(IMAGEN_INICIO);
    imgFinal = new Texture(IMAGEN_FIN);
    //et = new EstadoTeclado();
    controlTiempo = 1;

}

public static ControladorJuego getInstance(int posXinicial,int posYinicial,int ancho){
    if (ControladorJuego.miControlador==null){
        miControlador = new ControladorJuego();
        miControlador.setSnake(new Serpiente(posXinicial,posYinicial,ancho));
    }
    return ControladorJuego.miControlador;
}
private void pantallaInicio(){

}
private void controlaEstadoJugando(){


    //si tengo que mover la serpiente o crecer la serpiente, la muevo o la crezco
    if (controlTiempo % TIEMPO_MOVER==0){
        snake.moverse();
        controlTiempo++;
    }else if (controlTiempo==TIEMPO_CRECER){
        snake.crecer();
        controlTiempo=1;
    }else{
        controlTiempo++;
    }

    //Me habre chocado?
    if (snake.hasMuerto()){
        controladorVG = VideoJuego.FINALIZADO;

    }

    //Tengo que pintar la serpiente
    snake.render(batch);

}
private void iniciaPartida(){
    Serpiente nuevaSer = new Serpiente(snake);
    controladorVG = VideoJuego.JUGANDO;
    snake.dispose();
    snake = nuevaSer;
}
private void finalPartida(){

}
public void render(){
    switch (controladorVG){
        case INICIO: this.pantallaInicio();
        break;
        case JUGANDO: this.controlaEstadoJugando();
        break;
        case FINALIZADO: this.finalPartida();
        break;
    }
}
public void setSnake(Serpiente nuevaSerpiente){
    snake=nuevaSerpiente;
}

/*
public void dispose(){
    miControlador.dispose();
}

 */


    


}
