import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * La clase Producto representa un producto.
 */
public class Producto {

    private String codigo;
    private String nombre;
    private Double precio;
    private int cantidad;
    private LocalDate caducidad;


    /**
     * Crea un nuevo objeto Producto con los datos especificados.
     * @param codigo El código del producto.
     * @param nombre El nombre del producto.
     * @param precio El precio del producto.
     * @param cantidad La cantidad disponible del producto.
     * @param caducidad La fecha de caducidad del producto.
     */
    public Producto(String codigo, String nombre, Double precio, int cantidad, LocalDate caducidad){
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.caducidad = caducidad;
    }

    /**
     * Obtiene el código del producto.
     * @return El código del producto.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Establece el código del producto.
     * @param codigo El nuevo código del producto.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

     /**
     * Obtiene el nombre del producto.
     * @return El nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     * @param nombre El nuevo nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el precio del producto.
     * @return El precio del producto.
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto.
     * @param precio El nuevo precio del producto.
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la cantidad disponible del producto.
     * @return La cantidad disponible del producto.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad disponible del producto.
     * @param cantidad La nueva cantidad disponible del producto.
     */
    public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
    }

    /**
     * Obtiene la fecha de caducidad del producto.
     * @return La fecha de caducidad del producto.
     */
    public LocalDate getCaducidad() {
        return caducidad;
    }

    /**
     * Establece la fecha de caducidad del producto.
     * @param caducidad La nueva fecha de caducidad del producto.
     */
    public void setCaducidad(LocalDate caducidad) {
        this.caducidad = caducidad;    
    }

    /**
     * Devuelve una representación en cadena del producto.
     * @return Una cadena con los datos del producto formateados.
     */
    public String toString() {
        return String.format("| %-18s | %-23s | %-15.2f | %-19d |", codigo, nombre, precio, cantidad);
    }

    /**
     * Devuelve una representación en cadena del producto para el cliente.
     * @return Una cadena con los datos del producto formateados sin la cantidad de este.
     */
    public String toStringCliente(){
	return String.format("| %-18s | %-23s | %-15.2f |", codigo, nombre, precio);
    }
    
    /**
     * Devuelve una cadena con los datos del producto formateados para ser guardados en un archivo.
     * @return Una cadena con los datos del producto serializados.
     */
    public String serializa(){
        return String.format("%s;%s;%f;%d;%s\n", codigo, nombre, precio, cantidad, caducidad.toString());
    }

    /**
     * Deserializa una cadena para obtener los datos del producto.
     * @param producto La cadena con los datos del producto serializados.
     */
    public void deserializa(String producto){
        try {
            String[] campos = producto.split(";");
            codigo = campos[0];
            nombre = campos[1];
            precio = Double.parseDouble(campos[2]);
            cantidad = Integer.parseInt(campos[3]);
            caducidad = LocalDate.parse(campos[4]);
        } catch (DateTimeParseException | NumberFormatException ex) {
            System.out.println("La cadena recibida para serializar no representa a un estudiante de manera correcta.\n");
        }
        
    }

     /**
     * Compara este producto con otro objeto.
     * @param o El objeto con el que se va a comparar.
     * @return true si el objeto es igual a este producto, false en caso contrario.
     */
    @Override
    public boolean equals(Object o){
        if (o == null || getClass() != o.getClass())
            return false;
        Producto p = (Producto)o;
        return this.codigo.equals(p.codigo);
    }

}


