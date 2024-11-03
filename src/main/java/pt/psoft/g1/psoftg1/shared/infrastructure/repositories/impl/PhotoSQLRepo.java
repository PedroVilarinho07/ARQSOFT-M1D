package pt.psoft.g1.psoftg1.shared.infrastructure.repositories.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import pt.psoft.g1.psoftg1.shared.repositories.PhotoRepository;


@Repository
@Profile("sql")
public class PhotoSQLRepo implements PhotoRepository {

    @Override
    public void deleteByPhotoFile(String photoFile) {

    }
}
