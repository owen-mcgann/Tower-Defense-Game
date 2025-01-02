import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class WAVPlayer {

    private File audioFile;
    private FloatControl volumeControl;
    private float volume = 1.0f;  // Default volume to full

    // Constructor to load the WAV file and prepare for playback
    public WAVPlayer(String filePath) {
        this.audioFile = new File(filePath);
    }

    // Method to play the WAV file with a new Clip each time
    public void play() {
        try {
            // Create a new AudioInputStream for each playback
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);

            audioClip.open(audioStream);

            // Set volume control
            if (audioClip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                volumeControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
                setVolume(volume);  // Set to the previously set volume
            }

            // Play the clip
            audioClip.start();

            // Close the clip after it finishes playing
            audioClip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    audioClip.close();
                }
            });

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Method to stop the WAV file (if needed)
    public void stop() {
        // Since we're creating a new clip each time, stopping isn't needed in this case.
        // Each clip will stop itself after playing, but you could implement stopping all sounds if needed.
    }

    // Method to set the volume (input is a value between 0.0 and 1.0)
    public void setVolume(float volume) {
        this.volume = volume;  // Store the volume in case clips are created later
        if (volumeControl != null) {
            float minVolume = volumeControl.getMinimum();
            float maxVolume = volumeControl.getMaximum();

            // Scale the volume to the appropriate range and set it
            float newVolume = minVolume + (maxVolume - minVolume) * volume;
            volumeControl.setValue(newVolume);
            System.out.println("Volume set to: " + volume);
        }
    }

    // Method to close the audio resources (optional for cleanup)
    public void close() {
        // No need to explicitly close the clip, each one closes itself after playing.
    }
    public float getVolume(){
        return volumeControl.getValue();
    }
}
