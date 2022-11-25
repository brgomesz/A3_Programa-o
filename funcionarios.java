package view;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import data.funcionarios;
import data.funcionariosDao;
public class TelaFuncionarios extends JFrame {

	private JPanel contentPane;
	private JTextField txtMatricula;
	private JTextField txtNome;
	private JTextField txtCargo;
	private JTextField txtSalario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaFuncionarios frame = new TelaFuncionarios();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaFuncionarios() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMatricula = new JLabel("Matricula");
		lblMatricula.setBounds(10, 13, 87, 34);
		contentPane.add(lblMatricula);
		
		txtMatricula = new JTextField();
		txtMatricula.setBounds(66, 11, 201, 39);
		contentPane.add(txtMatricula);
		txtMatricula.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 67, 87, 34);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(66, 65, 301, 39);
		contentPane.add(txtNome);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setBounds(10, 126, 87, 34);
		contentPane.add(lblCargo);
		
		txtCargo = new JTextField();
		txtCargo.setColumns(10);
		txtCargo.setBounds(66, 124, 201, 39);
		contentPane.add(txtCargo);
		
		JLabel lblSalario = new JLabel("Salario");
		lblSalario.setBounds(10, 189, 87, 34);
		contentPane.add(lblSalario);
		
		txtSalario = new JTextField();
		txtSalario.setColumns(10);
		txtSalario.setBounds(66, 187, 201, 39);
		contentPane.add(txtSalario);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resp;
				funcionarios funcionario;
				funcionariosDao dao;
				boolean status;
				
				funcionario = new funcionarios();
				
				funcionario.setMatricula(txtMatricula.getText());
				funcionario.setNome(txtNome.getText());
				funcionario.setCargo(txtCargo.getText());
				funcionario.setSalario(Double.parseDouble(txtSalario.getText()));
				
				dao = new funcionariosDao(); 
				status = dao.conectar();
				if(status=false) {
					JOptionPane.showMessageDialog(null,"Erro na conexão com o sql");
				}else {
					resp = dao.salvar(funcionario);
					if (resp ==1) {
						JOptionPane.showMessageDialog(null,"Dados inseridos com sucesso!");
						txtMatricula.setText("");
						txtNome.setText("");
						txtCargo.setText("");
						txtSalario.setText("");
						txtMatricula.requestFocus();
					}else if (resp==1062){
						JOptionPane.showMessageDialog(null,"Usuário já cadastrado!");
					}else {
						JOptionPane.showMessageDialog(null,"erro");
					}
					dao.desconectar();
				}
				
			}
		});
		btnSalvar.setBounds(163, 250, 104, 39);
		contentPane.add(btnSalvar);
	}
}
