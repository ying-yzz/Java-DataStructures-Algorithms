package librerias.estructurasDeDatos.lineales;


/**
 * Write a description of class LEGListaConPIOrdenada here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LEGListaConPIOrdenada<E extends Comparable<E>> extends LEGListaConPI<E> { 
    public void insertar(E e){
        if(e==null){
            throw new IllegalArgumentException("No se puede insertar elemento");
        }
        inicio(); //pone el puntero al inicio
        //Mientras no sea el final o los elementos sean menores que e, avanzamos
        while (!this.esFin() && this.recuperar().compareTo(e) < 0) {
            this.siguiente();
        }
        //llamamos al método del padre con super para que inserte e en la lista
        super.insertar(e);
    }
    
}
