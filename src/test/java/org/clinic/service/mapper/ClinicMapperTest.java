package org.clinic.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ClinicMapperTest {

    private ClinicMapper clinicMapper;

    @BeforeEach
    public void setUp() {
        clinicMapper = new ClinicMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(clinicMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(clinicMapper.fromId(null)).isNull();
    }
}
