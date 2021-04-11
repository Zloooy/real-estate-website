import {MutationTypes} from './mutation-types'

import {MutationTree} from "vuex";
import {Api} from "@/generated-api/Api";
import {State} from "@/store/state";

export type Mutations<S = State> = {
    [MutationTypes.SET_TOKEN](state: S, payload:string): void
}

export const mutations: MutationTree<State> & Mutations = {
    [MutationTypes.SET_TOKEN](state, payload: string) {
        state.api = new Api({
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