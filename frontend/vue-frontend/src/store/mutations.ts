import {MutationTypes} from './mutation-types'

import {MutationTree} from "vuex";
import {Api} from "@/generated-api/Api";
import {State} from "@/store/state";

export type Mutations<S = State> = {
    SET_TOKEN(state: S, payload: string): void
}

export const mutations: MutationTree<State> & Mutations = {
    ['SET_TOKEN' as MutationTypes](state: State, payload: string) {
        state.api = new Api({
            baseUrl: "http://localhost:8009",
            baseApiParams: {
                headers: {
                    "Authorization": `Bearer ${payload}`
                },
                format: "json"
            }
        });
        state.authorization_set = true;
    }
}