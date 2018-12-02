package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository,PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initdata();
    }

    private void initdata(){
        Author eric = new Author("Eric" ,"Evans");
        Publisher hcollins = new Publisher("Harper Collins","some address");
        Book ddd = new Book("Domain Driven Design","1234");
        eric.getBooks().add(ddd);
        ddd.setPublisher(hcollins);
        ddd.getAuthors().add(eric);


        publisherRepository.save(hcollins);
        authorRepository.save(eric);
        bookRepository.save(ddd);


        Author rod = new Author("Rod" ,"Johnson");
        Publisher worx = new Publisher("worx","some worx address");
        Book noEJB = new Book("J2EE Development without EJB","23444");
        rod.getBooks().add(noEJB);
        noEJB.setPublisher(worx);
        noEJB.getAuthors().add(rod);

        publisherRepository.save(worx);
        authorRepository.save(rod);
        bookRepository.save(noEJB);

    }
}
