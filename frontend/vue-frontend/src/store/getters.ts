import { GetterTree } from "vuex";
import {Api} from "@/generated-api/Api";
import {State} from "@/store/state";

export type Getters = {
    api(state: State): Api,
    authorization_set(state: State): boolean
}

export const getters: GetterTree<State, State> & Getters = {
    api: (state) => {
        return state.api;
},
    authorization_set: (state) => {
        return state.authorization_set;
}
}