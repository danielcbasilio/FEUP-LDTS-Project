package org.crazytracks.model;

import org.crazytracks.model.track_element.Position;
import org.crazytracks.viewer.coin.DrawStrategy;
import org.crazytracks.viewer.SurferViewer;

public class Element{
    Position position;
    Animation anim;
    private DrawStrategy drawStrategy;
    public Element(Position position){
        this.position = position;
        this.anim = null;
        this.drawStrategy = null;
    }

    public Position getPosition(){
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getLeftPosition(){
        return new Position(position.getX()-1, position.getY());
    }

    public Position getRightPosition(){
        return new Position(position.getX()+1, position.getY());
    }

    public Position getUpPosition(){
        return new Position(position.getX(), position.getY()-1);
    }

    public Position getDownPosition(){
        return new Position(position.getX(), position.getY()+1);
    }

    public Object getView() {
        return new SurferViewer();
    }
    public void setAnim(Animation anim){
        this.anim = anim;
    }
    public Animation getAnim() {
        return anim;
    }
    public void setDrawStrategy(DrawStrategy drawStrategy){
        this.drawStrategy = drawStrategy;
    }
    public DrawStrategy getDrawStrategy(){
        return drawStrategy;
    }

}
