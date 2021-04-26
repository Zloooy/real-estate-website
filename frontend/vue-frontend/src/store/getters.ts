import { GetterTree } from "vuex/types";
import {Api} from "@/generated-api/Api";
import {Auth} from "@/generated-api/Auth";
import {state, State} from "@/store/state";
import {City, Complex} from "@/generated-api/data-contracts";
import {PublicApi} from "@/generated-api/PublicApi";

export type Getters = {
    auth(state: State): Auth,
    api(state: State): Api,
    public_api(state:State): PublicApi,
    authorization_set(state: State): boolean,
    cities(state: State): City[],
    complexes(state: State): Complex[]
}

export const getters: GetterTree<State, State> & Getters = {
    api: (state: State) => {
        return state.api;
},
    authorization_set: (state: State) => {
        return state.authorization_set;
},
    auth: (state: State) => {
        return new Auth({
        baseUrl:"http://localhost:8009"
    });
    },
    public_api(state: State): PublicApi {
        return new PublicApi();
    },
    cities(state: State): City[] {
        return state.cities;
    },
    complexes(state: State): Complex[] {
        return state.complexes;
    }
} as Getters;