package Asteroids;

/**
 * Created by vitor on 01/06/16.
 */
public class Hitbox {
    private boolean destruido = false;
    private boolean colisaoNave = false;
    private Asteroide a;

    public Hitbox(Asteroide a){
        this.a = a;
    }

    public void distancia(ObjetoJogo obj){
        switch(obj.getClass().getName()){
            case "Asteroids.Tiro":
                if(Math.sqrt(Math.pow((a.getCentro().getX()-obj.getCentro().getX()),2) +
                        Math.pow((a.getCentro().getY()-obj.getCentro().getY()),2)) < a.getRaio()){
                    destruido = true;
                    ((Tiro)obj).removeFlag = true;
                }
                break;
            case "Asteroids.Nave":
                if(Math.sqrt(Math.pow(a.getCentro().getX()-obj.getCentro().getX(),2) +
                        Math.pow(a.getCentro().getY()-obj.getCentro().getY(),2)) < a.getRaio()+12){
                    colisaoNave = true;
                }
                break;
        }
    }

    public boolean getDestruido() {
        return destruido;
    }

    public void setDestruido(boolean destruido) {
        this.destruido = destruido;
    }

    public boolean getColisaoNave() {
        return colisaoNave;
    }

    public void setColisaoNave(boolean colisaoNave) {
        this.colisaoNave = colisaoNave;
    }
}
