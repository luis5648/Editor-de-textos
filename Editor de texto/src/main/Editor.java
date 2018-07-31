package main;

/*
Alumno: Luis Gerardo Román Marín
Materia: Programación 2
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import java.io.*;


public class Editor implements ActionListener{
	JFrame v;
	Choice tipF,tamF;
	JButton bnormal,bitalic,bcurs;
	JPanel barra;
	JTextArea ta;
	JScrollPane jsp;
	MenuBar mb;
	Menu arch, edi, acerca;
	MenuItem nuevo,abrir,guardar,guardarc,salir,copy,paste,cut;
	FileDialog fd;
	JScrollPane jps;

	String ruta;
	public Editor(){
		v = new JFrame("Notas =)");
		
		barra = new JPanel();

		tipF = new Choice();
		tipF.add("San Serif");
		tipF.add("Time Roman");

		tamF = new Choice();
		tamF.add("12");
		tamF.add("14");
		tamF.add("16");
		tamF.add("18");

		bnormal = new JButton("N");
		bitalic = new JButton("I");
		bcurs = new JButton("B");
		ta = new JTextArea();
		jsp = new JScrollPane(ta);

		mb = new MenuBar();
		
		arch = new Menu("Archivo");
		edi = new Menu("Editar");
		acerca = new Menu("Acerca de...");

		nuevo = new MenuItem("Nuevo");
		abrir = new MenuItem("Abrir");
		guardar = new MenuItem("Guardar");
		guardarc = new MenuItem("Gardar como...");
		salir = new MenuItem("Salir");
		copy = new MenuItem("Copiar");
		paste = new MenuItem("Pegar");
		cut = new MenuItem("Cortar");


		mb.add(arch);
		mb.add(edi);
		mb.add(acerca);
		arch.add(nuevo);
		arch.add(abrir);
		arch.add(guardar);
		arch.add(guardarc);



		edi.add(copy);
		edi.add(paste);
		edi.add(cut);

		//Action listeners
		abrir.addActionListener(this);
		nuevo.addActionListener(this);
		guardar.addActionListener(this);
		guardarc.addActionListener(this);
		copy.addActionListener(this);
		cut.addActionListener(this);
		paste.addActionListener(this);

		barra.add(tipF);
		barra.add(tamF);
		barra.add(bnormal);
		barra.add(bitalic);
		barra.add(bcurs);

		barra.setLayout(new GridLayout(1,5));
		v.setMenuBar(mb);
		
		v.add(barra,BorderLayout.NORTH);
		v.add(jsp,BorderLayout.CENTER);
		v.setSize(800,600);
		v.setLocationRelativeTo(null);
		v.setVisible(true);
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void guardarArchivo(){
		
		

		File f = new File(ruta);
		try{
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			pw.write(ta.getText());
		
			pw.close();
			bw.close();	
		}catch(IOException e){
			
			System.err.println("error"+e);
			
				
		}
		

	}

	public void leerArchivo(){
		String c;
		ta.setText("");

		try{
			File inputFile = new File(ruta);
			FileInputStream fis=new FileInputStream(inputFile);

			BufferedReader dis= new BufferedReader(new InputStreamReader(fis));
			while((c=dis.readLine())!=null)
				ta.setText(ta.getText()+c+"\n");
			dis.close();
			fis.close();
		}
		catch(FileNotFoundException e){
			System.err.println("Error"+e);
		}
		catch(IOException e){
			System.err.println("Error"+e);
		}
	}
	//Eventos del menú

	public void actionPerformed(ActionEvent e){
		System.out.println("Fue presionado: "+e.getActionCommand());
		if((e.getActionCommand()).compareTo("Nuevo")==0)
			ta.setText(" ");
		else if (e.getSource().equals(abrir)) {

			//Abre la ventana para cargar archivos
			fd = new FileDialog(v,"abrir",FileDialog.LOAD);
			fd.setVisible(true);
			ruta = fd.getDirectory()+fd.getFile();
			leerArchivo();
	
		}
		else if (e.getSource().equals(guardarc)) {

			fd = new FileDialog(v,"Guardar como...",FileDialog.SAVE);
			fd.setVisible(true);
			ruta = fd.getDirectory()+fd.getFile();
			guardarArchivo();
		}
		else if (e.getSource().equals(guardar)){

			ruta = fd.getDirectory()+fd.getFile();
			guardarArchivo();			
		}
	}

	public static void main(String args[]){

		Editor principal = new Editor();
	}
}