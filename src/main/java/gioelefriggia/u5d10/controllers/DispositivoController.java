package gioelefriggia.u5d10.controllers;

import gioelefriggia.u5d10.services.EmployeeService;
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

    private final EmployeeService deviceService;

    @Autowired
    public DipendenteController(EmployeeService  deviceService) { // Corretto il nome del servizio
        this.deviceService = deviceService;
    }

    @GetMapping
    public ResponseEntity<List<Dipendente>> getAllEmployees() {
        List<Dipendente> dipendenti = deviceService.findAllEmployees(); // Corretto il nome del metodo
        return ResponseEntity.ok(dipendenti);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dipendente> getEmployeeById(@PathVariable UUID id) {
        return deviceService.findEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Dipendente> createEmployee(@RequestBody Dipendente dipendente) {
        Dipendente savedDipendente = deviceService.saveEmployee(dipendente);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDipendente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dipendente> updateEmployee(@PathVariable UUID id, @RequestBody Dipendente dipendente) {
        return deviceService.findEmployeeById(id)
                .map(existingDipendente -> {
                    dipendente.setId(existingDipendente.getId());
                    Dipendente updatedDipendente = deviceService.saveEmployee(dipendente);
                    return ResponseEntity.ok(updatedDipendente);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable UUID id) {
        deviceService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
