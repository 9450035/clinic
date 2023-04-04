package org.clinic.service.mapper;


import org.clinic.domain.*;
import org.clinic.service.dto.ClinicDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Clinic} and its DTO {@link ClinicDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ClinicMapper extends EntityMapper<ClinicDTO, Clinic> {



    default Clinic fromId(Long id) {
        if (id == null) {
            return null;
        }
        Clinic clinic = new Clinic();
        clinic.setId(id);
        return clinic;
    }
}
