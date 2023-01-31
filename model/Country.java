package br.gov.go.sefaz.hrs.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * <b>Secretaria de Estado da Fazenda de Goiás</b><br/>
 * Supervisão de Arquitetura e Prospecção Tecnológica<br/>
 * <i>SEFAZ-GO JavaEE Sample Project Archetype</i><br/><br/>
 * 
 * Entity to represent a <b>Country</b>.
 * 
 * @version 
 * <ul>
 * <li>$LastChangedRevision: 682 $</li>
 * <li>$LastChangedBy: SEFAZGO\thiago-sc $</li>
 * <li>$LastChangedDate: 2017-03-30 15:25:43 -0300 (Thu, 30 Mar 2017) $</li>
 * </ul>
 * 
 * @author Thiago-SC and Renato-RS
 * 
 */
@Entity
@Table(name = "COUNTRIES")
@Cacheable
public class Country implements Serializable {

	private static final long serialVersionUID = -1229471069266027525L;
	
	private String id;
	private String name;
	private Region region;

	/**
	 * @return Country identifier.
	 */
	@Id
	@Column(name = "COUNTRY_ID")
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return Country name.
	 */
	@Column(name = "COUNTRY_NAME")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return Country region.
	 */
	@ManyToOne
	@JoinColumn(name = "REGION_ID", nullable = false)
	public Region getRegion() {
		return region;
	}
	
	public void setRegion(Region region) {
		this.region = region;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}