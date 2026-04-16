package librerias.util;

 
/** 
 *  La clase Ordenacion contiene:
 *  - La implementacion de los algoritmos de ordenacion in-situ DyV
 *    Quick Sort y Merge Sort. 
 *  - Un metodo para comprobar si dos arrays genericos son iguales.
 *  
 * @author (EDA) 
 * @version (Curso 2017-2018)
 */

public class Ordenacion {
    
    // QUICK SORT -------------------------------------------------
    /** 
     *  Algoritmo de ordenacion quicksort (Hoare, 1963). 
     *  Utiliza el algoritmo de particion debido a Weiss, 
     *  con mediana de 3 para el calculo del pivote.
     *  
     *  @param a Sus elementos implementan la interfaz Comparable
     */
    public static <T extends Comparable<T>> void quickSort(T[]  a) {
        quickSort(a, 0, a.length - 1);
    }
   
    // Ordena el array a[izq, der] por quickSort, izq <= der
    private static <T extends Comparable<T>> void quickSort(T[] a, 
                                                            int izq, int der) {
        if (izq < der) {
            T pivot = mediana3(a, izq, der);
            int i = izq;
            int j = der - 1;
            for ( ; i < j;) {
                while (pivot.compareTo(a[++i]) > 0) { ; }
                while (pivot.compareTo(a[--j]) < 0) { ; }
                intercambiar(a, i, j);
            }
            intercambiar(a, i, j);        // Deshacer el ultimo cambio
            intercambiar(a, i, der - 1);  // Restaurar el pivote
            // PIVOTE ORDENADO -->
            quickSort(a, izq, i - 1);     // Ordenar rec. los elementos menores
            quickSort(a, i + 1, der);     // Ordenar rec. los elementos mayores
        }
    }

    // Intercambia los elementos ind1 e ind2 del array a
    private static <T> void intercambiar(T[] a, int ind1, int ind2) {
        T tmp = a[ind1];    
        a[ind1] = a[ind2];
        a[ind2] = tmp;   
    }
 
    // Tras calcular la Mediana de 3 del subArray a[izq, der], 
    // devuelve el valor del pivote
    private static <T extends Comparable<T>> T mediana3(T[] a, 
                                                        int izq, int der) {    
        int c = (izq + der) / 2;   
        if (a[c].compareTo(a[izq]) < 0)   { intercambiar(a, izq, c); }
        if (a[der].compareTo(a[izq]) < 0) { intercambiar(a, izq, der); }
        if (a[der].compareTo(a[c]) < 0)   { intercambiar(a, c, der); }
        // ocultar el pivote en la posicion der-1
        intercambiar(a, c, der - 1);
        return a[der - 1];
    }

    // MERGE SORT --------------------------------------------   
    // VERSION 1 (vista en teoria):   
    /**
     * Ordena ascendentemente el array v.
     * 
     * @param v  Sus elementos deben implementar la interfaz Comparable
     */
    public static <T extends Comparable<T>> void mergeSort1(T[] v) {
        mergeSort1(v, 0, v.length - 1);
    }
    
    /**
     * SII i<=f: ordena ascendentemente el subarray v[i, f].
     * 
     * @param v  Sus elementos implementan la interfaz Comparable
     * @param i  Extremo inferior del intervalo a ordenar
     * @param f  Extremo superior del intervalo a ordenar
     */
    private static <T extends Comparable<T>> void mergeSort1(T[] v, 
                                                             int i, int f) {
        if (i < f) {
            int m = (i + f) / 2;
            mergeSort1(v, i, m);
            mergeSort1(v, m + 1, f);
            merge1(v, i, f, m);
        }
    }        
    
    /**
     * Mezcla internamente los subarrays v[i, m] y v[m + 1, f], 
     * ambos ordenados Asc.
     * 
     * @param v  Sus elementos implementan la interfaz Comparable
     * @param i  Extremo inferior del intervalo a mezclar
     * @param f  Extremo superior del intervalo a mezclar
    */
    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> void merge1(T[] v, 
                                                         int i, int f, int m) {
        int a = i, b = m + 1, k = 0;
        T[] aux = (T[]) new Comparable[f - i + 1];
        while (a <= m && b <= f) {
            if (v[a].compareTo(v[b]) < 0) { aux[k++] = v[a++]; }
            else                          { aux[k++] = v[b++]; }
        }
        while (a <= m) { aux[k++] = v[a++]; }
        while (b <= f) { aux[k++] = v[b++]; }
        
        for (a = i, k = 0; a <= f; a++, k++) { v[a] = aux[k]; }
    }   
    
