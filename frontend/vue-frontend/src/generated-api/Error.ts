import { HttpClient, RequestParams } from "./http-client";

export class Error<SecurityDataType = unknown> extends HttpClient<SecurityDataType> {
  /**
   * No description
   *
   * @tags basic-error-controller
   * @name ErrorUsingGet
   * @summary error
   * @request GET:/error
   */
  errorUsingGet = (params: RequestParams = {}) =>
    this.request<Record<string, object>, void>({
      path: `/error`,
      method: "GET",
      ...params,
    });
  /**
   * No description
   *
   * @tags basic-error-controller
   * @name ErrorUsingPut
   * @summary error
   * @request PUT:/error
   */
  errorUsingPut = (params: RequestParams = {}) =>
    this.request<Record<string, object>, void>({
      path: `/error`,
      method: "PUT",
      ...params,
    });
  /**
   * No description
   *
   * @tags basic-error-controller
   * @name ErrorUsingPost
   * @summary error
   * @request POST:/error
   */
  errorUsingPost = (params: RequestParams = {}) =>
    this.request<Record<string, object>, void>({
      path: `/error`,
      method: "POST",
      ...params,
    });
  /**
   * No description
   *
   * @tags basic-error-controller
   * @name ErrorUsingDelete
   * @summary error
   * @request DELETE:/error
   */
  errorUsingDelete = (params: RequestParams = {}) =>
    this.request<Record<string, object>, void>({
      path: `/error`,
      method: "DELETE",
      ...params,
    });
  /**
   * No description
   *
   * @tags basic-error-controller
   * @name ErrorUsingOptions
   * @summary error
   * @request OPTIONS:/error
   */
  errorUsingOptions = (params: RequestParams = {}) =>
    this.request<Record<string, object>, void>({
      path: `/error`,
      method: "OPTIONS",
      ...params,
    });
  /**
   * No description
   *
   * @tags basic-error-controller
   * @name ErrorUsingHead
   * @summary error
   * @request HEAD:/error
   */
  errorUsingHead = (params: RequestParams = {}) =>
    this.request<Record<string, object>, void>({
      path: `/error`,
      method: "HEAD",
      ...params,
    });
  /**
   * No description
   *
   * @tags basic-error-controller
   * @name ErrorUsingPatch
   * @summary error
   * @request PATCH:/error
   */
  errorUsingPatch = (params: RequestParams = {}) =>
    this.request<Record<string, object>, void>({
      path: `/error`,
      method: "PATCH",
      ...params,
    });
  /**
   * No description
   *
   * @tags basic-error-controller
   * @name ErrorUsingTrace
   * @summary error
   * @request TRACE:/error
   */
  errorUsingTrace = (params: RequestParams = {}) =>
    this.request<Record<string, object>, void>({
      path: `/error`,
      method: "TRACE",
      ...params,
    });
}
