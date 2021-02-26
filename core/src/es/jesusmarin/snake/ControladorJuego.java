package es.jesusmarin.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
    protected final static String SONIDO_INICIO = "Sounds/cancionInicio.mp3";
    protected final static String SONIDO_PARTIDA = "Sounds/cancionPartida.mp3";
    protected final static String SONIDO_FIN = "Sounds/cancionFin.mp3";
    protected final static String SONIDO_MOVERSE = "Sounds/steps.mp3";
    protected final static String SONIDO_CRECER = "Sounds/sonidoCrecer.mp3";



    protected final int TIEMPO_CRECER = 120;
    protected final int TIEMPO_MOVER = 30;


    //Esto es un truco que pone un objeto ControladorJuego en la memoria ram en el espacio asignado a la clase ControladorJuego
    private static ControladorJuego miControlador;



    //Objetos que controla el controlador
    //MUSICA
    private Music inicio;
    private Music partida;
    private Music fin;
    private Sound steps;
    private Sound powerUp;

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
private ControladorJuego(int elMasChico){ //FALTA PONER ANCHOPANTALLA POR PARAMETRO
    controladorVG = VideoJuego.INICIO;
    batch = new SpriteBatch();

    imgInicial = new Texture(IMAGEN_INICIO);
    imgFinal = new Texture(IMAGEN_FIN);
    imgFondo = new Texture(IMAGEN_FONDO);

    inicio = Gdx.audio.newMusic(Gdx.files.internal(SONIDO_INICIO));
    partida = Gdx.audio.newMusic(Gdx.files.internal(SONIDO_PARTIDA));
    partida.setLooping(true);
    fin = Gdx.audio.newMusic(Gdx.files.internal(SONIDO_FIN));
    steps = Gdx.audio.newSound(Gdx.files.internal(SONIDO_MOVERSE));
    powerUp = Gdx.audio.newSound(Gdx.files.internal(SONIDO_CRECER));

    et = new EstadoTeclado(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    anchoPantalla = elMasChico;
    controlTiempo = 0;


}

public static ControladorJuego getInstance(int posXinicial,int posYinicial,int ancho ,int elMasChico, int anchoReal,int altoReal){
    if (ControladorJuego.miControlador==null){
        miControlador = new ControladorJuego(elMasChico);
        miControlador.setSnake(new Serpiente(posXinicial,posYinicial,ancho,elMasChico,anchoReal,altoReal));
    }
    return ControladorJuego.miControlador;
}
private void pantallaInicio(){
    batch.begin();
    //AQUI HABRIA QUE PINTAR EL FONDO

    batch.draw(imgInicial,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    inicio.play();

    batch.end();


    boolean recienTocado;
    recienTocado = Gdx.input.justTouched();

    if (recienTocado){

        this.iniciaPartida();
        inicio.dispose();
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

    //CREO QUE EL PROBLEMA DE MOVERSE Y CRECER A LA VEZ ESTA AQUI (SI CRECES NO TE MUEVES)


    if (controlTiempo==TIEMPO_CRECER){
        snake.crecer();
        powerUp.play();
        controlTiempo=1;
    }else if (controlTiempo % TIEMPO_MOVER==0){
        snake.moverse();
        steps.play();
        controlTiempo++;
    } else{
        controlTiempo++;
}

    //Me habre chocado?
    if (snake.hasMuerto()){
        controladorVG = VideoJuego.FINALIZADO;
        partida.dispose();

    }

    //Tengo que pinta r la serpiente
    snake.render(batch);

}
private void iniciaPartida(){

    Serpiente nuevaSer = new Serpiente(snake);
    controladorVG = VideoJuego.JUGANDO;
    snake.dispose();
    snake = nuevaSer;
    partida.play();
}
private void finalPartida(){

    batch.begin();
    //AQUI ES PROBABLE QUE HAYA QUE PINTAR EL FONDO

    batch.draw(imgFinal,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    batch.end();
    fin.play();

    boolean recienTocado;
    recienTocado = Gdx.input.justTouched();

    if (recienTocado){
        this.iniciaPartida();
        fin.dispose();
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
