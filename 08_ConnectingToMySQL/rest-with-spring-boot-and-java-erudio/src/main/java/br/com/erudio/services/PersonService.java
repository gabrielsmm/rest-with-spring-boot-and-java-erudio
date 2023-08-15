package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonService {
	
	private PersonRepository repository;
	
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	public PersonService(PersonRepository repository) {
		this.repository = repository;
	}
	
	public List<Person> findAll() {
		logger.info("Finding all people!");
		return repository.findAll();
	}

	public Person findById(Long id) {
		logger.info("Finding one person!");
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
	}
	
	public Person create(Person person) {
		logger.info("Creating one person!");
		return repository.save(person);
	}
	
	public Person update(Person person) {
		Person entity = findById(person.getId());
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		logger.info("Updating person!");
		return repository.save(entity);
	}
	
	public void delete(Long id) {
		Person entity = findById(id);
		
		logger.info("Deleting one person!");
		repository.delete(entity);
	}
	
}
