package ar.edu.iua.web_services.controladores;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import ar.edu.iua.excepciones.modelo_ex.CrearPlanEx;
import ar.edu.iua.modelo_webservices.academico.plan.PlanImpl_ws;
import ar.edu.iua.modelo_webservices.academico.plan.Plan_ws;
import ar.edu.iua.negocio_webservices.academico.plan.CrearPlanesImpl_ws;
import ar.edu.iua.web_services.util.utilWebServices;

public class CrearPlanesHandler implements HttpHandler {

    public void handle(HttpExchange exchange) throws IOException {
        String contextPath = exchange.getHttpContext().getPath();
        URI uri = exchange.getRequestURI();
        String path = uri.toString().replaceFirst(contextPath, "");
        Map<String, String> params = utilWebServices.leerParametrosConsulta(path);
        String body = utilWebServices.leerBody(exchange);

        try{
            ejecutarRespuesta(exchange, params, body);
        }catch(Exception e){
            System.out.println(e.getMessage());
            String msg = "504 ERROR INTERNO";
            exchange.sendResponseHeaders(504,0);
            OutputStream os = exchange.getResponseBody();
            os.write(msg.getBytes());
            os.close();
        }
        
    }

    private void ejecutarRespuesta(HttpExchange exchange,Map<String, String> params,String body) throws IOException{
        
        
        //creado = new Gson().fromJson(body, PlanImpl_ws.class);

        //Type planList = new TypeToken<ArrayList<PlanImpl_ws>>(){}.getType();
        //List<PlanImpl_ws> creados = new Gson().fromJson(body, planList);

        PlanImpl_ws[] planArray = new Gson().fromJson(body, PlanImpl_ws[].class);
        List<PlanImpl_ws> creados = Arrays.asList(planArray);

        CrearPlanesImpl_ws creador = new CrearPlanesImpl_ws();

        try {
            creador.crear(creados);
        } catch (CrearPlanEx e) {
            System.out.println(e.getMessage());
            String msg = "409 ERROR DE CONFLICTO: no se pudo crear el plan";
            exchange.sendResponseHeaders(204,0);
            OutputStream os = exchange.getResponseBody();
            os.write(msg.getBytes());
            os.close();
        }
        
        String msg = "200: Se creo el plan";
        exchange.sendResponseHeaders(200, msg.length());
        OutputStream os = exchange.getResponseBody();
        os.write(msg.getBytes());
        os.close();
        
    }
}