package ru.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.server.models.User;
import ru.server.repositories.IUserRepository;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    IUserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User result = repository.findByUsername(s);
        if (result !=null) return repository.findByUsername(s);
        else throw new UsernameNotFoundException("Пользователь не найден");
    }
    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }
    public boolean save(User u) {
        try {
            repository.save(u);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }
    public List<User> getAll() {
        return repository.findAll();
    }
}
