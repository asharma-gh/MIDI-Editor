package cs3500.music.model;

import java.util.List;

/**
 * :: NEW ::
 * Serves as a read-only version of the music model.
 */
public interface MusicModelObserver<K> {
  /**
   * Return a list of notes that is a partial set of the current composition.
   * The returned list should contain any notes being played at a certain beat.
   * @param beat the beat at which we want to derive a partial composition
   * @return a list of notes that is a partial set of the current composition
   */
  List<K> getCompositionAtBeat(int beat);

  /**
   * Gets the list of notes that is the present music composition.
   * @return the list of notes for this composition.
   */
  List<K> getComposition();

  /**
   * Creates a string representation to view the music composition.
   * The beat is displayed in the left-most column, and the pitch/octave are displayed at
   * the top.
   * @return a string representation of the music composition.
   */
  String viewComposition();

  /** :: NEW :: ... because music has a tempo
   * The tempo of this composition
   * @return the tempo of this composition
   */
  int getTempo();
}