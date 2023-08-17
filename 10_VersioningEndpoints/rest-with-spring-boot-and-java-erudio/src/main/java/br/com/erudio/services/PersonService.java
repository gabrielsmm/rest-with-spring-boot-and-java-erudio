package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.mapper.custom.PersonMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonService {
	
	private PersonRepository repository;
	private PersonMapper mapper;
	
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	public PersonService(PersonRepository repository, PersonMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	public List<PersonVO> findAll() {
		logger.info("Finding all people!");
		return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
	}

	public PersonVO findById(Long id) {
		logger.info("Finding one person!");
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		return DozerMapper.parseObject(entity, PersonVO.class);
	}
	
	public PersonVO create(PersonVO person) {
		logger.info("Creating one person!");
		
		var entity = DozerMapper.parseObject(person, Person.class);
		return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
	}
	
	public PersonVOV2 createV2(PersonVOV2 person) {
		logger.info("Creating one person with V2!");
		
		var entity = mapper.convertVoToEntity(person);
		return mapper.convertEntityToVo(repository.save(entity));
	}
	
	public PersonVO update(PersonVO person) {
		logger.info("Updating person!");
		Person entity = DozerMapper.parseObject(findById(person.getId()), Person.class);
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
	}
	
	public void delete(Long id) {
		logger.info("Deleting one person!");
		
		Person entity = DozerMapper.parseObject(findById(id), Person.class);
		repository.delete(entity);
	}
	
}
