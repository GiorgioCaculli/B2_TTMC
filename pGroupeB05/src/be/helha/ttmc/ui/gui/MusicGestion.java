package be.helha.ttmc.ui.gui;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicGestion{
	
	private MediaPlayer mp;
	private Media m;
	
	public MusicGestion(String path) {
		m= new Media(MusicGestion.class.getResource(path).toString());
		mp= new MediaPlayer(m);
		mp.setCycleCount(MediaPlayer.INDEFINITE);
		mp.setVolume(0.2);
		
		
	}

	public void start() {
		mp.play();
	}
	
}
