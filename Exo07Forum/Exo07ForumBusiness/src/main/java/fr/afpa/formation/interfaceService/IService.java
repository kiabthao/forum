package fr.afpa.formation.interfaceService;

import java.util.List;

/**
 * 	<b>FONCTIONNALITES METIIER RELATIVES AUX ENTITES DE LA BASE DE DONNES</b>
 * 
 * @author 34011-65-04
 *
 * @param <T> Entitée
 * @param <E> Exception
 */
public interface IService<T, E extends Exception> {

	/**
	 * return a list of all the entities in the DB
	 * 
	 * @return
	 * @throws E
	 */
	public abstract List<T> list() throws E;

//	public T save(T t) throws E;
	
	/**
	* <b>CREER UNE ENTITE DANS LES DONNEES PERSISTANTES.</b><br/>
	*
	* @param t L'entité à créer.
	* @return L'entité créée (son attribut 'id' est alimenté).
	* @throws E Erreurs possibles : <br/>
	* (1.)L'entité fournie est vide.<br/>
	* (2.)L'entité fournie possède un id non null.<br/>
	*/
	public abstract T create(T t) throws E;

	/**
	 * 
	 * @param t
	 * @return
	 * @throws E
	 */
	public abstract T update(T t) throws E;

	/**
	 * 
	 * @param t
	 * @return
	 * @throws E
	 */
	public List<T> createAll(List<T> t) throws E;

	/**
	 * 
	 * @param t
	 * @return
	 * @throws E
	 */
	public List<T> updateAll(List<T> t) throws E;

//	public List<T> saveAll(List<T> species) throws E;

	/**
	 * 
	 * @param t
	 * @throws E
	 */
	public void delete(T t) throws E;

	/**
	 * 
	 * @param species
	 * @throws E
	 */
	public void deleteAll(List<T> t) throws E;

	/**
	 * 
	 * @param id
	 * @throws E
	 */
	public void deleteById(Long id) throws E;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws E
	 */
	public T findById(Long id) throws E;

}
