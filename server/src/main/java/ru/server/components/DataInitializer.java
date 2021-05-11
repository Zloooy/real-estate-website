package ru.server.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.server.enums.Authority;
import ru.server.models.*;
import ru.server.repositories.*;
import ru.server.enums.UserRole;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.*;
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
    private IMetroRepository metroRepository;
    @Autowired
    private IDistrictRepository districtRepository;
    @Autowired
    private IContactsRepository contactsRepository;
    @Autowired
    private IAddressRepository addressRepository;
    @Autowired
    private IComplexRepository complexRepository;
    @Autowired
    private IFlatRepository flatRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private static final List<String>
            roleNames = Arrays.asList(ru.server.enums.UserRole.ADMIN, UserRole.CONTENT_MANAGER, UserRole.REALTOR, UserRole.CLIENT),
            authorityNames = Arrays.asList(Authority.CAN_ENTER),
            userLogins=Arrays.asList("admin", "manager"),
            userPasswords=Arrays.asList("password", "password"),
            userRoles=Arrays.asList(UserRole.ADMIN, UserRole.CONTENT_MANAGER),
            cityNames=Arrays.asList("Омск", "Санкт-Петербург", "Москва"),
            metroStationNames=Arrays.asList("Комендантский проспект", "Фрунзенская"),
            metroStationCities=Arrays.asList("Санкт-Петербург", "Санкт-Петербург"),
            metroStationColors=Arrays.asList("#7F0F7E","#007DCC"),
            contactNames = Arrays.asList("Иван Иванович Иванов"),
            contactPhones = Arrays.asList("222-32-32"),
            contactEmails = Arrays.asList("test@test.ru"),
            contactPhotos = Arrays.asList("http://вашнадежныйриелтор.рф/upload/photos/8e/8eda439d3124bb996fb4b1ab28d11c1b.jpg"),
            districtNames = Arrays.asList("Приморский", "Адмиралтейский"),
            districtCities = Arrays.asList("Санкт-Петербург", "Санкт-Петербург"),
            addressStreets = Arrays.asList("Плесецкая ул.", "Московский пр."),
            addressHouses = Arrays.asList("д. 2", "д. 65"),
            addressCities = Arrays.asList("Санкт-Петербург", "Санкт-Петербург"),
            addressDistrictNames = Arrays.asList("Приморский", "Адмиралтейский"),
            addressMetros = Arrays.asList("Комендантский проспект", "Фрунзенская"),
            complexAuthors = Arrays.asList("manager", "manager"),
            complexNames = Arrays.asList("Ultra City", "LEGENDA Московский 65"),
            complexAddresses = Arrays.asList("Плесецкая ул.", "Московский пр."),
            complexImages = Arrays.asList(
                    "https://get.pxhere.com/photo/city-metropolitan-area-architecture-urban-area-landmark-commercial-building-metropolis-tower-block-building-condominium-daytime-blue-skyscraper-mixed-use-human-settlement-sky-corporate-headquarters-tower-facade-headquarters-real-estate-apartment-reflection-tree-downtown-neighbourhood-residential-area-glass-symmetry-house-1612790.jpg",
                    "https://img2.goodfon.ru/wallpaper/nbig/7/7a/zdanie-4-bashni-w-proekt.jpg"
            ),
            complexComments = Arrays.asList("«Ultra City» — великолепный проект от компании «Северный город» в Приморском районе Санкт-Петербурга. Ultra City строится в соответствии с концепцией 3Е, которая подразумевает гармоничное сочетание экономики, эргономики, а также экологии. Территория закрыта. Круглосуточную охрану осуществляет служба безопасности. Камеры установлены в парадных, на въездах, во дворе и по периметру, управление ими доступно со смартфона.",
                    "Первый проект бизнес-класса от LEGENDA - это:\n" +
                            "\n" +
                            "    расположение на пересечении Московского проспекта и набережной Обводного канала\n" +
                            "    неоклассический стиль\n" +
                            "    4 корпуса с переменной этажностью до 8 этажей, объединяющих квартиры, коммерческие помещения на первом этаже, а также подземный паркинг на 570 машиномест\n" +
                            "    новая линейка планировочных и технических решений, отвечающая уровню недвижимости высокого класса двухуровневые квартиры, квартиры с двойным светом, а также квартиры с террасами\n" +
                            "    отдельные кладовые — помещения, которые расположатся на техническом этаже \n" +
                            "    в одном из корпусов встроенный детский сад на 110 мест"),
            complexContactNames = Arrays.asList("Иван Иванович Иванов","Иван Иванович Иванов"),
            flatImages = Arrays.asList("https://i.pinimg.com/originals/73/ab/98/73ab981e0fde9cf235492cf35a2bd2b5.jpg",
                    "https://i.ytimg.com/vi/6X8Bhjz8-uc/maxresdefault.jpg"
            ),
            flatComplexNames = Arrays.asList("Ultra City", "LEGENDA Московский 65"),
            flatAbout = Arrays.asList("Теытоое оопимание 1", "Тестовое описание 2");
            private static final Calendar workCalendar = new GregorianCalendar();
            private static final List<Date> complexDeliveryDates = Arrays.asList(Date.from(LocalDate.of(2022,1,1).atStartOfDay().toInstant(ZoneOffset.UTC)),Date.from(LocalDate.of(2025,1,1).atStartOfDay().toInstant(ZoneOffset.UTC)));
            private static final List<Complex.EstateCategory> complexEstateCategories = Arrays.asList(Complex.EstateCategory.NEW, Complex.EstateCategory.NEW);
            private static final List<Complex.EstateStatus> complexEstateStatuses = Arrays.asList(Complex.EstateStatus.ACCEPTED, Complex.EstateStatus.ACCEPTED);
            private final List<Boolean> complexEstateAdvertized = Arrays.asList(true, true);
            private static final List<Integer>
                    complexPrices = Arrays.asList(152709,289800),
                    complexSpaces = Arrays.asList(2,3),
                    complexAmountsOfRooms=Arrays.asList(2,3),
                    flatNumbersOfRooms=Arrays.asList(2,3),
                    flatFloors=Arrays.asList(2,3);
            private static final List<Flat.EstateType> flatEstateTypes = Arrays.asList(Flat.EstateType.FLAT,  Flat.EstateType.FLAT);
            private static final List<Double> flatPrices = Arrays.asList(100500.00,  360600.00);
            private static final List<Float> flatSquares= Arrays.asList(80f, 100f);
    private static final List<Set<String>> authorityNameGroups = Arrays.asList(Set.of(Authority.CAN_ENTER), new HashSet<>(), new HashSet<>(), new HashSet<>());
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
        addMetros();
        addDistricts();
        addAddresses();
        addContacts();
        System.out.println("ADDING COMPLEXES");
        addComplexes();
        addFlats();
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
    private void addMetros() {
        new ListInserter<Metro>().insert(
                metroRepository,
                (int i)-> new Metro(metroStationNames.get(i), metroStationColors.get(i), cityRepository.findByName(metroStationCities.get(i)).orElse(null)),
                metroStationNames.size()
        );
    }
    private void addDistricts() {
        new ListInserter<District>().insert(
                districtRepository,
                (int i)-> new District(districtNames.get(i), cityRepository.findByName(districtCities.get(i)).orElse(null)),
                districtNames.size()
        );
    }
    private void addContacts() {
        new ListInserter<Contacts>().insert(
                contactsRepository,
                (int i)-> new Contacts(contactNames.get(i), contactPhones.get(i), contactEmails.get(i), contactPhotos.get(i)),
                contactNames.size()
        );
    }
    private void addAddresses() {
        new ListInserter<Address>().insert(
                addressRepository,
                (int i)-> new Address(addressStreets.get(i), addressHouses.get(i), districtRepository.findByName(addressDistrictNames.get(i)).orElse(null), cityRepository.findByName(addressCities.get(i)).orElse(null), metroRepository.findByName(addressMetros.get(i)).orElse(null)),
                addressStreets.size()
        );
    }
    private void addComplexes() {
        new ListInserter<Complex>().insert(
                complexRepository,
                (int i)->new Complex(userRepository.findByUsername(complexAuthors.get(i)), complexNames.get(i), addressRepository.findByStreet(complexAddresses.get(i)).orElse(null), complexAmountsOfRooms.get(i), complexPrices.get(i), complexSpaces.get(i), complexImages.get(i), complexDeliveryDates.get(i), complexComments.get(i), contactsRepository.getByName("Иван Иванович Иванов").orElse(null), complexEstateStatuses.get(i), complexEstateCategories.get(i), complexEstateAdvertized.get(i)),
                complexNames.size()
        );
    }
    private void addFlats(){
        new ListInserter<Flat>().insert(
                flatRepository,
                (int i)-> new Flat(flatImages.get(i), complexRepository.findByName(flatComplexNames.get(i)).orElse(null), flatAbout.get(i), flatPrices.get(i), flatNumbersOfRooms.get(i), flatEstateTypes.get(i), flatSquares.get(i), flatFloors.get(i)),
                flatImages.size()
        );
    }
}
