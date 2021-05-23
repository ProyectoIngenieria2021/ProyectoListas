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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import signUpAuth.SignUp;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;

public class MainInterfaz extends JFrame {

	// Datos necesarios para Programa

	private JPanel contentPane;
	public JLabel errorLabel;

	String userText;
	String passText;
	String userCodeText;
	String persistencia;
	int contadorListas;
	int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
	
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

	public MainInterfaz() throws IOException {
		initComponents();
	}
	
	private void initComponents() {
		System.out.println("Usuario en pantalla principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((ancho) - (this.getWidth()), (alto) - (this.getHeight()), 1000, 700);
		setResizable(false);
		this.setTitle("Listphy");

		// Icono de la ventana que se obtiene de /img
		Image icon = Toolkit.getDefaultToolkit().getImage("./img/iconoApp.png");
		setIconImage(icon);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMisListas = new JLabel("Mis Listas");
		lblMisListas.setBounds(186, 82, 144, 14);
		contentPane.add(lblMisListas);
		
		JLabel lblListas = new JLabel("Listas");
		lblListas.setHorizontalAlignment(SwingConstants.CENTER);
		lblListas.setForeground(Color.BLACK);
		lblListas.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblListas.setBounds(453, 13, 232, 53);
		contentPane.add(lblListas);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(-19, 65, 1013, 8);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(176, -33, 13, 704);
		contentPane.add(separator_1);
		
		JLabel lblListphy = new JLabel("Listphy");
		lblListphy.setHorizontalAlignment(SwingConstants.CENTER);
		lblListphy.setForeground(Color.BLACK);
		lblListphy.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblListphy.setBounds(10, 24, 144, 43);
		contentPane.add(lblListphy);
		
		JButton btnCompletadas = new JButton("Mis Listas");
		btnCompletadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Usuario en seccion Mis Listas");
				lblMisListas.setText("Mis Listas");
			}
		});
		btnCompletadas.setBounds(10, 124, 144, 23);
		contentPane.add(btnCompletadas);
		
		JButton btnFavoritas = new JButton("Favoritas");
		btnFavoritas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Usuario en seccion Favoritas");
				lblMisListas.setText("Favoritas");
			}
		});
		btnFavoritas.setBounds(10, 169, 144, 23);
		contentPane.add(btnFavoritas);
		
		JButton btnCompletadas_1 = new JButton("Completadas");
		btnCompletadas_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Usuario en seccion Completadas");
				lblMisListas.setText("Completadas");
			}
		});
		btnCompletadas_1.setBounds(10, 213, 144, 23);
		contentPane.add(btnCompletadas_1);
		
		JButton btnNoCompletadas = new JButton("No Completadas");
		btnNoCompletadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Usuario en seccion No Completadas");
				lblMisListas.setText("No Completadas");
			}
		});
		btnNoCompletadas.setBounds(10, 259, 144, 23);
		contentPane.add(btnNoCompletadas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(186, 113, 796, 526);
		contentPane.add(scrollPane);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
		btnSalir.setBounds(36, 637, 89, 23);
		contentPane.add(btnSalir);
		
		JLabel lblTotalListas = new JLabel("Total Listas:");
		lblTotalListas.setBounds(825, 650, 75, 14);
		contentPane.add(lblTotalListas);
		
		JLabel lblNumeroListas = new JLabel("0");
		lblNumeroListas.setBounds(925, 650, 46, 14);
		contentPane.add(lblNumeroListas);
		
		JButton btnNuevaLista = new JButton("Nueva Lista +");
		btnNuevaLista.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try(){
				contadorListas++;
				lblNumeroListas.setText(String.valueOf(contadorListas));
				setVisible(false);
				CrearLista crearLista = new CrearLista();
				crearLista.setVisible(true);
				}
			}
		});
		btnNuevaLista.setBounds(864, 78, 120, 23);
		contentPane.add(btnNuevaLista);
		setLocationRelativeTo(null);

	}
}
