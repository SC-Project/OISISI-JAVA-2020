import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import entitites.Student;

public class StudentFrame extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Student s;

	public StudentFrame(Student student) {
		// public Student(String name, String lastName, String date, String
		// adress, String telephone, String email,
		// String index, Integer startDate, Integer studentYear, String status,
		// Double avgMark) {

		if (student == null) {
			s = new Student();
		} else {
			s = student;
		}
		setLayout(new GridLayout(11, 2));

		JLabel lbName = new JLabel("Ime");
		JTextField tfName = new JTextField(30);
		if (student != null) {
			tfName.setText(s.getName());
		}
		tfName.setSize(120, 30);
		add(lbName);
		add(tfName);

		JLabel lbLastName = new JLabel("Prezime");
		JTextField tfLastName = new JTextField(30);
		if (student != null) {
			tfLastName.setText(s.getLastName());
		}
		tfName.setSize(120, 30);
		add(lbLastName);
		add(tfLastName);

		JLabel lbBirthday = new JLabel("Datum Rodjenja");
		JTextField tfBirthday = new JTextField(30);
		if (student != null) {
			tfBirthday.setText(s.getDate());
		}
		tfBirthday.setSize(120, 30);
		add(lbBirthday);
		add(tfBirthday);

		JLabel lbAdresa = new JLabel("Adresa stanovanja");
		JTextField tfAdresa = new JTextField(30);
		if (student != null) {
			tfAdresa.setText(s.getAdress());
		}
		tfAdresa.setSize(120, 30);
		add(lbAdresa);
		add(tfAdresa);

		JLabel lbTelephone = new JLabel("Broj telefona");
		JTextField tfTelephone = new JTextField(30);
		if (student != null) {
			tfTelephone.setText(s.getTelephone());
		}
		tfTelephone.setSize(120, 30);
		add(lbTelephone);
		add(tfTelephone);

		JLabel lbEmail = new JLabel("Email");
		JTextField tfEmail = new JTextField(30);
		if (student != null) {
			tfEmail.setText(s.getEmail());
		}
		tfEmail.setSize(120, 30);
		add(lbEmail);
		add(tfEmail);

		JLabel lbIndex = new JLabel("Broj indeksa");
		JTextField tfIndex = new JTextField(30);
		if (student != null) {
			tfIndex.setText(s.getIndex());
		}
		tfIndex.setSize(120, 30);
		add(lbIndex);
		add(tfIndex);

		JLabel lbStartDate = new JLabel("Startna godina");
		JTextField tfStartDate = new JTextField(30);
		if (student != null) {
			tfStartDate.setText(s.getStartDate().toString());
		}
		tfStartDate.setSize(120, 30);
		add(lbStartDate);
		add(tfStartDate);

		// ComboBox
		JLabel lbStudentYear = new JLabel("Trenutna godina studija");
		add(lbStudentYear);
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("1 (prva godina)");
		comboBox.addItem("2 (druga godina)");
		comboBox.addItem("3 (treca godina)");
		comboBox.addItem("4 (cetvrta godina)");

		if (student != null) {
			if (student.getStudentYear() != null) {
				comboBox.setSelectedIndex(student.getStudentYear() - 1);
			}
		}

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (comboBox.getSelectedItem().toString().equals("1 (prva godina)")) {
					s.setStudentYear(1);
				} else if (comboBox.getSelectedItem().toString().equals("2 (druga godina)")) {
					s.setStudentYear(2);
				} else if (comboBox.getSelectedItem().toString().equals("3 (treca godina)")) {
					s.setStudentYear(3);
				} else {
					s.setStudentYear(4);
				}
			}
		});
		add(comboBox);

		JRadioButton rbBuzdet = new JRadioButton("Budzet", true);
		JRadioButton rbSamofinansiranje = new JRadioButton("Samofinansiranje");

		// ... Create a button group and add the buttons.
		ButtonGroup bgroup = new ButtonGroup();
		bgroup.add(rbBuzdet);
		bgroup.add(rbSamofinansiranje);

		// ... Arrange buttons vertically in a panel
		JPanel radioPanel = new JPanel();
		radioPanel.setLayout(new GridLayout(2, 1));
		radioPanel.add(rbBuzdet);
		radioPanel.add(rbSamofinansiranje);

		if (student != null) {
			if (student.getStatus() != null) {
				if (student.getStatus().equals("B")) {
					rbBuzdet.setSelected(true);
				} else {
					rbSamofinansiranje.setSelected(true);
				}
			}
		}
		rbBuzdet.setSelected(true);
		add(radioPanel);
		add(new JLabel(""));

		// Potvrda
		JButton btnSubmit = new JButton("Potvrda");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean allFine = true;
				// validacija za sva polja
				if ((tfName.getText().isEmpty()) || (tfLastName.getText().isEmpty()) || (tfBirthday.getText().isEmpty())
						|| (tfAdresa.getText().isEmpty()) || (tfTelephone.getText().isEmpty())
						|| (tfIndex.getText().isEmpty()) || (tfStartDate.getText().isEmpty())
						|| (tfEmail.getText().isEmpty()))
				{
					btnSubmit.setEnabled(false);;
				}
				
				
				
				
				// treba da se onemoguci pritisak d
				
				btnSubmit.setEnabled(false);;
				
				
				
				
				
				try {
					Integer.parseInt(tfTelephone.getText());
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null,"Morate da unesete broj u polje za Telefon");
					allFine = false;
				}
				
				try {
					Integer.parseInt(tfStartDate.getText());
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null,"Morate da unesete broj u polje za Pocetnu Godinu");
					allFine = false;
				}
					
				if(allFine){

					s.setName(tfName.getText());
					s.setLastName(tfLastName.getText());
					s.setDate(tfBirthday.getText());
					s.setAdress(tfAdresa.getText());
					s.setTelephone(tfTelephone.getText());
					s.setIndex(tfIndex.getText());
					s.setStartDate(Integer.parseInt(tfStartDate.getText()));
					s.setEmail(tfEmail.getText());

					if (comboBox.getSelectedItem().toString().equals("1 (prva godina)")) {
						s.setStudentYear(1);
					} else if (comboBox.getSelectedItem().toString().equals("2 (druga godina)")) {
						s.setStudentYear(2);
					} else if (comboBox.getSelectedItem().toString().equals("3 (treca godina)")) {
						s.setStudentYear(3);
					} else {
						s.setStudentYear(4);
					}

					if (student != null) {
						System.out.println(student.getAvgMark());
						if (student.getAvgMark() != null) {
							s.setAvgMark(student.getAvgMark());
						} 
					} else {
						double d = 0;
						s.setAvgMark(d);
						System.out.println(s.getAvgMark());
					}

					if (rbBuzdet.isSelected()) {
						s.setStatus("B");
					} else {
						s.setStatus("S");
					}
					setVisible(false);
				}

			}
		});
		add(btnSubmit);

		// Odustanak
		JButton cancel = new JButton("Odustanak");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				s = null;
				setVisible(false);
			}
		});
		add(cancel);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int scrW = (int) screenSize.getWidth() / 2;
		int scrH = (int) screenSize.getHeight() / 2;

		setSize(scrW, scrH);
		setLocationRelativeTo(null); // to be on center
		setModal(true);// da ne mozes da selectujes nista dok ne zavrsis sa ovim

	}

	public Student getStudent() {
		return s;
	}

	public void setSstudent(Student s) {
		this.s = s;
	}

}
