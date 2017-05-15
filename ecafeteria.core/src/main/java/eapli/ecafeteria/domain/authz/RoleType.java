package eapli.ecafeteria.domain.authz;

public enum RoleType {
    CAFETERIA_USER, // utente
    ADMIN, KITCHEN_MANAGER, MENU_MANAGER, CASHIER;

    /**
     * get available role types for user adding
     *
     * @return
     */
    public static RoleType[] nonUserValues() {
        return new RoleType[]{ADMIN, KITCHEN_MANAGER, MENU_MANAGER, CASHIER};
    }
}
