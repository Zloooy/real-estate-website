import {
  Article,
  City,
  Complex,
  ComplexQuery,
  District,
  Flat,
  Metro,
  PageArticle,
  PageComplex,
  RequestCallQuery,
  Tariff,
} from "./data-contracts";
import { ContentType, HttpClient, RequestParams } from "./http-client";

export class PublicApi<SecurityDataType = unknown> extends HttpClient<SecurityDataType> {
  /**
   * No description
   *
   * @tags article-controller
   * @name GetArticlesUsingGet
   * @summary Получить страницу статей
   * @request GET:/public_api/article
   */
  getArticlesUsingGet = (query?: { page?: number; size?: number; sort?: string }, params: RequestParams = {}) =>
    this.request<PageArticle, void>({
      path: `/public_api/article`,
      method: "GET",
      query: query,
      format: "json",
      ...params,
    });
  /**
   * No description
   *
   * @tags article-controller
   * @name FindArticleByIdUsingGet
   * @summary Получение статьи с заданным id
   * @request GET:/public_api/article/{id}
   */
  findArticleByIdUsingGet = (id: number, params: RequestParams = {}) =>
    this.request<Article, void>({
      path: `/public_api/article/${id}`,
      method: "GET",
      format: "json",
      ...params,
    });
  /**
   * No description
   *
   * @tags city-controller
   * @name GetAllCitiesUsingGet
   * @summary Получить все города
   * @request GET:/public_api/cities
   */
  getAllCitiesUsingGet = (params: RequestParams = {}) =>
    this.request<City[], void>({
      path: `/public_api/cities`,
      method: "GET",
      format: "json",
      ...params,
    });
  /**
   * No description
   *
   * @tags complex-controller
   * @name GetAdvertizedUsingPost
   * @summary Получение рекламируемых городов
   * @request POST:/public_api/advertized_complexes/
   */
  getAdvertizedUsingPost = (data: ComplexQuery, params: RequestParams = {}) =>
    this.request<Complex[], void>({
      path: `/public_api/advertized_complexes/`,
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
   * @name FindByIdUsingGet
   * @summary Комплекс с заданным id
   * @request GET:/public_api/complex/{id}
   */
  findByIdUsingGet = (id: number, params: RequestParams = {}) =>
    this.request<Complex, void>({
      path: `/public_api/complex/${id}`,
      method: "GET",
      format: "json",
      ...params,
    });
  /**
   * No description
   *
   * @tags complex-controller
   * @name GetComplexesUsingPost
   * @summary Получение страниц с городами
   * @request POST:/public_api/complexes
   */
  getComplexesUsingPost = (
    data: ComplexQuery,
    query?: { page?: number; size?: number; sort?: string },
    params: RequestParams = {},
  ) =>
    this.request<PageComplex, void>({
      path: `/public_api/complexes`,
      method: "POST",
      query: query,
      body: data,
      type: ContentType.Json,
      format: "json",
      ...params,
    });
  /**
   * No description
   *
   * @tags district-rest-controller
   * @name GetDistrictsByCityUsingGet
   * @summary Получить список районов города
   * @request GET:/public_api/districts
   */
  getDistrictsByCityUsingGet = (query: { city_id: number }, params: RequestParams = {}) =>
    this.request<District[], void>({
      path: `/public_api/districts`,
      method: "GET",
      query: query,
      format: "json",
      ...params,
    });
  /**
   * No description
   *
   * @tags flat-controller
   * @name GetFlatByIdUsingGet
   * @summary Получение квартиры по id
   * @request GET:/public_api/flat/{id}
   */
  getFlatByIdUsingGet = (id: number, params: RequestParams = {}) =>
    this.request<Flat, void>({
      path: `/public_api/flat/${id}`,
      method: "GET",
      format: "json",
      ...params,
    });
  /**
   * No description
   *
   * @tags flat-controller
   * @name GetComplexFlatsUsingGet
   * @summary Получение списка квартир комплекса
   * @request GET:/public_api/flats_by_complex/{id}
   */
  getComplexFlatsUsingGet = (id: number, params: RequestParams = {}) =>
    this.request<Flat[], void>({
      path: `/public_api/flats_by_complex/${id}`,
      method: "GET",
      format: "json",
      ...params,
    });
  /**
   * No description
   *
   * @tags metro-controller
   * @name GetCityMetrosUsingGet
   * @summary Получить станции метро города
   * @request GET:/public_api/metros
   */
  getCityMetrosUsingGet = (query: { city_id: number }, params: RequestParams = {}) =>
    this.request<Metro[], void>({
      path: `/public_api/metros`,
      method: "GET",
      query: query,
      format: "json",
      ...params,
    });
  /**
   * No description
   *
   * @tags request-call-controller
   * @name SendCallRequestUsingPut
   * @summary Отправка запроса на звонок
   * @request PUT:/public_api/request_call
   */
  sendCallRequestUsingPut = (data: RequestCallQuery, params: RequestParams = {}) =>
    this.request<void, void>({
      path: `/public_api/request_call`,
      method: "PUT",
      body: data,
      type: ContentType.Json,
      ...params,
    });
  /**
   * No description
   *
   * @tags tariff-rest-controller
   * @name GetAllTariffsUsingGet
   * @summary Получение всех тарифов
   * @request GET:/public_api/tariffs
   */
  getAllTariffsUsingGet = (params: RequestParams = {}) =>
    this.request<Tariff[], void>({
      path: `/public_api/tariffs`,
      method: "GET",
      format: "json",
      ...params,
    });
}
