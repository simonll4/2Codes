package ar.edu.iua.modelo.academico.plan;

import ar.edu.iua.modelo.Objeto;

public abstract class Materia extends Objeto {

    abstract public AnioPlan getAnio();

    abstract public void setAnio(AnioPlan anio);

    abstract public Integer getCodigo();

    abstract public void setCodigo(Integer codigo);

    abstract public void setCodigoVerificacion(Integer codigo);

    abstract public Integer getCodigoVerificacion();

    abstract public String getNombre();

    abstract public void setNombre(String nombre);

    abstract public Double getCargaHoraria();

    abstract public void setCargaHoraria(Double cargaHoraria);

}
