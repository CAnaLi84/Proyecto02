import java.util.ArrayList;

/**
 * Esta clase sirve para guardar los productos que el cliente ha comprado.
 *
 */

public class HistoricoCompras{
    private ArrayList<Producto> historico = new ArrayList<>();

    /**
     * Método que será invocado cuando un cliente realice la compra de
     * su carrito con el cual se guardan los productos guardados.
     */
    public void guardar(ArrayList<Producto> carritoCliente){
	for(Producto prod: carritoCliente){
	    historico.add(prod);
	}
    }

    /**
     * Método que imprime el historial de compras del cliente.
     */
    public void getHistorico(){
	if(historico.size() > 0){
	    for(Producto prod: historico){
		System.out.println(prod.toString());
	    }
	}else{
	    System.out.println("Aún no has comprado ningún producto");
	}
    }

}
