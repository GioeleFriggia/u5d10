package gioelefriggia.u5d10.services;

import gioelefriggia.u5d10.entities.Dispositivo;
import gioelefriggia.u5d10.repositories.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DispositivoService {

    private final DispositivoRepository dispositivoRepository;

    @Autowired
    public DispositivoService(DispositivoRepository dispositivoRepository) {
        this.dispositivoRepository = dispositivoRepository;
    }

    public List<Dispositivo> getAllDevices() {
        return dispositivoRepository.findAll();
    }

    public Dispositivo save(Dispositivo dispositivo) {
        return dispositivoRepository.save(dispositivo);
    }

    public Optional<Dispositivo> findDeviceById(UUID id) {
        return dispositivoRepository.findById(id);
    }

    public Dispositivo update(UUID id, Dispositivo updatedDispositivo) {
        Optional<Dispositivo> existingDispositivoOptional = dispositivoRepository.findById(id);
        if (existingDispositivoOptional.isPresent()) {
            Dispositivo existingDispositivo = existingDispositivoOptional.get();
            existingDispositivo.setTipo(updatedDispositivo.getTipo());
            existingDispositivo.setStato(updatedDispositivo.getStato());
            // Aggiungi altre proprietà da aggiornare se necessario
            return dispositivoRepository.save(existingDispositivo);
        }
        return null; // Gestisci il caso in cui il dispositivo non sia stato trovato
    }

    public void deleteDevice(UUID id) {
        dispositivoRepository.deleteById(id);
    }
}
