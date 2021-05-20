export interface Address {
  /** Город */
  city?: City;

  /** Район */
  district?: District;

  /** Номер дома */
  house?: string;

  /**
   * Униклальный идентификатор адреса
   * @format int64
   */
  id?: number;

  /** Ближайшая станция метро */
  metro?: Metro;

  /** Название улицы */
  street?: string;
}

export interface Article {
  /**
   * Уникальный идентификатор статьи блога
   * @format int64
   */
  id?: number;

  /** Илллюстрация к статье */
  image?: string;

  /**
   * Дата публикации статьи
   * @format date-time
   */
  publishDate?: string;

  /** Текст статьи */
  text?: string;

  /** Название статьи */
  title?: string;
}

export interface City {
  /**
   * Уникальный идентификатор города
   * @format int64
   */
  id?: number;

  /** Название города */
  name?: string;
}

export interface Complex {
  address?: Address;

  /**
   * Реклама - отображать ли в блоке рекомендаций
   * @example false
   */
  advertized?: boolean;

  /**
   * Количество комнат
   * @format int32
   */
  amountOfRooms?: number;
  author?: User;

  /** Категория надвижимости: новостройки, вторичка, аренда */
  category?: "NEW" | "RENT" | "SECONDARY";

  /** Комментарий */
  comment?: string;

  /** Контакты */
  contacts?: Contacts;

  /** Плановая дата сдачи */
  deliveryDate?: string;

  /**
   * Уникальный идентификатор комплекса
   * @format int64
   */
  id?: number;

  /** image */
  image?: string;

  /** Название комплекса */
  name?: string;

  /**
   * Цена, руб
   * @format int32
   */
  price?: number;

  /**
   * Площадь, м. кв.
   * @format int32
   */
  space?: number;

  /** Статус объекта: принят, отклонен, сдается */
  status?: "ACCEPTED" | "DECLINED" | "RENTED";
}

export interface ComplexQuery {
  /**
   * Рекламируется
   * @example false
   */
  advertized?: boolean;

  /**
   * Количество комнат
   * @format int32
   */
  amountOfRooms?: number;

  /**
   * id автора
   * @format int64
   */
  authorId?: number;

  /**
   * id города
   * @format int64
   */
  cityId: number;

  /**
   * id контакта
   * @format int64
   */
  contactsId?: number;

  /** Стартовая дата сдачи */
  deliveryDate?: string;

  /**
   * id района
   * @format int64
   */
  districtId?: number;

  /** Категория недвижимости: новостройка, вторичка... */
  estateCategory?: "NEW" | "RENT" | "SECONDARY";

  /**
   * Уникальный идентификатор комплекса
   * @format int64
   */
  id?: number;

  /**
   * id станции метро
   * @format int64
   */
  metroId?: number;

  /** Маска названия комплекса */
  name?: string;

  /**
   * Максимальная цена
   * @format int32
   */
  priceMax?: number;

  /**
   * Минимальная цена
   * @format int32
   */
  priceMin?: number;

  /**
   * Площадь, верхний порог
   * @format int32
   */
  spaceMax?: number;

  /**
   * Площадь, нижний порог
   * @format int32
   */
  spaceMin?: number;
}

export interface Contacts {
  email?: string;

  /**
   * Уникальный идентификатор контактов
   * @format int64
   */
  id?: number;

  /** Имя контактного лица */
  name?: string;

  /** Телефон */
  phone?: string;
  photo?: string;
}

export interface District {
  /** Город района */
  city?: City;

  /**
   * Уникальный идентификатор района
   * @format int64
   */
  id?: number;

  /** Название района */
  name?: string;
}

export interface Flat {
  /** Описание квартиры */
  about?: string;

  /** Комплекс, к которому относится квартира */
  complex?: Complex;

  /** Тип недвижимости */
  estateType?: "FLAT" | "HOUSE" | "ROOM";

  /**
   * Этаж квартиры
   * @format int32
   */
  floor?: number;

  /**
   * Уникальный идентификатор квартиры
   * @format int64
   */
  id?: number;
  image?: string;

  /**
   * Количество комнат
   * @format double
   */
  numberOfRooms?: number;

  /**
   * Цена
   * @format double
   */
  price?: number;

  /**
   * Площадь квартиры
   * @format float
   */
  square?: number;
}

export interface Greeting {
  text?: string;
}

export interface Links {
  empty?: boolean;
}

export interface Metro {
  /** Город станции метро */
  city?: City;

  /** Цвет станции метро */
  color?: string;

  /**
   * Уникльный идентификатор станции метро
   * @format int64
   */
  id?: number;

  /** Название станции метро */
  name?: string;
}

