/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.allergens;

import java.io.Serializable;
import java.util.Arrays;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author João Silva
 */
//@Embeddable
public enum Allergen implements Serializable {

    @Column(name = "Allergen")
    GLUTEN {
        private final String id = "Gluten";

        @Override
        public String toString() {
            return id;
        }
    },
    CRUSTACEANS {
        private final String id = "Crustaceans";

        @Override
        public String toString() {
            return id;
        }
    },
    EGGS {
        private final String id = "Eggs";

        @Override
        public String toString() {
            return id;
        }
    },
    FISH {
        private final String id = "Fish";

        @Override
        public String toString() {
            return "Fish";
        }
    },
    PEANUTS {
        private final String id = "Peanuts";

        @Override
        public String toString() {
            return id;
        }
    },
    SOYA {
        private final String id = "Soya";

        @Override
        public String toString() {
            return id;
        }
    },
    DAIRIES {
        private final String id = "Dairies";

        @Override
        public String toString() {
            return id;
        }
    },
    NUTS {
        private final String id = "Nuts";

        @Override
        public String toString() {
            return id;
        }
    },
    CELERY {
        private final String id = "Celery";

        @Override
        public String toString() {
            return id;
        }
    },
    MUSTARD {
        private final String id = "Mustard";

        @Override
        public String toString() {
            return id;
        }
    },
    SESAMESEEDS {
        private final String id = "Sesame Seeds";

        @Override
        public String toString() {
            return id;
        }
    },
    SULPHURDIOXIDESULPHITES {
        private final String id = "Sulphur Dioxide and Sulphites";

        @Override
        public String toString() {
            return id;
        }
    },
    LUPIN {
        private final String id = "Lupin";

        @Override
        public String toString() {
            return id;
        }
    },
    MOLLUSCS {
        private final String id = "Molluscs";

        @Override
        public String toString() {
            return id;
        }
    };

    @Override
    public abstract String toString();

    public static Iterable<Allergen> allergens() {
        return Arrays.asList(Allergen.values());
    }

}
