import java.util.ArrayList;
/**
 *
 *
 */

public class Memento{
    private ArrayList<Producto> miCarrito;

    public Memento(ArrayList<Producto> miCarrito){
	this.miCarrito = miCarrito;
    }

    public ArrayList<Producto> getCarrito(){
	return miCarrito;
    }

}
