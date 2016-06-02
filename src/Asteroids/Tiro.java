package Asteroids;

/**
 * Created by Vitor on 31/05/2016.
 */
public class Tiro extends ObjetoJogo {
    private static final int raio = 1;
    private static final Cor cor = new Cor("branco");
    public boolean removeFlag = false;

    public Tiro(Nave nave){
        centro = new Ponto(nave.getCentro().getX(),nave.getCentro().getY());
        this.vx = nave.getVx()+250*Math.cos(nave.getAngulo());
        this.vy = nave.getVy()+250*Math.sin(nave.getAngulo());
    }

    @Override
    public void desenhar(Tela t) {
        t.circulo(centro.getX(),centro.getY(),raio,cor);
    }

    @Override
    public void mover(int altTela, int largTela, double dt) {
        centro.setX(centro.getX() + vx*dt);
        centro.setY(centro.getY() + vy*dt);
        this.foraDaTela(altTela,largTela);
    }

    public void foraDaTela(int altTela, int largTela){
        if(centro.getX() > largTela || centro.getX() < 0 || centro.getY() > altTela || centro.getY() < 0){
            this.removeFlag = true;
        }
    }
}
