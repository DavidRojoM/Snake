package es.jesusmarin.snake;//gpackage es.jesusmarin.snake;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import es.jesusmarin.snake.ControladorJuego;

public class GdxGameSnake extends ApplicationAdapter {


	private ControladorJuego miControlador;

	//private static final float PORCENTAJE_PANTALLA_USADA = 0.90f;


	@Override
	public void create () {

		int elMasChico = Math.min(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		//elMasChico *=PORCENTAJE_PANTALLA_USADA;

		//Ahora se que elMasChico esta en la resolucion que usare para calcular el 90%


		Gdx.graphics.setWindowedMode(elMasChico, elMasChico);


		miControlador = ControladorJuego.getInstance(elMasChico/2,elMasChico/2,elMasChico/20,elMasChico);

	}



	@Override
	public void render () {


		Gdx.gl.glClearColor(46/255f, 146/255f, 59/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		miControlador.render();

	}
	
	@Override
	public void dispose(){
		miControlador.dispose();
	}
}
