package gioelefriggia.u5d10.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import gioelefriggia.u5d10.entities.Dispositivo;
import gioelefriggia.u5d10.services.DispositivoService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/dispositivi")
public class DispositivoController {

    private final DispositivoService dispositivoService;

    @Autowired
    public DispositivoController(DispositivoService dispositivoService) {
        this.dispositivoService = dispositivoService;
    }

    @GetMapping
    public ResponseEntity<List<Dispositivo>> getAllDevices() {
        List<Dispositivo> dispositivi = dispositivoService.getAllDevices();
        return ResponseEntity.ok(dispositivi);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dispositivo> getDeviceById(@PathVariable UUID id) {
        return dispositivoService.findDeviceById(id)
                .map(dispositivo -> ResponseEntity.ok(dispositivo))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Dispositivo> createDevice(@RequestBody Dispositivo dispositivo) {
        Dispositivo savedDispositivo = dispositivoService.save(dispositivo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDispositivo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dispositivo> updateDevice(@PathVariable UUID id, @RequestBody Dispositivo updatedDispositivo) {
        return dispositivoService.findDeviceById(id)
                .map(existingDispositivo -> {
                    updatedDispositivo.setId(existingDispositivo.getId());
                    Dispositivo modifiedDispositivo = dispositivoService.save(updatedDispositivo);
                    return ResponseEntity.ok(modifiedDispositivo);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable UUID id) {
        dispositivoService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }
}
