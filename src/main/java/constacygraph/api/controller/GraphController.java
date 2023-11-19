package constacygraph.api.controller;


import constacygraph.api.models.Graphs;
import constacygraph.api.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GraphController {

    @Autowired
    private GraphService service;

    @PostMapping
    private ResponseEntity<String> gerarNovoJson(@RequestBody String objetivo){
        return service.newGraph(objetivo);
    }

    @GetMapping
    private ResponseEntity<List<Graphs>> listAllgraph(){
        return service.listAllGraphs();
    }
    @GetMapping("/{objetivo}")
    public ResponseEntity<List<Graphs>> listByObjetivo(@PathVariable String objetivo) {
        return service.listByObjetivo(objetivo);
    }
    @GetMapping("/uniqueObjetivos")
    public ResponseEntity<List<String>> uniqueObjetivos(){
        return service.listObjetivos();
    }
    @PutMapping("/{objetivo}")
    public ResponseEntity<String> updateData(@PathVariable String objetivo){
        return service.updateFrequency(objetivo);
    }


    @DeleteMapping
    private ResponseEntity<String> delete(){
        return service.limparDados();
    }
}
