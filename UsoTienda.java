import javax.swing.JFrame;

public class UsoTienda {

   public static void main( String args[] ) {

      Login login = new Login();
      login.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      login.setSize( 500, 100 );
      login.setVisible( true );
   }
}
