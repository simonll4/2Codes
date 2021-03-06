package ar.edu.iua.negocio.academico.plan;

import ar.edu.iua.excepciones.modelo_ex.ModificarPlanEx;
import ar.edu.iua.excepciones.modelo_ex.VerificadorEx;
import ar.edu.iua.modelo.academico.plan.Plan;
import ar.edu.iua.persistencia.BaseDeDatos;
import ar.edu.iua.util.verificadores.VerificarIntegridad;

public class ModificarPlanImpl implements ModificarPlan {

    public boolean modificar(Plan plan) throws ModificarPlanEx{
        boolean bandera = false;

        boolean v;
        try {
            v = VerificarIntegridad.verificadorIntegridadPlan(plan);
        } catch (VerificadorEx e1) {
            throw new ModificarPlanEx(e1.getMessage());
        }

        if(v){
            for(int ii = 0; ii < BaseDeDatos.planesSize();ii++){
                try {
                    if(BaseDeDatos.getPlan(ii).getAnio().equals(plan.getAnio())){
                        BaseDeDatos.setPlan(ii, plan);
                        return !bandera;
                    }
                } catch (CloneNotSupportedException e) {
                    throw new ModificarPlanEx("No se pudo acceder al indice " + ii +" de la base de datos ModificarPlanImpl ln 24");
                }
            }
        }
        return bandera;
    }


    
}
