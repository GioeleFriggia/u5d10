package gioelefriggia.u5d10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import gioelefriggia.u5d10.entities.Dispositivo;
import java.util.UUID;

public interface DispositivoRepository extends JpaRepository<Dispositivo, UUID> {

}