import {Api} from "@/generated-api/Api";
import {Auth} from "@/generated-api/Auth";
import {City, Complex, ComplexQuery, Metro, District} from "@/generated-api/data-contracts";
import {PublicApi} from "@/generated-api/PublicApi";
import {ApiConfig} from "@/generated-api/http-client";
// export interface State {
//     auth: Auth,
//     api: Api,
//     public_api: PublicApi
//     authorization_set: boolean,
//     cities: City[]
//     city: City | null,
//     city_changed: boolean,
//     complexes: Complex[],
//     complex_category: ComplexQuery['estateCategory']
// }
const baseUrl = "http://localhost:8009";
const apiConfig: ApiConfig = {baseUrl: baseUrl, baseApiParams: {secure: true}};
export const state = {
    api: new Api(apiConfig),
    public_api: new PublicApi(apiConfig),
    authorization_set: false as boolean,
    auth: null as Auth | null,
    cities: [] as City[],
    city: null as City | null,
    complexes: [] as Complex[],
    complex_category: "NEW" as ComplexQuery['estateCategory'],
    sort: 'price' as string,
    metros: [] as Metro[],
    districts: [] as District[],
    amountOfRooms: undefined as undefined | number,
    complexSearchParamsChanged: true as boolean,
    complexPage: 0 as number,
    complexSize: 1 as number,
    complexesLoading: false as boolean,
    metro: null as Metro | null,
    district: null as District | null,
    minComplexPrice: undefined as number | undefined,
    maxComplexPrice: undefined as number | undefined,
    complexDeliveryDate: undefined as string | undefined
};

export type State = typeof state;
