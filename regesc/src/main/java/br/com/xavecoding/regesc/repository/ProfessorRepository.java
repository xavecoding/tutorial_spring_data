package br.com.xavecoding.regesc.repository;

import br.com.xavecoding.regesc.orm.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Long> {
}
