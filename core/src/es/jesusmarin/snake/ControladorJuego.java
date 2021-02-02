package es.jesusmarin.snake;

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

private static ControladorJuego miControlador;
    //Esto es un truco que pone un objeto ControladorJuego en la memoria ram en el espacio asignato a la clase ControladorJuego


    //Objetos que controla el controlador

    //Una serpiente
    protected Serpiente snake;



    /////////////////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTOS
    //
    /////////////////////////////////////////////////////////////////////////////////////
private ControladorJuego(){
}

public static ControladorJuego getInstance(int posXinicial,int posYinicial,int ancho){
    if (ControladorJuego.miControlador==null){
        miControlador = new ControladorJuego();
        miControlador.setSnake(new Serpiente(posXinicial,posYinicial,ancho));
    }
    return ControladorJuego.miControlador;
}
public void setSnake(Serpiente nuevaSerpiente){
    snake=nuevaSerpiente;
}

    //RESTO DEL ESTADO


}
