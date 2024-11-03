/*
package pt.psoft.g1.psoftg1.authormanagement.mappers;

import pt.psoft.g1.psoftg1.authormanagement.dataModel.AuthorDataModelMongo;
import pt.psoft.g1.psoftg1.authormanagement.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMongoMapper {

    public AuthorDataModelMongo toDataModel(Author author) {
        return new AuthorDataModelMongo(
                author.getAuthorNumber(),
                author.getName(),
                author.getBio(),
                author.getPhoto() != null ? author.getPhoto().getPhotoFile() : null
        );
    }

    public Author toDomainModel(AuthorDataModelMongo dataModel) {
        return new Author(
                dataModel.getName(),
                dataModel.getBio(),
                dataModel.getPhotoURI()
        );
    }
}
*/