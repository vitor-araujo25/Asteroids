/**
 * Created by Vitor on 19/05/2016.
 */
public class Nave extends ObjetoJogo{
    private double angulo;

    public Nave(double x, double y, double vx, double vy){
        centro = new Ponto(x,y);
        this.vx = vx;
        this.vy = vy;
        this.angulo = 0.0;
    }

    @Override
    public void desenhar(Tela t){
        Ponto[] vertices = new Ponto[3];
        vertices[0] = new Ponto(15,0);
        vertices[1] = new Ponto(-12,-13.5);
        vertices[2] = new Ponto(-12,13.5);

        for(Ponto p: vertices){
            p.rotacionar(angulo);
        }

        //parte branca da nave
        t.triangulo(vertices[0].getX()+centro.getX(),
                    vertices[0].getY()+centro.getY(),
                    vertices[1].getX()+centro.getX(),
                    vertices[1].getY()+centro.getY(),
                    vertices[2].getX()+centro.getX(),
                    vertices[2].getY()+centro.getY(),
                    Cor.BRANCO
        );

        //parte preta da nave
        t.triangulo(centro.getX(),centro.getY(),
                    vertices[1].getX()+centro.getX(),
                    vertices[1].getY()+centro.getY(),
                    vertices[2].getX()+centro.getX(),
                    vertices[2].getY()+centro.getY(),
                    Cor.PRETO
        );
    }

    //velocidade limitada a 300 px/s
    public void acelera(){
        if(vx < 300 && vx > -300){
            vx += Math.cos(angulo);
        }
        if(vy < 300 && vy > -300){
            vy += Math.sin(angulo);
        }
    }


    //"freio" da nave
    //quando o jogador para de acelerar, a nave perde velocidade gradualmente até parar
    public double atrito(double v){
        if(v > 0){
            if(v < 1){
                v = 0;
            }else{
                v -= 1;
            }
        }else{
            if(v > -1){
                v = 0;
            }else{
                v += 1;
            }
        }
        return v;
    }

    public void giraEsquerda(double dt){
        angulo -= 1.5*Math.PI*dt;
    }

    public void giraDireita(double dt){
        angulo += 1.5*Math.PI*dt;
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

    //põe a nave de volta no centro da tela com rotação 0 e velocidade 0
    public void reset(int altTela, int largTela){
        centro.setX((double)largTela/2);
        centro.setY((double)altTela/2);
        angulo = vx = vy = 0;
    }

    public double getAngulo() {
        return angulo;
    }

    public void setAngulo(double angulo) {
        this.angulo = angulo;
    }
}
