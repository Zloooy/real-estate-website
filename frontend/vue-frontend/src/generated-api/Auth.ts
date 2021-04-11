import { Request, Response } from "./data-contracts";
import { ContentType, HttpClient, RequestParams } from "./http-client";

export class Auth<SecurityDataType = unknown> extends HttpClient<SecurityDataType> {
  /**
   * No description
   *
   * @tags auth-controller
   * @name AuthUsingPost
   * @summary Аутентификация
   * @request POST:/auth
   */
  authUsingPost = (data: Request, params: RequestParams = {}) =>
    this.request<Response, void>({
      path: `/auth`,
      method: "POST",
      body: data,
      type: ContentType.Json,
      format: "json",
      ...params,
    });
}
