package cs3500.music.mock;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;

/**
 * Created by nbuqu on 6/17/2016.
 */
public class MidiMockSequence extends Sequence {

  public MidiMockSequence(float divisionType, int resolution) throws InvalidMidiDataException {
    super(divisionType, resolution);
  }

  @Override
  public Track createTrack() {
    MidiMockTracer.updateTrace("Track created for sequence.");
    return super.createTrack();
  }
}
