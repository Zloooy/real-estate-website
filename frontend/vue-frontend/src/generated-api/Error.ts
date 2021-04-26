import { ModelAndView } from "./data-contracts";
import { HttpClient, RequestParams } from "./http-client";

export class Error<SecurityDataType = unknown> extends HttpClient<SecurityDataType> {
  /**
   * No description
   *
   * @tags basic-error-controller
   * @name ErrorHtmlUsingGet
   * @summary errorHtml
   * @request GET:/error
   */
  errorHtmlUsingGet = (params: RequestParams = {}) =>
    this.request<ModelAndView, void>({
      path: `/error`,
      method: "GET",
      ...params,
    });
  /**
   * No description
   *
   * @tags basic-error-controller
   * @name ErrorHtmlUsingPut
   * @summary errorHtml
   * @request PUT:/error
   */
  errorHtmlUsingPut = (params: RequestParams = {}) =>
    this.request<ModelAndView, void>({
      path: `/error`,
      method: "PUT",
      ...params,
    });
  /**
   * No description
   *
   * @tags basic-error-controller
   * @name ErrorHtmlUsingPost
   * @summary errorHtml
   * @request POST:/error
   */
  errorHtmlUsingPost = (params: RequestParams = {}) =>
    this.request<ModelAndView, void>({
      path: `/error`,
      method: "POST",
      ...params,
    });
  /**
   * No description
   *
   * @tags basic-error-controller
   * @name ErrorHtmlUsingDelete
   * @summary errorHtml
   * @request DELETE:/error
   */
  errorHtmlUsingDelete = (params: RequestParams = {}) =>
    this.request<ModelAndView, void>({
      path: `/error`,
      method: "DELETE",
      ...params,
    });
  /**
   * No description
   *
   * @tags basic-error-controller
   * @name ErrorHtmlUsingOptions
   * @summary errorHtml
   * @request OPTIONS:/error
   */
  errorHtmlUsingOptions = (params: RequestParams = {}) =>
    this.request<ModelAndView, void>({
      path: `/error`,
      method: "OPTIONS",
      ...params,
    });
  /**
   * No description
   *
   * @tags basic-error-controller
   * @name ErrorHtmlUsingHead
   * @summary errorHtml
   * @request HEAD:/error
   */
  errorHtmlUsingHead = (params: RequestParams = {}) =>
    this.request<ModelAndView, void>({
      path: `/error`,
      method: "HEAD",
      ...params,
    });
  /**
   * No description
   *
   * @tags basic-error-controller
   * @name ErrorHtmlUsingPatch
   * @summary errorHtml
   * @request PATCH:/error
   */
  errorHtmlUsingPatch = (params: RequestParams = {}) =>
    this.request<ModelAndView, void>({
      path: `/error`,
      method: "PATCH",
      ...params,
    });
  /**
   * No description
   *
   * @tags basic-error-controller
   * @name ErrorHtmlUsingTrace
   * @summary errorHtml
   * @request TRACE:/error
   */
  errorHtmlUsingTrace = (params: RequestParams = {}) =>
    this.request<ModelAndView, void>({
      path: `/error`,
      method: "TRACE",
      ...params,
    });
}
