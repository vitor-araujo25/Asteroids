/**
 * Created by vitor on 01/06/16.
 */
public class Hitbox {
    private boolean bateu = false;
    private Asteroide a;

    public Hitbox(Asteroide a){
        this.a = a;
    }

    public void distancia(ObjetoJogo obj){
        switch(obj.getClass().getName()){
            case "Tiro":
                if(Math.sqrt(Math.pow(a.getCentro().getX()-obj.getCentro().getX(),2) +
                        Math.pow(a.getCentro().getY()-obj.getCentro().getY(),2)) < a.getRaio()){
                    bateu = true;
                }
                break;
            case "Nave":
                if(Math.sqrt(Math.pow(a.getCentro().getX()-obj.getCentro().getX(),2) +
                        Math.pow(a.getCentro().getY()-obj.getCentro().getY(),2)) < a.getRaio()+5){
                    bateu = true;
                }
                break;
        }
    }

    public boolean getBateu() {
        return bateu;
    }

    public void setBateu(boolean bateu) {
        this.bateu = bateu;
    }
}
