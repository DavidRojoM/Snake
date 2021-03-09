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
    protected final static String IMAGEN_NEGRA = "negro.png";

    protected final static String SONIDO_INICIO = "Sounds/cancionInicio.mp3";
    protected final static String SONIDO_PARTIDA = "Sounds/cancionPartida.mp3";
    protected final static String SONIDO_FIN = "Sounds/cancionFin.mp3";
    protected final static String SONIDO_MOVERSE = "Sounds/steps.mp3";
    protected final static String SONIDO_CRECER = "Sounds/sonidoCrecer.mp3";



    protected final int TIEMPO_CRECER = 120;//120
    protected final int TIEMPO_MOVER = 30;//30


    //Esto es un truco que pone un objeto ControladorJuego en la memoria ram en el espacio asignado a la clase ControladorJuego
    private static ControladorJuego miControlador;



    //Objetos que controla el controlador
    //SONIDOS
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
    protected Texture imgFondoNegra;



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

    protected float elMasChico;
    protected int anchoReal,altoReal,borde,resuLittle;
    /////////////////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTOS
    //
    /////////////////////////////////////////////////////////////////////////////////////
private ControladorJuego(int elMasChico,int anchoReal,int altoReal,int borde,int resuLittle){ //FALTA PONER ANCHOPANTALLA POR PARAMETRO
    controladorVG = VideoJuego.INICIO;
    batch = new SpriteBatch();

    setAnchoReal(anchoReal);
    setAltoReal(altoReal);
    this.borde = borde;
    this.resuLittle = resuLittle;

    imgInicial = new Texture(IMAGEN_INICIO);
    imgFinal = new Texture(IMAGEN_FIN);
    imgFondo = new Texture(IMAGEN_FONDO);
    imgFondoNegra = new Texture(IMAGEN_NEGRA);

    inicio = Gdx.audio.newMusic(Gdx.files.internal(SONIDO_INICIO));
    partida = Gdx.audio.newMusic(Gdx.files.internal(SONIDO_PARTIDA));
    partida.setLooping(true);
    fin = Gdx.audio.newMusic(Gdx.files.internal(SONIDO_FIN));
    fin.setVolume(0.4f);
    steps = Gdx.audio.newSound(Gdx.files.internal(SONIDO_MOVERSE));
    powerUp = Gdx.audio.newSound(Gdx.files.internal(SONIDO_CRECER));

    et = new EstadoTeclado(elMasChico,elMasChico);
    this.elMasChico = elMasChico;
    controlTiempo = 0;


}

public static ControladorJuego getInstance(int posXinicial,int posYinicial,int ancho ,int elMasChico,int anchoReal,int altoReal,int borde,int resuLittle){
    if (ControladorJuego.miControlador==null){
        miControlador = new ControladorJuego(elMasChico,anchoReal,altoReal,borde,resuLittle);
        miControlador.setSnake(new Serpiente(posXinicial,posYinicial,ancho,elMasChico,borde,resuLittle));
    }
    return ControladorJuego.miControlador;
}

    public int getAnchoReal() {
        return anchoReal;
    }

    public void setAnchoReal(int anchoReal) {
        this.anchoReal = anchoReal;
    }

    public int getAltoReal() {
        return altoReal;
    }

    public void setAltoReal(int altoReal) {
        this.altoReal = altoReal;
    }

    public float getElMasChico() {
        return elMasChico;
    }

    private void pantallaInicio(){
    batch.begin();
    //AQUI HABRIA QUE PINTAR EL FONDO
        batch.draw(imgFondoNegra,0,0,getAnchoReal(),getAltoReal());
    batch.draw(imgInicial,borde,borde,getElMasChico(),getElMasChico());
    inicio.play();

    batch.end();


    boolean recienTocado;
    recienTocado = Gdx.input.justTouched();

    if (recienTocado){

        this.iniciaPartida();
        inicio.stop();
    }
}
private void controlaEstadoJugando(){

    boolean recienTocado;

    recienTocado = Gdx.input.justTouched();
    if (recienTocado){

        int posNuevaX,posNuevaY;
        posNuevaX=Gdx.input.getX();
        posNuevaY = Gdx.input.getY();

        et.simulaTeclado(posNuevaX,posNuevaY);

        snake.cambiaDireccion(et);
    }

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

    if (snake.hasMuerto()){
        controladorVG = VideoJuego.FINALIZADO;
        partida.stop();

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
    batch.draw(imgFondoNegra,0,0,getAnchoReal(),getAltoReal());
    batch.draw(imgFinal,borde,borde,getElMasChico(),getElMasChico());
    batch.end();
    fin.play();

    boolean recienTocado;
    recienTocado = Gdx.input.justTouched();

    if (recienTocado){
        this.iniciaPartida();
        fin.stop();
    }
}

public void render(){
    batch.begin();
    batch.draw(imgFondoNegra,0,0,getAnchoReal(),getAltoReal());
    batch.draw(imgFondo,borde,borde,getElMasChico(),getElMasChico());
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
