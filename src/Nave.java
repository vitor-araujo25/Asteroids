/**
 * Created by Vitor on 19/05/2016.
 */
public class Nave extends ObjetoJogo{
    private double angulo;
    public static final Cor cor = new Cor("branco");

    public Nave(double x, double y, double vx, double vy){
        centro = new Ponto(x,y);
        this.vx = vx;
        this.vy = vy;
        this.angulo = 0.0;
    }

    @Override
    public void desenhar(Tela t){
        Ponto[] vertices = new Ponto[3];
        vertices[0] = new Ponto(10,0);
        vertices[1] = new Ponto(-8,-9);
        vertices[2] = new Ponto(-8,9);

        for(Ponto p: vertices){
            p.rotacionar(angulo);
        }

        t.triangulo(vertices[0].getX()+centro.getX(),
                    vertices[0].getY()+centro.getY(),
                    vertices[1].getX()+centro.getX(),
                    vertices[1].getY()+centro.getY(),
                    vertices[2].getX()+centro.getX(),
                    vertices[2].getY()+centro.getY(),
                    cor
        );

        t.triangulo(centro.getX(),centro.getY(),
                    vertices[1].getX()+centro.getX(),
                    vertices[1].getY()+centro.getY(),
                    vertices[2].getX()+centro.getX(),
                    vertices[2].getY()+centro.getY(),
                    new Cor(0,0,0)
        );
    }

    public void acelera(){
        if(vx < 300 && vx > -300){
            vx += Math.cos(angulo);
        }
        if(vy < 300 && vy > -300){
            vy += Math.sin(angulo);
        }
    }

    public void atritoX(){
        if(vx > 0){
            if(vx < 1){
                vx = 0;
            }else{
                vx -= 1;
            }
        }else{
            if(vx > -1){
                vx = 0;
            }else{
                vx += 1;
            }
        }
    }

    public void atritoY(){
        if(vy > 0){
            if(vy < 1){
                vy = 0;
            }else{
                vy -= 1;
            }
        }else{
            if(vy > -1){
                vy = 0;
            }else{
                vy += 1;
            }
        }
    }

    @Override
    public void mover(int altTela, int largTela, double dt){
        centro.setX(centro.getX() + vx*dt);
        centro.setY(centro.getY() + vy*dt);

        if(centro.getY() > altTela+5){
            centro.setY(-5);
        }
        if(centro.getY() < -5){
            centro.setY(altTela+5);
        }
        if(centro.getX() > largTela+5){
            centro.setX(-5);
        }
        if(centro.getX() < -5){
            centro.setX(largTela+5);
        }
    }

    public void reset(int altTela, int largTela){
        centro.setX((double)largTela/2);
        centro.setY((double)altTela/2);
        angulo = vx = vy = 0;
    }

    public void giraEsquerda(double dt){
        angulo -= Math.PI*dt;
    }

    public void giraDireita(double dt){
        angulo += Math.PI*dt;
    }

    public double getAngulo() {
        return angulo;
    }

    public void setAngulo(double angulo) {
        this.angulo = angulo;
    }
}
