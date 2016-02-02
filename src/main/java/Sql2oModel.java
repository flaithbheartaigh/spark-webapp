import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

/**
 * Created by Administrator on 2/1/2016.
 */
public class Sql2oModel {

    private Sql2o sql2o;

    public Sql2oModel(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    public long createBook(NewBookEditModel viewModel){
        return createBook(viewModel.getTitle(), viewModel.getAuthor(), viewModel.getImageUrl());
    }

    public long createBook(String title, String author, String imageUrl){
        try(Connection conn=sql2o.beginTransaction()){
            long id = conn.createQuery("insert into books(title, author, imageUrl) values(:title, :author, :imageUrl)", true)
                    .addParameter("title", title)
                    .addParameter("author", author)
                    .addParameter("imageUrl", imageUrl)
                    .executeUpdate().getKey(Long.class);

            // commit changes
            conn.commit();

            return id;
        }
    }

    public void deleteBook(int id){
        try(Connection conn=sql2o.beginTransaction()){
            conn.createQuery("delete from books where bookID=:id")
                    .addParameter("id", id)
                    .executeUpdate();

            // commit changes
            conn.commit();
        }
    }

    public List<Book> getBooks(){
        try(Connection conn = sql2o.beginTransaction()){
            return conn.createQuery("select * from books").executeAndFetch(Book.class);
        }
    }
}
