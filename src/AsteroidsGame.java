import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by vitor on 18/05/16.
 */
public class AsteroidsGame implements Jogo{

    Asteroide[] asts;

    public AsteroidsGame() {
        asts = new Asteroide[6];
        for (int i = 0; i < 6; i++) {
            asts[i] = new Asteroide(800 * Math.random(), 600 * Math.random(), ThreadLocalRandom.current().nextInt(1, 5),
                    Math.random() * 50, Math.random() * 50, new Cor(Math.random(), Math.random(), Math.random()));
        }
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

    }

    public void desenhar(Tela tela){
        for(Asteroide a: asts){
            a.desenhar(tela);
        }
    }

    public void tecla(String tecla){

    }

    public static void main(String[] args){
        new Motor(new AsteroidsGame());
    }

}
