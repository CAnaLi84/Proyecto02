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
	ponPestaniaAgregarProducto();
	ponPestaniaEliminarProducto();
	ponPestaniaSalir();
	add(panelFichas);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(false);
    }

    public void ponPestaniaVerArticulos(){
	JPanel panel = new JPanel(new GridLayout(1,2));
	JPanel panel_interno = new JPanel(new GridLayout(3,4,10,10));
	
	Color c1 = new Color(64,224,208);
	panel_interno.setBackground(c1);
	Color blanco = new Color(255,255,255);
	panel.setBackground(blanco);

	//Tamaño de etiquetas de imágenes
	int labelWidth = 100;
	int labelHeight = 100;

	JLabel atun = new JLabel(redimensionarImagen("productos/atun.png", labelWidth, labelHeight));
	JLabel cereales = new JLabel(redimensionarImagen("productos/cereales.jpg", labelWidth, labelHeight));
	JLabel galletas = new JLabel(redimensionarImagen("productos/galletas.png", labelWidth, labelHeight));
	JLabel jugo = new JLabel(redimensionarImagen("productos/jugoNaranja.png", labelWidth, labelHeight));
	JLabel leche = new JLabel(redimensionarImagen("productos/leche.png", labelWidth, labelHeight));
	JLabel pan = new JLabel(redimensionarImagen("productos/pan.png", labelWidth, labelHeight));
	JLabel picafresa = new JLabel(redimensionarImagen("productos/picafresa.jpeg", labelWidth, labelHeight));
	JLabel refresco = new JLabel(redimensionarImagen("productos/refresco.png", labelWidth, labelHeight));
	JLabel shampo = new JLabel(redimensionarImagen("productos/shampo.png", labelWidth, labelHeight));
	JLabel tostadas = new JLabel(redimensionarImagen("productos/tostadas.jpg", labelWidth, labelHeight));
	JLabel vino = new JLabel(redimensionarImagen("productos/vino.jpg", labelWidth, labelHeight));
	JLabel yogurth = new JLabel(redimensionarImagen("productos/yogurth.jpg", labelWidth, labelHeight));


        panel_interno.add(atun);
	panel_interno.add(cereales);
	panel_interno.add(galletas);
	panel_interno.add(jugo);
	panel_interno.add(leche);
	panel_interno.add(pan);
	panel_interno.add(picafresa);
	panel_interno.add(refresco);
	panel_interno.add(shampo);
	panel_interno.add(tostadas);
	panel_interno.add(vino);
	panel_interno.add(yogurth);

	atun.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e){
		    String resp = " ";
		    resp = buscaProducto("1234lc"); //imprimir el producto atun
		    articulos.setText(resp);
		}
	    });
	
	cereales.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e){
		    String resp = " ";
		    resp = buscaProducto("1234cl"); //imprimir el producto atun
		    articulos.setText(resp);
		}
	    });

	galletas.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e){
		    String resp = " ";
		    resp = buscaProducto("1234gs"); //imprimir el producto atun
		    articulos.setText(resp);
		}
	    });

	jugo.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e){
		    String resp = " ";
		    resp = buscaProducto("1234jn"); //imprimir el producto atun
		    articulos.setText(resp);
		}
	    });

	leche.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e){
		    String resp = " ";
		    resp = buscaProducto("1234lc"); //imprimir el producto atun
		    articulos.setText(resp);
		}
	    });

	pan.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e){
		    String resp = " ";
		    resp = buscaProducto("1234pn"); //imprimir el producto atun
		    articulos.setText(resp);
		}
	    });

	picafresa.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e){
		    String resp = " ";
		    resp = buscaProducto("1234pf"); //imprimir el producto atun
		    articulos.setText(resp);
		}
	    });

	refresco.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e){
		    String resp = " ";
		    resp = buscaProducto("1234rf"); //imprimir el producto atun
		    articulos.setText(resp);
		}
	    });

	shampo.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e){
		    String resp = " ";
		    resp = buscaProducto("1234sp"); //imprimir el producto atun
		    articulos.setText(resp);
		}
	    });

	tostadas.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e){
		    String resp = " ";
		    resp = buscaProducto("1234td"); //imprimir el producto atun
		    articulos.setText(resp);
		}
	    });

	vino.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e){
		    String resp = " ";
		    resp = buscaProducto("1234vn"); //imprimir el producto atun
		    articulos.setText(resp);
		}
	    });

	yogurth.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e){
		    String resp = " ";
		    resp = buscaProducto("1234yg"); //imprimir el producto atun
		    articulos.setText(resp);
		}
	    });
	
	panel.add(panel_interno);
	panel.add(articulos);
	panelFichas.addTab("Ver productos", null, panel);
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
     */
    public void ponPestaniaAgregarProducto(){
	int labelWidth = 100;
	int labelHeight = 100;
	JLabel carrito = new JLabel(redimensionarImagen("carrito.jpg",labelWidth, labelHeight));
	JPanel panel = new JPanel(new GridLayout(1,2));
	JButton boton_agregar = new JButton("Agregar al carrito");
	
	boton_agregar.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent evento){


		    String codigo = JOptionPane.showInputDialog("Ingrese el código del producto:");
		    if(codigo != null && !codigo.isEmpty()){
			Producto tuProducto = Aplicacion.buscaAux(codigo);
			if(tuProducto != null && tuProducto.getCantidad()>0){
			    cliente.agregarProductoCarrito(tuProducto);
			    JOptionPane.showMessageDialog(null,"Producto agregado al carrito");
			}else{
			    String noDisponible = "Producto no disponible";
			    JOptionPane.showMessageDialog(null, noDisponible);
			}
			
		    } else {
			JOptionPane.showMessageDialog(null, "Código de producto inválido");
		    }
		}
	    });
	
	panel.add(boton_agregar);
	panel.add(carrito);
	panelFichas.addTab("Agregar producto",panel);
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
	tienda.setSize(900, 410);
	tienda.setVisible(true);
	tienda.setResizable(false);
    }


    /**
     * 
     *
     */
    public String buscaProducto(String codigo){
	String res="<html>";
	if(almacen.getInventario() != null){
	    for(Producto prod  : almacen.getInventario()){
		if(prod.getCodigo().equals(codigo)){
		    res+="<br>"+prod.toString();
		}
	    }
	    res+="<html>";
	    return res;
	}else{
	    res = "Inventario vacío";
	    return res;
	}
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
