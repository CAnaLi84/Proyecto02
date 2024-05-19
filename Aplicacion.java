import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 * Esta clase representa una aplicación de gestión de inventario de productos.
 * Permite realizar diversas operaciones como agregar, eliminar, buscar y actualizar productos,
 * así como mostrar el inventario y su estado ordenado por fecha de caducidad.
 */
public class Aplicacion{

    private static ArrayList<Producto> inventario;

    /**
     * Método principal que inicia la aplicación y contiene el bucle principal del programa.
     * Muestra un menú de opciones y realiza acciones según la opción seleccionada por el usuario.
     */
    public static void main(String [] args){

        inventario = new ArrayList<Producto>();
        lee();

        int cantidad = -1;

        while(true){
            System.out.println("\n\nIntroduce el número de la opción que deseas realizar:");
            System.out.println("\n0: Salir");
            System.out.println("\n1: Dar de alta un producto");
            System.out.println("\n2: Ver inventario");
            System.out.println("\n3: Ver inventario por año y mes");
            System.out.println("\n4: Eliminar un producto");
            System.out.println("\n5: Buscar un producto");
            System.out.println("\n6: Actualizar un producto");
            System.out.println("\n7: Borrar todos los productos");
	    
            cantidad = scanInt("");
            while (cantidad < 0 || cantidad > 7){
                System.out.printf("\n\nIntenta de nuevo con una opción valida.");
                cantidad = scanInt("");
            }
            if (cantidad == 0)
                break;

            if (cantidad == 1)
                agrega();
                
            if (cantidad == 2)
                imprimeInventario();

            if (cantidad == 3)
                imprimeOrdenado();

            if (cantidad == 4)
                elimina();

            if (cantidad == 5)
                busca();

            if (cantidad == 6)
                actualiza();

            if (cantidad == 7)
                vacia();

            salirMenu(); 
        }
        guarda();
    }


    /**
     *
     */
    public Aplicacion (){
	inventario = new ArrayList<>();	
    }
    
    /**
     * Método para obtener el inventario de la aplicación.
     *
     */
    public static ArrayList<Producto> getInventario(){
	return inventario;
    }


    
    /**
     * Lee los productos almacenados en el archivo inventario.txt y los carga en la lista inventario.
     */
    public static void lee(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("inventario.txt"));
            String str = reader.readLine();

            while(str != null){
		Producto temp = new Producto(null, null, null, 0, null);	    
                temp.deserializa(str);
                inventario.add(temp);
                str = reader.readLine();
            }

