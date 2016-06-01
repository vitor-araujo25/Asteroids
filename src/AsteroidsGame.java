import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by vitor on 18/05/16.
 */
public class AsteroidsGame implements Jogo{

    Asteroide[] asts;
    Nave nave;
    Set<Tiro> tiros;

    public AsteroidsGame() {
        asts = new Asteroide[6];
        for(int i = 0; i < 6; i++) {
            asts[i] = new Asteroide(800 * Math.random(), //x
                    600 * Math.random(),  //y
                    ThreadLocalRandom.current().nextInt(1, 5), //tamanho
                    Math.pow((-1),ThreadLocalRandom.current().nextInt(1, 3))*(Math.random()*50 + 50), //velocidade_x
                    Math.pow((-1),ThreadLocalRandom.current().nextInt(1, 3))*(Math.random()*50 + 50), //velocidade_y
                    new Cor(Math.random(), Math.random(), Math.random()) //cor
            );
        }
        nave = new Nave((double)getLargura()/2,(double)getAltura()/2,0.0,0.0);
        tiros = new HashSet<>();
    }

    public String getTitulo(){
        return "Asteroids";
    }

    public int getLargura(){
        return 800;
    }

    public int getAltura(){
        return 600;
    }

    public void tique(Set<String> teclas, double dt){
        for(Asteroide a: asts){
            a.mover(getAltura(),getLargura(),dt);
        }
        if(teclas.contains("left") || teclas.contains("esquerda")){
            nave.giraEsquerda(dt);
        }
        if(teclas.contains("right") || teclas.contains("direita")){
            nave.giraDireita(dt);
        }
        if(teclas.contains("up") || teclas.contains("acima")) {
            nave.acelera();
        }else{
            if(nave.getVx() != 0){
                nave.atritoX();
            }
            if(nave.getVy() != 0){
                nave.atritoY();
            }
        }
        for(Iterator<Tiro> it = tiros.iterator(); it.hasNext();){
            Tiro t = it.next();
            t.mover(getAltura(),getLargura(),dt);
            if(t.removeFlag){
                it.remove();
            }
        }
        nave.mover(this.getAltura(),this.getLargura(),dt);
    }

    public void desenhar(Tela tela){
        nave.desenhar(tela);
        for(Asteroide a: asts){
            a.desenhar(tela);
        }
        for(Tiro t: tiros){
            t.desenhar(tela);
        }
    }

    public void tecla(String tecla){
        if(tecla.equals("space") || tecla.equals("espaço")){
            tiros.add(new Tiro(nave));
        }

    }

    public static void main(String[] args){
        new Motor(new AsteroidsGame());
    }

}
