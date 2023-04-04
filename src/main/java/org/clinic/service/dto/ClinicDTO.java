package org.clinic.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link org.clinic.domain.Clinic} entity.
 */
public class ClinicDTO implements Serializable {
    
    private Long id;

    private String name;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClinicDTO)) {
            return false;
        }

        return id != null && id.equals(((ClinicDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClinicDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
