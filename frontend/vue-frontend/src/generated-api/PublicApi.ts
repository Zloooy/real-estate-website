import { City, Complex, ComplexQuery, PageComplex } from "./data-contracts";
import { ContentType, HttpClient, RequestParams } from "./http-client";

export class PublicApi<SecurityDataType = unknown> extends HttpClient<SecurityDataType> {
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
      ...params,
    });
}
