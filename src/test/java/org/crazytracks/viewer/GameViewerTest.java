package org.crazytracks.viewer;

import org.crazytracks.gui.GUI;
import org.crazytracks.model.*;
import org.crazytracks.model.Surfer;
import org.crazytracks.model.track_element.Position;
import org.crazytracks.model.track_element.PowerUp;
import org.crazytracks.model.track_element.TrackElement;
import org.crazytracks.model.track_element.Wagon;
import org.crazytracks.model.track_element.coin.CopperCoin;
import org.crazytracks.model.track_element.coin.GoldCoin;
import org.crazytracks.viewer.GameViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class GameViewerTest {
    private GUI gui;
    private GameViewer viewer;
    private Track track;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException, FontFormatException {
        track = new Track();
        gui = Mockito.mock(GUI.class);
        viewer = new GameViewer(track);

        Wagon wagon = new Wagon(new Position(15, 0));
        track.addTrackElement(wagon);

        wagon = new Wagon(new Position(15, 1));
        track.addTrackElement(wagon);

        wagon = new Wagon(new Position(15, 2));
        track.addTrackElement(wagon);

        wagon = new Wagon(new Position(15, 3));
        track.addTrackElement(wagon);

        wagon = new Wagon(new Position(15, 4));
        track.addTrackElement(wagon);

        PowerUp powerUp = new PowerUp(new Position(15, 5));
        track.addTrackElement(powerUp);

        powerUp = new PowerUp(new Position(15, 6));
        track.addTrackElement(powerUp);

        GoldCoin goldCoin = new GoldCoin(new Position(16, 5));
        track.addTrackElement(goldCoin);

        CopperCoin copperCoin = new CopperCoin(new Position(16, 6));
        track.addTrackElement(copperCoin);

        Surfer surfer = new Surfer(new Position(0, 0));
        Animation anim = new Animation(4);
        surfer.setAnim(anim);
        track.setSurfer(surfer);
    }

    @Test
    void drawWagons() throws IOException {
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawWagon(new Position(15, 0));
        Mockito.verify(gui, Mockito.times(5)).drawWagon(Mockito.any(Position.class));
    }

    @Test
    void drawPowerUps() throws IOException {
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawPowerUp(new Position(15, 6));
        Mockito.verify(gui, Mockito.times(1)).drawPowerUp(new Position(15, 5));
        Mockito.verify(gui, Mockito.times(2)).drawPowerUp(Mockito.any(Position.class));
    }

    @Test
    void drawCoins() throws IOException {
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawGoldCoin(new Position(16, 5));
        Mockito.verify(gui, Mockito.times(1)).drawCopperCoin(new Position(16, 6));
        Mockito.verify(gui, Mockito.times(1)).drawGoldCoin(Mockito.any(Position.class));
        Mockito.verify(gui, Mockito.times(1)).drawCopperCoin(Mockito.any(Position.class));
    }

    @Test
    void drawSurfer() throws IOException {
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawSurfer(new Position(0,0),0);
        Mockito.verify(gui, Mockito.times(1)).drawSurfer(Mockito.any(Position.class), Mockito.eq(0));
    }

    @Test
    void refreshAndClear() throws IOException {
        viewer.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).clearScreen();
        Mockito.verify(gui, Mockito.times(1)).refreshScreen();
    }
}