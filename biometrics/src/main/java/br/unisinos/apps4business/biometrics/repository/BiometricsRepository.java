package br.unisinos.apps4business.biometrics.repository;

import br.unisinos.apps4business.biometrics.entity.Biometrics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiometricsRepository extends CrudRepository<Biometrics, Long> {

}
