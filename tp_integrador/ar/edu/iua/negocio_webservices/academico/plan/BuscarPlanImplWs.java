package ar.edu.iua.negocio_webservices.academico.plan;

import ar.edu.iua.excepciones.modelo_ex.BuscarPlanEx;
import ar.edu.iua.modelo_webservices.academico.plan.PlanWs;
import ar.edu.iua.persistencia.BaseDeDatos;

public class BuscarPlanImplWs implements BuscarPlanWs {

    @Override
    public PlanWs buscar(int anio) throws BuscarPlanEx{
        for(int ii = 0; ii < BaseDeDatos.planesSizeWs(); ii++){
            try {
                if(BaseDeDatos.getPlanWs(ii).getAnio().intValue() == anio){
                    return BaseDeDatos.getPlanWs(ii);
                }
            } catch (CloneNotSupportedException e) {
                throw new BuscarPlanEx("No se pudo obtener el plan " + ii + " de la base de datos BuscarPlanImpl ln 13");
            }
        }
        throw new BuscarPlanEx("No se encontro el plan. BuscarPlanImpl.java ln 12");
    }
    
}
