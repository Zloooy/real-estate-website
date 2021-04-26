import { Greeting, RepresentationModelObject } from "./data-contracts";
import { HttpClient, RequestParams } from "./http-client";

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
}
