package visual;


import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import model.City;
import model.ControladorRota;
import model.GA;
import model.Population;
import model.Util;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class FormCaixeiro extends JFrame {

	private JPanel contentPane;
	boolean resposta; 
	JButton btnOutraCidade;
	JButton btnCalcular;
	JList list;
	JList listCidadesIni;
	JList listCidadesFinal;
	JList listDistIni;
	JList listDistOti;
	JList listMelhora;
	ArrayList<City> cities;
	City cidadeInicialText;
	DefaultListModel modelo1;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCaixeiro frame = new FormCaixeiro();
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
	public FormCaixeiro() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		setUndecorated(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton btnCalcular = new JButton("CALCULAR ROTA");
		btnCalcular.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnCalcular.setBounds(224, 130, 360, 28);
		contentPane.add(btnCalcular);
		btnCalcular.setForeground(Color.WHITE);
		btnCalcular.setBackground(new Color(51, 51, 51));
		JButton btnExit = new JButton("FECHAR");
		btnExit.setBounds(669, 6, 125, 28);
		contentPane.add(btnExit);
		btnExit.setForeground(Color.WHITE);
		btnExit.setBackground(new Color(51, 51, 51));
		JButton btnOutraCidade = new JButton("OUTRA CIDADE");
		btnOutraCidade.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnOutraCidade.setBounds(429, 92, 155, 34);
		contentPane.add(btnOutraCidade);
		btnOutraCidade.setForeground(Color.WHITE);
		btnOutraCidade.setBackground(new Color(51, 51, 51));
			
		JList list = new JList();
    	list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	//((javax.swing.DefaultListCellRenderer)list.getCellRenderer()).setOpaque(false);
    	list.setFont(new Font("SansSerif", Font.BOLD, 20));
		list.setBounds(224, 92, 200, 34);
		contentPane.add(list);
		JList listCidadesIni = new JList();
		listCidadesIni.setBounds(123, 183, 184, 471);
		contentPane.add(listCidadesIni);
		
		JList listCidadesFinal = new JList();
		listCidadesFinal.setBounds(490, 183, 184, 471);
		contentPane.add(listCidadesFinal);
		
		JList listDistIni = new JList();
		listDistIni.setFont(new Font("SansSerif", Font.BOLD, 20));
		listDistIni.setBounds(219, 654, 88, 28);
		contentPane.add(listDistIni);
		
		JList listDistOti = new JList();
		listDistOti.setFont(new Font("SansSerif", Font.BOLD, 20));
		listDistOti.setBounds(586, 654, 88, 28);
		contentPane.add(listDistOti);
		
		JList listMelhora = new JList();
		listMelhora.setFont(new Font("SansSerif", Font.BOLD, 20));
		listMelhora.setBounds(686, 654, 88, 28);
		contentPane.add(listMelhora);
		
		JLabel lblDistnciaInicial = new JLabel("ROTEIRO INICIAL");
		lblDistnciaInicial.setBounds(166, 157, 98, 28);
		contentPane.add(lblDistnciaInicial);
		
		JLabel lblDistanciaInicial = new JLabel("Distancia Inicial:");
		lblDistanciaInicial.setBounds(123, 654, 98, 28);
		contentPane.add(lblDistanciaInicial);
		
		JLabel lblDistanciaOtimizada = new JLabel("Distancia \r\nOtim.:");
		lblDistanciaOtimizada.setBounds(490, 654, 98, 28);
		contentPane.add(lblDistanciaOtimizada);

		JLabel lblMelhora = new JLabel("MELHORA %");
		lblMelhora.setBounds(693, 626, 81, 28);
		contentPane.add(lblMelhora);
		
		JLabel lblRoteiroFinal = new JLabel("ROTEIRO FINAL");
		lblRoteiroFinal.setBounds(539, 157, 98, 28);
		contentPane.add(lblRoteiroFinal);
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(FormCaixeiro.class.getResource("/zimg/cvlogo.png")));
		lblNewLabel_1.setBounds(0, 0, 800, 700);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(FormCaixeiro.class.getResource("/zimg/cvbackground.png")));
		lblNewLabel.setBounds(0, 0, 800, 700);
		contentPane.add(lblNewLabel);
		
		ArrayList<City> cities = new ArrayList<City>(); 

    	cities.add(new City(1, "Aracaju"));
    	cities.add(new City(2, "Belem"));
    	cities.add(new City(3, "Belo Horizonte"));
    	cities.add(new City(4, "Boa Vista"));
    	cities.add(new City(5, "Brasilia"));
    	cities.add(new City(6, "Campo Grande"));
    	cities.add(new City(7, "Cuiaba"));
    	cities.add(new City(8, "Curitiba"));
    	cities.add(new City(9, "Florianopolis"));
    	cities.add(new City(10, "Fortaleza"));
    	cities.add(new City(11, "Goiania"));
    	cities.add(new City(12, "João Pessoa"));
    	cities.add(new City(13, "Maceió"));
    	cities.add(new City(14, "Manaus"));
    	cities.add(new City(15, "Natal"));
    	cities.add(new City(16, "Palmas"));
    	cities.add(new City(17, "Porto Alegre"));
    	cities.add(new City(18, "Porto Velho"));
    	cities.add(new City(19, "Recife"));
    	cities.add(new City(20, "Rio Branco"));
    	cities.add(new City(21, "R. Janeiro"));
    	cities.add(new City(22, "Salvador"));
    	cities.add(new City(23, "São Luis"));
    	cities.add(new City(24, "São Paulo"));
    	cities.add(new City(25, "Teresina"));
    	cities.add(new City(26, "Vitória"));
		
    	
    	//do
    	
    	Util.shuffle(cities);
    	
    	City cidadeInicialText = cities.get(0);
    	
    	DefaultListModel modelo = new DefaultListModel();
		list.setModel(modelo);
		modelo.addElement(cidadeInicialText);
		
		DefaultListModel modelo1 = new DefaultListModel();
		listCidadesIni.setModel(modelo1);
    	for(int i = 0; i <= cities.size() - 1; i++)
    	{
    		ControladorRota.addCity(cities.get(i));
    		modelo1.addElement(cities.get(i));
    		
    	}   
    	    	    	   	    	
    	DefaultListModel modelo2 = new DefaultListModel();
		listDistIni.setModel(modelo2);
					
    	Population pop = new Population(50, true);
        double distanciaInicial = pop.getFittest().getDistance();
        modelo2.addElement(distanciaInicial);
                     
        pop = GA.evolvePopulation(pop);
        for (int i = 0; i < 100; i++) {
            pop = GA.evolvePopulation(pop);
        }
             
        DefaultListModel modelo3 = new DefaultListModel();
		listCidadesFinal.setModel(modelo3);
		modelo3.addElement(pop.getFittest()+"\n");
        // --------------------------
        
		double distanciaFinal = pop.getFittest().getDistance();
        DefaultListModel modelo4 = new DefaultListModel();
		listDistOti.setModel(modelo4);
		modelo4.addElement(distanciaFinal);
       
        String melhoraValor = String.format("%.2f", distanciaFinal / distanciaInicial * 100)+"%";
        DefaultListModel modelo5 = new DefaultListModel();
		listMelhora.setModel(modelo5);
		modelo5.addElement(melhoraValor);
    	
		
		
		//chamada do sair
		btnExit.addActionListener(new sair());
		
		
	}


	
	
	class sair implements ActionListener {
		public void actionPerformed(ActionEvent e){		
				System.exit(0);
		}
	}


		
    
}
