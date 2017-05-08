/**
 *
 */
package eapli.framework.dto;

/**
 * a domain object that can be transformed to a GenericDTO
 *
 * @author Paulo Gandra Sousa
 *
 */
@FunctionalInterface
public interface DTOable {

    /**
     * returns a representation of the content of the object as a DTO.
     *
     * @return
     */
    GenericDTO toDTO();
}
