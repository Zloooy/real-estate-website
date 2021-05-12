import {Mutations} from "@/store/mutations";
import {ActionContext, ActionTree} from "vuex";
import {State} from "@/store/state";
import {City, Complex, District, Flat, Metro, Request, UserDto} from "@/generated-api/data-contracts";


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
    GET_COMPLEX({ commit }: AugmentActionContext,
                  payload: number): Promise<Complex>
    GET_COMPLEX_FLATS({commit, state}: AugmentActionContext,
                      payload:number): Promise<Flat[]>,
    GET_FLAT({commit, state}: AugmentActionContext,
                      payload:number): Promise<Flat>,
    GET_AUTH_TOKEN({commit, state}: AugmentActionContext, payload: Request ): Promise<void>,
    GET_USERS({commit, state}: AugmentActionContext, payload: void): Promise<void>,
    GET_USER_ROLES({commit, state}: AugmentActionContext, payload: void): Promise<void>
    SAVE_USER({state, commit} : AugmentActionContext, payload: UserDto) : Promise<void>
    CREATE_USER({state, commit} : AugmentActionContext, payload: UserDto) : Promise<void>
}
export const actions: ActionTree<State, State> & Actions = {
    SAVE_USER({state, commit, dispatch}: AugmentActionContext, payload): Promise<void> {
        return state.api.createUserUsingPost(payload.id || 0, payload)
            .then(()=>dispatch('GET_USERS', undefined));
    },
    CREATE_USER({state, dispatch}: AugmentActionContext, payload): Promise<void> {
        return state.api.createUserUsingPost1(payload)
            .then(()=>dispatch('GET_USERS', undefined))
    },
    GET_USERS({commit, state}: AugmentActionContext, payload: void): Promise<void> {
        return state.api.getAllUsersUsingGet()
            .then((request=>request.data))
            .catch(()=>[])
            .then((data)=>commit('SET_USERS', data));
    },
    GET_USER_ROLES({commit, state}: AugmentActionContext, payload: void): Promise<void> {
        return state.api.getAllUserRolesUsingGet()
            .then((response)=>response.data || [])
            .then(data=>commit('SET_USER_ROLES', data))
    },

    GET_FLAT({commit, state}: AugmentActionContext, payload: number): Promise<Flat> {
        return state.public_api.getFlatByIdUsingGet(payload)
            .then(resp=>resp.data)
            .catch(()=>undefined)
            .then(d=>commit('SET_FLAT', d));
    },
    GET_COMPLEX_FLATS({commit, state}: AugmentActionContext, payload: number): Promise<Flat[]> {
        return state.public_api.getComplexFlatsUsingGet(payload)
        .then(response=>response.data)
            .then((d)=>{
                console.debug("action data", d);
                return d;
        })
        .catch(()=>[])
            .then(d=>commit('SET_COMPLEX_FLATS', d));
    },
    GET_DISTRICTS({commit, state}: AugmentActionContext, payload: void): Promise<District[]> {
        return state.public_api.getDistrictsByCityUsingGet({city_id: state.city?.id || 9})
            .then(response => response.data)
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
            .then(()=>state.complexPage+=1)
            .then(()=>undefined)
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
            }).catch(()=>[] as Complex[])
            .then(d=>commit('SET_COMPLEXES', d));
    },
    GET_COMPLEX({commit, state}: AugmentActionContext, payload: number): Promise<Complex> {
        return state.public_api.findByIdUsingGet(payload, {})
            .then(r=>r.data)
            .then(d=>commit('SET_COMPLEX', d));
    },
    GET_AUTH_TOKEN({commit, state}, payload: Request ): Promise<void> {
       return state.auth.authUsingPost(payload)
           .then(result=>result.data.token || null)
           .catch(()=>null)
           .then(token=>commit('SET_TOKEN', token))
    }
    
}