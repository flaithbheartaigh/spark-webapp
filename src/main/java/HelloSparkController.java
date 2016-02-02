/**
 * Created by Administrator on 1/31/2016.
 */
import org.apache.commons.beanutils.BeanUtils;
import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;
import spark.Request;
import spark.Response;
import spark.Route;

import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.*;

import static spark.Spark.*;

public class HelloSparkController {
    public static void main(String[] args){

        // serve static files from /www in resources
        staticFileLocation("/www");

        // setup the database model
        Sql2o sql2o = new Sql2o("jdbc:h2:~/test", "sa", "");
        Sql2oModel model = new Sql2oModel(sql2o);

        // url /hello
        // simple response return
        get("/hello", (req, res) -> "Hello Spark!");

        // url /test-view
        // simple template "test-view.ftl"
        get("/test-view", (req, res) ->{
            Map<String, Object> viewModel = new HashMap<>();
            viewModel.put("message", "Hello Spark!");

            return new ModelAndView(viewModel, "test-view.ftl");
        },new FreeMarkerEngine());

        // url /books
        // data bound template
        get("/books", (req, res) ->{
            Map<String, Object> viewModel = new HashMap<>();
            viewModel.put("message", "Books");

            List<Book> books = model.getBooks();
            viewModel.put("books", books);
            return new ModelAndView(viewModel, "books.ftl");

        },new FreeMarkerEngine());

        // url /books/new
        // initial get of input form for new book in database
        get("/books/new", (req, res) -> {
            Map<String, Object> viewModel = new HashMap<>();
            viewModel.put("message", "Add Book");
            return new ModelAndView(viewModel, "new-book.ftl");
        },new FreeMarkerEngine());

        // url /books/new
        // subsequent post of new book data back to the
        // server to get saved back to the database
        post("/books/new", (req, res) -> {
            // todo: add response book to database, use custom viewModel class
            MultiMap<String> params = new MultiMap<String>();
            UrlEncoded.decodeTo(req.body(), params, "UTF-8");
            NewBookEditModel editModel = new NewBookEditModel();
            BeanUtils.populate(editModel, params);

            long id = model.createBook(editModel.getTitle(), editModel.getAuthor(), editModel.getImageUrl());
            res.redirect("/books");
            halt();
            return id;// ?? json & rest??
        });

        get("/books/delete/:id", (req, res)->{
            int id = Integer.parseInt(req.params().get(":id"));
            model.deleteBook(id);

            res.redirect("/books");
            halt();

            return null;
        });

        // url /spi-test
        // json testbed page "spi-test.ftl"
        get("/spi-test", (req, res) ->{
            Map<String, Object> viewModel = new HashMap<>();
            viewModel.put("message", "SPI Test Page");

            List<Book> books = model.getBooks();
            viewModel.put("books", books);

            return new ModelAndView(viewModel, "spi-test.ftl");
        },new FreeMarkerEngine());

        // Additional JSON Services?
    }
}

