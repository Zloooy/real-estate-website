
import {MutationTree} from "vuex";
import {Api} from "@/generated-api/Api";
import {State} from "@/store/state";
import {City} from "@/generated-api/data-contracts";

export type Mutations<S = State> = {
    SET_TOKEN(state: S, payload: string): void,
    SET_CITY(state: S, payload: City),
    SET_CITIES(state: S, payload: City[])
}

export const mutations: MutationTree<State> & Mutations = {
    SET_CITIES(state: State, payload: City[]) {
        state.cities = payload;
    },
    SET_TOKEN(state: State, payload: string) {
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
    },
    SET_CITY(state: State, payload: City){
        if (state.city == null)
            state.city_changed = true;
        else if (state.city.id !== payload.id)
            state.city_changed = true;
        state.city = payload;
    }
}