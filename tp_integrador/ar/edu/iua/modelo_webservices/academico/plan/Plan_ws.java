package ar.edu.iua.modelo_webservices.academico.plan;

import java.util.List;

import ar.edu.iua.modelo.Objeto;

public abstract class Plan_ws extends Objeto {

    abstract public Integer getAnio();

    abstract public void setAnio(Integer anio);

    abstract public void setEstadoBorrador();

    abstract public void setEstadoActivo();

    abstract public void setEstadoNoActivo();

    abstract public void setEstadoNulo();

    abstract public boolean isEstadoBorrador();

    abstract public boolean isEstadoActivo();

    abstract public boolean isEstadoNoActivo();

    abstract public boolean isEstadoNulo();

    abstract public List<AnioPlanImpl_ws> getAnios();

    abstract public void setAnios(List<AnioPlanImpl_ws> anios);

}
