package constacygraph.api.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import constacygraph.api.models.Graphs;
import constacygraph.api.repository.GraphRepository;
import org.hibernate.graph.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GraphService {

    @Autowired
    private GraphRepository repository;

 //aqui é onde irei criar novos dados
    //sempre que eu achar essa função no endpoint post
    // irá adicionar aqueles novos 366 dados porem com o objetivo com
    // o name diferente

    public ResponseEntity<String> newGraph(String objetivo) {
        try {
            // Ler o JSON do arquivo calendar_empty.json = Mockup para criar Json
            File jsonFile = new File("src/main/resources/static/calendar_empty.json");
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonFile);


            List<Graphs> graphsList = new ArrayList<>();

            // Adicionar o objetivo a todos os objetos no JSON
            for (JsonNode node : jsonNode) {
                ((ObjectNode) node).put("objetivo", objetivo);

                Graphs graphs = objectMapper.treeToValue(node, Graphs.class);
                graphsList.add(graphs);


            }

            // Salvar o JSON modificado de volta ao arquivo - cria um arquivo json, que pode ser descartado
            //objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/resources/static/calendar_modified.json"), jsonNode);


            // salvar esse arquivo
            repository.saveAll(graphsList);



            return new ResponseEntity<>("Novo Graph criado com sucesso", HttpStatus.CREATED);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Erro ao criar o novo Graph", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<List<Graphs>> listAllGraphs(){
        List<Graphs> list =  repository.findAll();
         return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public ResponseEntity<List<Graphs>> listByObjetivo(String objetivo) {
        List<Graphs> items = repository.findAllByObjetivo(objetivo);

        if (!items.isEmpty()) {
            return new ResponseEntity<>(items, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<String> limparDados(){
         repository.deleteAll();
        return new ResponseEntity<>("Todos dados apagados com sucesso.", HttpStatus.OK);
    }

 }
