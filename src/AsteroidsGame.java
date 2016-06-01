import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Random;

/**
 * Created by vitor on 18/05/16.
 */
public class AsteroidsGame implements Jogo{

    Set<Asteroide> asteroides;
    Nave nave;
    Set<Tiro> tiros;
    public static Random generator = new Random();

    public AsteroidsGame() {
        asteroides = new HashSet<>();
        for(int i = 0; i < 6; i++) {
            asteroides.add(new Asteroide(800 * Math.random(), //x
                    600 * Math.random(),  //y
                    generator.nextInt(4)+1, //tamanho
                    Math.pow((-1),generator.nextInt(2))*(Math.random()*50 + 80), //velocidade_x
                    Math.pow((-1),generator.nextInt(2))*(Math.random()*50 + 80), //velocidade_y
                    new Cor(generator.nextInt(236)+20, generator.nextInt(236)+20, generator.nextInt(236)+20) //cor
            ));
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
        for(Asteroide a: asteroides){
            a.mover(getAltura(),getLargura(),dt);
        }
        if(teclas.contains("left") || teclas.contains("esquerda") || teclas.contains("a")){
            nave.giraEsquerda(dt);
        }
        if(teclas.contains("right") || teclas.contains("direita") || teclas.contains("d")){
            nave.giraDireita(dt);
        }
        if(teclas.contains("up") || teclas.contains("acima") || teclas.contains("w")) {
            nave.acelera();
        }else{
            if(nave.getVx() != 0){
                nave.atritoX();
            }
            if(nave.getVy() != 0){
                nave.atritoY();
            }
        }
        if(tiros.size() != 0){
            for(Iterator<Tiro> itTiros = tiros.iterator(); itTiros.hasNext();){
                Tiro t = itTiros.next();
                t.mover(getAltura(),getLargura(),dt);
                for(Iterator<Asteroide> itAst = asteroides.iterator(); itAst.hasNext();){
                    Asteroide a = itAst.next();
                    a.getHb().distancia(t);
                    if(a.getHb().getBateu()){
                        a.divide(); //TODO: Remover o asteroide com bateu ligado e criar outros se necessario
                    }
                }
                if(t.removeFlag){
                    itTiros.remove();
                }
            }
        }
        nave.mover(this.getAltura(),this.getLargura(),dt);
    }

    public void desenhar(Tela tela){
        nave.desenhar(tela);
        for(Asteroide a: asteroides){
            a.desenhar(tela);
        }
        for(Tiro t: tiros){
            t.desenhar(tela);
        }
    }

    public void tecla(String tecla){
        if(tecla.equals("space") || tecla.equals("espaço") || tecla.equals("k")){
            tiros.add(new Tiro(nave));
        }

    }

    public static void main(String[] args){
        new Motor(new AsteroidsGame());
    }

}
