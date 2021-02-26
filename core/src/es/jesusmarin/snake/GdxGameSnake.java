package es.jesusmarin.snake;//gpackage es.jesusmarin.snake;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import es.jesusmarin.snake.ControladorJuego;

public class GdxGameSnake extends ApplicationAdapter {
	SpriteBatch batch;
	//Texture img;
	private OrthographicCamera camera;
	private ControladorJuego miControlador;



	private static final float PORCENTAJE_PANTALLA_USADA = 0.9f;
	
	@Override
	public void create () {

		batch = new SpriteBatch();


		//Aqui tengo que averiguar que ancho de pantalla tengo, que alto, que forma tendran las casillas donde poner las piezas de la serpiente,etc..r

		int anchoPantalla,altoPantalla,elMasChico;

		anchoPantalla=Gdx.graphics.getWidth();
		altoPantalla=Gdx.graphics.getHeight();

		elMasChico = Math.min(anchoPantalla,altoPantalla);

		//Ahora se que elMasChico esta en la resolucion que usare para calcular el 90%



		

		Gdx.graphics.setWindowedMode(elMasChico, elMasChico);



		miControlador = ControladorJuego.getInstance(elMasChico/2,elMasChico/2,elMasChico/20,elMasChico,anchoPantalla,altoPantalla);

	}



	@Override
	public void render () {


		Gdx.gl.glClearColor(46/255f, 146/255f, 59/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//batch.begin();
		//batch.draw(img, 0, 0);
		//batch.end();

		miControlador.render();

	}
	
	@Override
	public void dispose(){
		//batch.dispose();
		miControlador.dispose();
		//img.dispose();
	}
}
