package es.uca.gofestapppp;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    public String nombre, dni, telefono, id;
    public Date nacimiento, inscripcion;

    public User(String id, String nombre, String dni, String telefono, Date nacimiento, Date inscripcion) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.nacimiento = nacimiento;
        this.inscripcion = inscripcion;
    }

    public String getNombre() { return nombre; }

    public String getDni() { return dni; }

    public String getTelefono() { return telefono; }

    public Date getNacimiento() { return nacimiento; }

    public Date getInscripcion() { return inscripcion; }

    public String getId() { return id; }
}
