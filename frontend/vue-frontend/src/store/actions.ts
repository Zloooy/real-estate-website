import {Mutations} from "@/store/mutations";
import {ActionContext, ActionTree} from "vuex";
import {State} from "@/store/state";
import {City, Complex, District, Metro} from "@/generated-api/data-contracts";


type AugmentActionContext = {
    commit<K extends keyof Mutations>(
        key: K,
        payload: Parameters<Mutations[K]>[1]
    ): ReturnType<Mutations[K]>
} & Omit<ActionContext<State, State>, 'commit'>

export interface Actions {
    GET_CITIES({ commit }: AugmentActionContext,
    payload: null): Promise<City[]>,

    GET_ADVERTIZED_COMPLEXES({ commit }: AugmentActionContext,
               payload: void): Promise<void>
    GET_SEARCHED_COMPLEXES({ commit }: AugmentActionContext,
                             payload: void): Promise<void>
    GET_METROS({ commit }: AugmentActionContext,
               payload: void): Promise<Metro[]>
    GET_DISTRICTS({ commit }: AugmentActionContext,
               payload: void): Promise<District[]>
}
export const actions: ActionTree<State, State> & Actions = {
    GET_DISTRICTS({commit, state}: AugmentActionContext, payload: void): Promise<District[]> {
        return state.public_api.getDistrictsByCityUsingGet({city_id: state.city?.id || 9})
            .then(response => response.data)
            .then(p=>{
                console.debug("got district data", JSON.stringify(p));
                return p;
            })
            .catch(()=>[])
            .then(d=>commit('SET_DISTRICTS', d));
    },
    GET_METROS({commit, state}: AugmentActionContext, payload: void): Promise<Metro[]> {
        return state.public_api.getCityMetrosUsingGet({city_id: state.city?.id || 9})
            .then(response => response.data)
            .catch(err=>[])
            .then(d=>commit('SET_METROS', d));
    },
    GET_CITIES(context: AugmentActionContext, payload: null): Promise<City[]> {
        return context.getters.public_api.getAllCitiesUsingGet().then(response => response.data).catch(err => [])
            .then(d=>context.commit('SET_CITIES', d));
    },
    GET_SEARCHED_COMPLEXES({ commit, state }: AugmentActionContext,
                             payload: void): Promise<void> {
        if (state.complexSearchParamsChanged){
            commit('SET_COMPLEXES', []);
            state.complexPage = 0;
            state.complexSize = 1;
            state.complexSearchParamsChanged = false;
        }
        console.debug(`complexPage`, state.complexPage)
        if (state.complexPage >= state.complexSize){
            return new Promise<void>(()=>undefined);
        }
        state.complexesLoading = true;
        return state.public_api.getComplexesUsingPost(
            {
                cityId: state.city?.id || 9,
                estateCategory: state.complex_category,
                metroId: state.metro?.id,
                districtId: state.district?.id,
                amountOfRooms: state.amountOfRooms,
                priceMin: state.minComplexPrice,
                priceMax: state.maxComplexPrice,
                deliveryDate: state.complexDeliveryDate
            }, {sort: state.sort, page: state.complexPage})
            .then(response=>response.data)
            .then(data=>{
                console.debug('got data', data);
                commit('SET_COMPLEX_PAGE', data.number || 0);
                commit('SET_COMPLEX_SIZE', data.totalPages || 0);
                state.complexesLoading = false;
                return data.content;
            })
            .then(d=>commit('SET_COMPLEXES', state.complexes.concat(d || [])))
            .catch((err)=>undefined);
    },
    GET_ADVERTIZED_COMPLEXES({commit, state}: AugmentActionContext, payload: void): Promise<void> {
        return state.public_api.getAdvertizedUsingPost(
            {
                cityId: state.city?.id || 9,
                estateCategory:state.complex_category
            })
            .then(response => {
                return response.data
            }).catch(()=>[])
            .then(d=>commit('SET_COMPLEXES', d));
    }
}