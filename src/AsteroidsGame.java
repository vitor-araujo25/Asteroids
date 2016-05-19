import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by vitor on 18/05/16.
 */
public class AsteroidsGame implements Jogo{

    Asteroide[] asts;
    Nave nave;

    public AsteroidsGame() {
        asts = new Asteroide[6];
        for(int i = 0; i < 6; i++) {
            asts[i] = new Asteroide(800 * Math.random(), //x
                    600 * Math.random(),  //y
                    ThreadLocalRandom.current().nextInt(1, 5), //tamanho
                    Math.random()*200, //velocidade_x
                    Math.random()*200, //velocidade_y
                    new Cor(Math.random(), Math.random(), Math.random()) //cor
            );
        }
        nave = new Nave((double)getLargura()/2,(double)getAltura()/2,0.0,0.0,0.0);
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
            a.move(dt,getLargura(),getAltura());
        }
    }

    public void desenhar(Tela tela){
        for(Asteroide a: asts){
            a.desenhar(tela);
        }
        nave.desenhar(tela);
    }

    public void tecla(String tecla){

    }

    public static void main(String[] args){
        new Motor(new AsteroidsGame());
    }

}
