/**
 *
 */
package eapli.framework.dto;

/**
 * a class that can build an instance of a domain object based on a GenericDTO
 *
 * @author Paulo Gandra Sousa
 *
 */
@FunctionalInterface
public interface DtoParseable<T extends DTOable> {

    /**
     * parses the DTO and builds a new T based on the content of the DTO
     *
     * @param dto
     * @return
     */
    T valueOf(GenericDTO dto);
}
