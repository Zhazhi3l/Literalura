package com.hazzav.Literalura.model;

public class Persona {
    private String nombre;
    private String anoNacimiento;
    private String anoFallecimiento;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setAnoNacimiento(String anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    public String getAnoFallecimiento() {
        return anoFallecimiento;
    }

    public void setAnoFallecimiento(String anoFallecimiento) {
        this.anoFallecimiento = anoFallecimiento;
    }

    @Override
    public String toString() {
        return  "Nombre='" + nombre + '\'' +
                ", anoNacimiento='" + anoNacimiento + '\'' +
                ", anoFallecimiento='" + anoFallecimiento;
    }
}
