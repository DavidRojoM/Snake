package es.jesusmarin.snake;

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

//POS X

    protected int posX;

//POS Y

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

    //COPIATE
}