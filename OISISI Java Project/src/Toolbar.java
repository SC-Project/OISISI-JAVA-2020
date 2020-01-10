import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import entitites.Professor;
import entitites.Student;
import entitites.Subject;

public class Toolbar extends JPanel {
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton addBtn;
	private JButton editBtn;
	private JButton deleteBtn;
	private JButton searchBtn;

	private StudentFrame sf;
	private Student student;
	private Professor professor;
	private Subject subject;

	private ArrayList<Student> listStudent = new ArrayList<Student>();
	private ArrayList<Professor> listProfessor = new ArrayList<Professor>();
	private ArrayList<Subject> listSubject = new ArrayList<Subject>();

	private DefaultTableModel modelStudent;
	private Integer selectedStudentRow;

	public Toolbar() {

		sf = new StudentFrame(null);

		setLayout(new BorderLayout());
		setBorder(new BevelBorder(BevelBorder.LOWERED));

		JPanel p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.LEFT));
		// Add
		BufferedImage buttonIcon;
		try {
			buttonIcon = ImageIO.read(new File("pictures/addBtn.png"));
			Image newImage = buttonIcon.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			addBtn = new JButton(new ImageIcon(newImage));
			addBtn.setBorder(BorderFactory.createEmptyBorder());
			addBtn.setContentAreaFilled(false);
			addBtn.setToolTipText("Dodaje novi entitet za tabelu");

			addBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					sf = new StudentFrame(null);
					sf.setVisible(true);

					// recive Student
					student = sf.getStudent();
					if(student != null){
						listStudent.add(student);
						modelStudent.addRow(new Object[] { 
								student.getIndex(), student.getName(), student.getLastName(),
								student.getDate(),student.getAdress(),student.getTelephone(),
								student.getEmail(), student.getStartDate().toString(),
								student.getStudentYear().toString(),student.getStatus(),student.getAvgMark().toString() 
								});
					}
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Problems with finding or resizeing the ADD picture");
		}

		// Edit
		try {
			buttonIcon = ImageIO.read(new File("pictures/editBtn.png"));
			Image newImage = buttonIcon.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			editBtn = new JButton(new ImageIcon(newImage));
			editBtn.setBorder(BorderFactory.createEmptyBorder());
			editBtn.setContentAreaFilled(false);
			editBtn.setToolTipText("Podesava selektovani entitet");

			editBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent evt) {

					if (selectedStudentRow != null) {
						sf = new StudentFrame(student);
						sf.setVisible(true);

						// recive Student

						student = sf.getStudent();

						ArrayList<Student> newList = new ArrayList<Student>();
						for (int i = 0; i < listStudent.size(); i++) {
							if (i == selectedStudentRow) {
								newList.add(student);
							} else {
								newList.add(listStudent.get(i));
							}
						}

						listStudent = newList;

						modelStudent.removeRow(selectedStudentRow);
						modelStudent.insertRow(selectedStudentRow,
								new Object[] { student.getIndex(), student.getName(), student.getLastName(),
										student.getStudentYear(), student.getStatus(), student.getAvgMark() });

					} else {
						JOptionPane.showMessageDialog(null, "Morate prvo da selektujete jednog Studenta!");
					}
				}
			});
		} catch (

		IOException e) {
			e.printStackTrace();
			System.err.println("Problems with finding or resizeing the EDIT picture");
		}

		// Delete
		try {
			buttonIcon = ImageIO.read(new File("pictures/deleteBtn.png"));
			Image newImage = buttonIcon.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			deleteBtn = new JButton(new ImageIcon(newImage));
			deleteBtn.setBorder(BorderFactory.createEmptyBorder());
			deleteBtn.setContentAreaFilled(false);
			deleteBtn.setToolTipText("Brise selektovani entitet");

			deleteBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent evt) {
					if (selectedStudentRow != null) {
						int input = JOptionPane.showConfirmDialog(null, "Are you sure?");
						if (input == 0) {
							ArrayList<Student> newList = new ArrayList<Student>();
							for (int i = 0; i < listStudent.size(); i++) {
								if (i != selectedStudentRow) {
									newList.add(student);
								}
							}
							listStudent = newList;
							modelStudent.removeRow(selectedStudentRow);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Morate prvo da selektujete jednog Studenta!");
					}
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Problems with finding or resizeing the DELETE picture");
		}

		p.add(addBtn, BorderLayout.WEST);
		p.add(editBtn, BorderLayout.WEST);
		p.add(deleteBtn, BorderLayout.WEST);
		
		////////////////////////////////////////////
		

		JButton saveBtn = new JButton("Save Files");
		saveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				
				File f= new File("Lists/Students.txt"); 
				f.delete();
				exportFiles();
			}
		});
		
		p.add(saveBtn, BorderLayout.WEST);
		//////////////////////////////////////////////

		add(p, BorderLayout.WEST);

		// right side
		p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JTextField text = new JTextField(30);
		text.setSize(120, 30);
		p.add(text);

		try {
			buttonIcon = ImageIO.read(new File("pictures/searchBtn.png"));
			Image newImage = buttonIcon.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			searchBtn = new JButton(new ImageIcon(newImage));
			searchBtn.setBorder(BorderFactory.createEmptyBorder());
			searchBtn.setContentAreaFilled(false);
			p.add(searchBtn);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Problems with finding or resizeing the DELETE picture");
		}

		add(p, BorderLayout.EAST);

	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public ArrayList<Student> getListStudent() {
		return listStudent;
	}

	public void setListStudent(ArrayList<Student> listStudent) {
		this.listStudent = listStudent;
	}

	public ArrayList<Professor> getListProfessor() {
		return listProfessor;
	}

	public void setListProfessor(ArrayList<Professor> listProfessor) {
		this.listProfessor = listProfessor;
	}

	public ArrayList<Subject> getListSubject() {
		return listSubject;
	}

	public void setListSubject(ArrayList<Subject> listSubject) {
		this.listSubject = listSubject;
	}

	// Setting the student model and student list
	public void setStudentsModel(DefaultTableModel modelStudent) {
		this.modelStudent = modelStudent;

	}

	// and student list
	public void setStudentList(ArrayList<Student> studentList) {
		this.listStudent = studentList;

	}

	// Kada se selektuje red u student tabeli
	public void setSelectedRowStudent(int row) {
		selectedStudentRow = row;
	}

	public void setSelectedStudent(Student s) {
		student = s;
	}
	
	void exportFiles(){
		PrintWriter writer;
		try {
			writer = new PrintWriter("Lists/Students", "UTF-8");
			for(Student s : listStudent){
				writer.println(s.toString());
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	

	
	
}
