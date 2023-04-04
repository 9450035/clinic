package org.clinic.service;

import org.clinic.domain.Clinic;
import org.clinic.repository.ClinicRepository;
import org.clinic.service.dto.ClinicDTO;
import org.clinic.service.mapper.ClinicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Clinic}.
 */
@Service
@Transactional
public class ClinicService {

    private final Logger log = LoggerFactory.getLogger(ClinicService.class);

    private final ClinicRepository clinicRepository;

    private final ClinicMapper clinicMapper;

    public ClinicService(ClinicRepository clinicRepository, ClinicMapper clinicMapper) {
        this.clinicRepository = clinicRepository;
        this.clinicMapper = clinicMapper;
    }

    /**
     * Save a clinic.
     *
     * @param clinicDTO the entity to save.
     * @return the persisted entity.
     */
    public ClinicDTO save(ClinicDTO clinicDTO) {
        log.debug("Request to save Clinic : {}", clinicDTO);
        Clinic clinic = clinicMapper.toEntity(clinicDTO);
        clinic = clinicRepository.save(clinic);
        return clinicMapper.toDto(clinic);
    }

    /**
     * Get all the clinics.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ClinicDTO> findAll() {
        log.debug("Request to get all Clinics");
        return clinicRepository.findAll().stream()
            .map(clinicMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one clinic by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ClinicDTO> findOne(Long id) {
        log.debug("Request to get Clinic : {}", id);
        return clinicRepository.findById(id)
            .map(clinicMapper::toDto);
    }

    /**
     * Delete the clinic by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Clinic : {}", id);
        clinicRepository.deleteById(id);
    }
}
