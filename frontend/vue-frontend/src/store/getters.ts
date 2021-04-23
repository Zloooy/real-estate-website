import { GetterTree } from "vuex/types";
import {Api} from "@/generated-api/Api";
import {Auth} from "@/generated-api/Auth";
import {State} from "@/store/state";

export type Getters = {
    auth(state: State): Auth,
    api(state: State): Api,
    authorization_set(state: State): boolean
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
    }
} as Getters;