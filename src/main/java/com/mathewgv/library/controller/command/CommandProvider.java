package com.mathewgv.library.controller.command;

import com.mathewgv.library.controller.command.impl.Home;
import com.mathewgv.library.controller.command.impl.book.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private final Map<CommandName, Command> repository = new HashMap<>();

    public CommandProvider() {
        repository.put(CommandName.ADD_BOOK, new AddBook());
        repository.put(CommandName.ADD_AUTHOR, new AddAuthor());
        repository.put(CommandName.ADD_BOOK_META, new AddBookMeta());
        repository.put(CommandName.ADD_GENRE, new AddGenre());
        repository.put(CommandName.ADD_PUBLISHER, new AddPublisher());
        repository.put(CommandName.DELETE_BOOK, new DeleteBook());
        repository.put(CommandName.FIND_ALL_BOOK_METAS, new FindAllBookMetas());
        repository.put(CommandName.FIND_ALL_BOOK_METAS_BY_FILTER, new FindAllBookMetasByFilter());
        repository.put(CommandName.FIND_ALL_BOOKS, new FindAllBooks());
        repository.put(CommandName.FIND_ANY_BOOK, new FindAnyBook());
        repository.put(CommandName.FIND_BOOK_BY_ID, new FindBookById());
        repository.put(CommandName.UPDATE_BOOK, new UpdateBook());
        repository.put(CommandName.HOME, new Home());
//        repository.put(CommandName.SIGN_IN, new SingIn());
//        repository.put(CommandName.REGISTRATION, new Register());
//        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }

    public Command getCommand(String name) {
        Command command;
        try {
            if (name == null) {
                command = repository.get(CommandName.HOME);
            } else {
                command = repository.get(CommandName.valueOf(name.toUpperCase()));
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}
