/**
 * Created by Vitor on 19/05/2016.
 */
import java.util.ArrayList;

public class Ponto {
    private double x;
    private double y;

    public Ponto(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Ponto(ArrayList<Double> pontos){
        this.x = pontos.get(0);
        this.y = pontos.get(1);
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void rotacionar(double dir) {
        double tempX = x*Math.cos(dir) - y*Math.sin(dir);
        double tempY = y*Math.cos(dir) + x*Math.sin(dir);
        x = tempX;
        y = tempY;
    }

    public ArrayList<Double> soma(Ponto p){
        ArrayList<Double> pontos = new ArrayList<>();
        pontos.add(this.x+p.getX());
        pontos.add(this.y+p.getY());

        return pontos;
    }
}
