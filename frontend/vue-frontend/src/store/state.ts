import {Api} from "@/generated-api/Api";
import {Auth} from "@/generated-api/Auth";
import {City, Complex} from "@/generated-api/data-contracts";
import {PublicApi} from "@/generated-api/PublicApi";
export interface State {
    auth: Auth,
    api: Api,
    public_api: PublicApi
    authorization_set: boolean,
    cities: City[]
    city: City | null,
    city_changed: boolean,
    complexes: Complex[]
}
export const state: State = {
    api: new Api(),
    public_api: new PublicApi({baseUrl: "http://localhost:8009"}),
    authorization_set: false,
    auth: new Auth({
        baseUrl: "http://localhost:8009"
    }),
    cities: [],
    city: null,
    city_changed: false,
    complexes: []
}
