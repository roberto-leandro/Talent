package cr.talent.model;

import javax.persistence.*;

/**
 * Class that represents a Technical Resource's position within the Talent system.
 * It contains the capability level related to the position and the information inherited from
 * {@link cr.talent.model.BasicEntity} class.
 *
 * @author Elías Calderón
 */
@MappedSuperclass
public abstract class Position extends BasicEntity {

    /**
     * The capability and level related to the position. The organization's capability can be found as an attribute
     * in {@link cr.talent.model.CapabilityLevel}
     */
    @ManyToOne
    @JoinColumn(name = "org_capability_level_id", nullable = false)
    private CapabilityLevel capabilityLevel;

    /**
     * The technical resource that has the position
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "technical_resource_id")
    private TechnicalResource technicalResource;

    public Position(){}

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof Position){
            Position position = (Position) o;
            result = (this.capabilityLevel == null ?
                    position.getCapabilityLevel() == null :
                    this.capabilityLevel.equals(position.getCapabilityLevel()));
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        final int prime = 23;
        result = prime * result + (this.capabilityLevel == null ? 0 : this.capabilityLevel.hashCode());
        return result;
    }

    public CapabilityLevel getCapabilityLevel() {
        return capabilityLevel;
    }

    public void setCapabilityLevel(CapabilityLevel capabilityLevel) {
        this.capabilityLevel = capabilityLevel;
    }

    public TechnicalResource getTechnicalResource() {
        return technicalResource;
    }

    public void setTechnicalResource(TechnicalResource technicalResource) {
        this.technicalResource = technicalResource;
    }
}