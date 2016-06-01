/**
 * Created by Vitor on 31/05/2016.
 */

public abstract class ObjetoJogo {
    protected Ponto centro;
    protected double vy;
    protected double vx;

    abstract void desenhar(Tela t);
    abstract void mover(int altTela, int largTela, double dt);

    public Ponto getCentro() {
        return centro;
    }

    public void setCentro(Ponto centro) {
        this.centro = centro;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }
}
