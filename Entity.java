interface Entity {
    /**
     * @return the current health of the entity
     */
    double getHealth();

    /**
     * @param health value to set the entity's health to
     * @throws IllegalArgumentException if the value isn't in the [0-100] range
     */
    void setHealth(double health);

    /**
     * @return the current attack of the entity
     */
    double getAttack();

    /**
     * @return the current defense of the entity
     */
    double getDefense();

    /**
     * @param other the entity to attack, but it can be the entity itself!
     */
    void attack(Entity other);

    /**
     * @return whether the health is greater than 0, ie the entity is alive
     */
    default boolean isAlive() {
        return getHealth() > 0.0;
    }
}
