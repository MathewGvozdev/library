package com.mathewgv.library.controller.command;

import com.mathewgv.library.controller.command.impl.Home;
import com.mathewgv.library.controller.command.impl.admin.*;
import com.mathewgv.library.controller.command.impl.user.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private static final CommandProvider INSTANCE = new CommandProvider();

    private final Map<CommandName, Command> repository = new HashMap<>();

    public CommandProvider() {
        repository.put(CommandName.HOME, new Home());

        repository.put(CommandName.ADD_BOOK, new AddBook());
        repository.put(CommandName.DELETE_BOOK, new DeleteBook());
        repository.put(CommandName.FIND_ALL_BOOK_METAS, new FindAllBookMetas());
        repository.put(CommandName.FIND_ALL_BOOK_METAS_BY_FILTER, new FindAllBookMetasByFilter());
        repository.put(CommandName.FIND_ALL_BOOKS, new FindAllBooks());
        repository.put(CommandName.FIND_ANY_BOOK, new FindAnyBook());
        repository.put(CommandName.FIND_BOOK_BY_ID, new FindBookById());

        repository.put(CommandName.LOGIN, new Login());
        repository.put(CommandName.LOGOUT, new Logout());
        repository.put(CommandName.REGISTRATION, new Registration());
        repository.put(CommandName.CHANGE_LOCALE, new ChangeLocale());

        repository.put(CommandName.FIND_ALL_ORDERS, new FindAllOrders());
        repository.put(CommandName.FIND_USER_INFO, new FindUserInfo());
        repository.put(CommandName.MAKE_ORDER, new MakeOrder());
        repository.put(CommandName.UPDATE_ORDER, new UpdateOrder());
        repository.put(CommandName.UPDATE_USER_INFO, new UpdateUserInfo());
        repository.put(CommandName.ADMIN_MENU, new OpenAdminMenu());
        repository.put(CommandName.FIND_ALL_USERS, new FindAllUsers());
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

    public static CommandProvider getInstance() {
        return INSTANCE;
    }
}
