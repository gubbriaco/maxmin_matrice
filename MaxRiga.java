package maxmin_matrice;

import java.util.Arrays;

/**
 * @author gubbriaco
 */
public class MaxRiga extends Thread{

    private final int riga;
    private final int[][] matrice;
    private int valoreMax,rigaMax,colonnaMax;

    public MaxRiga(int riga, int[][] matrice){
        this.riga=riga;

        this.matrice=new int[matrice.length][matrice[0].length];
        for(int i=0;i<matrice.length;++i)
            this.matrice[i] = Arrays.copyOf(matrice[i], matrice[i].length);

        this.valoreMax=0;
        this.rigaMax=riga;
        this.colonnaMax=0;
    }

    @Override public void run(){
        int[] riga=Arrays.copyOf(matrice[this.riga],matrice[this.riga].length);
        for(int i=0;i<riga.length;++i)
            if(riga[i]>valoreMax) {
                valoreMax = riga[i];
                colonnaMax=i;
            }
    }

    public Numero getMax(){
        Numero numero=new Numero(valoreMax,rigaMax,colonnaMax);
        try{
            this.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        return numero;
    }



}