    // MERGE SORT --------------------------------------------   
    // VERSION 2 
    /**
     * Ordena ascendentemente el array v.
     * 
     * @param v  Sus elementos deben implementar la interfaz Comparable
     */
    public static <T extends Comparable<T>> void mergeSort2(T[] v) {
        //Lo que ve el usuario: espera pasarnos un array y que se ordene
        
        //Ordenamos el array, que se guarda en un auxiliar
        T[] aux = mergeSort2(v,0,v.length-1);
        
        //Actualizamos los elementos del array v con los elementos del array ordenado
        for(int i = 0; i<v.length;i++){
            v[i]=aux[i];
        }
    }
    
    /**
     * SII i<=f: devuelve un array con los elementos del subarray 
     * v[i, f] ordenados Asc. 
     * 
     * @param v  Sus elementos implementan la interfaz Comparable
     * @param i  Extremo inferior del intervalo a ordenar
     * @param f  Extremo superior del intervalo a ordenar
     * @return T[], el array resultante de ordenacion de v[i, f]
     */
    private static <T extends Comparable<T>> T[] mergeSort2(T[] v,
                                                             int i, int f) {
        //Se va ha decidir cómo dividir el problema
        //Dos casos base
        
        //Talla 1: rango definido por i e f contienen un elemento
        if(i==f){
            T[] res = (T[]) new Comparable[1];
            res[0]=v[i];
            return res;
        }
        
        //Talla 2: rango contiene dos elementos
        if((f - i) == 1){
            T[] res = (T[]) new Comparable[2];
            if(v[i].compareTo(v[f])<=0){
                res[0] = v[i];
                res[1] = v[f];
            }else{
                res[0] = v[f];
                res[1] = v[i];
            }
            return res;
        }
        
        //Caso recursivo
        //Cálculo punto medio evitando el overflow
        int medio = i+(f-i)/2;
        
        //Llamamos de forma recursiva para obtener dos arrays ordenados
        T[] izq = mergeSort2(v,i,medio);
        T[] der = mergeSort2(v,medio+1,f);
        
        //Juntamos esos dos arrays ordenados
        return (merge2 (izq,der));
        
    }        
    
    /**
     * Devuelve el array mezcla de v1 y v2, dos arrays ordenados Asc.
     * 
     * @param v1  Sus elementos implementan la interfaz Comparable
     * @param v2  Sus elementos implementan la interfaz Comparable
     * @return T[], el array resultante de la fusion de v1 y v2
     */
    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> T[] merge2(T[] v1, T[] v2) {
        //Nos dan dos arrays v1 y v2 ordenados
        //Nos piden juntarlo y ordenarlos arrays cuando estén juntos
        
        //Creación del array final de la unión de v1 y v2
        //Si se intenta escribir T[] res = new T[v1.length + v2.length]; da error
        //Primero decimos que se crea una array de Comparable y luego lo casteamos a T
        T[] res =(T[]) new Comparable[v1.length + v2.length];
        
        int i = 0; int j = 0; int k = 0;
        
        while(i<v1.length && j < v2.length){
            if(v1[i].compareTo(v2[j]) <= 0){
                res[k] = v1[i];
                i++;
            }else{
                res[k] = v2[j];
                j++;
            }
            k++;
        }
        
        //Si aun quedan elementos de v1
        while (i<v1.length){
            res[k] = v1[i];
            k++;
            i++;
        }
        
        //Si aun quedan elementos de v2
        while (j<v2.length){
            res[k] = v2[j];
            k++;
            j++;
        }
        
        return res;
        
    }
    
    // Metodo auxiliar --------------------------------------------
    /** 
     *  Comprueba si los arrays a y b son iguales elemento a elemento.
     *  
     *  @param a  Sus elementos implementan la interfaz Comparable
     *  @return boolean, el resultado de la comprobacion
     */
    public static <T extends Comparable<T>> boolean sonIguales(T[] a, T[] b) {
        boolean iguales = true;
        if (a.length != b.length) { iguales = false; }
        else {
            for (int i = 0; i < a.length && iguales; i++) {
                iguales = (a[i].compareTo(b[i]) == 0);
            }
        }    
        return iguales;
    }    
}
