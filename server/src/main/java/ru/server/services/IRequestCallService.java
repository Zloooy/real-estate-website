package ru.server.services;

import org.springframework.stereotype.Service;
import ru.server.data.RequestCallQuery;

@Service
public interface IRequestCallService {
    boolean sendMail(RequestCallQuery query);
}
