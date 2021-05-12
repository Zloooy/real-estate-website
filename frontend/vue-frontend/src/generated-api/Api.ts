import { Greeting, RepresentationModelObject, UserDto, UserRoleDto } from "./data-contracts";
import { ContentType, HttpClient, RequestParams } from "./http-client";

export class Api<SecurityDataType = unknown> extends HttpClient<SecurityDataType> {
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
