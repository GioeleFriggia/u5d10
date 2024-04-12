package gioelefriggia.u5d10.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import gioelefriggia.u5d10.entities.Dipendente;
import gioelefriggia.u5d10.services.DipendenteService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/dipendenti")
public class DipendenteController {

    private final DipendenteService dipendenteService;

    @Autowired
    public DipendenteController(DipendenteService dipendenteService) {
        this.dipendenteService = dipendenteService;
    }

    @GetMapping
    public ResponseEntity<List<Dipendente>> getAllEmployees() {
        List<Dipendente> dipendenti = dipendenteService.findAllEmployees();
        return ResponseEntity.ok(dipendenti);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dipendente> getEmployeeById(@PathVariable UUID id) {
        return dipendenteService.findEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Dipendente> createEmployee(@RequestBody Dipendente dipendente) {
        Dipendente savedDipendente = dipendenteService.saveEmployee(dipendente);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDipendente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dipendente> updateEmployee(@PathVariable UUID id, @RequestBody Dipendente dipendente) {
        return dipendenteService.findEmployeeById(id)
                .map(existingDipendente -> {
                    dipendente.setId(existingDipendente.getId());
                    Dipendente updatedDipendente = dipendenteService.saveEmployee(dipendente);
                    return ResponseEntity.ok(updatedDipendente);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable UUID id) {
        dipendenteService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
