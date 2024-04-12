package gioelefriggia.u5d10.services;

import gioelefriggia.u5d10.entities.Dipendente;
import gioelefriggia.u5d10.repositories.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DipendenteService {

    private final DipendenteRepository dipendenteRepository;

    @Autowired
    public DipendenteService(DipendenteRepository dipendenteRepository) { // Corrected constructor name to match class name
        this.dipendenteRepository = dipendenteRepository;
    }

    public List<Dipendente> findAllEmployees() {
        return dipendenteRepository.findAll();
    }

    public Optional<Dipendente> findEmployeeById(UUID id) {
        return dipendenteRepository.findById(id);
    }

    public Dipendente saveEmployee(Dipendente dipendente) {
        return dipendenteRepository.save(dipendente);
    }

    public void deleteEmployee(UUID id) {
        dipendenteRepository.deleteById(id);
    }

}
