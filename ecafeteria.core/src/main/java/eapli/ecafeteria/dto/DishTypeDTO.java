package eapli.ecafeteria.dto;

import eapli.framework.dto.DTO;

/**
 * a pure DTO for dish types
 */
@SuppressWarnings("squid:ClassVariableVisibilityCheck")
public class DishTypeDTO implements DTO {

    public String acronym;
    public String description;
    public boolean active;

    public DishTypeDTO(String acronym2, String description2, boolean active2) {
        acronym = acronym2;
        description = description2;
        active = active2;
    }
}
