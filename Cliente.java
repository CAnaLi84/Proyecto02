import java.util.ArrayList;
import java.util.Iterator;
/**
 * Clase Cliente que representa un cliente.
 * Originador para la  clase Memento.
 */

public class Cliente{
    private String username;
    private String contraseña;
    private ArrayList<Producto> miCarrito = new ArrayList<Producto>();
    private HistoricoCompras historico = new HistoricoCompras();

    /**
     * Crea un objeto cliente con los datos especificados.
     *
     */
    public Cliente(String username, String contraseña){
	this.username = username;
	this.contraseña = contraseña;
    }

    /**
     * Obtiene el nombre de usuario del cliente.
     * @return El nombre de usuario. 
     */
    public String getUsername(){
	return username;
    }

    /**
     * Asigna un nombre de usuario.
     * @param username El nombre de usuario que se quiere asignar.
     */
    public void setUsername(String username){
	this.username = username;
    }

    /**
     * Obtiene la contraseña del usuario.
     * @return La contraseña del usuario.
     */
    public String getContraseña(){
	return contraseña;
    }

    /**
     * Asigna una contraseña al usuario
     * @param contraseña La contraseña que se asigna a la cuenta del usuario.
     */
    public void setContraseña(){
	this.contraseña = contraseña;
    }

    /** 
     * Devuelve el carrito de compras actual.
     *
     */
    public ArrayList<Producto> getCarrito(){
	return miCarrito;
    }

    /**
     * Permite al usuario agregar un producto a su carrito de compras.
     * @param El producto que desea agregar al carrito.
     */
    public void agregarProductoCarrito(Producto miProducto){
	miCarrito.add(miProducto);
    }

    /**
     * Permite eliminar un producto del carrito de compras del cliente.
     * @param El producto que desea eliminar del carrito
     */
    public void eliminarProductoCarrito(Producto miProducto){
	Iterator<Producto> iter = miCarrito.iterator();
	while(iter.hasNext()){
	    Producto prod = iter.next();
	    if(prod.equals(miProducto)){
		iter.remove();
	    }
	}
    }

    /**
     * Permite realizar la compra de todos los productos que estén en el 
     * carrito del cliente al mismo tiempo que guarda esos productos en
     * su historial de compras realizadas.
     */
    public void comprarCarrito(){
        historico.guardar(this.guardar());
        miCarrito.clear();
	System.out.println("Gracias por su compra, vuelva pronto!");
    }

    /**
     * Permite guardar el estado del carrito al momento de invocarse.
     *
     */
    public void guardar(){
	Memento memento = new Memento(miCarrito);
    }

    /**
     * Permite restaurar el estádo del último carrito de compras
     *
     */
    public void restaurar(Memento memento){
	this.miCarrito = memento.getCarrito();
    }

    /**
     * Método que muestra el historial de comrpas del cliente.
     *
     */
    public Memento mostrarHistorialCompras(){
	Memento historial = historico.getUltimoEstadoGuardado();
	return historial;
    }

    /**
     * Método que permite al cliente ver los artículos de su carrito
     */
    public void verCarrito(){
	if(miCarrito != null){
	    for(Producto prod : miCarrito){
		System.out.println(prod.toStringCliente());
	    }
	}else{
	    System.out.println("Tu carrito de compras está no tiene productos.");
	}
    }
}
