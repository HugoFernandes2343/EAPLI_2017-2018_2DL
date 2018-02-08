/**
 * Provides generic interfaces for building persistence layers following the
 * Repository pattern (acts as an in memory collection of entities).
 * 
 * @startuml Repository <|-- IterableRepository Repository <|--
 *           DeleteableRepository
 * @enduml
 * 
 * @author Paulo Gandra Sousa
 * 
 */
package eapli.framework.persistence.repositories;