package entitites;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class StudentTabelModel extends AbstractTableModel {
	 	private static final int COLUMN_INDEX     	= 1;
	    private static final int COLUMN_NAME    	= 2;
	    private static final int COLUMN_LASTNAME    = 3;
	    private static final int COLUMN_STUDYEAR    = 4;
	    private static final int COLUMN_STATUS	    = 5;
	    private static final int COLUMN_AVGMARK     = 6;
	    
	    private String[] columnNames = {"Index","Name","Last Name","Stud Year", "Status", "Avg Mark"};
	    private List<Student> listStudent;
	    
	    public StudentTabelModel(List<Student> listStudent) {
	        this.listStudent = listStudent;
	         
	        int indexCount = 1;
	        for (Student student : listStudent) {
	        		student.setIndex(String.valueOf(indexCount++));
	        }
	    }
	    
	    @Override
		public int getColumnCount() {
	    	return columnNames.length;
		}
	    
		@Override
		public int getRowCount() {
			return listStudent.size();
		}
		@Override
	    public String getColumnName(int columnIndex) {
	        return columnNames[columnIndex];
	    }
	     
	    @Override
	    public Class<?> getColumnClass(int columnIndex) {
	        if (listStudent.isEmpty()) {
	            return Object.class;
	        }
	        return getValueAt(0, columnIndex).getClass();
	    }
	 
	    @Override
	    public Object getValueAt(int rowIndex, int columnIndex) {
	        Student employee = listStudent.get(rowIndex);
	        Object returnValue = null;
	         
	        switch (columnIndex) {
	        case COLUMN_INDEX:
	            returnValue = employee.getIndex();
	            break;
	        case COLUMN_NAME:
	            returnValue = employee.getName();
	            break;
	        case COLUMN_LASTNAME:
	            returnValue = employee.getLastName();
	            break;
	        case COLUMN_STUDYEAR:
	            returnValue = employee.getStudentYear();
	            break;
	        case COLUMN_STATUS:
	            returnValue = employee.getStatus();
	            break;
	        case COLUMN_AVGMARK:
	            returnValue = employee.getAvgMark();
	            break;
	        default:
	            throw new IllegalArgumentException("Invalid column index");
	        }
	         
	        return returnValue;
	    }
	     
	    @Override
	    public void setValueAt(Object value, int rowIndex, int columnIndex) {
	     //   Student employee = listStudent.get(rowIndex);
	      //  if (columnIndex == COLUMN_NO) {
	      //      employee.setIndex((int) value);
	      //  }      
	    }
	 
}
