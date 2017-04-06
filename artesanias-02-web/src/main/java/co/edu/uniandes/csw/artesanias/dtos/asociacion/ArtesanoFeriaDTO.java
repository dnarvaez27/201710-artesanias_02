package co.edu.uniandes.csw.artesanias.dtos.asociacion;

import co.edu.uniandes.csw.artesanias.dtos.ArtesanoDTO;
import co.edu.uniandes.csw.artesanias.dtos.FeriaDTO;
import co.edu.uniandes.csw.artesanias.dtos.StandDTO;
import co.edu.uniandes.csw.artesanias.entities.asociaciones.ArtesanoFeriaAssociation;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
public class ArtesanoFeriaDTO implements Serializable {

    private Long id;

    private FeriaDTO feria;

    private StandDTO stand;

    private ArtesanoDTO artesano;

    private Date fecha;

    public ArtesanoFeriaDTO() {
    }

    /**
     * Builds an ArtesanoFeriaDTO by the fields from the
     * ArtesanoFeriaAssociation given
     *
     * @param entity ArtesanoFeriaAssociation to fill up the ArtesanoFeriaDTO
     */
    public ArtesanoFeriaDTO(ArtesanoFeriaAssociation entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.feria = new FeriaDTO(entity.getFeria());
            this.stand = new StandDTO(entity.getStand());
            this.artesano = new ArtesanoDTO(entity.getArtesano());
            this.fecha = entity.getFecha();
        }
    }

    /**
     * Retrieves the id of the ArtesanoFeriaDTO
     *
     * @return The id of the ArtesanoFeriaDTO
     */
    public Long getId() {
        return id;
    }

    /**
     * Updates the id of the ArtesanoFeriaDTO by the one given by parameter
     *
     * @param id The new id of the ArtesanoFeriaDTO
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves a ArtesanoFeriaAssociation with the fields of this
     * ArtesanoFeriaDTO
     */
    public ArtesanoFeriaAssociation toEntity() {
        ArtesanoFeriaAssociation entity = new ArtesanoFeriaAssociation();
        entity.setId(this.id);
        entity.setFeria(this.feria.toEntity());
        entity.setStand(this.stand.toEntity());
        entity.setArtesano(this.artesano.toEntity());
        entity.setFecha(this.fecha);
        return entity;
    }

    /**
     * Retrieves the feria of the ArtesanoFeriaDTO
     *
     * @return The feria of the ArtesanoFeriaDTO
     */
    public FeriaDTO getFeria() {
        return feria;
    }

    /**
     * Updates the feria of the ArtesanoFeriaDTO by the one given by parameter
     *
     * @param feria The new feria of the ArtesanoFeriaDTO
     */
    public void setFeria(FeriaDTO feria) {
        this.feria = feria;
    }

    /**
     * Retrieves the stand of the ArtesanoFeriaDTO
     *
     * @return The stand of the ArtesanoFeriaDTO
     */
    public StandDTO getStand() {
        return stand;
    }

    /**
     * Updates the stand of the ArtesanoFeriaDTO by the one given by parameter
     *
     * @param stand The new stand of the ArtesanoFeriaDTO
     */
    public void setStand(StandDTO stand) {
        this.stand = stand;
    }

    /**
     * Retrieves the artesano of the ArtesanoFeriaDTO
     *
     * @return The artesano of the ArtesanoFeriaDTO
     */
    public ArtesanoDTO getArtesano() {
        return artesano;
    }

    /**
     * Updates the artesano of the ArtesanoFeriaDTO by the one given by
     * parameter
     *
     * @param artesano The new artesano of the ArtesanoFeriaDTO
     */
    public void setArtesano(ArtesanoDTO artesano) {
        this.artesano = artesano;
    }

    /**
     * Retrieves the fecha of the ArtesanoFeriaDTO
     *
     * @return The fecha of the ArtesanoFeriaDTO
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Updates the fecha of the ArtesanoFeriaDTO by the one given by parameter
     *
     * @param fecha The new fecha of the ArtesanoFeriaDTO
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
