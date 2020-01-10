import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import entitites.Professor;
import entitites.Student;
import entitites.Subject;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Toolbar tb;
	private StatusBar sb;
	private ArrayList<Student> studentList;
	private ArrayList<Professor> proffesorList;
	private ArrayList<Subject> subjectList;
	private Integer selectedStudentRow;
	
	private Student student;

	public MainFrame() {
		super("Studentska služba");
		selectedStudentRow = null;
		student = new Student();
		
		
		// For window dimensions
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int scrW = (int)screenSize.getWidth() * 3 / 4;
		int scrH = (int)screenSize.getHeight()* 3 / 4;;
			
		setSize(scrW, scrH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // turn of app on X
		setLocationRelativeTo(null); // to be on center
		setVisible(true); 
	
		setLayout(new BorderLayout());
		tb = new Toolbar();
		sb = new StatusBar();

		// Menu
		setJMenuBar(createMenuBar());
		// Toolabar
		add(tb, BorderLayout.NORTH);
		// StatusBar
		add(sb, BorderLayout.SOUTH);
		
		//CENTAR		
		// TABOVI NE SMEJU DA IMAJU ISTE STVARI UNUTAR NJIH. NE SME ISTA TABELA DA SE NADJE U DVA RAZLICITA TABA
	    JTabbedPane tabbedPane = new JTabbedPane();
	    
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout());
	    
		// Studenti u tab
	    JScrollPane studentTable = getStudentTableData();
	    tablePanel.add(studentTable,BorderLayout.CENTER); 
	    tabbedPane.addTab("Studenti", tablePanel);
	    tabbedPane.setMnemonicAt(0, KeyEvent.VK_1); //ALT + 1
	   
	    
	    
	    // Profesori u tab
	    tabbedPane.addTab("Profesori", new JButton("Nista"));
	    tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
	    
	    // Predmeti u tab
	    tabbedPane.addTab("Predmeti", new JButton("AAA"));
	    tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

	    add(tabbedPane, BorderLayout.CENTER);
	    
	    
	    // Prosledjivanje referenca u toolbar
	    tb.setStudentsModel(modelStudent);
	    tb.setStudentList(studentList);
	  
	}

	
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		// New
		JMenu newMenu = new JMenu("New");
		JMenuItem newItem = new JMenuItem("New");
		newItem.setMnemonic(KeyEvent.VK_W);
		newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
		newItem.setIcon(new ImageIcon("pictures/addBtn2.png"));
		
		newItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				StudentFrame sf = new StudentFrame(null);
				sf.setVisible(true);

				// recive Student
				student = sf.getStudent();
				if(student != null){
					studentList.add(student);
					modelStudent.addRow(new Object[] { 
							student.getIndex(), student.getName(), student.getLastName(),
							student.getDate(),student.getAdress(),student.getTelephone(),
							student.getEmail(), student.getStartDate().toString(),
							student.getStudentYear().toString(),student.getStatus(),student.getAvgMark().toString() 
							});
				}
			}
		});
		

		// Close
		JMenuItem closeItem = new JMenuItem("Close");
		closeItem.setMnemonic(KeyEvent.VK_C);
		closeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		closeItem.setIcon(new ImageIcon("pictures/closeIcon2.png"));
		closeItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}
		});

		newMenu.add(newItem);
		newMenu.add(closeItem);

		// Edit
		JMenu editMenu = new JMenu("Edit");
		JMenuItem editItem = new JMenuItem("Edit");
		editItem.setMnemonic(KeyEvent.VK_E);
		editItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		editItem.setIcon(new ImageIcon("pictures/editBtn2.png"));
		
		editItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {

				if (selectedStudentRow != null) {
					StudentFrame sf = new StudentFrame(student);
					sf.setVisible(true);

				// recive Student
					student = sf.getStudent();

					ArrayList<Student> newList = new ArrayList<Student>();
					for (int i = 0; i < studentList.size(); i++) {
						if (i == selectedStudentRow) {
							newList.add(student);
						} else {
							newList.add(studentList.get(i));
						}
					}

					studentList = newList;

					modelStudent.removeRow(selectedStudentRow);
					modelStudent.insertRow(selectedStudentRow,
							new Object[] { student.getIndex(), student.getName(), student.getLastName(),
									student.getStudentYear(), student.getStatus(), student.getAvgMark() });
					
				//	tb.setStudentsModel(modelStudent);
				//	tb.setStudentList(studentList);
				}
			}
		});

		// Delete
		JMenuItem deleteItem = new JMenuItem("Delete");
		deleteItem.setMnemonic(KeyEvent.VK_D);
		deleteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		deleteItem.setIcon(new ImageIcon("pictures/deleteBtn3.png"));
		
		deleteItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				if (selectedStudentRow != null) {
				int input = JOptionPane.showConfirmDialog(null, "Are you sure?");
				// 0=yes, 1=no, 2=cancel
				System.out.println(input);
				if (input == 0) {
						ArrayList<Student> newList = new ArrayList<Student>();
						for (int i = 0; i < studentList.size(); i++) {
							if (i != selectedStudentRow) {
								newList.add(studentList.get(i));
							}
						}
						studentList = newList;
						modelStudent.removeRow(selectedStudentRow);
				//		tb.setSelectedRowStudent(selectedStudentRow);
				//		tb.setStudentsModel(modelStudent);
	
					}
				} else {
					JOptionPane.showMessageDialog(null, "Morate prvo da selektujete jednog Studenta!");
				}
			}});
		
		editMenu.add(editItem);
		editMenu.add(deleteItem);

		// Help
		JMenu helpMenu = new JMenu("Help");
		JMenuItem helpItem = new JMenuItem("Help");
		helpItem.setMnemonic(KeyEvent.VK_H);
		helpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		helpItem.setIcon(new ImageIcon("pictures/helpIcon2.png"));

		// About
		JMenuItem aboutItem = new JMenuItem("About");
		aboutItem.setMnemonic(KeyEvent.VK_A);
		aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		aboutItem.setIcon(new ImageIcon("pictures/aboutIcon2.png"));

		helpMenu.add(helpItem);
		helpMenu.add(aboutItem);

		// final menu
		menuBar.add(newMenu);
		menuBar.add(editMenu);
		menuBar.add(helpMenu);

		return menuBar;
	}

	private DefaultTableModel modelStudent;
	private JScrollPane getStudentTableData() {
		
		modelStudent = new DefaultTableModel(); 
		JTable jt=new JTable(modelStudent){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			// da se ne moze rucno izmeniti na tabeli nego mora preko dialoga
			public boolean isCellEditable(int row, int column){
				return false;
			}
			
			public Component prepareRenderer(TableCellRenderer r, int row, int column){
				Component c = super.prepareRenderer(r, row, column);
				
				if(row % 2 == 0){
					c.setBackground(Color.WHITE);
				} else {
					c.setBackground(Color.LIGHT_GRAY);
				}
				
				if(isCellSelected(row, column)){
					c.setBackground(Color.GREEN);
				}
				
				return c;
			}
		};   
	
		// Dodavanje kolona
		String column[]={"Index","Name","Last Name","Birthday","Adress","Telephone","Email","Starting Date","Student Year", "Status", "Avg Mark"};    
		for (String s : column){
			modelStudent.addColumn(s); 
		}
		
		try (BufferedReader br = new BufferedReader(new FileReader("Lists/Students"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	if(line.contains("[") && line.contains("]") && line.contains(",")){
		    		String[] str = line.split("\\[")[1].split("\\]")[0].split(",");
		    		
		    		
		    	}
		    	
		    }
		} catch (Exception e) {
			
		}
		
		
		
		
		
		
		//dodavanje redova
		modelStudent.addRow(new Object[]{"1","Amit","Amar","1.1.2001","Adresa1","123142","Email1","2021","3", "B", "9.3"});
		modelStudent.addRow(new Object[]{"2","Bmit","Bmar","2.1.2002","Adresa2","223142","Email6","2023","2", "S", "8.3"});
		modelStudent.addRow(new Object[]{"3","Cmit","Cmar","3.1.2003","Adresa3","423142","Email4","2022","4", "B", "7.3"});
		modelStudent.addRow(new Object[]{"4","Dmit","Dmar","4.1.2004","Adresa4","223142","Email3","2026","1", "B", "6.3"});
		
		studentList = new ArrayList<Student>();
		studentList.add(new Student("Amit","Amar","1.1.2001", "Adresa1", "123142","Email1", "1", 2021, 3, "B",9.3));
		studentList.add(new Student("Bmit","Bmar","2.1.2002", "Adresa2", "223142","Email6", "2", 2023, 2, "C",8.3));
		studentList.add(new Student("Cmit","Cmar","3.1.2003", "Adresa3", "423142","Email4", "3", 2022, 4, "B",7.3));
		studentList.add(new Student("Dmit","Dmar","4.1.2004", "Adresa4", "223142","Email3", "4", 2026, 1, "B",6.3));
		

		jt.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	selectedStudentRow = jt.getSelectedRow();
            	setSelectedRowStudent(selectedStudentRow);
                
            }
        });

     
                
            
        
		
	    jt.setBounds(30,40,200,300);       
	    jt.setAutoCreateRowSorter(true);// za opciono sortiranje
	    JScrollPane studentTable =new JScrollPane(jt);
		return studentTable;
	}


	public ArrayList<Student> getStudentList() {
		return studentList;
	}


	public void setStudentList(ArrayList<Student> studentList) {
		this.studentList = studentList;
	}


	public ArrayList<Professor> getProffesorList() {
		return proffesorList;
	}


	public void setProffesorList(ArrayList<Professor> proffesorList) {
		this.proffesorList = proffesorList;
	}


	public ArrayList<Subject> getSubjectList() {
		return subjectList;
	}


	public void setSubjectList(ArrayList<Subject> subjectList) {
		this.subjectList = subjectList;
	}


	public void setSelectedRowStudent(int row) {
		selectedStudentRow = row;
		String index = (String) modelStudent.getValueAt(row, 0);

		if (studentList != null) {
			for (Student s : studentList) {
				if (s.getIndex().equals(index)) {
					student = s;
				}
			}
		} else {
			System.out.println("listStudent is empty");
		}
		
		tb.setSelectedStudent(student);
		tb.setSelectedRowStudent(row);

	}
}
