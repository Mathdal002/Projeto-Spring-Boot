package br.gov.go.sefaz.hrs.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * <b>Secretaria de Estado da Fazenda de Goiás</b></br>
 * Supervisão de Arquitetura e Prospecção Tecnológica</br>
 * <i>SEFAZ-GO JavaEE Sample Project Archetype</i></br></br>
 * 
 * Entity to represent a <b>Region</b>.
 * 
 * @version 
 * <ul>
 * <li>$LastChangedRevision: 751 $</li>
 * <li>$LastChangedBy: SEFAZGO\renato-rs $</li>
 * <li>$LastChangedDate: 2017-05-29 17:30:13 -0300 (Mon, 29 May 2017) $</li>
 * </ul>
 * 
 * @author Thiago-SC and Renato-RS
 * 
 */
@Entity
@Table(name = "REGIONS")
@Cacheable
public class Region implements Serializable {

	private static final long serialVersionUID = 4034051461252033514L;
	
	private Integer id;
	private String name;
	private List<Country> countries;
	
	/**
	 * @return Region identifier.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REGION_ID")
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @return Region name.
	 */
	@Column(name = "REGION_NAME")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Countries located in this region.
	 */
	@OneToMany(mappedBy="region", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
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
		Region other = (Region) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}