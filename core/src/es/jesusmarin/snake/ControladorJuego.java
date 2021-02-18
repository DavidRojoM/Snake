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
    protected final static String IMAGEN_FONDO = "imgFondo.png";


    protected final int TIEMPO_CRECER = 239;
    protected final int TIEMPO_MOVER = 59;


    //Esto es un truco que pone un objeto ControladorJuego en la memoria ram en el espacio asignado a la clase ControladorJuego
    private static ControladorJuego miControlador;



    //Objetos que controla el controlador

    //Una serpiente
    protected Serpiente snake;

    //un batch
    protected SpriteBatch batch;

    //Imagenes splash para inicio, final y fondo (Hay que meter una imagen en assets y en el estado para el inicio del juego y otra para game over)
    protected Texture imgInicial;
    protected Texture imgFinal;
    protected Texture imgFondo;



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
private ControladorJuego(int anchoReal){ //FALTA PONER ANCHOPANTALLA POR PARAMETRO
    controladorVG = VideoJuego.INICIO;
    batch = new SpriteBatch();
    imgInicial = new Texture(IMAGEN_INICIO);
    imgFinal = new Texture(IMAGEN_FIN);
    imgFondo = new Texture(IMAGEN_FONDO);
    et = new EstadoTeclado(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    anchoPantalla = anchoReal;
    controlTiempo = 0;

}

public static ControladorJuego getInstance(int posXinicial,int posYinicial,int ancho ,int altoAnchoPantalla, int anchoReal,int altoReal){
    if (ControladorJuego.miControlador==null){
        miControlador = new ControladorJuego(altoAnchoPantalla);
        miControlador.setSnake(new Serpiente(posXinicial,posYinicial,ancho,altoAnchoPantalla,anchoReal,altoReal));
    }
    return ControladorJuego.miControlador;
}
private void pantallaInicio(){
    batch.begin();
    //AQUI HABRIA QUE PINTAR EL FONDO

    batch.draw(imgInicial,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

    batch.end();


    boolean recienTocado;
    recienTocado = Gdx.input.justTouched();

    if (recienTocado){

        this.iniciaPartida();
    }
}
private void controlaEstadoJugando(){

    boolean recienTocado;

    recienTocado = Gdx.input.justTouched();
    if (recienTocado){

        //tendre que:
        //1º Ver donde he pinchado
        //necesito la posicion x y la posicion y
        int posNuevaX,posNuevaY;
        posNuevaX=Gdx.input.getX();
        posNuevaY = Gdx.input.getY();


        //2º le doy esos valores a "et"

        et.simulaTeclado(posNuevaX,posNuevaY);
        //3º le pregunto a "et" que teclas estan pulsadas y dependiendo de las dos reclas pulsadas y de la direccion de la serpiente, entonces le pido a la serpiente que cambie adecuadamente

        snake.cambiaDireccion(et);

    }

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

    //Tengo que pinta r la serpiente
    snake.render(batch);

}
private void iniciaPartida(){

    Serpiente nuevaSer = new Serpiente(snake);
    controladorVG = VideoJuego.JUGANDO;
    snake.dispose();
    snake = nuevaSer;
}
private void finalPartida(){

    batch.begin();
    //AQUI ES PROBABLE QUE HAYA QUE PINTAR EL FONDO

    batch.draw(imgFinal,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    batch.end();

    boolean recienTocado;
    recienTocado = Gdx.input.justTouched();

    if (recienTocado){
        this.iniciaPartida();
    }
}

public void render(){
    batch.begin();
    batch.draw(imgFondo,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    batch.end();
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

public void dispose() {
    if (snake != null) snake.dispose();

    batch.dispose();
    imgFinal.dispose();


    }
}
