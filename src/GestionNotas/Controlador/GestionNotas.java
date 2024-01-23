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
                String nombreCreador = nuevatarea.textFieldNombreCreador.getText();
                String NombreTarea = nuevatarea.textFieldnombreTarea.getText();
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

       //Configuramos el boton Modificar
        btn_modificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Creamos un int el cual le damos un Nombre
                int selectRow = tabla.getSelectedRow();
                //Creamos un if con el nombre del int creado
                if (selectRow != -1) {



                }else {

                }
            }
        });

        //Configuramos el boton eliminar
        btn_borrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Creamos un int con este nombre
                int seleccionFila = tabla.getSelectedRow();
                //Creamos un if el cual dice que al seleccionar una fila se tiene que eliminar
                if (seleccionFila!=-1){


                    int opcion = JOptionPane.showConfirmDialog(null,"¿Seguro que quieres eliminar esta tarea?","Confirmar eliminacion", JOptionPane.YES_NO_OPTION);

                    if (opcion == JOptionPane.YES_OPTION) {

                        //Para eliminar la fila
                        Model.removeRow(seleccionFila);

                        // Código para eliminar
                        // Puedes mostrar el mensaje de eliminación después de realizar la eliminación
                        JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Código para manejar el caso en que el usuario elige NO
                        JOptionPane.showMessageDialog(null, "No se ha realizado ninguna eliminación", "Cancelado", JOptionPane.WARNING_MESSAGE);
                    }

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
        Model.addColumn("Nombre creador");
        Model.addColumn("Nombre");
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
