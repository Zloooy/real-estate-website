package ru.server.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.server.enums.Authority;
import ru.server.models.City;
import ru.server.models.User;
import ru.server.repositories.IAuthorityRepository;
import ru.server.repositories.ICityRepository;
import ru.server.repositories.IRoleRepository;
import ru.server.repositories.IUserRepository;
import ru.server.enums.UserRole;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Component
public class DataInitializer implements ApplicationRunner {
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IAuthorityRepository authorityRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ICityRepository cityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private static final List<String>
            roleNames = Arrays.asList(ru.server.enums.UserRole.ADMIN, UserRole.CONTENT_MANAGER, UserRole.REALTOR, UserRole.CLIENT),
            authorityNames = Arrays.asList(Authority.CAN_ENTER),
            userLogins=Arrays.asList("admin", "manager"),
            userPasswords=Arrays.asList("password", "password"),
            userRoles=Arrays.asList(UserRole.ADMIN, UserRole.CONTENT_MANAGER),
            cityNames=Arrays.asList("Омск", "Санкт-Петербург");
    private static final List<Set<String>> authorityNameGroups = Arrays.asList(Set.of(Authority.CAN_ENTER), new HashSet<>(), new HashSet<>(), new HashSet<>());
    private static List<Set<User.Role.Authority>> authorityGroups;
    private static class ListInserter<T> {
        public <R extends CrudRepository<T, Long>> void insert(R repo, LongFunction<T> builder, long i) {
            repo.saveAll(LongStream.range(0, i).mapToObj(builder).collect(Collectors.toList()));
        }
        public <R extends CrudRepository<T, Long>> void insert(R repo, IntFunction<T> builder, int i) {
            repo.saveAll(IntStream.range(0, i).mapToObj(builder).collect(Collectors.toList()));
        }
        public <I, R extends CrudRepository<T, Long>> void insertFromList(R repo, Function<I, T> builder, List<I> list) {
           repo.saveAll(list.stream().map(builder).collect(Collectors.toList()));
        }
    }
    @Override
    public void run(ApplicationArguments args){
        addAuthorities();
        addRoles();
        assert roleRepository.getByName(UserRole.ADMIN).getAuthorities().size() > 0;
        addUsers();
        addCities();
    }
    private void addAuthorities(){
        new ListInserter<User.Role.Authority>().insertFromList(authorityRepository, User.Role.Authority::new, authorityNames);
    }
    private void addRoles() {
        new ListInserter<User.Role>().insert(
                roleRepository,
                (int i)->new User.Role(
                        roleNames.get(i),
                        authorityNameGroups.get(i).stream().map(authorityRepository::getByName).collect(Collectors.toSet())),
                roleNames.size()
        );
    }
    private void addUsers(){
        new ListInserter<User>().insert(
                userRepository,
                (int i)-> new User(userLogins.get(i), passwordEncoder.encode(userPasswords.get(i)), roleRepository.getByName(userRoles.get(i))),
                userLogins.size()
        );
    }
    private void addCities(){
        new ListInserter<City>().insert(
                cityRepository,
                (int i)-> new City(cityNames.get(i)),
                cityNames.size()
        );
    }
}
