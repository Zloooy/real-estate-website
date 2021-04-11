import {Mutations} from "@/store/mutations";
import {ActionContext} from "vuex";
import {State} from "@/store/state";



type AugmentActionContext = {
    commit<K extends keyof Mutations>(
        key: K,
        payload: Parameters<Mutations[K]>[1]
    ): ReturnType<Mutations[K]>
} & Omit<ActionContext<State, State>, 'commit'>

export interface Actions {
}
export const actions = {
}