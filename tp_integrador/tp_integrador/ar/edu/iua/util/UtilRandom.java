package ar.edu.iua.util;

import java.util.ArrayList;
import java.util.List;

import ar.edu.iua.modelo.Objeto;
import ar.edu.iua.modelo.academico.plan.AnioPlan;
import ar.edu.iua.modelo.academico.plan.AnioPlanImpl;
import ar.edu.iua.modelo.academico.plan.Materia;
import ar.edu.iua.modelo.academico.plan.MateriaImpl;
import ar.edu.iua.modelo.academico.plan.Plan;
import ar.edu.iua.modelo.academico.plan.PlanImpl;
import ar.edu.iua.util.metodos_aleatorios.ObtenerAnioPlan;
import ar.edu.iua.util.metodos_aleatorios.ObtenerMateria;
import ar.edu.iua.util.metodos_aleatorios.ObtenerPlan;

public class UtilRandom {

    public Plan construirPlan(List<Plan> listaPlanes) {
        int auxVerificacion = 1;

        Plan planAleatorio = new PlanImpl();

        ObtenerAnioPlan anioObtenido = new ObtenerAnioPlan();
        ObtenerMateria materiaObtenida = new ObtenerMateria();
        ObtenerPlan planObtenido = new ObtenerPlan();

        List<AnioPlan> aniosPlan = new ArrayList<AnioPlan>();

        planAleatorio.setAnio((int) (Math.random() + 2018));

        for (int jj = 0; jj < listaPlanes.size(); jj++) {
            if (planAleatorio.getAnio().equals(listaPlanes.get(jj).getAnio())) {
                planAleatorio.setAnio((int) (Math.random() * 20) + 2002);
                jj--;
            }
        }

        for (int ii = 0; ii < 5; ii++) {
            List<Materia> materiasPlan = new ArrayList<Materia>();
            AnioPlan anioAleatorio = new AnioPlanImpl();
            for (int jj = 0; jj < 12; jj++) {
                AnioPlan anioHerramienta=null;
                try {
                    anioHerramienta = (AnioPlanImpl) planObtenido.getPlan(listaPlanes).getAnios().get(ii).clone();
                    anioAleatorio.setNombre(anioHerramienta.getNombre());
                    anioAleatorio.setNumero(anioHerramienta.getNumero());
                } catch (CloneNotSupportedException e) {
                    
                    e.printStackTrace();
                }
                if (jj == 0) {
                    Materia materia=null;
                    try {
                        materia = (Materia)materiaObtenida.getMateria((AnioPlanImpl)anioHerramienta).clone();
                    } catch (CloneNotSupportedException e) {
                        
                        e.printStackTrace();
                    }
                    materiasPlan.add((Materia)materia);
                } else {
                    Materia materia=null;
                    try {
                        materia = (Materia)materiaObtenida.getMateria((AnioPlanImpl)anioHerramienta).clone();
                    } catch (CloneNotSupportedException e) {
                        
                        e.printStackTrace();
                    }
                    for (int kk = 0; kk < materiasPlan.size(); kk++) {
                        Boolean bandera = false;
                        if (materiasPlan.get(kk).getCodigoVerificacion().equals(((Materia) materia).getCodigoVerificacion())) {
                            bandera = false;
                            jj--;
                            break;
                        } else if (kk == materiasPlan.size() - 1) {
                            bandera = true;
                        }
                        if (bandera) {
                            materiasPlan.add((Materia) materia);
                            break;
                        }
                    }
                }
            }
            anioAleatorio.setPlan(planAleatorio);
            anioAleatorio.setMaterias(materiasPlan);
            for (int jj = 0; jj < materiasPlan.size(); jj++) {
                anioAleatorio.getMaterias().get(jj).setCodigoVerificacion(auxVerificacion);
                anioAleatorio.getMaterias().get(jj).setCodigo(Integer.valueOf(""
                        + anioAleatorio.getPlan().getAnio().toString()
                        + anioAleatorio.getNumero().toString()
                        + anioAleatorio.getMaterias().get(jj).getCodigoVerificacion().toString()));
                auxVerificacion++;
            }
            aniosPlan.add(anioAleatorio);
        }

        planAleatorio.setAnios(aniosPlan);
        return planAleatorio;
    }
}