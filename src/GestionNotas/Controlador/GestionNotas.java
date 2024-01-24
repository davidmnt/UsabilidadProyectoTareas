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
    private static DefaultTableModel Model;
    NuevaTarea nuevatarea = new NuevaTarea();
    ModTarea modtarea = new ModTarea();

    JFrame ventanaNuevaTarea = new JFrame("Gestion de Notas");
    JFrame ventanaModTarea = new JFrame("Gestion de Notas");

    int numeroRow = 0;



    public GestionNotas(){
        Model = new DefaultTableModel();

       comprobarUsabilidadBotones();

        //Metodo para configurar la tabla
        createTable();

        //Configurar boton nueva nota
        btn_nuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ventanaNuevaTarea.setContentPane(nuevatarea.PanelNuevaTareaMod);
                ventanaNuevaTarea.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ventanaNuevaTarea.setBounds(100,100,450,400);
                ventanaNuevaTarea.setVisible(true);

            }
        });

        //Btn_añadir
        nuevatarea.btnAñadirMod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Creamos estos String que mas tarde usuaremos
                String nombreCreador = nuevatarea.textFieldNombreCreadorMod.getText();
                String NombreTarea = nuevatarea.textFieldnombreTareaMod.getText();
                String Nota = nuevatarea.textAreaTareaMod.getText();

                ObjNota nota = new ObjNota(nombreCreador,NombreTarea,Nota);

                //Ahora crearemos un if para que se rellene un campo obligatorio
                if (Nota.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Por favor rellene el campo nota Y seleccione una fila","Error al Añadi",JOptionPane.ERROR_MESSAGE);

                    //Ahora creamos un arrraylist con los String anteriormente Creados
                } else {
                    Object[] Nuevo = {nota.getNombreCreador(),nota.getNombreTarea(),nota.getTarea()};

                    Model.addRow(Nuevo); //Esto nos sirve para que podamos añadir la tabla con el array

                    ventanaNuevaTarea.dispose();
                    nuevatarea.textAreaTareaMod.setText(" ");
                    nuevatarea.textFieldnombreTareaMod.setText(" ");
                    nuevatarea.textFieldNombreCreadorMod.setText(" ");


                    btn_borrar.setEnabled(true);
                    btn_modificar.setEnabled(true);


                }
            }
        });

        //Configuramos para cerrar la ventana emergente
        nuevatarea.btn_cancelarButtonMod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaNuevaTarea.dispose();
            }
        });

       //Configuramos el boton Modificar
        btn_modificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int idRow = tabla.getSelectedRow();
                numeroRow = idRow;
                if (idRow != -1) {

                    ventanaModTarea.setContentPane(modtarea.PanelModNota);
                    ventanaModTarea.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    ventanaModTarea.setBounds(100,100,450,400);
                    ventanaModTarea.setVisible(true);

                    String nombreCreadorAntiguo = (String) tabla.getValueAt(idRow,0);
                    String nombreTareaAntiguo = (String) tabla.getValueAt(idRow,1);
                    String tareaAntigua = (String) tabla.getValueAt(idRow,2);

                    modtarea.textFieldNombreCreadorMod.setText(nombreCreadorAntiguo);
                    modtarea.textFieldnombreTareaMod.setText(nombreTareaAntiguo);
                    modtarea.textAreaTareaMod.setText(tareaAntigua);

                }else {
                    JOptionPane.showMessageDialog(null,"Seleccione una tarea","Error al seleccionar",JOptionPane.ERROR_MESSAGE);
                }



            }
        });

        modtarea.btnAñadirMod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreCreadorNuevo = modtarea.textFieldNombreCreadorMod.getText();
                String nombreTareaNuevo = modtarea.textFieldnombreTareaMod.getText();
                String tareaNuevo = modtarea.textAreaTareaMod.getText();

                if(!nombreCreadorNuevo.isEmpty()){
                    tabla.setValueAt(nombreCreadorNuevo,numeroRow,0);
                }

                if(!nombreTareaNuevo.isEmpty()){
                    tabla.setValueAt(nombreTareaNuevo,numeroRow,1);
                }

                if(!tareaNuevo.isEmpty()){
                    tabla.setValueAt(tareaNuevo,numeroRow,2);
                }

                ventanaModTarea.dispose();
                modtarea.textFieldNombreCreadorMod.setText(" ");
                modtarea.textFieldnombreTareaMod.setText(" ");
                modtarea.textAreaTareaMod.setText(" ");




            }
        });

        modtarea.btn_cancelarButtonMod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaModTarea.dispose();
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

                        comprobarUsabilidadBotones();

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

    private void comprobarUsabilidadBotones(){
        if(Model.getRowCount() <= 0){
            btn_borrar.setEnabled(false);
            btn_modificar.setEnabled(false);
            System.out.println(Model.getRowCount());
        }
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
        frame.setBounds(100,100,550,500);
        frame.setVisible(true);



    }
}
