package maxmin_matrice;

/**
 * @author gubbriaco
 */
public class Numero {

    private final int valore, riga, colonna;

    public Numero(int valore, int riga, int colonna){
        this.valore=valore;
        this.riga=riga;
        this.colonna=colonna;
    }

    public int getValore() {
        return valore;
    }

    public int getRiga() {
        return riga;
    }

    public int getColonna() {
        return colonna;
    }

}
