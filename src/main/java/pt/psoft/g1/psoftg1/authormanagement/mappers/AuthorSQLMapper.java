package pt.psoft.g1.psoftg1.authormanagement.mappers;

import pt.psoft.g1.psoftg1.authormanagement.dataModel.sql.AuthorDataModel;
import pt.psoft.g1.psoftg1.authormanagement.factories.AuthorFactory;
import pt.psoft.g1.psoftg1.authormanagement.model.Author;

import java.util.ArrayList;
import java.util.List;

public class AuthorSQLMapper {

    public static AuthorDataModel convertToDataModelAuthor(Author author) {
        String photo;
        try {
            photo = author.getPhoto().getPhotoFile();
        } catch (Exception ignored) {photo = null;}

        return new AuthorDataModel(
                author.getAuthorNumber().toString(),
                author.getName(),
                author.getBio(),
                photo
        );
    }

    public static Author convertToDomainAuthor(AuthorFactory authorFactory, AuthorDataModel dataModel) {

        Author author = authorFactory.newAuthor(
                dataModel.getName(),
                dataModel.getBio(),
                dataModel.getPhotoURI()
        );
        author.setAuthorNumber(dataModel.getAuthorNumber());
        return author;
    }

    public static List<Author> convertListToDomainAuthor(AuthorFactory authorFactory, List<AuthorDataModel> authors) {
        List<Author> newAuthors = new ArrayList<>();
        for (AuthorDataModel authorDataModel : authors) {
            Author author = convertToDomainAuthor(authorFactory, authorDataModel);  // Assuming newAuthor method accepts AuthorDataModel and returns Author
            newAuthors.add(author);
        }
        return newAuthors;
    }

}
