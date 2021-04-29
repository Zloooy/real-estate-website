
import {MutationTree} from "vuex";
import {Api} from "@/generated-api/Api";
import {State, state} from "@/store/state";
import {City, Complex, ComplexQuery} from "@/generated-api/data-contracts";

export type Mutations<S = State> = {
    SET_TOKEN(state: S, payload: string): void,
    SET_CITY(state: S, payload: City),
    SET_CITIES(state: S, payload: City[])
    SET_COMPLEXES(state: S, payload: Complex[])
    SET_COMPLEX_CATEGORY(state: S, payload: ComplexQuery['estateCategory'])
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
        state.city = payload;
    },
    SET_COMPLEXES(state: State, payload: Complex[]){
        state.complexes = payload;
    },
    SET_COMPLEX_CATEGORY(state: State, payload: ComplexQuery["estateCategory"]) {
        state.complex_category = payload;
        },
}