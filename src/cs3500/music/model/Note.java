package cs3500.music.model;

import java.util.Objects;

/**
 * This class is used to represent a Note of music.
 */
public class Note {
  private Pitch pitch;
  private int octave;
  private int duration;
  private int startingBeat;

  /**
   * Construct a note based on the given paramets
   * @param pitch the pitch of the note
   * @param octave the octave the note is being played at
   * @param duration the duration the note is played for
   * @param startingBeat when the note should start being played
   */
  public Note(Pitch pitch, int octave, int duration, int startingBeat) {
    this.pitch = pitch;
    this.octave = octave;
    this.duration = duration;
    this.startingBeat = startingBeat;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.pitch, this.octave, this.duration, this.startingBeat);
  }

  @Override
  public boolean equals(Object o) {
    if(!(o instanceof Note)) { return false; }

    else {
      Note n = (Note) o;
      return (this.getPitch().equals(n.getPitch())) && (this.getOctave() == n.getOctave()) &&
              (this.getDuration() == n.getDuration()) &&
              (this.getStartingBeat() == n.getStartingBeat());
    }
  }

  @Override
  public String toString() {
    return this.pitch.toString() + Integer.toString(this.octave);
  }


  /**
   * Returns the pitch of this note
   * @return the pitch of this note
   */
  public Pitch getPitch() { return this.pitch; }

  /**
   * Return the octave of this note
   * @return the octave of this note
   */
  public int getOctave() { return this.octave; }

  /**
   * Return the duration of this note
   * @return the duration of this note
   */
  public int getDuration() { return this.duration; }

  /**
   * Return when this note should start
   * @return the starting beat for this note
   */
  public int getStartingBeat() { return this.startingBeat; }

  /**
   * EFFECT: Updates the duration to a new specified duration
   * @param newDuration the desired duration
   */
  public void updateDuration(int newDuration) { this.duration = newDuration; }

  /**
   * EFFECT: Updates the starting beat of the note
   * @param newStart the desired starting beat
   */
  public void updateStartingBeat(int newStart) { this.startingBeat = newStart; }
}