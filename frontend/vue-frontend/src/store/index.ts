import {createStore, Store as VuexStore, CommitOptions, DispatchOptions} from 'vuex'
import {Getters, getters} from "@/store/getters";
import {Mutations, mutations} from "@/store/mutations";
import {actions, Actions} from "@/store/actions";
import {state, State} from "@/store/state";



export const store = createStore<State>({
  state: {...state},
  getters,
  mutations,
  actions,
});

export type Store = Omit<VuexStore<State>, 'getters' | 'dispatch' | 'commit'> & {
  dispatch<K extends keyof Actions>(
      key: K,
      payload: Parameters<Actions[K]>[1],
      options?: DispatchOptions
  ): ReturnType<Actions[K]>
} & {
  commit<K extends keyof Mutations>(
      key: K,
      payload: Parameters<Mutations[K]>[1],
      options?: CommitOptions
  ): ReturnType<Mutations[K]>
}
      & {
    getters: { [K in keyof Getters]: ReturnType<Getters[K]> }
  };

export function useStore() {
  return store as Store;
}
