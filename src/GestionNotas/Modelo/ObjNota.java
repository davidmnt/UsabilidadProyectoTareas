package GestionNotas.Modelo;

public class ObjNota {
    private String nombreCreador;
    private String nombreTarea;
    private String Tarea;

    public ObjNota(){

    }

    public ObjNota(String nombreCreador, String nombreTarea, String tarea) {
        this.nombreCreador = nombreCreador;
        this.nombreTarea = nombreTarea;
        Tarea = tarea;
    }

    public String getNombreCreador() {
        return nombreCreador;
    }

    public void setNombreCreador(String nombreCreador) {
        this.nombreCreador = nombreCreador;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public String getTarea() {
        return Tarea;
    }

    public void setTarea(String tarea) {
        Tarea = tarea;
    }

    @Override
    public String toString() {
        return "ObjNota{" +
                "nombreCreador='" + nombreCreador + '\'' +
                ", nombreTarea='" + nombreTarea + '\'' +
                ", Tarea='" + Tarea + '\'' +
                '}';
    }
}
