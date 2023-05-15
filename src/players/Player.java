package src.players;


public class Player {
    private boolean moved;
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean hasMoved() {
        return this.moved;
    }

    public void changeMove() {
        this.setMoved(!this.moved);
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }
}
