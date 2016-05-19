/**
 * Created by Vitor on 19/05/2016.
 */
public class Nave {
    Ponto centro;
    private double vx;
    private double vy;
    private double angulo;
    public static final Cor cor = new Cor("branco");

    public Nave(double x, double y, double vx, double vy, double angulo){
        centro = new Ponto(x,y);
        this.setVx(vx);
        this.setVy(vy);
        this.setAngulo(angulo);
    }

    public void desenhar(Tela t){
        Ponto p1,p2,p3;
        p1 = new Ponto(centro.getX()+5,centro.getY());
        p2 = new Ponto(centro.getX()-3,centro.getY()-4);
        p3 = new Ponto(centro.getX()-3,centro.getY()+4);
        t.triangulo(p1.getX(),p1.getY(),
                p2.getX(),p2.getY(),
                p3.getX(),p3.getY(),
                cor
        );
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public double getAngulo() {
        return angulo;
    }

    public void setAngulo(double angulo) {
        this.angulo = angulo;
    }
}