export interface ModelAndView {
  empty?: boolean;
  model?: object;
  modelMap?: Record<string, object>;
  reference?: boolean;
  status?:
    | "ACCEPTED"
    | "ALREADY_REPORTED"
    | "BAD_GATEWAY"
    | "BAD_REQUEST"
    | "BANDWIDTH_LIMIT_EXCEEDED"
    | "CHECKPOINT"
    | "CONFLICT"
    | "CONTINUE"
    | "CREATED"
    | "DESTINATION_LOCKED"
    | "EXPECTATION_FAILED"
    | "FAILED_DEPENDENCY"
    | "FORBIDDEN"
    | "FOUND"
    | "GATEWAY_TIMEOUT"
    | "GONE"
    | "HTTP_VERSION_NOT_SUPPORTED"
    | "IM_USED"
    | "INSUFFICIENT_SPACE_ON_RESOURCE"
    | "INSUFFICIENT_STORAGE"
    | "INTERNAL_SERVER_ERROR"
    | "I_AM_A_TEAPOT"
    | "LENGTH_REQUIRED"
    | "LOCKED"
    | "LOOP_DETECTED"
    | "METHOD_FAILURE"
    | "METHOD_NOT_ALLOWED"
    | "MOVED_PERMANENTLY"
    | "MOVED_TEMPORARILY"
    | "MULTIPLE_CHOICES"
    | "MULTI_STATUS"
    | "NETWORK_AUTHENTICATION_REQUIRED"
    | "NON_AUTHORITATIVE_INFORMATION"
    | "NOT_ACCEPTABLE"
    | "NOT_EXTENDED"
    | "NOT_FOUND"
    | "NOT_IMPLEMENTED"
    | "NOT_MODIFIED"
    | "NO_CONTENT"
    | "OK"
    | "PARTIAL_CONTENT"
    | "PAYLOAD_TOO_LARGE"
    | "PAYMENT_REQUIRED"
    | "PERMANENT_REDIRECT"
    | "PRECONDITION_FAILED"
    | "PRECONDITION_REQUIRED"
    | "PROCESSING"
    | "PROXY_AUTHENTICATION_REQUIRED"
    | "REQUESTED_RANGE_NOT_SATISFIABLE"
    | "REQUEST_ENTITY_TOO_LARGE"
    | "REQUEST_HEADER_FIELDS_TOO_LARGE"
    | "REQUEST_TIMEOUT"
    | "REQUEST_URI_TOO_LONG"
    | "RESET_CONTENT"
    | "SEE_OTHER"
    | "SERVICE_UNAVAILABLE"
    | "SWITCHING_PROTOCOLS"
    | "TEMPORARY_REDIRECT"
    | "TOO_EARLY"
    | "TOO_MANY_REQUESTS"
    | "UNAUTHORIZED"
    | "UNAVAILABLE_FOR_LEGAL_REASONS"
    | "UNPROCESSABLE_ENTITY"
    | "UNSUPPORTED_MEDIA_TYPE"
    | "UPGRADE_REQUIRED"
    | "URI_TOO_LONG"
    | "USE_PROXY"
    | "VARIANT_ALSO_NEGOTIATES";
  view?: View;
  viewName?: string;
}

export interface Pageable {
  /** @format int32 */
  page?: number;

  /** @format int32 */
  size?: number;
  sort?: string;
}

export interface PageArticle {
  content?: Article[];
  empty?: boolean;
  first?: boolean;
  last?: boolean;

  /** @format int32 */
  number?: number;

  /** @format int32 */
  numberOfElements?: number;
  pageable?: Pageable;

  /** @format int32 */
  size?: number;
  sort?: Sort;

  /** @format int64 */
  totalElements?: number;

  /** @format int32 */
  totalPages?: number;
}

export interface PageComplex {
  content?: Complex[];
  empty?: boolean;
  first?: boolean;
  last?: boolean;

  /** @format int32 */
  number?: number;

  /** @format int32 */
  numberOfElements?: number;
  pageable?: Pageable;

  /** @format int32 */
  size?: number;
  sort?: Sort;

  /** @format int64 */
  totalElements?: number;

  /** @format int32 */
  totalPages?: number;
}

export interface RepresentationModelObject {
  links?: Links;
}

export interface Request {
  /** Логин */
  login?: string;

  /** Незашифрованный пароль */
  password?: string;
}

export interface RequestCallQuery {
  callRequestType?: "COMPLEX" | "FLAT" | "WANT_TO_BUY" | "WANT_TO_SELL";
  comment?: string;
  contactEmail?: string;
  contactPhone?: string;
  name?: string;

  /** @format int64 */
  placeId?: number;
}

export interface Response {
  /** Токен */
  token?: string;
}

export interface Role {
  /**
   * Уникальный идентификатор
   * @format int64
   */
  id?: number;

  /** Название роли */
  name?: string;
}

export interface Sort {
  empty?: boolean;
  sorted?: boolean;
  unsorted?: boolean;
}

export interface Tariff {
  /**
   * Уникальный идентификатор тарифа
   * @format int64
   */
  id?: number;

  /** Название услуги */
  name?: string;

  /**
   * Цена услуги
   * @format double
   */
  price?: number;

  /** Тип цены тарифа (проценты, рубли и т. п.). */
  priceType?: "PERCENT_OF_MONTHLY_RENT_RATE" | "PERCENT_OF_TRANSACTION_AMOUNT" | "ROUBLES";
}

export interface User {
  /**
   * Уникальный идентификатор
   * @format int64
   */
  id?: number;

  /** Логин */
  login?: string;

  /** Роль пользователя */
  role?: Role;
}

export interface UserDto {
  /** @format int64 */
  id?: number;
  login?: string;
  password?: string;

  /** @format int64 */
  roleId?: number;
}

export interface UserRoleDto {
  /** @format int64 */
  id?: number;
  roleName?: string;
}

export interface View {
  contentType?: string;
}
