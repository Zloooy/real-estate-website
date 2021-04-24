import { GetterTree } from "vuex/types";
import {Api} from "@/generated-api/Api";
import {Auth} from "@/generated-api/Auth";
import {state, State} from "@/store/state";
import {City} from "@/generated-api/data-contracts";
import {PublicApi} from "@/generated-api/PublicApi";

export type Getters = {
    auth(state: State): Auth,
    api(state: State): Api,
    public_api(state:State): PublicApi,
    authorization_set(state: State): boolean,
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
    }
} as Getters;