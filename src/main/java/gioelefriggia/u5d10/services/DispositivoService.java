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

    public List<Dispositivo> findAllDevices() {
        return dispositivoRepository.findAll();
    }

    public Optional<Dispositivo> findDeviceById(UUID id) {
        return dispositivoRepository.findById(id);
    }

    public Dispositivo saveDevice(Dispositivo dispositivo) {
        return dispositivoRepository.save(dispositivo);
    }

    public void deleteDevice(UUID id) {
        dispositivoRepository.deleteById(id);
    }


}
