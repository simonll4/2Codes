package ar.edu.iua.util;

import java.util.ArrayList;
import java.util.List;
import ar.edu.iua.modelo.academico.plan.AnioPlan;
import ar.edu.iua.modelo.academico.plan.AnioPlanImpl;
import ar.edu.iua.modelo.academico.plan.Materia;
import ar.edu.iua.modelo.academico.plan.Plan;
import ar.edu.iua.modelo.academico.plan.PlanImpl;
import ar.edu.iua.util.metodos_aleatorios.ObtenerMateria;
import ar.edu.iua.util.metodos_aleatorios.ObtenerPlan;

public class UtilRandom {

    public Plan construirPlan(List<Plan> listaPlanes) {

        Plan planAleatorio = new PlanImpl();
        ObtenerMateria materiaObtenida = new ObtenerMateria();
        ObtenerPlan planObtenido = new ObtenerPlan();
        List<AnioPlan> aniosPlan = new ArrayList<AnioPlan>();

        planAleatorio.setAnio((int) (Math.random() * 37) + 1995);

        for (int jj = 0; jj < listaPlanes.size(); jj++) {
            if (planAleatorio.getAnio().equals(listaPlanes.get(jj).getAnio())) {
                planAleatorio.setAnio((int) (Math.random() * 39) + 1995);
                jj = -1;
            }
        }

        for (int ii = 0; ii < 5; ii++) { // Recorrer todos los aniosPlan de la lista
            List<Materia> materiasPlan = new ArrayList<Materia>();
            AnioPlan anioAleatorio = null;
            try {
                anioAleatorio = (AnioPlanImpl) planObtenido.getPlan(listaPlanes).getAnios().get(ii).clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

            for (int jj = 0; jj < 12; jj++) {// For encargado de rellenar con materias cada anioPlan
                AnioPlan anioHerramienta = null;
                try {
                    anioHerramienta = (AnioPlanImpl) planObtenido.getPlan(listaPlanes).getAnios().get(ii).clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                Materia materiaAleatoria = null;
                try {
                    materiaAleatoria = (Materia) materiaObtenida.getMateria(anioHerramienta).clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                if (jj == 0) {
                    materiasPlan.add(materiaAleatoria);
                } else {
                    for (int kk = 0; kk < materiasPlan.size(); kk++) {
                        Boolean bandera = false;
                        String matActual = UtilTranslate.traducirCadena(materiasPlan.get(kk).getNombre());
                        String matAleatoria = UtilTranslate.traducirCadena(materiaAleatoria.getNombre());

                        if (matActual.equals(matAleatoria)) {
                            bandera = false;
                            jj--;
                            break;
                        } else if (kk == materiasPlan.size() - 1) {
                            bandera = true;
                        }
                        if (bandera) {
                            materiasPlan.add(materiaAleatoria);
                            break;
                        }
                    }
                }
            }
            anioAleatorio.setPlan(planAleatorio);
            anioAleatorio.setMaterias(materiasPlan);
            
            for (int jj = 0; jj < materiasPlan.size(); jj++) {
                if(jj<9){
                    anioAleatorio.getMaterias().get(jj).setCodigo(Integer.valueOf(""
                                + anioAleatorio.getPlan().getAnio().toString()
                                + anioAleatorio.getNumero().toString()
                                + "0" + (jj+1)));   
                }
                else{
                    anioAleatorio.getMaterias().get(jj).setCodigo(Integer.valueOf(""
                                + anioAleatorio.getPlan().getAnio().toString()
                                + anioAleatorio.getNumero().toString()
                                + (jj+1)));
                }
            }
            aniosPlan.add(anioAleatorio);
        }
            
        planAleatorio.setAnios(aniosPlan);
        int random = (int)(Math.random()*2);
        if(random == 0){
            planAleatorio.setEstadoNoActivo();
        }
        if(random == 1){
            planAleatorio.setEstadoBorrador();
        }
        return planAleatorio;
    }
}