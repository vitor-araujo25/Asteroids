import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Random;

/**
 * Created by vitor on 18/05/16.
 */
public class AsteroidsGame implements Jogo{

    Set<Asteroide> asteroides,filaAsteroides;
    Nave nave;
    Set<Tiro> tiros;
    static int quantidadeAsteroidesNovos = 2;
    static Random generator = new Random();
    static int vidas = 3;
    static int score = 0;
    static boolean gameOver = false;
    static boolean gameStart = false;
    static long t0;


    public AsteroidsGame() {
        asteroides = new HashSet<>();
        filaAsteroides = new HashSet<>();
        for(int i = 0; i < 6; i++) {
            asteroides.add(new Asteroide(800 * Math.random(), //x
                    600 * Math.random(),  //y
                    generator.nextInt(4)+1, //tamanho
                    Math.pow((-1),generator.nextInt(2))*(Math.random()*50 + 100), //velocidade_x
                    Math.pow((-1),generator.nextInt(2))*(Math.random()*50 + 100), //velocidade_y
                    new Cor(generator.nextInt(236)+20, generator.nextInt(236)+20, generator.nextInt(236)+20) //cor
            ));
        }
        nave = new Nave((double)getLargura()/2,(double)getAltura()/2,0.0,0.0);
        tiros = new HashSet<>();
    }

    public void pontuador(int tam){
        score += (5-tam)*10;
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
        long tf = System.currentTimeMillis() - t0;
        if(!gameOver && tf > 1000){
            if (teclas.contains("left") || teclas.contains("esquerda") || teclas.contains("a")) {
                nave.giraEsquerda(dt);
            }
            if (teclas.contains("right") || teclas.contains("direita") || teclas.contains("d")) {
                nave.giraDireita(dt);
            }
            if (teclas.contains("up") || teclas.contains("acima") || teclas.contains("w")) {
                gameStart = true;
                nave.acelera();
            }else{
                if(nave.getVx() != 0){
                    nave.setVx(nave.atrito(nave.getVx()));
                }
                if(nave.getVy() != 0){
                    nave.setVy(nave.atrito(nave.getVy()));
                }
            }

            //move os tiros e checa se o tiro colidiu com algum asteroide
            if(tiros.size() != 0){
                for (Iterator<Tiro> itTiros = tiros.iterator(); itTiros.hasNext(); ) {
                    Tiro t = itTiros.next();
                    t.mover(getAltura(), getLargura(), dt);
                    for (Iterator<Asteroide> itAst = asteroides.iterator(); itAst.hasNext(); ) {
                        Asteroide a = itAst.next();
                        a.getHb().distancia(t);
                        if (a.getHb().getDestruido()) {
                            pontuador(a.getTam());
                            a.geraNovos(filaAsteroides);
                            itAst.remove();
                        }
                    }
                    if (t.removeFlag) {
                        itTiros.remove();
                    }
                }
            }

            //cria os asteroides que estão na fila e os tiram da fila
            for (Iterator<Asteroide> it = filaAsteroides.iterator(); it.hasNext(); ) {
                Asteroide a = it.next();
                asteroides.add(a);
                it.remove();
            }

            /*
            *   move os asteroides, testa a colisao da nave com cada um deles e reseta a nave se bater
            *   a colisao da nave com os asteroides só é considerada se eu já tiver acelerado no jogo
            *   desde o ultimo reset e se já tiverem passado 2 segundos de jogo desde o ultimo reset
            */
            for (Asteroide a : asteroides) {
                a.mover(getAltura(), getLargura(), dt);
                if (gameStart) {
                    a.getHb().distancia(nave);
                    if (a.getHb().getColisaoNave()) {
                        t0 = System.currentTimeMillis();
                        vidas--;
                        nave.reset(getAltura(), getLargura());
                        gameStart = false;
                        a.getHb().setColisaoNave(false);
                        if(vidas == 0) {
                            gameOver = true;
                            gameStart = true;
                        }
                    }
                }
            }

            /*
            *   cria asteroides novos se a quantidade de asteroides na tela for menor que 4
            *   a quantidade de novos asteroides criados vai aumentando de acordo com a quantidade de
            *   vezes que os asteroides na tela foram reduzidos a menos de 4, até um limite de mais 8 asteroides
            */
            if (asteroides.size() < 4) {
                for(int i = 0; i < quantidadeAsteroidesNovos; i++){
                    asteroides.add(new Asteroide(0,0,
                            generator.nextInt(4)+1, //tamanho
                            Math.pow((-1),generator.nextInt(2))*(Math.random()*50 + 100), //velocidade_x
                            Math.pow((-1),generator.nextInt(2))*(Math.random()*50 + 100), //velocidade_y
                            new Cor(generator.nextInt(236)+20, generator.nextInt(236)+20, generator.nextInt(236)+20) //cor
                    ));
                }
                if(quantidadeAsteroidesNovos < 8){
                    quantidadeAsteroidesNovos++;
                }
            }

            nave.mover(getAltura(), getLargura(), dt);
        }else{
            for(Asteroide a: asteroides){
                a.mover(getAltura(),getLargura(), dt);
            }
            if(tiros.size() != 0){
                for(Iterator<Tiro> it = tiros.iterator(); it.hasNext();){
                    Tiro t = it.next();
                    t.mover(getAltura(),getLargura(),dt);
                    if(t.removeFlag){
                        it.remove();
                    }
                }
            }
        }
    }

    public void desenhar(Tela tela){
        if(!gameOver){
            nave.desenhar(tela);
        }
        for(Asteroide a: asteroides){
            a.desenhar(tela);
        }
        for(Tiro t: tiros){
            t.desenhar(tela);
        }

        if(gameOver){
            tela.texto("GAME OVER",getLargura()/2-210,getAltura()/2+10,70,Cor.BRANCO);
        }

        //interface de pontos e vidas
        tela.texto("Vidas",80,40,20,Cor.BRANCO);
        tela.texto(Integer.toString(vidas),95,80,40,Cor.BRANCO);
        tela.texto("Pontos",getLargura()-140,40,20,Cor.BRANCO);
        tela.texto(Integer.toString(score),getLargura()-145,80,40,Cor.BRANCO);
        if(!gameStart){
            tela.texto("Bem-vindo ao Asteroids!",300,60,20,Cor.BRANCO);
            tela.texto("Acelere [W] ou [up] para começar!",250,500,20,Cor.BRANCO);
            tela.texto("Pressione [space] ou [K] para atirar!",240,530,20,Cor.BRANCO);
            tela.texto("Pressione [A], [left], [D], [right] para girar",215,560,20,Cor.BRANCO);
        }
    }

    public void tecla(String tecla){
        if((tecla.equals("space") || tecla.equals("espaço") || tecla.equals("k")) && !gameOver){
            gameStart = true;
            tiros.add(new Tiro(nave));
        }

    }

    public static void main(String[] args){
        new Motor(new AsteroidsGame());
        AsteroidsGame.t0 = System.currentTimeMillis();
    }

}
