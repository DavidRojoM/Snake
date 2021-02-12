package es.jesusmarin.snake;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GdxGameSnake extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	private ControladorJuego miControlador;

	private static final float PANTALLA90 = 0.9f;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		//Aqui tengo que averiguar que ancho de pantalla tengo, que alto, que forma tendran las casillas donde poner las piezas de la serpiente,etc..

		int anchoPantalla,altoPantalla,elMasChico;
		anchoPantalla=Gdx.graphics.getWidth();
		altoPantalla=Gdx.graphics.getHeight();
		elMasChico = altoPantalla;
		if (anchoPantalla<elMasChico)
			elMasChico = anchoPantalla;

		//Ahora se que elMasChico esta en la resolucion que usare para calcular el 90%

		elMasChico = (int) ((float)elMasChico*PANTALLA90);

		miControlador = ControladorJuego.getInstance(anchoPantalla/2,altoPantalla/2,elMasChico/20);
		//img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//batch.begin();
		//batch.draw(img, 0, 0);
		//batch.end();
		miControlador.render();
	}
	
	@Override
	public void dispose(){
		batch.dispose();
		miControlador.dispose();
		img.dispose();
	}
}
