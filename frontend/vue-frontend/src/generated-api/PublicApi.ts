import { City } from "./data-contracts";
import { HttpClient, RequestParams } from "./http-client";

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
}
