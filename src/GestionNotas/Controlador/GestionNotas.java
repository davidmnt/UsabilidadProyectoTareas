package GestionNotas.Controlador;

import GestionNotas.Modelo.ObjNota;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GestionNotas {
    private JPanel MainPanel;
    private JLabel label_titulo;

    private JPanel JPanel_tabla;
    private JTable tabla;
    private JButton btn_nuevo;
    private JButton btn_modificar;
    private JButton btn_borrar;
    private JPanel JPanel_btn;

    private JLabel Ayuda;
    private DefaultTableModel Model;
    NuevaTarea nuevatarea = new NuevaTarea();
    JFrame ventanaNuevaTarea = new JFrame("Gestion de Notas");



    public GestionNotas(){
        Model = new DefaultTableModel();

        //Metodo para configurar la tabla
        createTable();

        //Configurar boton nueva nota
        btn_nuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ventanaNuevaTarea.setContentPane(nuevatarea.PanelNuevaTarea);
                ventanaNuevaTarea.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ventanaNuevaTarea.setBounds(0,0,350,300);
                ventanaNuevaTarea.setVisible(true);

            }
        });

        //Btn_añadir
        nuevatarea.btnAñadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Creamos estos String que mas tarde usuaremos
                String nombreCreador = nuevatarea.textFieldnombreTarea.getText();
                String NombreTarea = nuevatarea.textFieldNombreCreador.getText();
                String Nota = nuevatarea.textAreaTarea.getText();

                ObjNota nota = new ObjNota(nombreCreador,NombreTarea,Nota);

                //Ahora crearemos un if para que se rellene un campo obligatorio
                if (Nota.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Por favor rellene el campo nota Y seleccione una fila","Error al Añadi",JOptionPane.ERROR_MESSAGE);

                    //Ahora creamos un arrraylist con los String anteriormente Creados
                } else {
                    Object[] Nuevo = {nota.getNombreCreador(),nota.getNombreTarea(),nota.getTarea()};

                    Model.addRow(Nuevo); //Esto nos sirve para que podamos añadir la tabla con el array

                    ventanaNuevaTarea.dispose();
                    nuevatarea.textAreaTarea.setText(" ");
                    nuevatarea.textFieldnombreTarea.setText(" ");
                    nuevatarea.textFieldNombreCreador.setText(" ");
                }
            }
        });

        //Configuramos para cerrar la ventana emergente
        nuevatarea.btn_cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaNuevaTarea.dispose();
            }
        });

       /* //Configuramos el boton Modificar
        btn_modificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Creamos un int el cual le damos un Nombre
                int selectRow = tabla.getSelectedRow();
                //Creamos un if con el nombre del int creado
                if (selectRow !=-1 && !textField_nota.getText().isEmpty()){ //Aqui hacemos que sea obligatorio que se rellene el campo Notas
                    //Con esto añadimos los campos de texto en las columnas
                    tabla.setValueAt(textField_nombre.getText(),selectRow,0);
                    tabla.setValueAt(textField_apellido.getText(),selectRow,1);
                    tabla.setValueAt(textField_nota.getText(),selectRow,2);

                    //El else nos sirve en caso de que no se cumpla la condición del If (no se rellene notas), nos saldrá este mensaje de Error
                }else{
                    JOptionPane.showMessageDialog(null,"Por favor rellene el campo Notas y seleccione una Fila","Error al Modificar",JOptionPane.ERROR_MESSAGE);
                }
            }
        });*/

        //Configuramos el boton eliminar
        btn_borrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Creamos un int con este nombre
                int seleccionFila = tabla.getSelectedRow();
                //Creamos un if el cual dice que al seleccionar una fila se tiene que eliminar
                if (seleccionFila!=-1){
                    //Para eliminar la fila
                    Model.removeRow(seleccionFila);

                    JOptionPane.showMessageDialog(null,"Has eliminado correctamente","Error",JOptionPane.ERROR_MESSAGE);

                    //En caso de que no se seleccione ninguna fila nos saldra este mensaje de error
                }else{
                    JOptionPane.showMessageDialog(null,"Por favor seleccione una fila para eliminarla","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //El boton Ayuda
        Ayuda.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                JOptionPane.showMessageDialog(null,"Para crear una nueva nota, pulsar en nueva nota y rellenar los campos deseados"
                        ,"Ayuda",JOptionPane.INFORMATION_MESSAGE);

            }
        });
    }

    //Este es el metodo para configurar la tabla y que se haga visible
    //Aqui pondremos los campos necesarios paa la tabla
    private void createTable() {

        //Aqui estamos añadiendo los campos necesaios para la tabla
        Model.addColumn("Nombre");
        Model.addColumn("Nombre creador");
        Model.addColumn("Nota");
        tabla.setModel(Model);

    }

//Este es el main para que se inicie la aplicación
    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestion de Notas");
        frame.setContentPane(new GestionNotas().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0,0,550,500);
        frame.setVisible(true);
    }
}
