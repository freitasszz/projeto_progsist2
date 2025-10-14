package proj_progsist2.proj_estagios.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estudantes")
public class EstudanteController {

    private static List<Estudante> estudantes = new ArrayList<>();
    private static Long nextId = 1L;

    
    static {
        estudantes.add(new Estudante(nextId++, "1", "Ana Paula Souza", "ana.souza@email.com", "2002-03-15", "2020"));
        estudantes.add(new Estudante(nextId++, "2", "Carlos Henrique Lima", "carlos.lima@email.com", "2001-10-22", "2019"));
        estudantes.add(new Estudante(nextId++, "3", "Fernanda Oliveira", "fernanda.oliveira@email.com", "2003-07-05", "2021"));
        estudantes.add(new Estudante(nextId++, "4", "Lucas Pereira", "lucas.pereira@email.com", "2002-04-11", "2020"));
        estudantes.add(new Estudante(nextId++, "5", "Gabriela Martins", "gabriela.martins@email.com", "2001-12-25", "2019"));
        estudantes.add(new Estudante(nextId++, "6", "Rafael Costa", "rafael.costa@email.com", "2000-09-13", "2018"));
        estudantes.add(new Estudante(nextId++, "7", "Juliana Silva", "juliana.silva@email.com", "2002-06-18", "2020"));
        estudantes.add(new Estudante(nextId++, "8", "Marcos Vinícius", "marcos.vinicius@email.com", "2003-01-30", "2021"));
        estudantes.add(new Estudante(nextId++, "9", "Camila Azevedo", "camila.azevedo@email.com", "2001-11-08", "2019"));
        estudantes.add(new Estudante(nextId++, "10", "Felipe Cardoso", "felipe.cardoso@email.com", "2000-08-27", "2018"));
    }
    

    
    // GET /estudantes - Listar todos
    @GetMapping
    public List<Estudante> listar() {
        return estudantes;
    }

    // GET /estudantes/{id} - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Estudante> buscarPorId(@PathVariable Long id) {
        Optional<Estudante> estudante = estudantes.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
        
        return estudante.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /estudantes - Criar novo
    @PostMapping
    public Estudante criar(@RequestBody Estudante novoEstudante) {
        novoEstudante.setId(nextId++);
        estudantes.add(novoEstudante);
        return novoEstudante;
    }

    // PUT /estudantes/{id} - Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Estudante> atualizar(@PathVariable Long id, @RequestBody Estudante estudanteAtualizado) {
        for (int i = 0; i < estudantes.size(); i++) {
            if (estudantes.get(i).getId().equals(id)) {
                estudanteAtualizado.setId(id);
                estudantes.set(i, estudanteAtualizado);
                return ResponseEntity.ok(estudanteAtualizado);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE /estudantes/{id} - Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (estudantes.removeIf(e -> e.getId().equals(id))) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}