package br.gov.go.sefaz.hrs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.sefaz.hrs.model.Country;
import br.gov.go.sefaz.hrs.model.Region;
import br.gov.go.sefaz.hrs.repository.jpa.CountryJpaRepository;
import br.gov.go.sefaz.javaee.repository.history.jpa.HistoryJpa;

/**
 * <b>Secretaria de Estado da Fazenda de Goiás</b></br>
 * Supervisão de Arquitetura e Prospecção Tecnológica</br>
 * <i>SEFAZ-GO JavaEE Sample Project Archetype</i><br/><br/>
 * 
 * Country Service.
 * 
 * @author Thiago-SC and Renato-RS
 * 
 */
@Service
public class CountryService {

	@Autowired
	private CountryJpaRepository countryJpaRepository;
	
	/**
	 * Saves a Country.
	 * Inserts a new record or updates an existing country.
	 * 
	 * @param country Record to be saved.
	 */
	@HistoryJpa
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public Country save(String usuarioHistorico, Country country) {
		return this.countryJpaRepository.save(country);
	}

	/**
	 * Deletes a country by Id.
	 * 
	 * @param id Identification of the record to be deleted.
	 */
	@HistoryJpa
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void delete(String usuarioHistorico, String id) {
		this.countryJpaRepository.delete(id);
	}
	
	/**
	 * Returns whether a Country with the given id exists.
	 * @param id of Country
	 * @return true if a Country with the given id exists, false otherwise
	 */
	public boolean exists(String id){
		return this.countryJpaRepository.exists(id);
	}
	
	/**
	 * Finds a Country by Id.
	 * 
	 * @param id Identification of the record to be retrieved.
	 * @return The Country record with the parameter specified.
	 */
	public Country find(String id) {
		return this.countryJpaRepository.findOne(id);
	}
	
	/**
	 * Finds the Countries by Name.
	 * 
	 * @param name Name of the records to be retrieved.
	 * @return The Countries with the parameter specified.
	 */
	public List<Country> findByName(String name) {
		return this.countryJpaRepository.findByNameContainingIgnoreCase(name);
	}
	
	/**
	 * Finds the Countries by Region.
	 * 
	 * @param region Region of the records to be retrieved.
	 * @return The Countries with the parameter specified.
	 */
	public List<Country> findByRegion(Region region) {
		return this.countryJpaRepository.findByRegion(region);
	}
	
	/**
	 * Finds all Countries.
	 * 
	 * @return All Countries available on database.
	 */
	public List<Country> findAll() {
		return this.countryJpaRepository.findAll();
	}
	
	/**
	 * Finds all Countries.
	 * @param pageable to define the page information 
	 * @return A pageable Countries available on database.
	 */
	public Page<Country> findAll(Pageable pageable) {
		return this.countryJpaRepository.findAll(pageable);
	}
	
	/**
	 * Counts all Countries.
	 * 
	 * @return Number of Countries available on database.
	 */
	public Long count() {
		return this.countryJpaRepository.count();
	}
}