package es.jesusmarin.snake;//gpackage es.jesusmarin.snake;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import es.jesusmarin.snake.ControladorJuego;

public class GdxGameSnake extends ApplicationAdapter {


	private ControladorJuego miControlador;

	private static final float PORCENTAJE_PANTALLA_USADA = 0.90f;

	int elMasChico,anchoReal,altoReal;

	public int getElMasChico() {
		return elMasChico;
	}

	public void setElMasChico(int elMasChico) {
		this.elMasChico = elMasChico;
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

	@Override
	public void create () {

		setAnchoReal(Gdx.graphics.getWidth());
		setAltoReal(Gdx.graphics.getHeight());

		int resuLittle = Math.min(getAnchoReal(),getAltoReal());

		int resu90 = (int) (resuLittle*PORCENTAJE_PANTALLA_USADA);

		int borde=Math.abs(resu90-resuLittle)/2;





		//elMasChico *=PORCENTAJE_PANTALLA_USADA;

		//Ahora se que elMasChico esta en la resolucion que usare para calcular el 90%


		Gdx.graphics.setWindowedMode(resuLittle, resuLittle);


		miControlador = ControladorJuego.getInstance(resu90/2,resu90/2,resu90/20,resu90,getAnchoReal(),getAltoReal(),borde,resuLittle);

	}



	@Override
	public void render () {


		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		miControlador.render();

	}
	
	@Override
	public void dispose(){
		miControlador.dispose();
	}
}
