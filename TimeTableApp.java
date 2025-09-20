import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TimeTableApp extends JFrame{
  private JTextField className, classCode, times;
  private JTable timeTable;
  private DefaultTableModel tableModel;
  private JButton addClass, removeClass;
  
  

  public TimeTableApp(){
    setTitle("My Timetable");
    setSize(600, 600);
    setResizable(true);
    setLayout(new FlowLayout());

    //Textfields
    className = new JTextField(10);
    classCode = new JTextField(10);
    times = new JTextField(10);

    //buttons
    addClass = new JButton("add");
    addClass.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        addClass();
      }
    });

    removeClass = new JButton("remove");
    removeClass.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        removeClass();
      }
    });

    //Table setup
    String[] columnNames = {"Class Name", "Class Code", "Times"};
    tableModel = new DefaultTableModel(columnNames, 0);
    timeTable = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(timeTable);
    scrollPane.setPreferredSize(new Dimension(500, 300));


    add(new JLabel("Class Name:"));
    add(className);
    add(new JLabel("Class Code"));
    add(classCode);
    add(new JLabel("Times"));
    add(times);
    add(addClass);
    add(removeClass);
    add(scrollPane);

  }

  public void addClass(){
    String name = className.getText();
    String code = classCode.getText();
    String time= times.getText();

    if(!name.isEmpty() && !code.isEmpty() && !time.isEmpty()){
      TimeTable newClass = new TimeTable(name, code, time);
      tableModel.addRow(new Object[]{newClass.className, newClass.classCode, newClass.times});
      clearFields();
    }else{
      JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  private void removeClass() {
    int selectedRow = timeTable.getSelectedRow();
    if (selectedRow != -1) {
        tableModel.removeRow(selectedRow);
    } else {
        JOptionPane.showMessageDialog(this, "Please select a class to remove.", "Selection Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void clearFields() {
    className.setText("");
    classCode.setText("");
    times.setText("");
}




public static void main(String[] args) {
  SwingUtilities.invokeLater(() -> {
      TimeTableApp app = new TimeTableApp();
      app.setVisible(true);
      app.setLocationRelativeTo(null);
      app.setDefaultCloseOperation(EXIT_ON_CLOSE);
  });
  }

}
