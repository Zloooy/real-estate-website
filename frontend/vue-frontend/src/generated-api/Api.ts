import {
  Article,
  Complex,
  Contacts,
  CreationResponse,
  District,
  Flat,
  Greeting,
  Metro,
  RepresentationModelObject,
  Tariff,
  UserDto,
  UserRoleDto,
} from "./data-contracts";
import { ContentType, HttpClient, RequestParams } from "./http-client";

export class Api<SecurityDataType = unknown> extends HttpClient<SecurityDataType> {
  /**
   * No description
   *
   * @tags article-controller
   * @name CreateArticleUsingPost
   * @summary Обновить статью с id
   * @request POST:/api/article/new
   */
  createArticleUsingPost = (data: Article, params: RequestParams = {}) =>
    this.request<CreationResponse, void>({
      path: `/api/article/new`,
      method: "POST",
      body: data,
      type: ContentType.Json,
      format: "json",
      ...params,
    });
  /**
   * No description
   *
   * @tags article-controller
   * @name EditArticleByIdUsingPost
   * @summary Обновить статью с id
   * @request POST:/api/article/{id}
   */
  editArticleByIdUsingPost = (id: number, data: Article, params: RequestParams = {}) =>
    this.request<boolean, void>({
      path: `/api/article/${id}`,
      method: "POST",
      body: data,
      type: ContentType.Json,
      format: "json",
      ...params,
    });
  /**
   * No description
   *
   * @tags article-controller
   * @name DeleteArticleByIdUsingDelete
   * @summary Удаление статьи по id
   * @request DELETE:/api/article/{id}
   */
  deleteArticleByIdUsingDelete = (id: number, params: RequestParams = {}) =>
    this.request<boolean, void>({
      path: `/api/article/${id}`,
      method: "DELETE",
      format: "json",
      ...params,
    });
  /**
   * No description
   *
   * @tags complex-controller
   * @name CreateNewComplexUsingPost
   * @summary Создание комплекса
   * @request POST:/api/complexes/new
   */
  createNewComplexUsingPost = (data: Complex, params: RequestParams = {}) =>
    this.request<CreationResponse, void>({
      path: `/api/complexes/new`,
      method: "POST",
      body: data,
      type: ContentType.Json,
      format: "json",
      ...params,
    });
  /**
   * No description
   *
   * @tags complex-controller
   * @name EditComplexUsingPost
   * @summary Редактирование комплекса
   * @request POST:/api/complexes/{id}
   */
  editComplexUsingPost = (id: number, data: Complex, params: RequestParams = {}) =>
    this.request<boolean, void>({
      path: `/api/complexes/${id}`,
      method: "POST",
      body: data,
      type: ContentType.Json,
      format: "json",
      ...params,
    });
  /**
   * No description
   *
   * @tags complex-controller
   * @name DeleteComplexUsingDelete
   * @summary Удаление комплекса
   * @request DELETE:/api/complexes/{id}
   */
  deleteComplexUsingDelete = (id: number, params: RequestParams = {}) =>
    this.request<boolean, void>({
      path: `/api/complexes/${id}`,
      method: "DELETE",
      format: "json",
      ...params,
    });
  /**
   * No description
   *
   * @tags contacts-controller
   * @name GetAllContactsUsingGet
   * @summary Получение списка всех контактов
   * @request GET:/api/contacts
   */
  getAllContactsUsingGet = (params: RequestParams = {}) =>
    this.request<Contacts[], void>({
      path: `/api/contacts`,
      method: "GET",
      format: "json",
      ...params,
    });
  /**
   * No description
   *
   * @tags contacts-controller
   * @name CreateNewContactsUsingPost
   * @summary Создание нового контакта
   * @request POST:/api/contacts/new
   */
  createNewContactsUsingPost = (data: Contacts, params: RequestParams = {}) =>
    this.request<boolean, void>({
      path: `/api/contacts/new`,
      method: "POST",
      body: data,
      type: ContentType.Json,
      format: "json",
      ...params,
    });
  /**
   * No description
   *
   * @tags contacts-controller
   * @name DeleteContactUsingDelete
   * @summary Удаление контактов
   * @request DELETE:/api/contacts/{id}
   */
  deleteContactUsingDelete = (id: number, params: RequestParams = {}) =>
    this.request<boolean, void>({
      path: `/api/contacts/${id}`,
      method: "DELETE",
      ...params,
    });
  /**
   * No description
   *
   * @tags district-rest-controller
   * @name CreateNewDistrictUsingPost
   * @summary Создать новый район
   * @request POST:/api/districts/new
   */
  createNewDistrictUsingPost = (data: District, params: RequestParams = {}) =>
    this.request<boolean, void>({
      path: `/api/districts/new`,
      method: "POST",
      body: data,
      type: ContentType.Json,
      format: "json",
      ...params,
    });
  /**
   * No description
   *
   * @tags flat-controller
   * @name CreateFlatUsingPost
   * @summary Coздание новой квартиры
   * @request POST:/api/flat/new
   */
  createFlatUsingPost = (data: Flat, params: RequestParams = {}) =>
    this.request<CreationResponse, void>({
      path: `/api/flat/new`,
      method: "POST",
      body: data,
      type: ContentType.Json,
      format: "json",
      ...params,
    });
  /**
   * No description
   *
   * @tags flat-controller
   * @name EditFlatUsingPost
   * @summary Редактирование сущестувующей квартиры
   * @request POST:/api/flat/{id}
   */
  editFlatUsingPost = (id: number, data: Flat, params: RequestParams = {}) =>
    this.request<boolean, void>({
      path: `/api/flat/${id}`,
      method: "POST",
      body: data,
      type: ContentType.Json,
      format: "json",
      ...params,
    });
  /**
   * No description
   *
   * @tags flat-controller
   * @name DeleteFlatUsingDelete
   * @summary Удаление квартиры
   * @request DELETE:/api/flat/{id}
   */
  deleteFlatUsingDelete = (id: number, params: RequestParams = {}) =>
    this.request<boolean, void>({
      path: `/api/flat/${id}`,
      method: "DELETE",
      format: "json",
      ...params,
    });
  /**
   * No description
   *
   * @tags greeting-controller
   * @name GreetingUsingGet
   * @summary greeting
   * @request GET:/api/greeting
   */
  greetingUsingGet = (params: RequestParams = {}) =>
    this.request<Greeting, void>({
      path: `/api/greeting`,
      method: "GET",
      ...params,
    });
  /**
   * No description
   *
   * @tags metro-controller
   * @name CreateMetroUsingPost
   * @summary Создать новую станцию метро
   * @request POST:/api/metros/new
   */
  createMetroUsingPost = (data: Metro, params: RequestParams = {}) =>
    this.request<boolean, void>({
      path: `/api/metros/new`,
      method: "POST",
      body: data,
      type: ContentType.Json,
      format: "json",
      ...params,
    });
  /**
   * No description
   *
   * @tags profile-controller
   * @name ListAllFormsOfMetadataUsingGet
   * @summary listAllFormsOfMetadata
   * @request GET:/api/profile
   */
  listAllFormsOfMetadataUsingGet = (params: RequestParams = {}) =>
    this.request<RepresentationModelObject, void>({
      path: `/api/profile`,
      method: "GET",
      ...params,
    });
  /**
   * No description
   *
   * @tags profile-controller
   * @name ProfileOptionsUsingOptions
   * @summary profileOptions
   * @request OPTIONS:/api/profile
   */
  profileOptionsUsingOptions = (params: RequestParams = {}) =>
    this.request<object, void>({
      path: `/api/profile`,
      method: "OPTIONS",
      ...params,
    });
  /**
   * No description
   *
   * @tags tariff-rest-controller
   * @name CreateNewTariffUsingPost
   * @summary Создание нового тарифа
   * @request POST:/api/tariffs/new
   */
  createNewTariffUsingPost = (data: Tariff, params: RequestParams = {}) =>
    this.request<boolean, void>({
      path: `/api/tariffs/new`,
      method: "POST",
      body: data,
      type: ContentType.Json,
      format: "json",
      ...params,
    });
  /**
   * No description
   *
   * @tags tariff-rest-controller
   * @name UpdateTariffUsingPost
   * @summary Обновление информации по тарифу
   * @request POST:/api/tariffs/{id}
   */
  updateTariffUsingPost = (id: number, data: Tariff, params: RequestParams = {}) =>
    this.request<boolean, void>({
      path: `/api/tariffs/${id}`,
      method: "POST",
      body: data,
      type: ContentType.Json,
      format: "json",
      ...params,
    });
  /**
   * No description
   *
   * @tags tariff-rest-controller
   * @name DeleteTariffUsingDelete
   * @summary Удаление тарифа
   * @request DELETE:/api/tariffs/{id}
   */
  deleteTariffUsingDelete = (id: number, params: RequestParams = {}) =>
    this.request<boolean, void>({
      path: `/api/tariffs/${id}`,
      method: "DELETE",
      format: "json",
      ...params,
    });
  /**
   * No description
   *
   * @tags user-controller
   * @name GetAllUsersUsingGet
   * @summary Получение списка всех пользователей
   * @request GET:/api/get_all_users
   */
  getAllUsersUsingGet = (params: RequestParams = {}) =>
    this.request<UserDto[], void>({
      path: `/api/get_all_users`,
      method: "GET",
      format: "json",
      ...params,
    });
  /**
   * No description
   *
   * @tags user-controller
   * @name GetUserAuthoritiesUsingPost
   * @summary Получение разрешений пользователя
   * @request POST:/api/user/authorities
   */
  getUserAuthoritiesUsingPost = (params: RequestParams = {}) =>
    this.request<string[], void>({
      path: `/api/user/authorities`,
      method: "POST",
      format: "json",
      ...params,
    });
  /**
   * No description
   *
   * @tags user-controller
   * @name CreateUserUsingPost1
   * @summary Изменение ползователя
   * @request POST:/api/user/new
   */
  createUserUsingPost1 = (data: UserDto, params: RequestParams = {}) =>
    this.request<boolean, void>({
      path: `/api/user/new`,
      method: "POST",
      body: data,
      type: ContentType.Json,
      ...params,
    });
  /**
   * No description
   *
   * @tags user-controller
   * @name CreateUserUsingPost
   * @summary Изменение ползователя
   * @request POST:/api/user/{id}
   */
  createUserUsingPost = (id: number, data: UserDto, params: RequestParams = {}) =>
    this.request<boolean, void>({
      path: `/api/user/${id}`,
      method: "POST",
      body: data,
      type: ContentType.Json,
      ...params,
    });
  /**
   * No description
   *
   * @tags user-role-controller
   * @name GetAllUserRolesUsingGet
   * @summary Получение списка ролей
   * @request GET:/api/user_roles
   */
  getAllUserRolesUsingGet = (params: RequestParams = {}) =>
    this.request<UserRoleDto[], void>({
      path: `/api/user_roles`,
      method: "GET",
      format: "json",
      ...params,
    });
}
