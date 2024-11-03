package pt.psoft.g1.psoftg1.authormanagement.mappers;

import pt.psoft.g1.psoftg1.authormanagement.dataModel.sql.AuthorDataModel;
import pt.psoft.g1.psoftg1.authormanagement.factories.AuthorFactory;
import pt.psoft.g1.psoftg1.authormanagement.model.Author;

import java.util.Optional;

public class AuthorSQLMapper {

    public static Author convertToDomainAuthor(AuthorFactory authorFactory, AuthorDataModel dataModel) {

        Author author = authorFactory.newAuthor(
                dataModel.getName(),
                dataModel.getBio(),
                dataModel.getPhotoURI()
        );
        author.setAuthorNumber(dataModel.getAuthorNumber());
        return author;
    }

}
