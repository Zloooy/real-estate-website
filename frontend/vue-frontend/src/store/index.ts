import {createStore, Store as VuexStore, CommitOptions, DispatchOptions} from 'vuex'
import {Getters, getters} from "@/store/getters";
import {Mutations, mutations} from "@/store/mutations";
import {actions, Actions} from "@/store/actions";
import {State} from "@/store/state";



export const store = createStore<State>({
  getters,
  mutations,
  actions,
  modules: {
  }
});

export type Store = Omit<VuexStore<State>, 'getters' | 'dispatch' | 'commit'> & {
  commit<K extends keyof Mutations, P extends Parameters<Mutations[K]>[1]>(
      key: K,
      payload: P,
      options: CommitOptions
  ): ReturnType<Mutations[K]>
      & {
    dispatch<K extends keyof Actions>(
        key: K,
        payload: Parameters<Actions[K][1]>,
        options?: DispatchOptions
    ): ReturnType<Actions[K]>
  } & {
    getters: { [K in keyof Getters]: ReturnType<Getters[K]> }
  }
};

export function useStore() {
  return store as Store;
}
