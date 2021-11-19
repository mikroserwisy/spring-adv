package pl.training.events;

public enum Player {

    CIRCLE, CROSS;

    public Player reverse() {
        return this == CIRCLE ? CROSS : CIRCLE;
    }

}