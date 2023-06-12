package ru.shlokov.UserRequest.service;

import ru.shlokov.UserRequest.model.Request;
import ru.shlokov.UserRequest.model.User;
import ru.shlokov.UserRequest.model.enums.RequestState;

import java.util.List;

/**
 * @author Shlokov Andrey
 */
public interface RequestService {

    boolean create(String text,String user);

    boolean changeState(Request request, RequestState state);

    Request show(int id);

    Request edit(Request request);

    List<Request> showAllSorted(String user);
    List<Request> showAllSorted();

}
