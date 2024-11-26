package clueGame;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class PlayMusic {
	String filePath;
	
	public PlayMusic(String filePath) {
		this.filePath = filePath;
		this.Play(filePath);
	}
	
	public void Play(String filePath) {
		File music;
		try {
			music =new File(filePath);
			if(music.exists()) {
				AudioInputStream audio = AudioSystem.getAudioInputStream(music);
				Clip clip = AudioSystem.getClip();
				clip.open(audio);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}else {
				JOptionPane.showMessageDialog(null,"Whoops buddy you didn't give the right filepath!\n"+filePath);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
