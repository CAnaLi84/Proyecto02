import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 *
 */
public class TiendaVirtual extends JFrame{
    private Aplicacion almacen = new Aplicacion();
    private Cliente cliente = new Cliente("Pau88","12345");
    JTabbedPane panelFichas = new JTabbedPane();
    JLabel articulos = new JLabel();

    //Constructor de la ventana de menú
    public TiendaVirtual(){
	super("Tienda Virtual");
	ponPestaniaVerArticulos();
	ponPestaniaVerCarrito();
	ponPestaniaSalir();
	add(panelFichas);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(false);
    }

    public void ponPestaniaVerArticulos(){
	JPanel panel = new JPanel();
	JPanel panel_interno = new JPanel(new GridLayout(3,4,70,10));
	
	Color blanco = new Color(255,255,255);
	panel.setBackground(blanco);
	panel_interno.setBackground(blanco);

	//Arreglo con el nombre, imágen y código de barras de cada producto
	String[][] productos = {
	    {"Atún", "productos/atun.png", "1234at"},
	    {"Cereales", "productos/cereales.jpg", "1234cl"},
	    {"Galletas", "productos/galletas.png", "1234gs"},
	    {"Jugo de naranja", "productos/jugoNaranja.png", "1234jn"},
	    {"Leche", "productos/leche.png", "1234lc"},
	    {"Pan", "productos/pan.png", "1234pn"},
	    {"Picafresa", "productos/picafresa.jpeg", "1234pf"},
	    {"Refresco", "productos/refresco.png", "1234rf"},
	    {"Shampo", "productos/shampo.png", "1234sp"},
	    {"Tostadas", "productos/tostadas.jpg", "1234td"},
	    {"Vino", "productos/vino.jpg", "1234vn"},
	    {"Yogurth", "productos/yogurth.jpg", "1234yg"}
	};

	//Tamaño de etiquetas de imágenes
	int labelWidth = 100;
	int labelHeight = 100;

	for(String[] producto : productos){
	    //Obtener un área para cada producto
	    JPanel panelProducto = new JPanel();
	    panelProducto .setLayout(new BoxLayout(panelProducto,BoxLayout.Y_AXIS));
	    panelProducto.setBackground(blanco);

	    JLabel imagen = new JLabel(redimensionarImagen(producto[1],labelWidth,labelHeight));
	    JLabel nombre = new JLabel(producto[0], SwingConstants.CENTER);
	    JLabel codigo = new JLabel("Codigo: " + producto[2], SwingConstants.CENTER);
	    JButton botonAgregar = new JButton("Agregar al carrito");

	
        botonAgregar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event){
		    Producto tuProducto = almacen.buscaAux(producto[2]);
		    if (tuProducto != null && tuProducto.getCantidad() > 0) {
			cliente.agregarProductoCarrito(tuProducto);
			JOptionPane.showMessageDialog(null, "Producto agregado al carrito");
		    } else {
			JOptionPane.showMessageDialog(null, "Producto no disponible");
		    }
		}
		
	    });

	panelProducto.add(imagen);
        panelProducto.add(nombre);
        panelProducto.add(codigo);
        panelProducto.add(botonAgregar);
	panel_interno.add(panelProducto);

	}

	panel.add(panel_interno);
	panel.add(articulos);
	panelFichas.addTab("Ver productos",null,panel);
    }

    /**
     *
     *
     */
    public void ponPestaniaVerCarrito(){
	JPanel panel = new JPanel();
	panelFichas.addTab("Ver carrito",panel);
    }


    /**
     *
     *
     */
    public void ponPestaniaEliminarProducto(){
	JPanel panel = new JPanel();
	panelFichas.addTab("Eliminar producto",panel);
    }
    
    /**
     * Pestaña para salir
     */
    public void ponPestaniaSalir(){
	int labelWidth = 100;
	int labelHeight = 100;
	JLabel imagen = new JLabel(redimensionarImagen("SALIR.png", labelWidth, labelHeight));
	JPanel panel = new JPanel(new GridLayout(1,2)); // crea el segundo panel
	JButton boton_salir = new JButton("Salir");
	panel.add(boton_salir);
	panel.add(imagen);
	boton_salir.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
		    System.exit(1);
		}
	    });
	panelFichas.addTab("Cerrar sesión", panel);
    }

    /**
     * Método para visualizar la interfaz
     */
    public static void muestraInterfaz() {
	TiendaVirtual tienda = new TiendaVirtual();
	tienda.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	tienda.setSize(900, 600);
	tienda.setVisible(true);
	tienda.setResizable(false);
    }


    /**
     *  Redimenciona las imágenes al tamaño de la etiqueta
     */
    public ImageIcon redimensionarImagen(String ruta, int ancho, int alto){
	ImageIcon icon = new ImageIcon(ruta);
	Image imagen = icon.getImage();
	Image imagenEscalada = imagen.getScaledInstance(ancho, alto, java.awt.Image.SCALE_SMOOTH);
	return new ImageIcon(imagenEscalada);
    }
}
