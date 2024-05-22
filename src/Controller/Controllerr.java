package Controller;

import Model.Learner;
import Model.LearnerDAO;
import View.Form;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Controllerr implements ActionListener {

    LearnerDAO dao = new LearnerDAO();
    Learner le = new Learner();
    Form formm;

    public Controllerr(Form f) {
        this.formm = f;
        this.formm.btnList.addActionListener(this);
        this.formm.btnSave.addActionListener(this);
        this.formm.btnDel.addActionListener(this);
        this.formm.btnExit.addActionListener(this);
        this.formm.btnSearch.addActionListener(this);
        this.formm.btnUpdate.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== formm.btnList) {
              cleanTable();
              tolist(formm.tblApren);
              clean();
        } 
          if (e.getSource()== formm.btnSave) {
              save();
              tolist(formm.tblApren);
              clean();
        } 
            if (e.getSource()== formm.btnSearch) {
              
             int row = formm.tblApren.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(formm, "please select a row");
                } else {
                    int id = Integer.parseInt(formm.tblApren.getValueAt(row, 0).toString());
                    String typedoc = formm.tblApren.getValueAt(row, 1).toString();
                    String docnum = formm.tblApren.getValueAt(row, 2).toString();
                    String name = formm.tblApren.getValueAt(row, 3).toString();
                    String born = formm.tblApren.getValueAt(row, 4).toString();
                    String gender = formm.tblApren.getValueAt(row, 5).toString();
                    String city = formm.tblApren.getValueAt(row, 6).toString();
                    
                    formm.txtId.setText(String.valueOf(id));
                    formm.cbxTipe.setSelectedItem(typedoc);
                    formm.txtDocnum.setText(docnum);
                    formm.txtName.setText(name);
                    formm.jdcBorn.setDate(java.sql.Date.valueOf(born));
                    if (gender.equalsIgnoreCase("Male")) {
                        formm.rbMale.setSelected(true);
                        
                    }else if (gender.equalsIgnoreCase("Female")){
                        formm.rbFemale.setSelected(true);
                    }
                    formm.txtCity.setText(city);
                }
        } 
        if (e.getSource()== formm.btnDel) {
           delete();
            tolist(formm.tblApren);
            clean();
        }
         if (e.getSource()== formm.btnExit) {
             int salir = JOptionPane.showConfirmDialog(null, "¿seguro desea salir?", "exit", JOptionPane.YES_NO_OPTION);
        if (salir==JOptionPane.OK_OPTION) {
            System.exit(0);
        }
        }
          if (e.getSource()== formm.btnUpdate) {
           update();
            tolist(formm.tblApren);
            clean();
        }
    }

    public void tolist(JTable tblApren) {
        centerTable(tblApren);
        DefaultTableModel mod = (DefaultTableModel) formm.tblApren.getModel();
        mod.setRowCount(0);
        List<Learner> listt = dao.tolist();
        for (Learner learn : listt) {
            Object[] obj = {learn.getId(), learn.getDoctype(), learn.getDocnum(), learn.getName(), learn.getBorn(), learn.getGender(), learn.getCity()};
            mod.addRow(obj);
        }
        tblApren.setModel(mod);
    }

    public void save() {
        String type = formm.cbxTipe.getSelectedItem().toString();
        String docnum = formm.txtDocnum.getText();
        String name = formm.txtName.getText();
        java.util.Date born = formm.jdcBorn.getDate();
        String gender = formm.rbMale.isSelected() ? "Male" : "Female";
        String city = formm.txtCity.getText();

        Learner lear = new Learner();
        lear.setDoctype(type);
        lear.setDocnum(docnum);
        lear.setName(name);
        lear.setBorn(born);
        lear.setGender(gender);
        lear.setCity(city);

        int r = dao.add(lear);
        if (r == 1) {
            JOptionPane.showMessageDialog(formm, "successful registration");

        } else {
            JOptionPane.showMessageDialog(formm, "unsuccesful registration");
        }
    }

    public void update() {
        if (formm.txtId.getText().equals(null)) {
            JOptionPane.showMessageDialog(formm, "Id not found");
        } else {
            int id = Integer.parseInt(formm.txtId.getText());
            String type = formm.cbxTipe.getSelectedItem().toString();
            String docnum = formm.txtDocnum.getText();
            String name = formm.txtName.getText();
            java.util.Date born = formm.jdcBorn.getDate();
            String gender = formm.rbMale.isSelected() ? "Male" : "Female";
            String city = formm.txtCity.getText();
            le.setId(id);
            le.setDoctype(type);
            le.setDocnum(docnum);
            le.setName(name);
            le.setBorn(born);
            le.setGender(gender);
            le.setCity(city);
            
            int r = dao.update(le);
              if (r == 1) {
            JOptionPane.showMessageDialog(formm, "registered user");

        } else {
            JOptionPane.showMessageDialog(formm, "unregistered user");
        }
        }
    }
    public void delete(){
        int roww = formm.tblApren.getSelectedRow();
        if (roww == -1) {
            JOptionPane.showMessageDialog(formm, "please select a row");
        }else{
            int id = Integer.parseInt(formm.tblApren.getValueAt(roww, 0).toString());
            dao.Delete(id);
            JOptionPane.showMessageDialog(formm, "register deleted");
        }
        cleanTable();
    }

    public void centerTable(JTable tblApren) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < formm.tblApren.getColumnCount(); i++) {
            tblApren.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    void cleanTable() {
        DefaultTableModel model = (DefaultTableModel) formm.tblApren.getModel();
        model.setRowCount(0);
    }

    public void clean() {
        formm.txtId.setText("");
        formm.cbxTipe.setSelectedIndex(0);
        formm.txtDocnum.setText("");
        formm.txtName.setText("");
        formm.jdcBorn.setDate(null);
        formm.rbMale.setSelected(false);
        formm.rbFemale.setSelected(false);
        formm.txtCity.setText("");
    }

}
