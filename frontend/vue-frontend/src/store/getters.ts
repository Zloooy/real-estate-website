import { GetterTree } from "vuex/types";
import {Api} from "@/generated-api/Api";
import {Auth} from "@/generated-api/Auth";
import {State} from "@/store/state";
import {City, Complex, District, Flat, Metro} from "@/generated-api/data-contracts";
import {PublicApi} from "@/generated-api/PublicApi";

export type Getters = {
    auth(state: State): Auth,
    api(state: State): Api,
    public_api(state:State): PublicApi,
    authorization_set(state: State): boolean,
    cities(state: State): City[],
    complexes(state: State): Complex[],
    metros(state: State): Metro[],
    districts(state: State): District[],
    complexSearchParamsChanged(state: State): boolean
    complex_category(state: State): State['complex_category']
    complex(state: State): Complex | null,
    complexFlats(state: State): State['complexFlats'],
    flat(state: State): State['flat'],
    users(state: State): State['users'],
    userRoles(state: State): State['userRoles'],
    tariffs(state: State): State['tariffs']
    articles(state: State): State['articles']
}

export const getters: GetterTree<State, State> & Getters = {
    users(state: State): State['users'] {
        return state.users;
    },
    userRoles(state: State): State['userRoles'] {
     return state.userRoles;   
    },
    api: (state: State) => {
        return state.api;
},
    authorization_set: (state: State) => {
        return state.authorization_set;
    },
    auth: (state: State) => {
        return state.auth;
    },
    public_api(state: State): PublicApi {
        return state.public_api;
    },
    cities(state: State): City[] {
        return state.cities;
    },
    complexes(state: State): Complex[] {
        return state.complexes;
    },
    metros(state: State): Metro[] {
        return state.metros;
    },
    districts(state: State): District[] {
        return state.districts;
    },
    complexSearchParamsChanged(state: State): boolean {
        return state.complexSearchParamsChanged;
    },
    complex_category(state: State): State['complex_category']{
        return state.complex_category;
    },
    complex(state: State): Complex | null{
        return state.complex ? state.complex : null;
    },
    complexFlats(state: State): State["complexFlats"] {
        return state.complexFlats;
    },
    flat(state: State): State['flat'] {
        return state.flat;
    },
    tariffs(state: State): State["tariffs"] {
        return state.tariffs;
    },
    articles(state: State): State['articles'] {
        return state.articles;
    }
} as Getters;