package ar.edu.iua.negocio_webservices.academico.plan;

import java.util.List;

import ar.edu.iua.excepciones.modelo_ex.CrearPlanEx;
import ar.edu.iua.modelo_webservices.academico.plan.PlanImplWs;

public interface CrearPlanesWs  {

    /*
        Este metodo requiere que se guarde en la base de datos un listado de planes
        Se debe validar y retornar false si:
            - planes no puede ser null
            - Ver las mismas reglas de CrearPlan.crear(Plan plan)
    */
    boolean crear(List<PlanImplWs> planes) throws CrearPlanEx;    
    
}
