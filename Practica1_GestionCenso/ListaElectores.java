package aplicaciones.censo;

import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import librerias.estructurasDeDatos.lineales.LEGListaConPIOrdenada;

/**
 * ListaElectores: representa una lista de habitantes, 
 *                 registrados en el censo, y por ello, electores
 * 
 * @author  Profesores EDA 
 * @version Septiembre 2023
 */

public class ListaElectores {
   
    private ListaConPI<Habitante> censo;
    private int talla;
    
    /**
     * Métodos consultores de atributos
     */
    public ListaConPI<Habitante> getCenso() { return censo; }
    public int getTalla() { return talla; }
    
    /**
     * Devuelve el String que representa una ListaElectores 
     * 
     * @return el String con la ListaElectores en el formato texto dado. 
     */
    public String toString() {
        String res = "";
        if (talla == 0) return res;
        censo.inicio();
        for (int pos = 0; pos <= censo.talla() - 2; pos++) {
            res += censo.recuperar() + ", \n";
            censo.siguiente();
        }
        res += censo.recuperar();
        return res;
    }
   
    /**
     * Crea una ListaElectores...
     * 
     * @param orden Un boolean que indica si el censo,  
     *              debe estar ordenada ascendentemente (true) o no (false). 
     *              
     * @param n     Un int que indica la talla, número de elementos, de la lista              
     */
    public ListaElectores(boolean orden, int n) {
        talla = n;
        //El censo está ordenado; se instancia
        //censo creado como private ListaConPI<Habitante> censo
        if (orden){
            this.censo = new LEGListaConPIOrdenada<Habitante>();
        }
        //El censo no está ordenado
        else{
            this.censo = new LEGListaConPI<Habitante>();
        }
        
        //Añadimos habitantes al censo/lista
        for(int i = 0; i < n; i++){
            
            //Creación nuevo habitante para añadir
            Habitante nuevoHabitante = new Habitante();
            
            //Comprueba si el nuevo habitante está en el censo
            //Si el indice es -1 indica que el habitante no se encuentra en el censo
            if(this.indice(nuevoHabitante) == -1){
                this.censo.insertar(nuevoHabitante);
            }
        }
    }
    
    /**
     * Devuelve el índice o posicion del Habitante h en una ListaElectores, 
     * o -1 si h no forma parte de la lista. 
     * 
     * @param h un Habitante
     * @return  el índice de h en un censo, un valor int
     *          0 o positivo si h esta en el censo      
     *          o -1 en caso contrario
     */
    protected int indice(Habitante h) {
       int pos = 0;
       
       //Vamos al principio de la lista
       this.censo.inicio();
       
       while(!this.censo.esFin()){
           
           //Obtenemos un habitante y comparamos si son iguales
           //Se utiliza equals para comparar el dni y ver si dos personas son iguales
           //El compareTo se utiliza para ordenar en este caso
           if(this.censo.recuperar().equals(h)){
               
               //Al ser encontrado se devuelve la posición actual
               return pos;
           }
           
           //Avanzamos el PI y la posición
           this.censo.siguiente();
           pos++;
       }
       
       //No se encuentra en la lista
       return -1;
        
    }
    
}
