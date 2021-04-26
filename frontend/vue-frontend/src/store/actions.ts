import {Mutations} from "@/store/mutations";
import {ActionContext, ActionTree} from "vuex";
import {state, State} from "@/store/state";
import {City, ComplexQuery} from "@/generated-api/data-contracts";


type AugmentActionContext = {
    commit<K extends keyof Mutations>(
        key: K,
        payload: Parameters<Mutations[K]>[1]
    ): ReturnType<Mutations[K]>
} & Omit<ActionContext<State, State>, 'commit'>

export interface Actions {
    GET_CITIES({ commit }: AugmentActionContext,
    payload: null): Promise<City[]>,

    GET_ADVERTIZED_COMPLEXES({ commit , }: AugmentActionContext,
               payload: String): Promise<City[]>
}
export const actions: ActionTree<State, State> & Actions = {
    GET_CITIES({commit}: AugmentActionContext, payload: null): Promise<City[]> {
        return state.public_api.getAllCitiesUsingGet().then(response => response.data).catch(err => [])
            .then(d=>commit('SET_CITIES', d));
    },
    GET_ADVERTIZED_COMPLEXES({commit}: AugmentActionContext, payload: ("NEW" | "RENT" | "SECONDARY")): Promise<City[]> {
        return state.public_api.getAdvertizedUsingPost({cityId: state.city?.id || 9, estateCategory:payload})
            .then(response => {
                console.debug(JSON.stringify(response));
                return response.data
            }).catch(()=>[])
            .then(d=>commit('SET_COMPLEXES', d));
    },
}