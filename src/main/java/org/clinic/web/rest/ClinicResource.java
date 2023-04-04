package org.clinic.web.rest;

import org.clinic.service.ClinicService;
import org.clinic.web.rest.errors.BadRequestAlertException;
import org.clinic.service.dto.ClinicDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link org.clinic.domain.Clinic}.
 */
@RestController
@RequestMapping("/api")
public class ClinicResource {

    private final Logger log = LoggerFactory.getLogger(ClinicResource.class);

    private static final String ENTITY_NAME = "clinicClinic";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClinicService clinicService;

    public ClinicResource(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    /**
     * {@code POST  /clinics} : Create a new clinic.
     *
     * @param clinicDTO the clinicDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new clinicDTO, or with status {@code 400 (Bad Request)} if the clinic has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/clinics")
    public ResponseEntity<ClinicDTO> createClinic(@RequestBody ClinicDTO clinicDTO) throws URISyntaxException {
        log.debug("REST request to save Clinic : {}", clinicDTO);
        if (clinicDTO.getId() != null) {
            throw new BadRequestAlertException("A new clinic cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClinicDTO result = clinicService.save(clinicDTO);
        return ResponseEntity.created(new URI("/api/clinics/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /clinics} : Updates an existing clinic.
     *
     * @param clinicDTO the clinicDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated clinicDTO,
     * or with status {@code 400 (Bad Request)} if the clinicDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the clinicDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/clinics")
    public ResponseEntity<ClinicDTO> updateClinic(@RequestBody ClinicDTO clinicDTO) throws URISyntaxException {
        log.debug("REST request to update Clinic : {}", clinicDTO);
        if (clinicDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClinicDTO result = clinicService.save(clinicDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, clinicDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /clinics} : get all the clinics.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of clinics in body.
     */
    @GetMapping("/clinics")
    public List<ClinicDTO> getAllClinics() {
        log.debug("REST request to get all Clinics");
        return clinicService.findAll();
    }

    /**
     * {@code GET  /clinics/:id} : get the "id" clinic.
     *
     * @param id the id of the clinicDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the clinicDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/clinics/{id}")
    public ResponseEntity<ClinicDTO> getClinic(@PathVariable Long id) {
        log.debug("REST request to get Clinic : {}", id);
        Optional<ClinicDTO> clinicDTO = clinicService.findOne(id);
        return ResponseUtil.wrapOrNotFound(clinicDTO);
    }

    /**
     * {@code DELETE  /clinics/:id} : delete the "id" clinic.
     *
     * @param id the id of the clinicDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/clinics/{id}")
    public ResponseEntity<Void> deleteClinic(@PathVariable Long id) {
        log.debug("REST request to delete Clinic : {}", id);
        clinicService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
