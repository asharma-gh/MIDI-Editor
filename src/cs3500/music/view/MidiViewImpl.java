package cs3500.music.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sound.midi.*;

import cs3500.music.NoteComparatorForInstrument;
import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;

/**
 * A skeleton for MIDI playback
 */


public class MidiViewImpl implements ICompositionView {
  private final Synthesizer synth;
  private final Receiver receiver;
  private final Sequencer sequencer;

  public MidiViewImpl() {
    Synthesizer syn;
    Receiver r;
    Sequencer seq;
    try {
      syn = MidiSystem.getSynthesizer();
      r = syn.getReceiver();
      syn.open();
    } catch (MidiUnavailableException e) {
      syn = null;
      r = null;
      e.printStackTrace();
    }

    try {
      seq = MidiSystem.getSequencer();
      seq.open();
    }
    catch (Exception e) {
      seq = null;
      e.printStackTrace();
    }
    this.synth = syn;
    this.receiver = r;
    this.sequencer = seq;
  }
  /**
   * Relevant classes and methods from the javax.sound.midi library:
   * <ul>
   *  <li>{@link MidiSystem#getSynthesizer()}</li>
   *  <li>{@link Synthesizer}
   *    <ul>
   *      <li>{@link Synthesizer#open()}</li>
   *      <li>{@link Synthesizer#getReceiver()}</li>
   *      <li>{@link Synthesizer#getChannels()}</li>
   *    </ul>
   *  </li>
   *  <li>{@link Receiver}
   *    <ul>
   *      <li>{@link Receiver#send(MidiMessage, long)}</li>
   *      <li>{@link Receiver#close()}</li>
   *    </ul>
   *  </li>
   *  <li>{@link MidiMessage}</li>
   *  <li>{@link ShortMessage}</li>
   *  <li>{@link MidiChannel}
   *    <ul>
   *      <li>{@link MidiChannel#getProgram()}</li>
   *      <li>{@link MidiChannel#programChange(int)}</li>
   *    </ul>
   *  </li>
   * </ul>
   * @see <a href="https://en.wikipedia.org/wiki/General_MIDI">
   *   https://en.wikipedia.org/wiki/General_MIDI
   *   </a>
   */

  public void playNote() throws InvalidMidiDataException {
    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, 0, 60, 64);
    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, 0, 60, 64);
    this.receiver.send(start, -1);
    this.receiver.send(stop, this.synth.getMicrosecondPosition() + 200000);
    try {
      Thread.sleep(1000);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    this.receiver.close(); // Only call this once you're done playing *all* notes
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
      Sequence sequence = new Sequence(Sequence.PPQ, 96);
      sequencer.setTempoInMPQ(comp.getTempo());
      Track track = sequence.createTrack();
      int channel = 0;
      int curInstr = composition.get(0).getInstrumentMIDI();
      MidiMessage instrChange = new ShortMessage(
              ShortMessage.PROGRAM_CHANGE, channel, curInstr, 0);
      track.add(new MidiEvent(instrChange,
              (long) (sequence.getResolution() * composition.get(0).getStartingBeat())));
      for (int n = 0; n < composition.size(); n += 1) {
        Note addToTrack = composition.get(n);
        long startBeat = (long) (sequence.getResolution() * addToTrack.getStartingBeat());
        long stopBeat = (long) (sequence.getResolution() *
                (addToTrack.getStartingBeat() + addToTrack.getDuration()));
        int pitchToMidiValue = (addToTrack.getPitch().ordinal()
                + ((addToTrack.getOctave() + 1) * 12));
        if (curInstr != addToTrack.getInstrumentMIDI()) {
          channel += 1;
          curInstr = addToTrack.getInstrumentMIDI();
          instrChange = new ShortMessage(
                  ShortMessage.PROGRAM_CHANGE, channel, curInstr, 0);
          track.add(new MidiEvent(instrChange, startBeat));
        }
        MidiMessage volume = new ShortMessage(
                ShortMessage.CONTROL_CHANGE, channel, 7, addToTrack.getVolume());
        MidiMessage start = new ShortMessage(
                ShortMessage.NOTE_ON, channel, pitchToMidiValue, 64);
        MidiMessage stop = new ShortMessage(
                ShortMessage.NOTE_OFF, channel, pitchToMidiValue, 64);
        track.add(new MidiEvent(volume, startBeat));
        track.add(new MidiEvent(start, startBeat));
        track.add(new MidiEvent(stop, stopBeat));
      }
      this.sequencer.setSequence(sequence);
    }
    catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }
}
