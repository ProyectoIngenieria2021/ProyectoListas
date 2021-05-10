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
	String ayuda = "<html><body>En esta ventana debe introducir sus datos. Ejmp: Usuario: Jose, Password: 1234, <br>codUsuario: kj25 (Sus Iniciales y dos numeros seguidos sin espacio, <br>es importante que lo recuerde.)</html></body>";
	String error = "<html><body>Ocurrio un error en la app, por favor revise si introdujo bien sus datos, <br>si no se trata de eso reinicie la app, si ocurrio otro tipo de problema instale <br>y desistale o compruebe la version.</html></body>";
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

	public MainInterfaz() {
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(189, 100, 805, 571);
		contentPane.add(scrollPane);
		
		JLabel lblListas = new JLabel("Listas");
		lblListas.setHorizontalAlignment(SwingConstants.CENTER);
		lblListas.setForeground(Color.BLACK);
		lblListas.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblListas.setBounds(453, 13, 232, 53);
		contentPane.add(lblListas);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(-19, 87, 1013, 8);
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
		
		JButton btnCompletadas = new JButton("Completadas");
		btnCompletadas.setBounds(10, 124, 144, 23);
		contentPane.add(btnCompletadas);
		setLocationRelativeTo(null);

	}
}
