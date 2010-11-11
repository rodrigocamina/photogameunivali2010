package engine.util;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.Point;

/**
 *
 * @author Administrador
 */

public class Poligono {
private Point[] cordenadas;
    public Poligono(Point[] cordenadas) throws Exception{
        if(cordenadas.length<3){
            throw new Exception("NOT ENOUGHT POINTS");
        }else{
            this.cordenadas = cordenadas;
        }
    }

    public Point[] getCordenadas() {
        return cordenadas;
    }
    
}
