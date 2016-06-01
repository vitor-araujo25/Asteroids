/**
 * Created by vitor on 18/05/16.
 */
public class Asteroide extends ObjetoJogo{
    private int tam;
    private Cor cor;
    private int raio;
    private Hitbox hb;

    public Asteroide(double x, double y, int tam, double vx, double vy, Cor cor){
        centro = new Ponto(x,y);
        this.tam = tam;
        this.cor = cor;
        this.vx = vx;
        this.vy = vy;
        this.raio = (15*tam)/2;
        this.hb = new Hitbox(this);
    }

    @Override
    public void desenhar(Tela t){
        t.circulo(centro.getX(), centro.getY(), raio, cor);
    }

    @Override
    public void mover(int altTela, int largTela, double dt){
        centro.setX(centro.getX() + vx*dt);
        centro.setY(centro.getY() + vy*dt);
        if(centro.getY() > altTela+raio){
            centro.setY(-raio);
        }
        if(centro.getY() < -raio){
            centro.setY(altTela+raio);
        }
        if(centro.getX() > largTela+raio){
            centro.setX(-raio);
        }
        if(centro.getX() < -raio){
            centro.setX(largTela+raio);
        }
    }

    public int getTam() {
        return tam;
    }

    public void setTam(int tam) {
        this.tam = tam;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public int getRaio() {
        return raio;
    }

    public void setRaio(int raio) {
        this.raio = raio;
    }

    public Hitbox getHb() {
        return hb;
    }

    public void setHb(Hitbox hb) {
        this.hb = hb;
    }
}
