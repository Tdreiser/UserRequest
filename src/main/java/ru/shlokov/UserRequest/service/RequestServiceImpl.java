package ru.shlokov.UserRequest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.shlokov.UserRequest.model.Request;
import ru.shlokov.UserRequest.model.User;
import ru.shlokov.UserRequest.repository.RequestRepository;
import ru.shlokov.UserRequest.model.enums.RequestState;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Shlokov Andrey
 */
@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestRepository repository;

    @Override
    public boolean create(String text, String user) {
        if (text == null || user == null)
            return false;
        repository.save(new Request(text, user));
        return true;
    }

    @Override
    public boolean changeState(Request request, RequestState state) {
        if (request != null && state != null) {
            request.setState(state);
            return true;
        }
        return false;
    }


    @Override
    public Request show(int id) {
        return repository.getReferenceById(id);
    }

    @Override
    public Request edit(Request request) {
        return repository.saveAndFlush(request);
    }

    @Override
    public List<Request> showAllSorted(String user) {
        return repository.findAllByAuthor(user).stream().sorted(Comparator.comparing(Request::getDateOfCreation)).collect(Collectors.toList());
    }

    @Override
    public List<Request> showAllSorted() {
        return repository.findAll(Sort.by("dateOfCreation"));
    }
}
