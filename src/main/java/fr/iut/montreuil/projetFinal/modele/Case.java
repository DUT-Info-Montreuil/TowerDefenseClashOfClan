package fr.iut.montreuil.projetFinal.modele;

public class Case {
    private int x,y;

    public Case(int y, int x){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Case{" +
                "y=" + y +
                ", x=" + x +
                '}';
    }
}
