package Controller;

import Model.Learner;
import Model.Tab;
import Model.TabDAO;
import View.Ficha;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Controllerr2 implements ActionListener {

    Tab t = new Tab();
    TabDAO tdao = new TabDAO();
    Ficha fi = new Ficha();

    public Controllerr2(Ficha fich) {
        this.fi = fich;
        this.fi.btnList.addActionListener(this);
        this.fi.btnSave.addActionListener(this);
        this.fi.btnDel.addActionListener(this);
        this.fi.btnExit.addActionListener(this);
        this.fi.btnSearch.addActionListener(this);
        this.fi.btnUpdate.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fi.btnList) {
            cleanTableF();
            List(fi.tblFicha);
            cleanF();
        }
        if (e.getSource() == fi.btnSave) {
            saveF();
            List(fi.tblFicha);
            cleanF();
        }
        if (e.getSource() == fi.btnSearch) {
            int row = fi.tblFicha.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(fi, "please select a row");
            } else {
                int id = Integer.parseInt(fi.tblFicha.getValueAt(row, 0).toString());
                int IdAprendiz = Integer.parseInt(fi.tblFicha.getValueAt(row, 5).toString());
                String tabnum = fi.tblFicha.getValueAt(row, 1).toString();
                String tabname = fi.tblFicha.getValueAt(row, 2).toString();
                String site = fi.tblFicha.getValueAt(row, 3).toString();
                String city = fi.tblFicha.getValueAt(row, 4).toString();
                

                fi.txtId.setText(String.valueOf(id));
                fi.txtIdapren.setText(String.valueOf(IdAprendiz) );
                fi.txtTabn.setText(tabnum);
                fi.txtTabnam.setText(tabname);
                fi.txtSite.setText(site);
                fi.cbSog.setSelected(false);
                fi.cbDui.setSelected(false);
                fi.cbTun.setSelected(false);
                
                if (city.equalsIgnoreCase("Sogamoso")) {
                    fi.cbSog.setSelected(true);

                } else if (city.equalsIgnoreCase("Duitama")) {
                    fi.cbDui.setSelected(true);
                }else if (city.equalsIgnoreCase("Tunja")) {
                    fi.cbTun.setSelected(true);
                }
            }
        }
        if (e.getSource() == fi.btnDel) {
            delete();
            List(fi.tblFicha);
            cleanF();
        }
        if (e.getSource() == fi.btnUpdate) {
            updateF();
            List(fi.tblFicha);
            cleanF();
        }
        if (e.getSource()==fi.btnExit) {
            int salir = JOptionPane.showConfirmDialog(null, "¿seguro desea salir?", "exit", JOptionPane.YES_NO_OPTION);
        if (salir==JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }
    }

    public void List(JTable tblFicha) {
        centerTable(tblFicha);
        DefaultTableModel mod = (DefaultTableModel) fi.tblFicha.getModel();
        mod.setRowCount(0);
        List<Tab> listt = tdao.tolist();
        for (Tab tb : listt) {
            Object[] obj = {tb.getId(), tb.getTabnum(), tb.getTabname(), tb.getSite(), tb.getCity(), tb.getIdAprendiz()};
            mod.addRow(obj);
        }
        tblFicha.setModel(mod);
    }

    public void saveF() {
        String tabnum = fi.txtTabn.getText();
        String tabname = fi.txtTabnam.getText();
        String site = fi.txtSite.getText();
        String City = fi.cbSog.isSelected() ? "Sogamoso" : fi.cbDui.isSelected() ? "Duitama" : "Tunja";
        int idA = Integer.parseInt(fi.txtIdapren.getText());
        Tab t = new Tab();
        t.setTabnum(tabnum);
        t.setTabname(tabname);
        t.setSite(site);
        t.setCity(City);
        t.setIdAprendiz(idA);
        int r = tdao.addF(t);
        if (r == 1) {
            JOptionPane.showMessageDialog(fi, "succesful regristation");
        } else {
            JOptionPane.showMessageDialog(fi, "Unsuccesful registration, try again");
        }
    }

    public void updateF() {
        if (fi.txtId.getText().equals(null)) {
            JOptionPane.showMessageDialog(fi, "Id not found");
        } else {
            int id = Integer.parseInt(fi.txtId.getText());

            String tabnum = fi.txtTabn.getText();
            String tabname = fi.txtTabnam.getText();
            String site = fi.txtSite.getText();
            String city = fi.cbSog.isSelected() ? "Sogamoso" : fi.cbDui.isSelected() ? "Duitama" : "Tunja";
            int idFora = Integer.parseInt(fi.txtIdapren.getText());

            t.setId(id);
            t.setTabnum(tabnum);
            t.setTabname(tabname);
            t.setSite(site);
            t.setCity(city);
            t.setIdAprendiz(idFora);

            int r = tdao.updateF(t);
            if (r == 1) {
                JOptionPane.showMessageDialog(fi, "updated tab");

            } else {
                JOptionPane.showMessageDialog(fi, "unupdated tab");
            }
        }
    }

    public void delete() {
        int roww = fi.tblFicha.getSelectedRow();
        if (roww == -1) {
            JOptionPane.showMessageDialog(fi, "please select a row");
        } else {
            int id = Integer.parseInt(fi.tblFicha.getValueAt(roww, 0).toString());
            tdao.DeleteF(id);
            JOptionPane.showMessageDialog(fi, "register deleted");
        }
        cleanTableF();
    }

    void cleanTableF() {
        DefaultTableModel model = (DefaultTableModel) fi.tblFicha.getModel();
        model.setRowCount(0);
    }

    public void centerTable(JTable tblApren) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < fi.tblFicha.getColumnCount(); i++) {
            tblApren.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    public void cleanF() {
        fi.txtId.setText(null);
        fi.txtIdapren.setText(null);
        fi.txtTabn.setText(null);
        fi.txtTabnam.setText(null);
        fi.txtSite.setText(null);
        fi.cbDui.setSelected(false);
        fi.cbSog.setSelected(false);
        fi.cbTun.setSelected(false);
    }
}
