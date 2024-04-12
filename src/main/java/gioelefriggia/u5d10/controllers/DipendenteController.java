package gioelefriggia.u5d10.controllers;

import gioelefriggia.u5d10.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import gioelefriggia.u5d10.entities.Dipendente;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/dipendenti")
public class DipendenteController {

    private final DeviceService dispositivoService;

    @Autowired
    public DipendenteController(DeviceService dispositivoService) {
        this.dispositivoService = dispositivoService;
    }

    @GetMapping
    public ResponseEntity<List<Dipendente>> getAllEmployees() {
        List<Dipendente> dipendenti = dispositivoService.findAllEmployees();
        return ResponseEntity.ok(dipendenti);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dipendente> getEmployeeById(@PathVariable UUID id) {
        return dispositivoService.findEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Dipendente> createEmployee(@RequestBody Dipendente dipendente) {
        Dipendente savedDipendente = dispositivoService.saveEmployee(dipendente);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDipendente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dipendente> updateEmployee(@PathVariable UUID id, @RequestBody Dipendente dipendente) {
        return dispositivoService.findEmployeeById(id)
                .map(existingDipendente -> {
                    dipendente.setId(existingDipendente.getId());
                    Dipendente updatedDipendente = dispositivoService.saveEmployee(dipendente);
                    return ResponseEntity.ok(updatedDipendente);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable UUID id) {
        dispositivoService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
