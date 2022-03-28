package maxmin_matrice;

/**
 * @author gubbriaco
 */
public class Main {

    public static void main(String... strings) {

        int[][] m = {{3, 1, 7},
                     {3, 2, 4},
                     {6, 3, 5},
                     {2, 6, 6}};

        if(m.length==m[0].length)
            System.err.println("Errore! Matrice non rettangolare!");
        else {

            stampa_matrice(m);

            //inizializzo un array contenente tutti i thread che calcolano il massimo per ogni riga
            int N = m.length;
            MaxRiga[] maxriga = new MaxRiga[N];
            for (int i = 0; i < maxriga.length; ++i) {
                maxriga[i] = new MaxRiga(i, m);
                maxriga[i].start();
            }

            //inizializzo un array contenente tutti i thread che calcolano il minimo per ogni colonna
            int M = m[0].length;
            MinColonna[] mincolonna = new MinColonna[M];
            for (int j = 0; j < mincolonna.length; ++j) {
                mincolonna[j] = new MinColonna(j, m);
                mincolonna[j].start();
            }


            //utilizzo oggetti di tipo Numero cosi da mantenere per ogni valore sia
            //la riga che la colonna corrispondente

            //inizializzo un array contenente tutti i max delle righe
            Numero[] max = new Numero[N];
            for (int i = 0; i < max.length; ++i)
                max[i] = maxriga[i].getMax();

            //inizializzo un array contenente tutti i min delle colonne
            Numero[] min = new Numero[M];
            for (int i = 0; i < min.length; ++i)
                min[i] = mincolonna[i].getMin();

            System.out.println();

            //ora controllo se alle stesse posizioni dei due array max e min esiste un numero uguale
            //tale che esso sarà contemporaneamente il massimo della riga e il minimo della colonna
            for (int i = 0; i < max.length; ++i) {
                for (int j = 0; j < min.length; ++j) {
                    boolean condizione = (max[i].getValore() == min[j].getValore()) &&
                            (max[i].getRiga() == min[j].getRiga()) &&
                            (max[i].getColonna() == min[j].getColonna());
                    if (condizione) {
                        System.out.println("Il valore cercato è " + max[i].getValore() + " (" + max[i].getRiga() +
                                "," + max[i].getColonna() + ")");
                        System.exit(0);
                    }
                }
            }
            System.err.println("Errore! Nessun valore trovato.");

        }//else

    }//main


    /**
     *
     * @param array
     */
    private static void stampa_array(int[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; ++i) {
            if (i == array.length - 1) {
                System.out.print(array[i]);
                System.out.print("]");
            } else System.out.print(array[i] + ",");
        }
    }

    /**
     *
     * @param matrice
     */
    private static void stampa_matrice(int[][] matrice) {
        for (int i = 0; i < matrice.length; ++i) {
            stampa_array(matrice[i]);
            System.out.println();
        }
    }


}