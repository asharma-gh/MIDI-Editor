package cs3500.music.model;

import java.util.List;

/**
 * This interface is used to represent a model for music compositions.
 * It contains certain methods that may be useful for the composition.
 * It is parametrized over K, a given Note implementation.
 */
public interface MusicModel<K> {

  /**
   * EFFECT: add a note to the current composition
   * @param note the note to be added
   */
  void addNote(K note);

  /**
   * EFFECT: remove a note from the current composition.
   * @param note the note to be removed
   */
  void removeNote(K note);

  /**
   * EFFECT: merge one music composition with this one such that it plays simultaneously
   * @param music the music composition to be merged
   */
  void interweaveMusic(MusicModel<K> music);

  /**
   * EFFECT: extend this music composition to play the given one immediately after
   * @param music the music composition that will be used to extend this composition.
   */
  void extendMusic(MusicModel<K> music);

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

  /**
   * Finds all of the different instruments that are used in the composition.
   * @return a list of all instruments used in the composition
   */
  List<Integer> getInstruments();

  /** :: NEW :: ... because music has a tempo
   * The tempo of this composition
   * @return the tempo of this composition
   */
  int getTempo();

}
