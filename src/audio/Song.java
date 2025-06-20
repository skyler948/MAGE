package audio;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Song {
	
	private static Thread thread;
	private static boolean started = false, playing = false;
	private static String filename = "";
	
	// https://stackoverflow.com/tags/javasound/info
	// https://stackoverflow.com/a/34332448
	
	/** Play a song! (.wav only).
	 * If a song sounds incorrect, the AudioFormat is probably incorrect.
	 * @param path for song, "/" directs to "res" as a root folder
	 */
	public static synchronized void playSong(String path) {
		if (!started) {
			started = true;
			
			thread = new Thread(new Runnable() {
				public void run() {
					try {
						filename = path;
						
						AudioInputStream ais = AudioSystem.getAudioInputStream(Song.class.getResourceAsStream(path));
						
						AudioFormat af = new AudioFormat(Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
						SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
						sdl.open(af);
						
						int bytesRead = 0;
						byte[] buffer = new byte[1024];
						sdl.start();
						
						while ((bytesRead = ais.read(buffer)) != -1 && started) {
							sdl.write(buffer, 0, bytesRead);
							playing = true;
						}
						sdl.drain();
						
						sdl.close();
						ais.close();
						
						started = false;
						playing = false;
						
						filename = "";
						
						thread.join();
						
						return;
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
						e.printStackTrace();
					} catch (Throwable e) {
						e.printStackTrace();
					}
					return;
				}
			});
		} else {
			System.out.println("A song is already playing.");
			return;
		}
		
		thread.start();
	}
	
	public static boolean isStarted() {
		return started;
	}
	
	public static boolean isPlaying() {
		return playing;
	}
	
	public static void stopCurrentSong() {
		started = false;
		playing = false;
	}
	
	public static String getFilename() {
		return filename;
	}

}
