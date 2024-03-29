package org.crazytracks.viewer;

import org.crazytracks.gui.GUI;
import org.crazytracks.model.leaderboard.Leaderboard;
import org.crazytracks.model.leaderboard.Player;
import org.crazytracks.viewer.LeaderboardViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeaderboardViewerTest {
    private LeaderboardViewer viewer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        gui = Mockito.mock(GUI.class);

        Leaderboard leaderboard = Mockito.mock(Leaderboard.class);
        viewer = new LeaderboardViewer(leaderboard);
    }

    @Test
    void testDraw() throws IOException {
        viewer.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).drawLeaderboard(Mockito.anyList());
    }
}