            reader.close();

        } catch (IOException io){
            io.printStackTrace();
        }
    }

    public static void guarda(){
        try{
       
            BufferedWriter writter = new BufferedWriter(new FileWriter("inventario.txt"));
            for(Producto p : inventario) {
                writter.write(p.serializa());
            }
            writter.close();

        } catch (IOException io){
            System.out.println("\nNo fue posible leer el archivo.");
        }       
    }

    

    public static void imprimeInventario(){
	lee();
        if (inventario.size() == 0){
            System.out.printf("\n\nEl inventario esta vacio.");
            return;
        }
        System.out.printf("\n\n");
        String ln = "----------------------------------------------------------------------------------------";
        System.out.printf("%s\n", ln);
        System.out.printf("| %-18s | %-23s | %-15s | %-19s |\n", "Codigo", "Nombre", "Precio", "Cantidad");
        System.out.printf("%s\n", ln);
        inventario.sort((a,b) -> a.getNombre().compareTo(b.getNombre()));
        for(Producto p : inventario) {
            System.out.printf("%s\n", p.toString());
            System.out.printf("%s\n", ln);
        }
    }

    public static void imprimeOrdenado(){
        if (inventario.size() == 0){
            System.out.printf("\n\nEl inventario esta vacio.");
            return;
        }
        inventario.sort((a,b) -> a.getCaducidad().compareTo(b.getCaducidad()));

        String ln = "----------------------------------------------------------------------------------------";
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril","Mayo", "Junio", "Julio", "Agosto","Septiembre", "Octubre", "Noviembre", "Diciembre"};

        int mesActual = -1;
        int yearActual = -1;

        System.out.printf("\n\n");

        for(Producto p : inventario) {
            int mes = p.getCaducidad().getMonthValue();
            int year = p.getCaducidad().getYear();
            if (year != yearActual)
                System.out.printf("\n\n%d\n\n", year);
            if (mes != mesActual || year != yearActual){
                System.out.printf("\n%s:\n%s\n", meses[mes-1], ln);
                System.out.printf("| %-18s | %-23s | %-15s | %-19s |\n", "Codigo", "Nombre", "Precio", "Cantidad");
                System.out.printf("%s\n", ln);
            }   
            System.out.printf("%s\n", p.toString());
            System.out.printf("%s\n", ln);
            }
        }

    public static void agrega(){
        Scanner sc = new Scanner(System.in);

        String codigo = scanCodigo("\n\nIntroduce el codigo del producto:"); 

        System.out.println("\n\nIntroduce el nombre del producto:");
        String nombre = sc.nextLine();

        Double precio = scanDouble("\n\nIntroduce el precio del producto:");
        int cantidad = scanInt("\n\nIntroduce la cantidad del producto:");

        LocalDate caducidad = scanDate("\n\nIntroduce la fecha de caducidad del producto usando el formato 0000-00-00 (Año-Mes-Dia):");

        Producto p = new Producto(codigo, nombre, precio, cantidad, caducidad);
        inventario.add(p);

        System.out.println("\n\nProducto agregado con exito.");
    }
    

    public static void elimina(){
        System.out.println("\n\nIntroduce el codigo del producto que deseas eliminar:");
        Scanner sc = new Scanner(System.in);
        String codigo = sc.nextLine();

        Producto p = buscaAux(codigo);
        
        if (p == null)
            System.out.println("\n\nEl producto no se encuentra en la lista.");
        else {
            imprimeProducto(p);

            String entrada = scanSiNo("\n\n¿Estas seguro de que deseas eliminarlo? (Si/No)");
            if (entrada.equals("Si")){
                inventario.remove(p);
                System.out.println("\n\nProducto eliminado con exito.");
            }      
        }
           
    }


    public static boolean seEncuentra(String codigo){
	boolean encuentra = false;
	Producto p = buscaAux(codigo);
        if (p == null){
            encuentra = false;
	}else{
	    encuentra = true;
	}return encuentra;
    }

    
    public static void busca(){
        System.out.println("\n\nIntroduce el codigo del producto que deseas encontrar:");
        Scanner sc = new Scanner(System.in);
        String codigo = sc.nextLine();

        Producto p = buscaAux(codigo);
        if (p == null)
            System.out.println("\n\nEl producto no se encuentra en la lista.");
        else
            imprimeProducto(p);
    }

    public static void actualiza(){
        System.out.println("\n\nIntroduce el codigo del producto que deseas modificar:");
        Scanner sc = new Scanner(System.in);
        String codigo = sc.nextLine();

        Producto p = buscaAux(codigo);
        
        if (p == null)
            System.out.println("\n\nEl producto no se encuentra en la lista.");
        else {
            inventario.remove(p);
            agrega(); 
        }
    }

    public static void vacia(){
        String entrada = scanSiNo("\n\n¿Estas seguro de que deseas eliminar todos los productos en el inventario? (Si/No):");
        if (entrada.equals("Si")){
            inventario = new ArrayList<Producto>();
            System.out.println("\n\nInventario eliminado con exito.");  
        }
            

    }

    public static void salirMenu() {
        System.out.println("\n\nIntroduce la letra M cuando desees salir al menu:\n");
        Scanner sc = new Scanner(System.in);
        String entrada = "";

        while(!entrada.equals("M")){
            entrada = sc.nextLine();
        }
    }

    public static Producto buscaAux(String codigo){
        for(Producto p : inventario) {
            if (p.getCodigo().equals(codigo))
                return p;
        }
        return null;
    }


    private static String scanCodigo(String mensaje){
        System.out.println(mensaje);
        Scanner sc = new Scanner(System.in);
        String codigo = sc.nextLine();
        Producto p = buscaAux(codigo);
        if (p != null){
            System.out.println("\n\nEse producto ya se encuentra en la lista. Prueba con otro codigo.");
            return scanCodigo(mensaje);
        }
        return codigo;
    }


    private static int scanInt(String mensaje){
        int n = 0;
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println(mensaje);
            n = sc.nextInt();
        } catch (InputMismatchException me){
            System.out.println("\n\nIntentalo de nuevo con un numero valido.");
            scanInt(mensaje);
        }
        return n;
    }

    private static Double scanDouble(String mensaje){
        Double d = 0.0;
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println(mensaje);
            d = sc.nextDouble();
        } catch (InputMismatchException me){
            System.out.println("\n\nIntentalo de nuevo con un numero decimal valido.");
            scanDouble(mensaje); 
        }
        return d;
    }

    private static LocalDate scanDate(String mensaje){
        LocalDate fecha = LocalDate.now();
        try{
            System.out.println(mensaje);
            Scanner sc = new Scanner(System.in);
            String entrada = sc.nextLine();
            fecha = LocalDate.parse(entrada);
            if(fecha.getYear() < 2025 || fecha.getYear() > 2027){
                throw new Exception();
            }
        } catch (DateTimeParseException dpe){
            System.out.println("\n\nIntentalo de nuevo con una fecha valida.");
            scanDate(mensaje);
        } catch (Exception e) {
            System.out.println("\n\nInténtalo de nuevo. El año en la fecha debe estar en el rango de 2025 a 2027.");
            scanDate(mensaje);
        }
        return fecha;
    }

    private static String scanSiNo(String mensaje){
            System.out.println(mensaje);
            Scanner sc = new Scanner(System.in);
            String entrada = sc.nextLine();
            if (entrada.equals("Si") || entrada.equals("No"))
                return entrada;
            else 
                return scanSiNo(mensaje);
    }

    private static void imprimeProducto(Producto p){
        String ln = "----------------------------------------------------------------------------------------";
        System.out.printf("%s\n", ln);
        System.out.printf("| %-18s | %-23s | %-15s | %-19s |\n", "Codigo", "Nombre", "Precio ($)", "Cantidad (Unidades)");
        System.out.printf("%s\n", ln);
        System.out.printf("%s\n", p.toString());
        System.out.printf("%s\n", ln);
    }
}
