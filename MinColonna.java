package maxmin_matrice;

import java.util.Arrays;

/**
 * @author gubbriaco
 */
public class MinColonna extends Thread{

    private final int colonna;
    private final int[][] matrice;
    private int valoreMin,rigaMin,colonnaMin;

    public MinColonna(int colonna, int[][] matrice){

        this.colonna=colonna;

        this.matrice=new int[matrice.length][matrice[0].length];
        for(int i=0;i<matrice.length;++i)
            this.matrice[i] = Arrays.copyOf(matrice[i], matrice[i].length);

        this.valoreMin=Integer.MAX_VALUE;
        this.rigaMin=0;
        this.colonnaMin=colonna;

    }


    @Override public void run(){
        int[] c=getColonna();
        int[] colonna=Arrays.copyOf(c,c.length);
        for(int i=0;i<colonna.length;++i)
            if(colonna[i]<valoreMin) {
                valoreMin = colonna[i];
                rigaMin=i;
            }
    }


    public Numero getMin(){
        Numero numero=new Numero(valoreMin,rigaMin,colonnaMin);
        try{
            this.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        return numero;
    }


    private int[] getColonna(){
        int[] c=new int[matrice.length];
        for(int i=0;i<matrice.length;++i)
            for(int j=0;j<matrice[i].length;++j)
                if(j==this.colonna)
                    c[i]=matrice[i][j];
        return c;
    }

}
