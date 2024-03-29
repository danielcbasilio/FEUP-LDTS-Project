package org.crazytracks.model;

import org.crazytracks.gui.sui.soundeffects.CoinCollisionListener;
import org.crazytracks.gui.sui.soundeffects.PowerUpCollisionListener;
import org.crazytracks.gui.sui.soundeffects.SoundEffectPlayer;
import org.crazytracks.gui.sui.soundeffects.WagonCollisionListener;
import org.crazytracks.model.track_element.*;
import org.crazytracks.model.track_element.coin.Coin;

import java.util.ArrayList;
import java.util.List;

public class Track {
    private Surfer surfer;
    private List<TrackElement> trackElements = new ArrayList<>();
    private final List<CoinCollisionListener> coinCollisionListeners = new ArrayList<>();
    private final List<WagonCollisionListener> wagonCollisionListeners = new ArrayList<>();
    private final List<PowerUpCollisionListener> powerUpCollisionListeners = new ArrayList<>();
    private final Animation anim;
    public Track(){
        this.anim = new Animation(4);
        coinCollisionListeners.add(new SoundEffectPlayer());
        wagonCollisionListeners.add(new SoundEffectPlayer());
        powerUpCollisionListeners.add(new SoundEffectPlayer());
    }

    public Surfer getSurfer() {
        return surfer;
    }

    public void setSurfer(Surfer surfer) {
        this.surfer = surfer;
    }

    public List<TrackElement> getTrackElements() {
        return trackElements;
    }

    public void setTrackElements(List<TrackElement> trackElements) {
        this.trackElements = trackElements;
    }

    public boolean isEmpty(Position position) {
        for (TrackElement trackElement : trackElements) {
            if (trackElement.getPosition().equals(position)) {
                return false;
            }
        }
        return !surfer.getPosition().equals(position);
    }
    public TrackElement getTrackElement(Position position) {
        for (TrackElement trackElement: trackElements) {
            if (trackElement.getPosition().equals(position)) {
                return trackElement;
            }
        }
        return null;
    }

    public List<Wagon> getWagons() {
        List<Wagon> wagons = new ArrayList<>();
        for (TrackElement trackElement : trackElements) {
            if (trackElement instanceof Wagon) {
                wagons.add((Wagon) trackElement);
            }
        }
        return wagons;
    }

    public List<PowerUp> getPowerUps() {
        List<PowerUp> powerUps = new ArrayList<>();
        for (TrackElement trackElement : trackElements) {
            if (trackElement instanceof PowerUp) {
                powerUps.add((PowerUp) trackElement);
            }
        }
        return powerUps;
    }

    public List<Coin> getCoins() {
        List<Coin> coins = new ArrayList<>();
        for (TrackElement trackElement : trackElements) {
            if (trackElement instanceof Coin) {
                coins.add((Coin) trackElement);
            }
        }
        return coins;
    }

    public void addTrackElement(TrackElement trackElement) {
        trackElements.add(trackElement);
    }

    public void removeTrackElement(Position position) {
        TrackElement trackElement = getTrackElement(position);
        this.trackElements.remove(trackElement);
    }

    public void moveAllTrackElementsDown() {
        for (TrackElement trackElement : trackElements) {
            trackElement.setPosition(trackElement.getDownPosition());
        }
        this.anim.nextAnimMode();
    }

    public int getAnimMode(){
        return this.anim.getAnimMode();
    }

    public void notifyCoinCollisionListeners() {
        for (CoinCollisionListener coinCollisionListener : coinCollisionListeners) {
            coinCollisionListener.onCoinCollision();
        }
    }

    public void notifyWagonCollisionListeners() {
        for (WagonCollisionListener wagonCollisionListener : wagonCollisionListeners) {
            wagonCollisionListener.onWagonCollision();
        }
    }

    public void notifyPowerUpCollisionListeners() {
        for (PowerUpCollisionListener powerUpCollisionListener : powerUpCollisionListeners) {
            powerUpCollisionListener.onPowerUpCollision();
        }
    }

    public List<CoinCollisionListener> getCoinCollisionListeners() {
        return this.coinCollisionListeners;
    }

    public List<WagonCollisionListener> getWagonCollisionListeners() {
        return this.wagonCollisionListeners;
    }

    public List<PowerUpCollisionListener> getPowerUpCollisionListeners() {
        return this.powerUpCollisionListeners;
    }
}
