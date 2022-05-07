package ar.edu.iua.negocio.academico.plan;

import ar.edu.iua.modelo.academico.plan.Plan;

public class CrearPlanImpl implements CrearPlan {

    public boolean crear(Plan plan) {

        if(plan == null) return false;//Plan no puede ser null
        if(plan.isEstadoNulo())return false;//No se permiten planes sin estado
        if(plan.getAnio() == null && !plan.isEstadoBorrador())return false;//No se permite plan con anio nulo, excepto que sea un borrador
        if(plan.getAnio().intValue() < 1990 || plan.getAnio().intValue()>2040)return false;//No se permite plan con anio 1990 < anio < 2040
        if((plan.getAnios()==null || plan.getAnios().size()==0) && !plan.isEstadoBorrador())return false;//No se permite listado de anios null o vacio, excepto que sea un borrador
        for(int ii = 0; ii < plan.getAnios().size(); ii++){
            if(!plan.getAnios().get(ii).getPlan().equals(plan))return false;//No se permite un anio con plan distinto al que pertenece
            if(plan.getAnios().get(ii).getNumero().intValue()<=0)return false;//No se permite que el numero de anio sea menor igual a 0
            if(plan.getAnios().get(ii)==null && !plan.isEstadoNulo())return false;//No se permite que el numero de anio sea null, excepto que sea un borrador
            if(plan.getAnios().get(ii).getNumero().intValue() < 1)return false;//anio comienza en 1
            if(plan.getAnios().get(ii).getNumero().intValue() != ii+1)return false;//anios orden secuencial
            for(int jj = 0; jj < plan.getAnios().size();jj++){
                if(plan.getAnios().get(ii).getNumero().equals(plan.getAnios().get(jj).getNumero()))return false;//No se permiten anios con el mismo numero
            }
            if(plan.getAnios().get(ii).getNombre().equals(null) && !plan.isEstadoBorrador())return false;//No se permite un anio sin nombre excepto que sea borrador
            if((plan.getAnios().get(ii).getMaterias()==null || plan.getAnios().get(ii).getMaterias().size()==0) && !plan.isEstadoBorrador())return false;//No se permite un listado de materias null o vacio excepto que sea borrador
            for(int kk = 0;kk < plan.getAnios().get(ii).getMaterias().size();kk++){
                if(!plan.getAnios().get(ii).getMaterias().get(kk).getAnio().equals(plan.getAnios().get(ii)))return false;//No se permite que una materia tenga un anioPlan distinto al que pertenece
                if(plan.getAnios().get(ii).getMaterias().get(kk).getCodigo().intValue()<=0)return false;//No se permite materia con el codigo menor igual a 0
                if(plan.getAnios().get(ii).getMaterias().get(kk).getCodigo().equals(null) && !plan.isEstadoBorrador())return false;//No se permite una materia de codigo null, excepto que sea borrador
                if(plan.getAnios().get(ii).getMaterias().get(kk).getCodigo().intValue() < 1)return false;//Codigo materia comienza en 1
                if(plan.getAnios().get(ii).getMaterias().get(kk).getCodigo().intValue() != kk+1)return false;//Orden de las materias secuencial
                for(int hh = 0; hh < plan.getAnios().get(ii).getMaterias().size(); hh++){
                    if(plan.getAnios().get(ii).getMaterias().get(hh).getCodigo().equals(plan.getAnios().get(ii).getMaterias().get(kk).getCodigo()))return false;//No se permiten materias con el mismo codigo
                }
                if(plan.getAnios().get(ii).getMaterias().get(kk).getNombre().equals(null) && !plan.isEstadoBorrador())return false;//No se permiten materias con nombre null, excepto que sea borrador
                if(plan.getAnios().get(ii).getMaterias().get(kk).getCargaHoraria().doubleValue() <= 0.0)return false;//No se permiten materias con carga horaria <=0
                if(plan.getAnios().get(ii).getMaterias().get(kk).getCargaHoraria().equals(null) && !plan.isEstadoBorrador())return false;//No se pèrmiten materias con carga horaria null, excepto que sea borrador   
            }
        }
        return false;
    }

}