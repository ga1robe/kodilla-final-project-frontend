package com.kodilla.servicefrontend;

import com.kodilla.books.domain.Book;
import com.kodilla.books.service.BookService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class BookForm extends VerticalLayout {
    private TextField title = new TextField("Title");
    private TextField author = new TextField("Author");
    private TextField publicationYear = new TextField("Publication year");
    private ComboBox<BookType> type = new ComboBox<>("Book type");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private MainView mainView;
    private BookService service = BookService.getInstance();
    private Binder<Book> binder = new Binder<Book>(Book.class);

    public BookForm(MainView mainView) {
        type.setItems(BookType.values());
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
        add(title, author, publicationYear, type, buttons);
        this.mainView = mainView;
    }

    private void save() {
        Book book = binder.getBean();
        service.save(book);
        mainView.refresh();
        setBook(null);
    }

    private void delete() {
        Book book = binder.getBean();
        service.delete(book);
        mainView.refresh();
        setBook(null);
    }

    public void setBook(Book book) {
        binder.setBean(book);
        if (book == null) {
            setVisible(false);
        } else {
            setVisible(true);
            title.focus();
        }
    }
}