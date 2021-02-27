package es.jesusmarin.snake;

public class EstadoTeclado {

    /////////////////////////////////////////////////////////////////////////
    //
    //ESTADO
    //
    ///////////////////////////////////////////////////////////////////////

    private boolean teclaArriba;
    private boolean teclaAbajo;
    private boolean teclaIzq;
    private boolean teclaDer;

    private int limiteAlturaZonaArriba;
    private int limiteAnchoZonaLateral;

    /////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTO
    //
    ///////////////////////////////////////////////////////////////////////

    public EstadoTeclado(int ancho,int alto){
        teclaArriba = false;
        teclaAbajo = false;
        teclaIzq = false;
        teclaDer = false;
        limiteAnchoZonaLateral = ancho/2;
        limiteAlturaZonaArriba = alto/2;
    }

    public boolean isTeclaArriba() {
        return teclaArriba;
    }

    public boolean isTeclaAbajo() {
        return teclaAbajo;
    }

    public boolean isTeclaIzq() {
        return teclaIzq;
    }

    public boolean isTeclaDer() {
        return teclaDer;
    }

    public void simulaTeclado(int posX,int posY){
        if (posY<limiteAlturaZonaArriba){
            teclaArriba = true;
            teclaAbajo = false;
        }else{
            teclaArriba = false;
            teclaAbajo = true;
        }
        if (posX< limiteAnchoZonaLateral){
            teclaIzq = true;
            teclaDer = false;
        }else{
            teclaIzq = false;
            teclaDer = true;
        }
    }


}
