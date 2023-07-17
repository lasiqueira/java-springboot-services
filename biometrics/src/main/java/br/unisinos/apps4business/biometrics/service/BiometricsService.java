package br.unisinos.apps4business.biometrics.service;

import br.unisinos.apps4business.biometrics.entity.Biometrics;
import br.unisinos.apps4business.biometrics.repository.BiometricsRepository;
import org.springframework.stereotype.Service;

@Service
public class BiometricsService {
    private final BiometricsRepository biometricsRepository;

    public BiometricsService(BiometricsRepository biometricsRepository) {
        this.biometricsRepository = biometricsRepository;
    }

    public void saveBiometrics(Biometrics biometrics){
        biometricsRepository.save(biometrics);
    }
}
