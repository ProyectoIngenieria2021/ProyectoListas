package mainProgram;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

public class CrearLista extends JFrame {

	// Datos necesarios para Programa

	private JPanel contentPane;
	public JLabel errorLabel;

	String urlPhoto = "./img/ImagenDefecto.png";
	String userText;
	String passText;
	String userCodeText;
	String persistencia;
	int contadorListas;
	int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    private JTextField nombreListaField;
    String rutaLocalImagen; // url de imagen de Lista
	ImageIcon imagen;
	
	// Para que podamos utilizar las clase auth que nos permite comprobar el usuario
	// con la BBDD

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainInterfaz frame = new MainInterfaz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CrearLista() throws IOException {
		initComponents();
	}
	
	private void initComponents() {
		System.out.println("Usuario en pantalla principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((ancho) - (this.getWidth()), (alto) - (this.getHeight()), 1000, 700);
		setResizable(false);
		this.setTitle("Listphy");
		
		JLabel listaPhotoDefecto = new JLabel("");
		listaPhotoDefecto.setBounds(43, 43, 218, 204);
		contentPane.add(listaPhotoDefecto);
		imagen = new ImageIcon(urlPhoto);
		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(listaPhotoDefecto.getWidth(), listaPhotoDefecto.getHeight(),
				Image.SCALE_DEFAULT));
		listaPhotoDefecto.setIcon(icono);

		// Icono de la ventana que se obtiene de /img
		Image icon = Toolkit.getDefaultToolkit().getImage("./img/iconoApp.png");
		setIconImage(icon);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblListas = new JLabel("Crear Nueva Lista");
		lblListas.setHorizontalAlignment(SwingConstants.CENTER);
		lblListas.setForeground(Color.BLACK);
		lblListas.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblListas.setBounds(268, 11, 410, 53);
		contentPane.add(lblListas);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(-19, 71, 1013, 8);
		contentPane.add(separator);
		
		nombreListaField = new JTextField();
		nombreListaField.setBounds(343, 140, 259, 20);
		contentPane.add(nombreListaField);
		nombreListaField.setColumns(10);
		
		JLabel lblNombreLista = new JLabel("Nombre Lista: ");
		lblNombreLista.setBounds(343, 115, 259, 14);
		contentPane.add(lblNombreLista);
		
		JLabel lblDatos = new JLabel("Datos");
		lblDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatos.setForeground(Color.BLACK);
		lblDatos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDatos.setBounds(387, 75, 162, 32);
		contentPane.add(lblDatos);
		
		JLabel lblDatosLista = new JLabel("Datos Lista: ");
		lblDatosLista.setBounds(343, 184, 259, 14);
		contentPane.add(lblDatosLista);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(343, 209, 259, 128);
		contentPane.add(textArea);
		
		JButton btnSeleccionarImagen = new JButton("Seleccionar Imagen");
		btnSeleccionarImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

				int returnValue = jfc.showOpenDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					rutaLocalImagen = selectedFile.getAbsolutePath();
					System.out.println("Ruta local de imagen seleccionada por usuario: " + rutaLocalImagen);
					imagen = new ImageIcon(rutaLocalImagen);
					Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(listaPhotoDefecto.getWidth(),
							listaPhotoDefecto.getHeight(), Image.SCALE_DEFAULT));
					listaPhotoDefecto.setIcon(icono);
					// A continuacion guardar esa foto para que se actualice y quede guardada la ruta en listas
				}
			}
		});
		btnSeleccionarImagen.setBounds(343, 364, 151, 23);
		contentPane.add(btnSeleccionarImagen);
		setLocationRelativeTo(null);

	}
}
