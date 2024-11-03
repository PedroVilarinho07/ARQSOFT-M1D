/*
package pt.psoft.g1.psoftg1.shared.infrastructure.repositories.impl;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pt.psoft.g1.psoftg1.shared.model.ForbiddenName;
import pt.psoft.g1.psoftg1.shared.repositories.ForbiddenNameRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataForbiddenNameRepository extends ForbiddenNameRepository, CrudRepository<ForbiddenName, Long> {

    List<ForbiddenName> findByForbiddenNameIsContained(String pat);

    Optional<ForbiddenName> findByForbiddenName(String forbiddenName);

    int deleteForbiddenName(String forbiddenName);

}

 */
