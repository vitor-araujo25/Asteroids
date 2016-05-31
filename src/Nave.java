/**
 * Created by Vitor on 19/05/2016.
 */

import java.util.Set;

public class Nave {
    private Ponto centro;
    private Ponto[] vertices;
    private double vx;
    private double vy;
    private double angulo;
    public static final Cor cor = new Cor("branco");

    public Nave(double x, double y, double vx, double vy, double angulo){
        vertices = new Ponto[3];
        vertices[0] = new Ponto(20,0);
        vertices[1] = new Ponto(-10,-11);
        vertices[2] = new Ponto(-10,11);
        centro = new Ponto(x,y);
        this.setVx(vx);
        this.setVy(vy);
        this.setAngulo(angulo);
    }

    public void desenhar(Tela t){
        Ponto[] verticesTela = new Ponto[3];
        for(int i = 0; i < verticesTela.length; i++){
            verticesTela[i] = new Ponto(centro.soma(vertices[i]));
        }
        t.triangulo(verticesTela[0].getX(),verticesTela[0].getY(),
                    verticesTela[1].getX(),verticesTela[1].getY(),
                    verticesTela[2].getX(),verticesTela[2].getY(),
                cor
        );
    }

    public void acelera(Set<String> t){
        if(t.contains("up") || t.contains("acima")) {
            vx += Math.cos(angulo)*100;
            vy += Math.sin(angulo)*100;
        }
        if(t.contains("down") || t.contains("abaixo")){
            vx -= Math.cos(angulo)*100;
            vy -= Math.sin(angulo)*100;
        }
    }

    public void mover(double dt){
        centro.setX(centro.getX() + vx*dt);
        centro.setY(centro.getY() + vx*dt);
    }

    public void giraNave(Set<String> t, double dt){
        if(t.contains("left") || t.contains("esquerda")){
            angulo += 2*Math.PI*dt;
            for(Ponto p: vertices){
                p.rotacionar(angulo);
            }
        }else if(t.contains("right") || t.contains("direita")){
            angulo -= 2*Math.PI*dt;
            for(Ponto p: vertices){
                p.rotacionar(angulo);
            }
        }
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
