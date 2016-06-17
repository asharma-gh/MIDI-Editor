package cs3500.music.view;

import java.util.Collections;
import java.util.List;

import javax.sound.midi.*;

import cs3500.music.model.NoteComparatorForInstrument;
import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;

/**
 * This class is used as a Midi implementation of the composition view.
 * All of the notes in the composition are added to a sequencer and played.
 */

public class MidiViewImpl implements ICompositionView<MusicModel<Note>> {
  private final Sequencer sequencer;

  public MidiViewImpl() {
    Sequencer seq;
    try {
      seq = MidiSystem.getSequencer();
      seq.open();
    }
    catch (Exception e) {
      seq = null;
      e.printStackTrace();
    }
    this.sequencer = seq;
  }

  public void playComposition() {
    this.sequencer.start();
    try {
      Thread.sleep(this.sequencer.getMicrosecondLength());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    this.sequencer.close();
  }

  @Override
  public void buildComposition(MusicModel<Note> comp) {
    List<Note> composition = comp.getComposition();
    Collections.sort(composition, new NoteComparatorForInstrument());
    if (composition.size() == 0) {
      throw new IllegalArgumentException("There is no composition to play!");
    }
    try {
      Sequence sequence = new Sequence(Sequence.PPQ, 16);
      Track track = sequence.createTrack();
      sequencer.setTempoInMPQ(comp.getTempo());
      int channel = 0;
      int curInstr = composition.get(0).getInstrumentMIDI();
      MidiMessage instrChange = new ShortMessage(
              ShortMessage.PROGRAM_CHANGE, channel, curInstr, 0);
      track.add(new MidiEvent(instrChange,
              (long) (sequence.getResolution() * composition.get(0).getStartingBeat())));
      for (Note n : composition) {
        long startBeat = (long) (sequence.getResolution() * n.getStartingBeat());
        long stopBeat = (long) (sequence.getResolution() *
                (n.getStartingBeat() + n.getDuration()));
        int pitchToMidiValue = (n.getPitch().ordinal() + ((n.getOctave() + 1) * 12));
        if (curInstr != n.getInstrumentMIDI()) {
          channel += 1;
          curInstr = n.getInstrumentMIDI();
          instrChange = new ShortMessage(ShortMessage.PROGRAM_CHANGE, channel, curInstr, 0);
          track.add(new MidiEvent(instrChange, startBeat));
        }
        track.add(new MidiEvent(new ShortMessage(
                ShortMessage.CONTROL_CHANGE, channel, 7, n.getVolume()), startBeat));
        track.add(new MidiEvent(new ShortMessage(
                ShortMessage.NOTE_ON, channel, pitchToMidiValue, 64), startBeat));
        track.add(new MidiEvent(new ShortMessage(
                ShortMessage.NOTE_OFF, channel, pitchToMidiValue, 64), stopBeat));
      }
      this.sequencer.setSequence(sequence);
    }
    catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }
}
