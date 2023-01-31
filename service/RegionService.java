package br.gov.go.sefaz.hrs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.sefaz.hrs.model.Region;
import br.gov.go.sefaz.hrs.repository.jdbc.RegionJdbcRepository;
import br.gov.go.sefaz.hrs.repository.jpa.RegionJpaRepository;
import br.gov.go.sefaz.javaee.repository.history.jpa.HistoryJpa;

/**
 * <b>Secretaria de Estado da Fazenda de Goiás</b></br>
 * Supervisão de Arquitetura e Prospecção Tecnológica</br>
 * <i>SEFAZ-GO JavaEE Sample Project Archetype</i><br/><br/>
 * 
 * Region Service.
 * 
 * @author Thiago-SC and Renato-RS
 * 
 */
@Service
public class RegionService {

	@Autowired
	private RegionJpaRepository regionJpaRepository;
	
	@Autowired
	private RegionJdbcRepository regionJdbcRepository;
	
	/**
	 * Saves a Region.
	 * Inserts a new record or updates an existing region.
	 * 
	 * @param region Record to be saved.
	 */
	@HistoryJpa
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public Region save(String usuarioHistorico, Region region) {
		return this.regionJpaRepository.save(region);
	}

	/**
	 * Deletes a region by Id.
	 * 
	 * @param id Identification of the record to be deleted.
	 */
	@HistoryJpa
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void delete(String usuarioHistorico, Integer id) {
		this.regionJpaRepository.delete(id);
	}
	
	/**
	 * Returns whether a Region with the given id exists.
	 * @param id of Region
	 * @return true if a Region with the given id exists, false otherwise
	 */
	public boolean exists(Integer id) {
		return this.regionJpaRepository.exists(id);
	}
	
	/**
	 * Finds a Region by Id.
	 * 
	 * @param id Identification of the record to be retrieved.
	 * @return The Region record with the parameter specified.
	 */
	public Region find(Integer id) {
		return this.regionJpaRepository.findOne(id);
	}
	
	/**
	 * Finds a Region by Name.
	 * 
	 * @param name Name of the record to be retrieved.
	 * @return The Region record with the parameter specified.
	 */
	public List<Region> findByName(String name) {
		return this.regionJpaRepository.findByNameContainingIgnoreCase(name);
	}
	
	/**
	 * Finds all Regions.
	 * 
	 * @return All Regions available on database.
	 */
	public List<Region> findAll() {
		return this.regionJpaRepository.findAll();
	}
	
	/**
	 * Finds all Region.
	 * @param pageable to define the page information 
	 * @return A pageable Region available on database.
	 */
	public Page<Region> findAll(Pageable pageable) {
		return this.regionJpaRepository.findAll(pageable);
	}
	
	/**
	 * Finds all Region with JDBC Query.
	 * @return All Regions available on database.
	 */
	public List<Region> findAllJdbc() {
		return this.regionJdbcRepository.findAll();
	}
	
	/**
	 * Counts all Regions.
	 * 
	 * @return Number of Regions available on database.
	 */
	public Long count() {
		return this.regionJpaRepository.count();
	}
}