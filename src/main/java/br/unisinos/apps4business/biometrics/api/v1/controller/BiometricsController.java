package br.unisinos.apps4business.biometrics.api.v1.controller;

import br.unisinos.apps4business.biometrics.api.v1.converter.BiometricsConverter;
import br.unisinos.apps4business.biometrics.api.v1.dto.BiometricsRequestDTO;
import br.unisinos.apps4business.biometrics.message.producer.BiometricsProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/biometrics")
public class BiometricsController {

    private final BiometricsProducer biometricsProducer;
    private final BiometricsConverter biometricsConverter;
    private final Logger logger;

    public BiometricsController(BiometricsProducer biometricsProducer, BiometricsConverter biometricsConverter) {
        this.biometricsProducer = biometricsProducer;
        this.biometricsConverter = biometricsConverter;
        this.logger = LoggerFactory.getLogger(BiometricsController.class);
    }

    @PostMapping(value="", produces = "application/json")
    public ResponseEntity sendBiometrics(@Valid @RequestBody BiometricsRequestDTO biometricsRequestDTO){
        logger.debug("Calling endpoint /v1/biometrics");
        logger.debug("biometricsRequestDTO={}", biometricsRequestDTO);
        biometricsProducer.sendMessage(biometricsConverter.convertRequestDTOToMessageDTO(biometricsRequestDTO));

        return ResponseEntity.ok().build();
    }

}
