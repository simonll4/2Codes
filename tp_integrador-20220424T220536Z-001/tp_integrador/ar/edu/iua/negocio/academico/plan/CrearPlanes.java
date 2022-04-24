package ar.edu.iua.negocio.academico.plan;

import java.util.List;

import ar.edu.iua.modelo.academico.plan.Plan;

public interface CrearPlanes extends CrearPlan {

    /*
        Este metodo requiere que se guarde en la base de datos un listado de planes
        Se debe validar y retornar false si:
            - planes no puede ser null
            - Ver las mismas reglas de CrearPlan.crear(Plan plan)
    */
    boolean crear(List<Plan> planes);    
    
}
