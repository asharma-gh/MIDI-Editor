package cs3500.music.controller;

import org.junit.Test;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import cs3500.music.model.INote;
import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicModelImpl;
import cs3500.music.model.MusicModelObserver;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.view.GuiView;
import cs3500.music.view.GuiViewFrame;

import static org.junit.Assert.*;

/**
 * Created by Arvin on 6/25/2016.
 */
public class CompositeControllerTest {
  private GuiView<INote> view;
  private MusicModel<INote> model;
  private CompositeController controller;

  private void initData() {
    this.view = new GuiViewFrame();
    this.model = new MusicModelImpl.Builder().build();
    this.model.addNote(new Note(Pitch.B, 1, 5, 0));

    this.controller = new CompositeController(model, view);
    this.controller.constructView();
    this.controller.displayView();
  }

  @Test
  public void removeNote() throws Exception {

  }

  @Test
  public void testAddNote() {
    initData();
    int[] noteVals = {0, 1, 2, 3, 4, 5};
    assertTrue(model.getComposition().size() == 1);
    this.controller.addNote(noteVals);
    assertTrue(model.getComposition().size() == 2);
    assertTrue(model.getComposition().contains(new Note(Pitch.integerToPitch(0), 1, 2, 3, 4, 5)));


  }

}