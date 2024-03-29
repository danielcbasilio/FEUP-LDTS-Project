package org.crazytracks.gui.track_animation;
import com.googlecode.lanterna.TextColor;
import org.crazytracks.gui.GUI;
import org.crazytracks.model.track_element.Position;
import org.crazytracks.model.track_element.TrackElement;
import org.crazytracks.model.track_element.Wagon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrackAnimation implements Runnable {
    private final int xMargin;
    private final TextColor borderColor;
    private final GUI gui;
    private int animMode;
    private final int trackHeight;
    private final List<TrackElement> trackElements;

    private final PositionAdapter positionAdapter;


    public TrackAnimation(GUI gui, int xMargin, TextColor borderColor, int trackHeight) throws IOException {
        this.gui = gui;
        this.xMargin = xMargin;
        this.trackHeight = trackHeight;
        this.animMode = 0;
        this.borderColor = borderColor;
        this.trackElements = loadTrackList();
        this.positionAdapter = new PositionAdapter(this.xMargin, this.trackHeight-1);

    }
    public void step() throws IOException, InterruptedException {
        this.animMode = (this.animMode + 1)%4;
        decrementYPos();
        Thread.sleep(1000);
    }

    public void drawTrackAnimation() throws IOException {
        gui.drawTrack(xMargin, animMode, borderColor);
        drawTrackElements();
    }

    private List<TrackElement> loadTrackList(){
        List<TrackElement> loadedTrack = new ArrayList<>();

        loadedTrack.add(new Wagon(new Position(2, 25)));
        loadedTrack.add(new Wagon(new Position(2, 26)));
        loadedTrack.add(new Wagon(new Position(2, 27)));
        loadedTrack.add(new Wagon(new Position(2, 28)));

        loadedTrack.add(new Wagon(new Position(0, 22)));
        loadedTrack.add(new Wagon(new Position(0, 23)));
        loadedTrack.add(new Wagon(new Position(0, 24)));
        loadedTrack.add(new Wagon(new Position(0, 25)));
        loadedTrack.add(new Wagon(new Position(0, 26)));
        loadedTrack.add(new Wagon(new Position(0, 27)));

        loadedTrack.add(new Wagon(new Position(1, 12)));
        loadedTrack.add(new Wagon(new Position(1, 13)));
        loadedTrack.add(new Wagon(new Position(1, 14)));
        loadedTrack.add(new Wagon(new Position(1, 15)));

        loadedTrack.add(new Wagon(new Position(1, 5)));
        loadedTrack.add(new Wagon(new Position(1, 6)));

        loadedTrack.add(new Wagon(new Position(1, 34)));
        loadedTrack.add(new Wagon(new Position(1, 35)));

        loadedTrack.add(new Wagon(new Position(2, 35)));
        loadedTrack.add(new Wagon(new Position(2, 36)));
        loadedTrack.add(new Wagon(new Position(2, 37)));
        loadedTrack.add(new Wagon(new Position(2, 38)));
        loadedTrack.add(new Wagon(new Position(2, 39)));
        loadedTrack.add(new Wagon(new Position(2, 40)));
        loadedTrack.add(new Wagon(new Position(2, 41)));
        loadedTrack.add(new Wagon(new Position(2, 42)));

        return loadedTrack;
    }
    private void decrementYPos(){
        List<Integer> toLoop = new ArrayList<>();
        for (int i = 0; i < trackElements.size(); i++){
            TrackElement te = trackElements.get(i);
            if (te.getPosition().getY() > 0){
                te.setPosition(new Position(te.getPosition().getX(), te.getPosition().getY()-1));
            } else {
                toLoop.add(i);
            }
        }
        for (int i : toLoop){
            int elementXPos = trackElements.get(i).getPosition().getX();
            trackElements.get(i).setPosition(new Position(elementXPos, this.trackHeight-1));
        }
    }
    private void drawTrackElements() throws IOException {
        for (TrackElement te : trackElements){
            Position adaptedPosition = positionAdapter.adaptPosition(te.getPosition());
            gui.drawWagon(adaptedPosition);
        }
    }

    @Override
    public void run() {
        do try {
            step();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } while (true);
    }
}
