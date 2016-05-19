/**
 * Created by vitor on 18/05/16.
 */
public class Asteroide {
    double x;
    double y;
    int tam;
    Cor cor;
    double vx;
    double vy;

    public Asteroide(double x, double y, int tam, double vx, double vy, Cor cor){
        this.x = x;
        this.y = y;
        this.tam = tam;
        this.cor = cor;
//        if()

        this.vx = vx;
        this.vy = vy;
    }

    public void desenhar(Tela t){
        t.circulo(x,y,(10*tam)/2,cor);
    }

    public void move(){

    }
}
